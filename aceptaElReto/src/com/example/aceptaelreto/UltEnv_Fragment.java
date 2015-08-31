package com.example.aceptaelreto;

import java.util.ArrayList;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import acr.estructuras.ProblemWSType;
import acr.estructuras.SubmissionWSType;
import acr.estructuras.SubmissionsListWSType;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UltEnv_Fragment  extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
     
    private TableLayout tableEnv;
	private Bundle token;
	private static Boolean misEnv;
	private TextView title;
	
    public static UltEnv_Fragment newInstance(int sectionNumber, String tk, Boolean b) {
    	UltEnv_Fragment fragment = new UltEnv_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        misEnv = b;
        return fragment;
    }
    
    public UltEnv_Fragment() {
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	
        View rootView = inflater.inflate(R.layout.mis_env_layout, container, false);
        token = getArguments();
        
        title = (TextView)rootView.findViewById(R.id.title);
        tableEnv = (TableLayout)rootView.findViewById(R.id.tableUE);
		tableEnv.setStretchAllColumns(true);
		tableEnv.bringToFront();
        
		MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
		task.execute();
		
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
    
    
    private class MyAsyncTask extends AsyncTask<Integer, Void, ArrayList<SubmissionWSType>>{
    	
    	private ProgressDialog pDlg = null;
    	private Context mContext = null;
        private String processMessage = "Processing...";
        private CallerWS ws;
        private WSquery path;
        private SubmissionsListWSType subs = null;
        private ArrayList<SubmissionWSType> sublist;
        private ArrayList<Integer> envs = new ArrayList<Integer>();
        private ArrayList<String> users = new ArrayList<String>();
        private ArrayList<String> probs = new ArrayList<String>();
        private ArrayList<String> res = new ArrayList<String>();
        private ArrayList<String> lengs = new ArrayList<String>();
        private ArrayList<Float> temps = new ArrayList<Float>();
        private ArrayList<Integer> mems = new ArrayList<Integer>();
        private ArrayList<Integer> pos = new ArrayList<Integer>();
        
    	
    	public MyAsyncTask(Context mContext, String processMessage) {

    	   this.mContext = mContext;
    	   this.processMessage = processMessage;
           ws= new CallerWS();
           path = ws.getPath();
    	}
    	
    	public void showProgressDialog() {  
            pDlg = new ProgressDialog(mContext);
            pDlg.setMessage(processMessage);
            pDlg.setProgressDrawable(mContext.getWallpaper());
            pDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDlg.setCancelable(false);
            pDlg.show();
        }
    	
    	@Override
        protected void onPreExecute() {
        	showProgressDialog();

        }
    	

    	@Override
    	protected ArrayList<SubmissionWSType> doInBackground(Integer... params) {
        	
        	if(misEnv){
	        	path.addType(type.currentuser);
	    		path.addType(type.submissions);
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
	    				probs.add(i, aux.problem.title);
	    				res.add(i, aux.result.name());
	    				lengs.add(i, aux.language.name());
	    				temps.add(i, aux.executionTime);
	    				mems.add(i, aux.memoryUsed);
	    				pos.add(i, aux.ranking);
	    			}
	    		}
        	}else{
	    		path.addType(type.submission);
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
	    				probs.add(i, aux.problem.title);
	    				res.add(i, aux.result.name());
	    				lengs.add(i, aux.language.name());
	    				temps.add(i, aux.executionTime);
	    				mems.add(i, aux.memoryUsed);
	    				pos.add(i, aux.ranking);
	    			}
	    		}
        	}
        	
    		return sublist;
    	}
    	
    	@Override
        protected void onPostExecute(final ArrayList<SubmissionWSType> problem) { 
    		
    		if (problem != null){
    			Resources resource = mContext.getResources();
    			if(misEnv){

		    		for(int i = 0; i < problem.size()+1; i++){
		    			TableRow tr =  new TableRow(mContext);
		    			final int aux= i;
						tr.setOnClickListener(new OnClickListener(){
	
							@Override
							public void onClick(View v) {
								if (aux>0) getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
										  ProbMenuFragment.newInstance(problem.get(aux).problem.num,token.getString("TOKEN"))).addToBackStack(null).commit();
			
							}
						});
		    		    TextView c1  = new TextView(mContext);
		    		    TextView c2  = new TextView(mContext);
		    		    TextView c3  = new TextView(mContext);
		    		    TextView c4  = new TextView(mContext);
		    		    TextView c5 = new TextView(mContext);
		    		    TextView c6 = new TextView(mContext);
		    		    TextView c7 = new TextView(mContext);
		    		    
		    			if (i==0){
		    				c1.setText("Envío"); 
		    				c1.setTextColor(resource.getColor(R.color.white));
		    			    c2.setText("Problema");
		    			    c2.setTextColor(resource.getColor(R.color.white));
		    			    c3.setText("Res");
		    			    c3.setTextColor(resource.getColor(R.color.white));
		    			    c4.setText("Leng");
		    			    c4.setTextColor(resource.getColor(R.color.white));
		    			    c5.setText("Tiempo");
		    			    c5.setTextColor(resource.getColor(R.color.white));
		    			    c6.setText("Mem");
		    			    c6.setTextColor(resource.getColor(R.color.white));
		    			    c7.setText("Pos");
		    			    c7.setTextColor(resource.getColor(R.color.white));
		    			    tr.setBackgroundColor(resource.getColor(R.color.background));			    
		    			    
		    			}else{
		    				c1.setText(String.valueOf(envs.get(i-1)));  
		    			    c2.setText(probs.get(i-1));
		    			    c3.setText(res.get(i-1));
		    			    if (lengs.get(i-1).equals("CPP")) c4.setText("C++");
		    			    else c4.setText(lengs.get(i-1));
		    			    if (temps.get(i-1) == null) c5.setText("-");
		    			    else c5.setText(String.valueOf(temps.get(i-1)));
		    			    if (mems.get(i-1) == null) c6.setText("-");
		    			    else c6.setText(String.valueOf(mems.get(i-1)));
		    			    if (pos.get(i-1) == null) c7.setText("-");
		    			    else c7.setText(String.valueOf(pos.get(i-1)));
		    			}
		    		   	
		    		    c1.setGravity(Gravity.CENTER);
		    		    c2.setGravity(Gravity.CENTER); 
		    		    c3.setGravity(Gravity.CENTER);
		    		    c4.setGravity(Gravity.CENTER);
		    		    c5.setGravity(Gravity.CENTER);
		    		    c6.setGravity(Gravity.CENTER);
		    		    c7.setGravity(Gravity.CENTER);
		    		    tr.addView(c1);
		    		    tr.addView(c2);
		    		    tr.addView(c3);
		    		    tr.addView(c4);
		    		    tr.addView(c5);
		    		    tr.addView(c6);
		    		    tr.addView(c7);
		    		    tr.setPadding(1, 1, 1, 1);
		    		    tableEnv.addView(tr);
		    		}
		    		
		    		title.setText("Mis envíos:");
		    		
	    		}else{
	    			
	    			for(int i = 0; i < 11; i++){
		    			TableRow tr =  new TableRow(mContext);
		    			final int aux= i;
						tr.setOnClickListener(new OnClickListener(){
	
							@Override
							public void onClick(View v) {
								if (aux>0) getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
										  ProbMenuFragment.newInstance(problem.get(aux).problem.num,token.getString("TOKEN"))).addToBackStack(null).commit();
			
							}
						});
		    		    TextView c1  = new TextView(mContext);
		    		    TextView c2  = new TextView(mContext);
		    		    TextView c3  = new TextView(mContext);
		    		    TextView c4  = new TextView(mContext);
		    		    TextView c5 = new TextView(mContext);
		    		    TextView c6 = new TextView(mContext);
		    		    TextView c7 = new TextView(mContext);
		    		    TextView c8 = new TextView(mContext);
		    		    
		    			if (i==0){
		    				c1.setText("Envío"); 
		    				c1.setTextColor(resource.getColor(R.color.white));
		    				c2.setText("Usuario"); 
		    				c2.setTextColor(resource.getColor(R.color.white));
		    			    c3.setText("Problema");
		    			    c3.setTextColor(resource.getColor(R.color.white));
		    			    c4.setText("Res");
		    			    c4.setTextColor(resource.getColor(R.color.white));
		    			    c5.setText("Leng");
		    			    c5.setTextColor(resource.getColor(R.color.white));
		    			    c6.setText("Tiempo");
		    			    c6.setTextColor(resource.getColor(R.color.white));
		    			    c7.setText("Mem");
		    			    c7.setTextColor(resource.getColor(R.color.white));
		    			    c8.setText("Pos");
		    			    c8.setTextColor(resource.getColor(R.color.white));	    			    
		    			    tr.setBackgroundColor(resource.getColor(R.color.background));			    
		    			    
		    			}else{
		    				c1.setText(String.valueOf(envs.get(i-1))); 
		    				c2.setText(users.get(i-1));
		    			    c3.setText(probs.get(i-1));
		    			    c4.setText(res.get(i-1));
		    			    if (lengs.get(i-1).equals("CPP")) c5.setText("C++");
		    			    else c5.setText(lengs.get(i-1));
		    			    if (temps.get(i-1) == null) c6.setText("-");
		    			    else c6.setText(String.valueOf(temps.get(i-1)));
		    			    if (mems.get(i-1) == null) c7.setText("-");
		    			    else c7.setText(String.valueOf(mems.get(i-1)));
		    			    if (pos.get(i-1) == null) c8.setText("-");
		    			    else c8.setText(String.valueOf(pos.get(i-1)));
		    			}
		    		   	
		    		    c1.setGravity(Gravity.CENTER); c1.setMaxWidth(100);
		    		    c2.setGravity(Gravity.CENTER); c2.setMaxWidth(180);
		    		    c3.setGravity(Gravity.CENTER); c3.setMaxWidth(180);
		    		    c4.setGravity(Gravity.CENTER);
		    		    c5.setGravity(Gravity.CENTER);
		    		    c6.setGravity(Gravity.CENTER);
		    		    c7.setGravity(Gravity.CENTER);
		    		    c8.setGravity(Gravity.CENTER);
		    		    tr.addView(c1);
		    		    tr.addView(c2);
		    		    tr.addView(c3);
		    		    tr.addView(c4);
		    		    tr.addView(c5);
		    		    tr.addView(c6);
		    		    tr.addView(c7);
		    		    tr.addView(c8);
		    		    tr.setPadding(1, 1, 1, 1);
		    		    tableEnv.addView(tr);
		    		}
		    		
		    		title.setText("Últimos envíos recibidos:");
	    			
	    		}
				
			}
        	pDlg.dismiss();
        }
    	
    }

}
