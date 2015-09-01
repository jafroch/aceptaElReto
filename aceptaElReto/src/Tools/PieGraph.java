package Tools;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

import com.example.aceptaelreto.R;
import com.example.aceptaelreto.R.styleable;

public class PieGraph extends View {

    private int mPadding;
    private int mInnerCircleRatio;
    private ArrayList<PieSlice> mSlices = new ArrayList<PieSlice>();
    private Paint mPaint = new Paint();
    private int mSelectedIndex = -1;

    private boolean mDrawCompleted = false;
    private RectF mRectF = new RectF();
    private Bitmap mBackgroundImage = null;
    private Point mBackgroundImageAnchor = new Point(0,0);
    private boolean mBackgroundImageCenter = false;



    private int mDuration = 300;//in ms
    private Interpolator mInterpolator;
    private Animator.AnimatorListener mAnimationListener;
    private ValueAnimator mValueAnimator;

    public PieGraph(Context context) {
        this(context, null);
    }

    public PieGraph(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieGraph(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PieGraph, 0, 0);
        mInnerCircleRatio = a.getInt(R.styleable.PieGraph_pieInnerCircleRatio, 0);
        mPadding = a.getDimensionPixelSize(R.styleable.PieGraph_pieSlicePadding, 0);
    }

    public void onDraw(Canvas canvas) {
        float midX, midY, radius, innerRadius;

        canvas.drawColor(Color.TRANSPARENT);
        mPaint.reset();
        mPaint.setAntiAlias(true);

        if(mBackgroundImage != null) {
            if(mBackgroundImageCenter)
                mBackgroundImageAnchor.set(
                        getWidth() / 2 - mBackgroundImage.getWidth() / 2,
                        getHeight() / 2 - mBackgroundImage.getHeight() / 2
                );
            canvas.drawBitmap(mBackgroundImage, mBackgroundImageAnchor.x, mBackgroundImageAnchor.y, mPaint);
        }

        float currentAngle = 270;
        float currentSweep = 0;
        float totalValue = 0;
        

        midX = getWidth() / 2;
        midY = getHeight() / 2;
        if (midX < midY) {
            radius = midX;
        } else {
            radius = midY;
        }

        radius -= mPadding;
        innerRadius = radius * mInnerCircleRatio / 255;

        for (PieSlice slice : mSlices) {
            totalValue += slice.getValue();
        }

        int count = 0;
        for (PieSlice slice : mSlices) {
            Path p = slice.getPath();
            p.reset();

            if (mSelectedIndex == count) {
                mPaint.setColor(slice.getSelectedColor());
            } else {
                mPaint.setColor(slice.getColor());
            }
            currentSweep = (slice.getValue() / totalValue) * (360);

            mRectF.set(midX - radius, midY - radius, midX + radius, midY + radius);
            createArc(p, mRectF, currentSweep,currentAngle + mPadding, currentSweep - mPadding);
            
            mRectF.set(midX - innerRadius, midY - innerRadius,midX + innerRadius, midY + innerRadius);
            createArc(p, mRectF, currentSweep,(currentAngle + mPadding) + (currentSweep - mPadding),-(currentSweep - mPadding));
                        
            p.close();

            // Create selection region
            Region r = slice.getRegion();
            r.set((int) (midX - radius),
                    (int) (midY - radius),
                    (int) (midX + radius),
                    (int) (midY + radius));
            canvas.drawPath(p, mPaint);
            
            float offset = currentSweep / 2;
            
            float x = (float) ((radius-100)*Math.cos(Math.toRadians(currentAngle+offset)) + midX);
            float y = (float) ((radius-100)*Math.sin(Math.toRadians(currentAngle+offset)) + midY);
            
            float xp = (float) ((radius-170)*Math.cos(Math.toRadians(currentAngle+offset)) + midX);
            float yp = (float) ((radius-170)*Math.sin(Math.toRadians(currentAngle+offset)) + midY);
            
            float percentage = (slice.getValue() / totalValue)*100;
            
            mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            mPaint.setColor(Color.BLACK); 
            mPaint.setTextSize(30);
            canvas.drawText(Integer.toString((int)slice.getValue()),x,y,mPaint);
            canvas.drawText(String.format("%.2f", percentage)+"%",xp,yp,mPaint);
            
            currentAngle = currentAngle + currentSweep;

            count++;
        }
        mDrawCompleted = true;
    }

    private void createArc(Path p, RectF mRectF, float currentSweep, float startAngle, float sweepAngle) {
        if (currentSweep == 360) {
            p.addArc(mRectF, startAngle, sweepAngle);
        } else {
            p.arcTo(mRectF, startAngle, sweepAngle);
        }
    }

    public Bitmap getBackgroundBitmap() {
        return mBackgroundImage;
    }

    public void setBackgroundBitmap(Bitmap backgroundBitmap, int pos_x, int pos_y) {
        mBackgroundImage = backgroundBitmap;
        mBackgroundImageAnchor.set(pos_x, pos_y);
        postInvalidate();
    }

    public void setBackgroundBitmap(Bitmap backgroundBitmap) {
        mBackgroundImageCenter = true;
        mBackgroundImage = backgroundBitmap;
        postInvalidate();
    }

    /**
     * sets padding
     * @param padding
     */
    public void setPadding(int padding) {
        mPadding = padding;
        postInvalidate();
    }

    public void setInnerCircleRatio(int innerCircleRatio) {
        mInnerCircleRatio = innerCircleRatio;
        postInvalidate();
    }

    public ArrayList<PieSlice> getSlices() {
        return mSlices;
    }

    public void setSlices(ArrayList<PieSlice> slices) {
        mSlices = slices;
        postInvalidate();
    }

    public PieSlice getSlice(int index) {
        return mSlices.get(index);
    }

    public void addSlice(PieSlice slice) {
        mSlices.add(slice);
        postInvalidate();
    }

    public void removeSlices() {
        mSlices.clear();
        postInvalidate();
    }

   
}
