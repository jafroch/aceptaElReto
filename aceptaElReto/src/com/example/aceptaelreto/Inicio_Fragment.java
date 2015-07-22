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
import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TextView;
 /*
  * clase que genera el fragment de 
  */
public class Inicio_Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
     

    private Spinner sp;
    private Button buscar;
    private EditText txtbuscar;
    private CallerWS ws;
    private WSquery path;
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
        token = this.getArguments();
        this.txtbuscar = (EditText)rootView.findViewById(R.id.info_buscar);
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
				
				// Falta controlar cuando no existe
				
				String info = txtbuscar.getText().toString();
				int infoSearch = Integer.parseInt(info); 
				String text = sp.getSelectedItem().toString();
				if (text.equals("Problema")){
					 getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
							  Enviar_Fragment.newInstance(infoSearch,token.getString("TOKEN"))).addToBackStack(null).commit();
				}
				else{
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
							  Usuario_Fragment.newInstance(infoSearch,token.getString("TOKEN"))).addToBackStack(null).commit();
				}

			}
		});
        this.ws= new CallerWS();
        path = this.ws.getPath();
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
}
