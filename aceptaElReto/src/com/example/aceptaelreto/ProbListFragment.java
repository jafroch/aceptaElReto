package com.example.aceptaelreto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import acr.estructuras.CategoryWSType;
import acr.estructuras.ListWSType;
import acr.estructuras.ProblemDetailsList;
import acr.estructuras.ProblemListWSType;
import acr.estructuras.ProblemWSType;
import acr.estructuras.UserWSType;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;

 /*
  * clase que genera el fragment de 
  */
public class ProbListFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    
    
    private ArrayAdapter<String> adapter;
    private ArrayList<String> etiq = new ArrayList<String>();
    private ArrayList<Integer> ids = new ArrayList<Integer>();
    private TextView pb;
    private ListView list;
    private CallerWS ws;
    private WSquery path;
    private Bundle token;
    private String aux;
    private int adapOpc;
    private MyAsyncTask task;
    private View header;
    private boolean first = true;
    private boolean isProblem;
    
    public static ProbListFragment newInstance(int sectionNumber, String tk) {
    	ProbListFragment fragment = new ProbListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        return fragment;
    }
    
    public ProbListFragment() {
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.lista_prob, container, false);
        isProblem = false;
        //header = (View)inflater.inflate(R.layout.problem_header,null);
        
        token = this.getArguments();
        adapOpc = 0;
        list = (ListView)rootView.findViewById(R.id.listap);
        pb = (TextView)rootView.findViewById(R.id.pb);
        
    	//list.addHeaderView(header); 
      //  list.addFooterView(rootView);
        
        adapter = new MyArrayAdapter<String>(getActivity(),etiq,ids,adapOpc);
    	
        list.setAdapter(adapter);
    	list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
        		if (first){
        			aux  =(String) (list.getItemAtPosition(position));
        			first = false;
        		}
        		if (isProblem){
        			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
    						ProbMenuFragment.newInstance(ids.get(position),token.getString("TOKEN"))).addToBackStack(null).commit();
        		}
            	adapter.clear();
            	path.cleanQuery();
            	task = new MyAsyncTask(getActivity(),"GETting data...",view,position);
            	task.execute(ids.get(position));                
        	  }
        });
    	
        task = new MyAsyncTask(getActivity(),"GETting data...",rootView,0);
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
	    
		
		public MyAsyncTask(Context mContext, String processMessage, View view, int pos) {

		   this.mContext = mContext;
		   this.processMessage = processMessage;
		   this.mView = view;
		   this.mPosition = pos;
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
	        //hideKeyboard();
	    	showProgressDialog();

	    }
		

		@Override
		protected CategoryWSType doInBackground(Integer... params) {
	    	
	    	click = params[0];
	    	
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
	    	    	
	    	    	for(int i=0;i<listProb.size();i++){
	    	    		etiq.add(listProb.get(i).title);
	    	    		ids.add(i, listProb.get(i).num);
	    	    	}	
	    	    	

	    	    	
	    	    // Explorando Categorías/Volúmenes
	    	    	
	    		}else{
	    			CategoryWSType aux = null;
		    		
		    		for(int i=0;i<arrayCat.size();i++){
		    			aux = arrayCat.get(i);
		    			etiq.add(aux.name);
		    			ids.add(i, aux.id);
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

			adapter.notifyDataSetChanged();
			
			pb.setText("Buscar por: ");
	    	
	    	pDlg.dismiss();
	    }
		
	}


}
