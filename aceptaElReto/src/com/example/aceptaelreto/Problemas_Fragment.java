package com.example.aceptaelreto;

import java.util.ArrayList;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import acr.estructuras.CategoryWSType;
import acr.estructuras.ProblemListWSType;
import acr.estructuras.ProblemWSType;
import android.app.ActionBar.LayoutParams;
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
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

 /*
  * clase que genera el fragment de 
  */
public class Problemas_Fragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    
    
    private ArrayAdapter<String> adapter;
    private ArrayList<String> etiq = new ArrayList<String>();
    private ArrayList<String> desc = new ArrayList<String>();
    private ArrayList<Integer> numProbs = new ArrayList<Integer>();
    private ArrayList<Integer> numEnvs= new ArrayList<Integer>();
    private ArrayList<Integer> numUsers = new ArrayList<Integer>();
    private ArrayList<Integer> numAcep = new ArrayList<Integer>();
    private ArrayList<Integer> ids = new ArrayList<Integer>();
    private TextView pb;
    private TextView empty;
    private CallerWS ws;
    private WSquery path;
    private Bundle token;
    private String aux;
    private MyAsyncTask task;
    private boolean isProblem;
    private TableLayout table;
    
    
    public static Problemas_Fragment newInstance(int sectionNumber, String tk) {
    	Problemas_Fragment fragment = new Problemas_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        return fragment;
    }
    
    public Problemas_Fragment() {
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.problemas_layout, container, false);
        isProblem = false;
        //header = (View)inflater.inflate(R.layout.problem_header,null);
        
        token = this.getArguments();
        
        table = (TableLayout)rootView.findViewById(R.id.table);
        table.setStretchAllColumns(true);
		table.bringToFront();
        pb = (TextView)rootView.findViewById(R.id.pb);
        empty = (TextView)rootView.findViewById(R.id.empty);
        empty.setVisibility(View.GONE);


    	
        task = new MyAsyncTask(getActivity(),"GETting data...");
		task.execute(0);
		
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
    
    
    public void getListaProblemas(int pos){
		
    	
    	adapter.notifyDataSetChanged();
		
    }

private class MyAsyncTask extends AsyncTask<Integer, Void, CategoryWSType>{
		
		private ProgressDialog pDlg = null;
		private Context mContext = null;
	    private String processMessage = "Processing...";
	    private ArrayList<CategoryWSType> arrayCat = null;
		private CategoryWSType cat = null;
	    private int click;
	    private View mView;
        private int mPosition;
	    
		
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
		protected CategoryWSType doInBackground(Integer... params) {
	    	
	    	click = params[0];
	    	path.cleanQuery();
	    	etiq.clear();
    		ids.clear();
	    	if (click != 0){
	    		
	    		if (aux=="Volúmenes"){
	    			path.addType(type.volume);
	    			if (click>1){
			    		path.addID(click);
		    			path.addNumSubCat(1);
			    	}
	    		}
		    	if (aux=="Categorías"){
		    		path.addType(type.cat);
		    		if (click>45){
			    		path.addID(click);
		    			path.addNumSubCat(1);
			    	}
		    	}
	    		  		
	    		ws.setPath(path);
	    		String respuesta = ws.getCall(token.getString("TOKEN"));
	    		Traductor tradu = new Traductor(respuesta);
	    		ArrayList<CategoryWSType> arrayCat = null;
	    		CategoryWSType cat = null;
	    	
	    		try{
	    			cat = tradu.getCategoria();
	    		} catch (Exception e) {
	    		// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		
	    		if (cat != null){
	    			arrayCat = (ArrayList<CategoryWSType>) cat.subcats_lista;
	    		}
	    		
	    		//Caso lista de problemas
	    		
	    		if (arrayCat == null){
	    			isProblem = true;
	    			path.cleanQuery();
	    			if (aux=="Volúmenes"){
	    				path.addType(type.volume);
	    				path.addID(click);
		    			path.addType(type.problems);
	    			}
	    			else{
	    				path.addType(type.cat);
	    				path.addID(click);
		    			path.addType(type.allproblems);
	    			}
	    	
	    			ws.setPath(path);
	    	    	respuesta = ws.getCall(token.getString("TOKEN"));
	    			tradu = new Traductor(respuesta);
	    	    	ProblemListWSType arrayProb = null;
	    	    	ArrayList<ProblemWSType> listProb = null;
	    	    	try{
	    	    		arrayProb = tradu.getProblemaList();		
	    	    	} catch (Exception e) {
	    	    		// TODO Auto-generated catch block
	    	    		e.printStackTrace();
	    	    	}
	    	    	listProb = (ArrayList<ProblemWSType>) arrayProb.problemlist;
	    	    	
	    	    	if (listProb != null){
	    	    		for(int i=0;i<listProb.size();i++){
		    	    		etiq.add(listProb.get(i).title);
		    	    		numEnvs.add(listProb.get(i).totalSubs);
		    	    		numUsers.add(listProb.get(i).totalUsers);
			    			numAcep.add(listProb.get(i).ac);
		    	    		ids.add(i, listProb.get(i).num);
		    	    	}
	    	    	}else empty.setVisibility(View.VISIBLE);
		    	    	   	
	    	    	
	    	    // Explorando Categorías/Volúmenes
	    	    	
	    		}else{
	    			CategoryWSType aux = null;
		    		
		    		for(int i=0;i<arrayCat.size();i++){
		    			aux = arrayCat.get(i);
		    			etiq.add(aux.name);
		    			desc.add(aux.desc);
		    			numProbs.add(aux.numOfAllProblems);
		    			numEnvs.add(aux.totalSubs);
		    			numAcep.add(aux.ac);
		    			ids.add(aux.id);
		    		}
	    		}	
			
	    	}else{
	    		etiq.add("Volúmenes");
				ids.add(0,1);
				etiq.add("Categorías");
				ids.add(1,45);
	        	
	        }
	     	        	        
			return cat;
		}
		
		@Override
	    protected void onPostExecute(CategoryWSType perfil) { 
			
			getTable(click);
			
			pb.setText("Buscar por: ");
	    	
	    	pDlg.dismiss();
	    }
		
		public void getTable(int id){
			table.removeAllViews();
			Resources resource = mContext.getResources();
			if (id==0){
				for(int i = 0; i < etiq.size(); i++){
					TableRow tr =  new TableRow(getActivity());
					tr.setPadding(1, 1, 1, 1);
					final int idd = i;
					tr.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							MainActivity.probList=true;
							TableRow lTableRow = ((TableRow) v);
							TextView lTextView = (TextView)lTableRow.getChildAt(0);
							aux = lTextView.getText().toString();
							etiq.clear();
							task = new MyAsyncTask(getActivity(),"GETting data...");
		                	task.execute(ids.get(idd));
							
		
						}
					});
	    		    TextView c1  = new TextView(mContext); 
	    		    c1.setTextSize(20);
	    			c1.setTag(Integer.toString(i));
	    		    c1.setText(etiq.get(i));
	    			c1.setGravity(Gravity.CENTER);
	    			tr.addView(c1);
	    			table.addView(tr);
	    		}
				
			}
			else if (id!=0 && !isProblem){
				
				// Header 	
				TableRow tr =  new TableRow(getActivity());
				tr.setPadding(1, 1, 1, 1);
    		    TextView c1  = new TextView(mContext); 
    		    TextView c2  = new TextView(mContext); 
    		    TextView c3  = new TextView(mContext); 
    		    TextView c4  = new TextView(mContext); 
    		    c1.setTextColor(resource.getColor(R.color.white));
    		    c2.setTextColor(resource.getColor(R.color.white));
    		    c3.setTextColor(resource.getColor(R.color.white));
    		    c4.setTextColor(resource.getColor(R.color.white));
    		    if (aux.equals("Volúmenes")) c1.setText("Números");
    		    else c1.setText("Nombre");		    
    		    c2.setText("Descripción");
    		    c3.setText("Nº Problemas");
    		    c4.setText("AC/Envíos");
    			c1.setGravity(Gravity.CENTER);
    			c2.setGravity(Gravity.CENTER);
    			c3.setGravity(Gravity.CENTER);
    			c4.setGravity(Gravity.CENTER);
    			tr.addView(c1);
    			tr.addView(c2);
    			tr.addView(c3);
    			tr.addView(c4);
    			tr.setBackgroundColor(resource.getColor(R.color.background));
    			table.addView(tr);
    			
    			// Tabla 
    			for(int i = 0; i < etiq.size(); i++){
					tr =  new TableRow(getActivity());
					tr.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1.2f));
					tr.setPadding(1, 1, 1, 1);
					final int idd = i;
					tr.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							MainActivity.probList=true;
							etiq.clear();desc.clear();numProbs.clear();numEnvs.clear();numAcep.clear();
							task = new MyAsyncTask(getActivity(),"GETting data...");
		                	task.execute(ids.get(idd));
							
		
						}
					});
					c1  = new TextView(mContext); 
	    		    c2  = new TextView(mContext); 
	    		    c3  = new TextView(mContext); 
	    		    c4  = new TextView(mContext);
	    		    c1.setText(etiq.get(i));
	    		    c2.setText(desc.get(i));
	    		    c3.setText(Integer.toString(numProbs.get(i)));
	    		    c4.setText(Integer.toString(numAcep.get(i))+" / "+Integer.toString(numEnvs.get(i)));
	    			c1.setGravity(Gravity.CENTER); //c1.setMaxWidth(260);
	    			c2.setGravity(Gravity.CENTER); //c2.setMaxWidth(250);
	    			c3.setGravity(Gravity.CENTER); 
	    			c4.setGravity(Gravity.CENTER); //c4.setMaxWidth(180);
	    			c1.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.6f));
	    			c2.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.4f));
	    			c3.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.1f));
	    			c4.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.1f));
	    			tr.addView(c1);
	    			tr.addView(c2);
	    			tr.addView(c3);
	    			tr.addView(c4);
	    			table.addView(tr);
	    		}
    			
			}else if (id!=0 && isProblem){
				
				// Header 		
				TableRow tr =  new TableRow(getActivity());
				tr.setPadding(1, 1, 1, 1);
    		    TextView c1  = new TextView(mContext); 
    		    TextView c2  = new TextView(mContext); 
    		    TextView c3  = new TextView(mContext); 
    		    TextView c4  = new TextView(mContext);
    		    c1.setTextColor(resource.getColor(R.color.white));
    		    c2.setTextColor(resource.getColor(R.color.white));
    		    c3.setTextColor(resource.getColor(R.color.white));
    		    c4.setTextColor(resource.getColor(R.color.white));
    		    c1.setText("Id");
    		    c2.setText("Título");
    		    c3.setText("AC/Envíos");
    		    c4.setText("AC/Usuarios");
    			c1.setGravity(Gravity.CENTER);
    			c2.setGravity(Gravity.CENTER);
    			c3.setGravity(Gravity.CENTER);
    			c4.setGravity(Gravity.CENTER);
    			tr.addView(c1);
    			tr.addView(c2);
    			tr.addView(c3);
    			tr.addView(c4);
    			tr.setBackgroundColor(resource.getColor(R.color.background));
    			table.addView(tr);
    			
    			// Tabla 
    			for(int i = 0; i < etiq.size(); i++){
					tr =  new TableRow(getActivity());
					tr.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1.2f));
					tr.setPadding(1, 1, 1, 1);
					final int idd = i;
					tr.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
	
							MainActivity.numTransaction += 1;
		        			MainActivity.probList=false;
		        			etiq.clear();desc.clear();numProbs.clear();numEnvs.clear();numAcep.clear();numEnvs.clear();
		        			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
		    						ProbMenuFragment.newInstance(ids.get(idd),token.getString("TOKEN"))).addToBackStack(null).commit();
							
		
						}
					});
					c1  = new TextView(mContext); 
	    		    c2  = new TextView(mContext); 
	    		    c3  = new TextView(mContext); 
	    		    c4  = new TextView(mContext);
	    		    c1.setText(Integer.toString(ids.get(i)));
	    		    c2.setText(etiq.get(i));
	    		    c3.setText(Integer.toString(numEnvs.get(i))+" / "+Integer.toString(numAcep.get(i)));
	    		    c4.setText(Integer.toString(numEnvs.get(i))+" / "+Integer.toString(numUsers.get(i)));
	    			c1.setGravity(Gravity.CENTER);
	    			c2.setGravity(Gravity.CENTER); c2.setMaxWidth(300);
	    			c3.setGravity(Gravity.CENTER);
	    			c4.setGravity(Gravity.CENTER);
	    			c1.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.2f));
	    			c2.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.8f));
	    			c3.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.1f));
	    			c4.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.1f));
	    			tr.addView(c1);
	    			tr.addView(c2);
	    			tr.addView(c3);
	    			tr.addView(c4);
	    			table.addView(tr);
	    		}	
			}
		}
		
	}


}
