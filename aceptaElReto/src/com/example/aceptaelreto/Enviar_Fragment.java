package com.example.aceptaelreto;



import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import acr.estructuras.CategoryWSType;
import acr.estructuras.ProblemWSType;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Enviar_Fragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private CallerWS ws;
    private WSquery path;
    
	private TextView txtProblemName;
	private TextView txtProblemNum;
	private TextView txtLenguaje;
	private TextView txtComentario;
	private TextView txtCF;
	private TextView txtCode;
	private Spinner splenguaje;
	private EditText comentario;
	private EditText code;
	private Button bChooseFile;
	private Button bEnviar;
	private static int numProb;
	Bundle token;
	
    public static Enviar_Fragment newInstance(int sectionNumber, String tk) {
        Enviar_Fragment fragment = new Enviar_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        numProb = sectionNumber;
        fragment.setArguments(args);
        return fragment;
    }
    
    public Enviar_Fragment() {
    	 
    }
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.editor_layaout, container, false);
		token = this.getArguments();
        this.txtProblemName = (TextView)rootView.findViewById(R.id.txtProblemName);
        this.txtProblemNum = (TextView)rootView.findViewById(R.id.txtProblemNum);
        this.txtLenguaje = (TextView)rootView.findViewById(R.id.txtLenguaje);
        this.txtComentario = (TextView)rootView.findViewById(R.id.txtComentario);
        this.txtCF = (TextView)rootView.findViewById(R.id.txtCF);
        this.txtCode = (TextView)rootView.findViewById(R.id.txtCode);
        this.comentario = (EditText)rootView.findViewById(R.id.Comentario);
        this.code = (EditText)rootView.findViewById(R.id.Code);
        this.splenguaje = (Spinner)rootView.findViewById(R.id.lenguaje);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.lenguaje_opc, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        splenguaje.setAdapter(adapter);

        this.bChooseFile = (Button)rootView.findViewById(R.id.bChooseFile);
        this.bEnviar = (Button)rootView.findViewById(R.id.bEnviar);
        
		//btnEditProfile = (Button)rootView.findViewById(R.id.btnEditProfile);
		/*btnEditProfile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), EditInfoActivity.class);
				intent.putExtra("WALKER", walker);
				startActivityForResult(intent, Constants.RESULT_EDIT);
			}
		});
		*/
		
        
		this.ws= new CallerWS();
        path = this.ws.getPath();
        this.setEtiquetas();
        return rootView;
    }
	
	 @Override
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
	                ARG_SECTION_NUMBER));
	 }
	 
	 public void setEtiquetas(){
/*
		path.addType(type.problem);
		path.addID(numProb);
		this.ws.setPath(path);
 		String respuesta = ws.getCall(getActivity(),token.getString("TOKEN"));
 		Traductor tradu = new Traductor(respuesta);
 		ProblemWSType prob = null;
 		try{
 		  	prob = tradu.getProblema();
 	    } catch (Exception e) {
 		// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
		 
		 this.txtProblemName.setText("Problema: "+prob.title);
		 this.txtProblemNum.setText("Número: "+prob.num);
		 this.txtLenguaje.setText("Lenguaje: ");
		 this.txtComentario.setText("Comentario: ");
		 this.txtCF.setText("Archivo: ");
		 this.txtCode.setText("Código: ");*/
	 }
}
