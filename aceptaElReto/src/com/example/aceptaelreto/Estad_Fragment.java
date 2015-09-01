package com.example.aceptaelreto;

import java.util.ArrayList;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import Tools.PieGraph;
import Tools.PieSlice;
import acr.estructuras.ProblemWSType;
import acr.estructuras.SubmissionWSType;
import acr.estructuras.SubmissionsListWSType;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Estad_Fragment extends Fragment{

	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private PieGraph pg1,pg2;
	private LinearLayout leg1,leg11,leg12,leg13;
	private LinearLayout leg2,leg21,leg22,leg23,leg24,leg25,leg26,leg27,leg28,leg29,leg210;
	private TextView numEnv, userTry, userDone;
	private TableLayout clasif, ultEnv;
	private static ProblemWSType problem;
	private Bundle token;
	
	public static Estad_Fragment newInstance(int sectionNumber, String tk, ProblemWSType infoProblem) {
		Estad_Fragment fragment = new Estad_Fragment();
        problem = infoProblem;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        return fragment;
    }
    
    public Estad_Fragment() {
    	 
    }
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.estad_problem, container, false);
		token = getArguments();

		pg1 = (PieGraph)rootView.findViewById(R.id.graph1);
		leg1 = (LinearLayout)rootView.findViewById(R.id.graph1Legend);
		leg11 = (LinearLayout)rootView.findViewById(R.id.graph1Legend1);
		leg12 = (LinearLayout)rootView.findViewById(R.id.graph1Legend2);
		leg13 = (LinearLayout)rootView.findViewById(R.id.graph1Legend3);
		
		pg2 = (PieGraph)rootView.findViewById(R.id.graph2);
		leg2 = (LinearLayout)rootView.findViewById(R.id.graph2Legend);
		leg21 = (LinearLayout)rootView.findViewById(R.id.graph2Legend1);
		leg22 = (LinearLayout)rootView.findViewById(R.id.graph2Legend2);
		leg23 = (LinearLayout)rootView.findViewById(R.id.graph2Legend3);
		leg24 = (LinearLayout)rootView.findViewById(R.id.graph2Legend4);
		leg25 = (LinearLayout)rootView.findViewById(R.id.graph2Legend5);
		leg26 = (LinearLayout)rootView.findViewById(R.id.graph2Legend6);
		leg27 = (LinearLayout)rootView.findViewById(R.id.graph2Legend7);
		leg28 = (LinearLayout)rootView.findViewById(R.id.graph2Legend8);
		leg29 = (LinearLayout)rootView.findViewById(R.id.graph2Legend9);
		leg210 = (LinearLayout)rootView.findViewById(R.id.graph2Legend10);

		numEnv = (TextView)rootView.findViewById(R.id.textRow12);		
		userTry = (TextView)rootView.findViewById(R.id.textRow22);
		userDone = (TextView)rootView.findViewById(R.id.textRow32);
		
		clasif = (TableLayout)rootView.findViewById(R.id.tableC);
		clasif.setStretchAllColumns(true);
		clasif.bringToFront();
		
		ultEnv = (TableLayout)rootView.findViewById(R.id.tableUE);
		ultEnv.setStretchAllColumns(true);
		ultEnv.bringToFront();
	    
		MyAsyncTask task = new MyAsyncTask(getActivity());
		task.execute(problem.num);
		
		setGraphLeng();
		setGraphEnv();
		
		
        return rootView;
    }
	
	 @Override
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
	                ARG_SECTION_NUMBER));
	 }
	 
	 public void setGraphLeng(){
		 
		 if (problem.totalSubs != 0){
			 
			numEnv.setText(" "+Integer.toString(problem.totalSubs)+" ");
			userTry.setText(" "+Integer.toString(problem.totalUsers)+" ");
			userDone.setText(" "+Integer.toString(problem.dacu)+" ");
			
		 	PieSlice slice = new PieSlice();
		
			if (problem.c !=0){
				slice.setColor(Color.parseColor("#99CC00"));
				slice.setValue(problem.c);
				slice.setTitle("C");
				pg1.addSlice(slice);
			}else leg11.setVisibility(View.GONE);
		
			if (problem.cpp !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#FFBB33"));
				slice.setValue(problem.cpp);
				slice.setTitle("Cpp");
				pg1.addSlice(slice);
			}else leg12.setVisibility(View.GONE);
		
			if (problem.java !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#AA66CC"));
				slice.setValue(problem.java);
				slice.setTitle("Java");
				pg1.addSlice(slice);
			}else leg13.setVisibility(View.GONE);
		
			pg1.setPadding(1);
		 
		 }else{
			 leg1.setVisibility(View.GONE);
			 numEnv.setText("0");
			 userTry.setText("0");
			 userDone.setText("0");
		 }
	 }
	 
public void setGraphEnv(){
		 
		 if (problem.totalSubs != 0){
		 	PieSlice slice = new PieSlice();
		
			if (problem.ac !=0){
				slice.setColor(Color.parseColor("#99CC00"));
				slice.setValue(problem.ac);
				slice.setTitle("Accepted");
				pg2.addSlice(slice);
			}else leg21.setVisibility(View.GONE);
		
			if (problem.pe !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#FFBB33"));
				slice.setValue(problem.pe);
				slice.setTitle("Presentation error");
				pg2.addSlice(slice);
			}else leg22.setVisibility(View.GONE);
		
			if (problem.wa !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#AA66CC"));
				slice.setValue(problem.wa);
				slice.setTitle("Wrong Answer");
				pg2.addSlice(slice);
			}else leg23.setVisibility(View.GONE);
		
			if (problem.tl !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#109618"));
				slice.setValue(problem.tl);
				slice.setTitle("Time limit exceeded");
				pg2.addSlice(slice);
			}else leg24.setVisibility(View.GONE);
			
			if (problem.ml !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#7fc7af"));
				slice.setValue(problem.ml);
				slice.setTitle("Memory limit exceeded");
				pg2.addSlice(slice);
			}else leg25.setVisibility(View.GONE);
			
			if (problem.ol !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#29adcd"));
				slice.setValue(problem.ol);
				slice.setTitle("Output limit exceeded");
				pg2.addSlice(slice);
			}else leg26.setVisibility(View.GONE);
			
			if (problem.rf !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#FF6666"));
				slice.setValue(problem.rf);
				slice.setTitle("Restricted function");
				pg2.addSlice(slice);
			}else leg27.setVisibility(View.GONE);
			
			if (problem.rte !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#FF66FF"));
				slice.setValue(problem.rte);
				slice.setTitle("Run-time error");
				pg2.addSlice(slice);
			}else leg28.setVisibility(View.GONE);
			
			if (problem.ce !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#00FFCC"));
				slice.setValue(problem.ce);
				slice.setTitle("Compilation error");
				pg2.addSlice(slice);
			}else leg29.setVisibility(View.GONE);
			
			if (problem.ir !=0){
				slice = new PieSlice();
				slice.setColor(Color.parseColor("#009933"));
				slice.setValue(problem.ir);
				slice.setTitle("Internal error");
				pg2.addSlice(slice);
			}else leg210.setVisibility(View.GONE);
			
			pg2.setPadding(1);
		 
		 }else leg2.setVisibility(View.GONE);
	 }

private class MyAsyncTask extends AsyncTask<Integer, Void, ArrayList<SubmissionWSType>>{
	
	private Context mContext = null;
    private CallerWS ws;
    private WSquery path;
    
    private SubmissionsListWSType subs = null;
    private ArrayList<SubmissionWSType> sublist;
    private ArrayList<Integer> envs = new ArrayList<Integer>();
    private ArrayList<String> users = new ArrayList<String>();
    private ArrayList<String> lengs = new ArrayList<String>();
    private ArrayList<Float> temps = new ArrayList<Float>();
    private ArrayList<Integer> mems = new ArrayList<Integer>();
    
    private SubmissionsListWSType subs2 = null;
    private ArrayList<SubmissionWSType> sublist2;
    private ArrayList<String> users2 = new ArrayList<String>();
    private ArrayList<String> res2 = new ArrayList<String>();
    private ArrayList<String> lengs2 = new ArrayList<String>();
    private ArrayList<Float> temps2 = new ArrayList<Float>();
    private ArrayList<Integer> mems2 = new ArrayList<Integer>();
    private ArrayList<Integer> pos2 = new ArrayList<Integer>();
    
	public MyAsyncTask(Context mContext) {

	   this.mContext = mContext;
       this.ws= new CallerWS();
       this.path = this.ws.getPath();
	}
	
	
	@Override
    protected void onPreExecute() {


    }
	
	@Override
	protected ArrayList<SubmissionWSType> doInBackground(Integer... params) {
		
		//Tabla Clasificación
		
		path.addType(type.problem);
		path.addID(params[0]);
		path.addType(type.ranking);
		this.ws.setPath(path);
		String respuesta = ws.getCall(token.getString("TOKEN"));
		Traductor tradu = new Traductor(respuesta);
		try{
			subs = tradu.getSubmissionList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sublist = (ArrayList<SubmissionWSType>) subs.submissionlist;
		if (sublist != null){
			SubmissionWSType aux = null;
			for (int i=0;i<sublist.size();i++){
				aux = sublist.get(i);
    			envs.add(i, aux.num);
				users.add(i, aux.user.nick);
				lengs.add(i, aux.language.name());
				temps.add(i, aux.executionTime);
				mems.add(i, aux.memoryUsed);
			}
		}
		
		//Tabla Últimos Envíos	
		path.cleanQuery();
		path.addType(type.problem);
		path.addID(params[0]);
		path.addType(type.submissions);
		this.ws.setPath(path);
		respuesta = ws.getCall(token.getString("TOKEN"));
		tradu = new Traductor(respuesta);
		try{
			subs2 = tradu.getSubmissionList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sublist2 = (ArrayList<SubmissionWSType>) subs2.submissionlist;
		if (sublist2 != null){
			SubmissionWSType aux = null;
			for (int i=0;i<sublist2.size();i++){
				aux = sublist2.get(i);
    			users2.add(aux.user.nick);
    			res2.add(aux.result.name());
    			lengs2.add(aux.language.name());
    			temps2.add(aux.executionTime);
    			mems2.add(aux.memoryUsed);
    			pos2.add(aux.ranking);

			}
		}

		return sublist;
	}
	
	@Override
    protected void onPostExecute(ArrayList<SubmissionWSType> list) { 

		Resources resource = mContext.getResources();
		
		//Tabla Clasificación
		int max = 6;
		if (list!=null){
		
			if (max > list.size()) max = list.size()+1;
			
			for(int i = 0; i < max; i++){
				TableRow tr =  new TableRow(mContext);
			    TextView c1  = new TextView(mContext);
			    TextView c2  = new TextView(mContext);
			    TextView c3  = new TextView(mContext);
			    TextView c4  = new TextView(mContext);
			    TextView c5 = new TextView(mContext);
			    TextView c6 = new TextView(mContext);
			    
				if (i==0){
					c1.setText("Pos"); 
					c1.setTextColor(resource.getColor(R.color.white));
				    c2.setText("Envío");
				    c2.setTextColor(resource.getColor(R.color.white));
				    c3.setText("Usuario");
				    c3.setTextColor(resource.getColor(R.color.white));
				    c4.setText("Leng");
				    c4.setTextColor(resource.getColor(R.color.white));
				    c5.setText("Tiempo");
				    c5.setTextColor(resource.getColor(R.color.white));
				    c6.setText("Mem");
				    c6.setTextColor(resource.getColor(R.color.white));
				    tr.setBackgroundColor(resource.getColor(R.color.background));			    
				    
				}else{
					c1.setText(Integer.toString(i));  
				    c2.setText(String.valueOf(envs.get(i-1)));
				    c3.setText(users.get(i-1));
				    if (lengs.get(i-1).equals("CPP")) c4.setText("C++");
				    else c4.setText(lengs2.get(i-1));
				    c5.setText(String.valueOf(temps.get(i-1)));
				    c6.setText(String.valueOf(mems.get(i-1)));
				}
			   	
			    c1.setGravity(Gravity.CENTER);
			    c2.setGravity(Gravity.CENTER);
			    c3.setGravity(Gravity.CENTER);
			    c4.setGravity(Gravity.CENTER);
			    c5.setGravity(Gravity.CENTER);
			    c6.setGravity(Gravity.CENTER);
			    tr.addView(c1);
			    tr.addView(c2);
			    tr.addView(c3);
			    tr.addView(c4);
			    tr.addView(c5);
			    tr.addView(c6);
			    tr.setPadding(1, 1, 1, 1);
			    clasif.addView(tr);
			}
		}
			
		
		//Tabla Últimos Envíos
		if (sublist2!=null){
			max = 6;
			if (max > sublist2.size()) max = sublist2.size()+1;
			
			for(int i = 0; i < max; i++){
				TableRow tr =  new TableRow(mContext);
			    TextView c1  = new TextView(mContext);
			    TextView c2  = new TextView(mContext);
			    TextView c3  = new TextView(mContext);
			    TextView c4  = new TextView(mContext);
			    TextView c5 = new TextView(mContext);
			    TextView c6 = new TextView(mContext);
			    
				if (i==0){
					c1.setText("Usuario"); 
					c1.setTextColor(resource.getColor(R.color.white));
				    c2.setText("Res");
				    c2.setTextColor(resource.getColor(R.color.white));
				    c3.setText("Leng");
				    c3.setTextColor(resource.getColor(R.color.white));
				    c4.setText("Tiempo");
				    c4.setTextColor(resource.getColor(R.color.white));
				    c5.setText("Mem");
				    c5.setTextColor(resource.getColor(R.color.white));
				    c6.setText("Pos");
				    c6.setTextColor(resource.getColor(R.color.white));
				    tr.setBackgroundColor(resource.getColor(R.color.background));			    
				    
				}else{
					c1.setText(users2.get(i-1));  
				    c2.setText(res2.get(i-1));
				    if (lengs2.get(i-1).equals("CPP")) c3.setText("C++");
				    else c3.setText(lengs2.get(i-1));
				    if (temps2.get(i-1) == null) c4.setText("-");
				    else c4.setText(String.valueOf(temps2.get(i-1)));
				    if (mems2.get(i-1) == null) c5.setText("-");
				    else c5.setText(String.valueOf(mems2.get(i-1)));
				    if (pos2.get(i-1) == null) c6.setText("-");
				    else c6.setText(String.valueOf(pos2.get(i-1)));
				}
			   	
			    c1.setGravity(Gravity.CENTER);
			    c2.setGravity(Gravity.CENTER);
			    c3.setGravity(Gravity.CENTER);
			    c4.setGravity(Gravity.CENTER);
			    c5.setGravity(Gravity.CENTER);
			    c6.setGravity(Gravity.CENTER);
			    tr.addView(c1);
			    tr.addView(c2);
			    tr.addView(c3);
			    tr.addView(c4);
			    tr.addView(c5);
			    tr.addView(c6);
			    tr.setPadding(1, 1, 1, 1);
			    ultEnv.addView(tr);
			}
		}		
    }
	
}

}

