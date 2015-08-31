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
public class Doc_Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
     

    private Button faq;
    private Button veredictos;
    private Button historia;
    private Button quienesomos;
    private TextView doc;
    Bundle token;
 
    public static Doc_Fragment newInstance(int sectionNumber, String tk) {
        Doc_Fragment fragment = new Doc_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        return fragment;
    }
    
    public Doc_Fragment() {
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.doc_menu, container, false);
        
        token = this.getArguments();
        
        doc = (TextView)rootView.findViewById(R.id.doc);
        doc.setText("Documentación");
        
        //Abrir FAQ
        faq = (Button)rootView.findViewById(R.id.b_faq);
        faq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						Doc_List_Fragment.newInstance(0,token.getString("TOKEN"),1)).addToBackStack(null).commit();
			}
		});
        
        //Abrir Veredictos
        veredictos = (Button)rootView.findViewById(R.id.b_veredictos);
        veredictos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						  Doc_List_Fragment.newInstance(0,token.getString("TOKEN"),2)).addToBackStack(null).commit();
			}
		});
        
        //Abrir Historia
        historia = (Button)rootView.findViewById(R.id.b_historia);
        historia.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						Doc_List_Fragment.newInstance(0,token.getString("TOKEN"),3)).addToBackStack(null).commit();
			}
		});
        
        //Abrir Quienés somos
        quienesomos = (Button)rootView.findViewById(R.id.b_quienes_somos);
        quienesomos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						Doc_List_Fragment.newInstance(0,token.getString("TOKEN"),4)).addToBackStack(null).commit();
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
}

