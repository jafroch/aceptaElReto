package com.example.aceptaelreto;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import acr.estructuras.UserWSType;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ProbMenuFragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
    

   private Button enunc;
   private Button enviar;
   private Button estad;
   private Button cred;
   private Button misEnv;
   Bundle token;

   public static ProbMenuFragment newInstance(int sectionNumber, String tk) {
	   ProbMenuFragment fragment = new ProbMenuFragment();
       Bundle args = new Bundle();
       args.putInt(ARG_SECTION_NUMBER, sectionNumber);
       args.putString("TOKEN", tk);
       fragment.setArguments(args);
       return fragment;
   }
   
   public ProbMenuFragment() {

   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
   	
       View rootView = inflater.inflate(R.layout.prob_menu, container, false);
       
       token = this.getArguments();
       
       //Abrir Enunciado
       enunc = (Button)rootView.findViewById(R.id.b_enunciado);
       enunc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						  Faq_Fragment.newInstance(0,token.getString("TOKEN"))).addToBackStack(null).commit();
			}
		});
       
       //Abrir Enviar
       enviar = (Button)rootView.findViewById(R.id.b_enviar);
       enviar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						  Enviar_Fragment.newInstance(200,token.getString("TOKEN"))).addToBackStack(null).commit();
			}
		});
       
       //Abrir Historia
       estad = (Button)rootView.findViewById(R.id.b_estadisticas);
       estad.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						  Faq_Fragment.newInstance(0,token.getString("TOKEN"))).addToBackStack(null).commit();
			}
		});
       
       //Abrir Quienés somos
       cred = (Button)rootView.findViewById(R.id.b_creditos);
       cred.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						  Faq_Fragment.newInstance(0,token.getString("TOKEN"))).addToBackStack(null).commit();
			}
		});

       
       //Abrir Quienés somos
       misEnv = (Button)rootView.findViewById(R.id.b_mis_envios);
       misEnv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						  Faq_Fragment.newInstance(0,token.getString("TOKEN"))).addToBackStack(null).commit();
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
