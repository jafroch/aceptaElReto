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
public class Faq_Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
     

    private TextView faq1;
    Bundle token;
 
    public static Faq_Fragment newInstance(int sectionNumber, String tk) {
        Faq_Fragment fragment = new Faq_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        return fragment;
    }
    
    public Faq_Fragment() {
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.faq_layout, container, false);
        
        token = this.getArguments();
        faq1 = (TextView)rootView.findViewById(R.id.faq1);
        faq1.setText("¡Acepta el reto! es un almacén"+" ("+"repositorio"+") "+"de problemas de programación en español, con un juez en línea incorporado. "
        		+ "Cualquier usuario puede resolver los problemas propuestos y enviar su solución al juez para comprobar si es correcta.");
        
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
}


