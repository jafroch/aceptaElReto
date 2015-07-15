package com.example.aceptaelreto;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WebServiceTask;

import java.util.ArrayList;
import java.util.List;

import acr.estructuras.CountryWSType;
import acr.estructuras.NewSession;
import acr.estructuras.ResponseList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
 /*
  * clase que genera el fragment de 
  */
public class Layout2Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    
    //----------------------------------------------------------
    private static final String SERVICE_URL = "https://www.aceptaelreto.com/ws/";
    
    private static final String TAG = "AndroidRESTClientActivity";
    private EditText textoSalida;
    private CallerWS ws;
    private WSquery path;
 
    public static Layout2Fragment newInstance(int sectionNumber) {
        Layout2Fragment fragment = new Layout2Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public Layout2Fragment() {
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout2, container,
                false);
        this.textoSalida= (EditText) rootView.findViewById(R.id.editText1);
        this.ws= new CallerWS();
        path = this.ws.getPath();
        this.retrieveSampleData(rootView);
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
    //--------------------------------------------------------------------------
    
     
   
 
    public void retrieveSampleData(View vw) {
    	 	
       this.path.login("apptest", "testtest");
       this.ws.setPath(path);
      
       
      /* String salida = this.ws.postCall(this.getActivity());
       Traductor traductor = new Traductor(salida);
       NewSession sesion = null;
      try {
		 sesion = traductor.getSession();
      } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
       this.textoSalida.setText(salida);
	
       */
        
    }
 
    public void clearControls(View vw) {
 
        //clear del campo de texto
    	
                 
    }
     
    public void postData(View vw) {
    	//POST
    }
 
    public void handleResponse(String response) {
         
       
    }
 
    private void hideKeyboard() {
 
        //ocultar teclado que ahora no nos importa
    }
}