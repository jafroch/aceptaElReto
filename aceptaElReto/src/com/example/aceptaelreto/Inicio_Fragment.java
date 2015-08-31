package com.example.aceptaelreto;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WebServiceTask;
import ws.WSquery.type;

import java.util.ArrayList;
import java.util.List;

import acr.estructuras.CountryWSType;
import acr.estructuras.NewSession;
import acr.estructuras.ProblemWSType;
import acr.estructuras.ResponseList;
import acr.estructuras.UserWSType;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
 /*
  * clase que genera el fragment de 
  */
public class Inicio_Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
     
    private CallerWS ws;
    private WSquery path;
    private boolean p;
    private Spinner sp;
    private Button buscar;
    private EditText txtbuscar;
    private String tk;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private String[] groups;
    private String[][] children;
    Bundle token;
 
    public static Inicio_Fragment newInstance(int sectionNumber, String tk) {
        Inicio_Fragment fragment = new Inicio_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        return fragment;
    }
    
    public Inicio_Fragment() {
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	
        View rootView = inflater.inflate(R.layout.inicio_layout, container, false);
        token = getArguments();
        p = false;
        txtbuscar = (EditText)rootView.findViewById(R.id.info_buscar);
        
        expListView = (ExpandableListView) rootView.findViewById(R.id.list);
        prepareListData();
        listAdapter = new ExpandableListAdapter(getActivity(), groups, children);
        expListView.setAdapter(listAdapter);
        
        
        this.sp = (Spinner)rootView.findViewById(R.id.buscar);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.buscar_opc, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp.setAdapter(adapter);
        buscar = (Button)rootView.findViewById(R.id.bbuscar);
        // Iniciar Fragment con Usuario o Problema
        buscar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String info = txtbuscar.getText().toString();
				int infoSearch = Integer.parseInt(info); 
				String text = sp.getSelectedItem().toString();
			    tk = token.getString("TOKEN");
				
				if (text.equals("Problema")) p = true;
				else p =false;
				
				MyAsyncTask task = new MyAsyncTask(getActivity());
				task.execute(infoSearch);

			}
		});
        
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
    
    
 private void prepareListData() {
    	
    	
    		groups = new String[] { "¿Qué es?", "¿Por dónde empiezo?"};

            children = new String [][] {
                { "¡Acepta el reto! es un almacén y juez en línea de problemas de programación en español que acepta soluciones en C, C++ y Java.\n\n "
                		+ "No es un mero listado de problemas, sino mucho más. ¡Es un corrector automático!\n\n"
                		+"Si quieres poner a prueba tu habilidad programando y compararla con la de otros, ¡éste es tu sitio!" },
                { "Lo primero que querrás hacer será leer algunos de los múltiples problemas disponibles. Si no sabes por cuál empezar, puedes "
                		+ "recorrer las diferentes categorías o mirar el problema de la semana que te proponemos abajo.\n\n Si te llama la atención algún "
                		+ "problema, crees que eres capaz de resolverlo y quieres intentarlo, regístrate. ¡Es fácil, rápido y no te enviaremos spam! "
                		+ "Con tu cuenta, podrás enviar tus soluciones y compararlas con las de otros usuarios." },
                };
    }
    
    private class MyAsyncTask extends AsyncTask<Integer, Void, Boolean>{
    	
    	private Context mContext = null;
    	private ProblemWSType prob = null;
    	private UserWSType perfil = null;
        private int id;
        
    	
    	public MyAsyncTask(Context mContext) {
    	   this.mContext = mContext;
           ws= new CallerWS();
           path = ws.getPath();
    	}
    	
    	
    	@Override
        protected void onPreExecute() {

        }
    	

    	@Override
    	protected Boolean doInBackground(Integer... params) {
        	
        	id = params[0];
        	Boolean b = false;
        	if (p){
        		path.addType(type.problem);	    
			    path.addID(id);  
			    ws.setPath(path);

				String respuesta = ws.getCall(tk);
		    	Traductor tradu = new Traductor(respuesta);

		    	try{
		    		prob = tradu.getProblema();
		    	} catch (Exception e) {
		    	// TODO Auto-generated catch block
		    		e.printStackTrace();
		    	}
		    	
		    	if (prob!=null) b=true;
		    	
        	}else{
				path.addType(type.user);
				path.addID(id);
				ws.setPath(path);
				String respuesta = ws.getCall(tk);
		    	Traductor tradu = new Traductor(respuesta);   	
		    	
		    	try{
		    		perfil = tradu.getUser();
		    	} catch (Exception e) {
		    	// TODO Auto-generated catch block
		    		e.printStackTrace();
		    	}
		    	
		    	if (perfil!=null) b=true;
		    	
        	}
    	       		    		  
    		return b;
    	}
    	
    	@Override
        protected void onPostExecute(Boolean b) { 

	    	if(!b && p) Toast.makeText(getActivity(),"No existe problema con id: "+id, Toast.LENGTH_LONG).show();
	    	if(b && p){
	    		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						  ProbMenuFragment.newInstance(prob,tk)).addToBackStack(null).commit();
	    	}
	    	
	    	if(!b && !p) Toast.makeText(getActivity(),"No existe usuario con id: "+id, Toast.LENGTH_LONG).show();
	    	if(b && !p){
	    		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						  Perfil_Fragment.newInstance(perfil,tk)).addToBackStack(null).commit();
	    	}
        }
    	
    }

}
