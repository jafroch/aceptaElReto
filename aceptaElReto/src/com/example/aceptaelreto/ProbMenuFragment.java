package com.example.aceptaelreto;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import acr.estructuras.ProblemWSType;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ProbMenuFragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
    
   private TextView numprob;
   private TextView nomprob;
   private Button enunc;
   private Button enviar;
   private Button estad;
   private Button cred;
   private Button misEnv;
   private static int idSearch;
   private CallerWS ws;
   private WSquery path;
   private MyAsyncTask task;
   private static ProblemWSType infoProblem;
   private String urlImage;
   Bundle token;
   
   public static ProbMenuFragment newInstance(int id, String tk) {
	   ProbMenuFragment fragment = new ProbMenuFragment();
       Bundle args = new Bundle();
       args.putString("TOKEN", tk);
       fragment.setArguments(args);
       idSearch = id;
       infoProblem = null;
       return fragment;
   }
   
   public static ProbMenuFragment newInstance(ProblemWSType infoP, String tk) {
	   ProbMenuFragment fragment = new ProbMenuFragment();
       Bundle args = new Bundle();
       args.putString("TOKEN", tk);
       fragment.setArguments(args);
       infoProblem = infoP;
       idSearch = infoProblem.num;
       return fragment;
   }
   
   public ProbMenuFragment() {

   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
   	
       View rootView = inflater.inflate(R.layout.prob_menu, container, false);
       
       token = this.getArguments();
       
       numprob = (TextView)rootView.findViewById(R.id.num_prob);
       nomprob = (TextView)rootView.findViewById(R.id.nom_prob);
       //Abrir Enunciado
       enunc = (Button)rootView.findViewById(R.id.b_enunciado);
       enunc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.numTransaction += 1;
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						Enunc_Fragment.newInstance(idSearch,token.getString("TOKEN"),urlImage)).addToBackStack(null).commit();
			}
		});
       
       //Abrir Enviar
       enviar = (Button)rootView.findViewById(R.id.b_enviar);
       enviar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.numTransaction += 1;
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						  Enviar_Fragment.newInstance(idSearch,token.getString("TOKEN"))).addToBackStack(null).commit();
			}
		});
       
       //Abrir Estadísticas
       estad = (Button)rootView.findViewById(R.id.b_estadisticas);
       estad.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.numTransaction += 1;
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						 Estad_Fragment.newInstance(0,token.getString("TOKEN"),infoProblem)).addToBackStack(null).commit();
			}
		});
       

  
       
       task = new MyAsyncTask(getActivity(),"GETting data...");
	   task.execute(idSearch);
       
       return rootView;
   }
   @Override
   public void onAttach(Activity activity) {
       super.onAttach(activity);
       ((MainActivity) activity).onSectionAttached(getArguments().getInt(
               ARG_SECTION_NUMBER));
   }

private class MyAsyncTask extends AsyncTask<Integer, Void, ProblemWSType>{
	
	private ProgressDialog pDlg = null;
	private Context mContext = null;
    private String processMessage = "Processing...";
	private ProblemWSType prob = null;
	private ProblemWSType aux = null;
    private int id;
    
	
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
        //hideKeyboard();
    	showProgressDialog();

    }
	

	@Override
	protected ProblemWSType doInBackground(Integer... params) {
    	
    	id = params[0];
    	String respuesta;
    	Traductor tradu;
    
    //ProblemWSType
    	
    	if(infoProblem == null){
		    path.addType(type.problem);	    
		    path.addID(id);    		
	    	ws.setPath(path);
	    	respuesta = ws.getCall(token.getString("TOKEN"));
	    	tradu = new Traductor(respuesta);
	    	
	    	try{
	    		prob = tradu.getProblema();
	    	} catch (Exception e) {
	    	// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}		
	    	
    	}else prob=infoProblem;
    	
    //URL de la imagen
    	
    	path.cleanQuery();
    	path.addType(type.problem);	    
	    path.addID(id);
    	path.addType(type.paths);
    	ws.setPath(path);
    	respuesta = ws.getCall(token.getString("TOKEN"));
    	tradu = new Traductor(respuesta);  	
    	try{
    	    aux = tradu.getProblema();		
    	} catch (Exception e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	}
    	urlImage = aux.previewLink;   	
    	
    //Créditos
    	
    	
    	    		  
		return prob;
	}
	
	@Override
    protected void onPostExecute(ProblemWSType problem) { 

		numprob.setText("("+String.valueOf(problem.num)+") ");
		nomprob.setText(problem.title);
		
    	infoProblem = problem;
    	
    	pDlg.dismiss();
    }
	
}


}

