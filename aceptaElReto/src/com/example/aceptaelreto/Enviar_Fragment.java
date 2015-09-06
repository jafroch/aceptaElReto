package com.example.aceptaelreto;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.io.FilenameUtils;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import Tools.SimpleFileDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


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
	private TextView fileName;
	private Spinner splenguaje;
	private EditText comentario;
	private EditText code;
	private Button bChooseFile;
	private Button bEnviar;
	private Button bDelete;
	private static int numProb;
	private String m_chosen;
	private LinearLayout campoCode;
	Bundle token;
	
    public static Enviar_Fragment newInstance(int id, String tk) {
        Enviar_Fragment fragment = new Enviar_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, id);
        args.putString("TOKEN", tk);
        numProb = id;
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
        this.fileName = (TextView)rootView.findViewById(R.id.fileName);
        
        this.comentario = (EditText)rootView.findViewById(R.id.Comentario);
        this.code = (EditText)rootView.findViewById(R.id.Code);
        
        this.splenguaje = (Spinner)rootView.findViewById(R.id.lenguaje);
        
        this.campoCode = (LinearLayout)rootView.findViewById(R.id.campoCode);
        
        this.bChooseFile = (Button)rootView.findViewById(R.id.bChooseFile);
        this.bDelete = (Button)rootView.findViewById(R.id.bDelete);
        this.bEnviar = (Button)rootView.findViewById(R.id.bEnviar);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.lenguaje_opc, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        splenguaje.setAdapter(adapter);

        
        bChooseFile.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) {
				/////////////////////////////////////////////////////////////////////////////////////////////////
				//Create FileOpenDialog and register a callback
				/////////////////////////////////////////////////////////////////////////////////////////////////
				SimpleFileDialog FileOpenDialog =  new SimpleFileDialog(getActivity(), "FileOpen",
						new SimpleFileDialog.SimpleFileDialogListener()
				{
					@Override
					public void onChosenDir(String chosenDir) 
					{
						// The code in this function will be executed when the dialog OK button is pushed 
						m_chosen = chosenDir;
						String ext = FilenameUtils.getExtension(m_chosen);
						String name = FilenameUtils.getName(m_chosen);
						if ( ( ext.equals("c") && splenguaje.getSelectedItem().toString().equals("C") )|| ( ext.equals("cpp") && splenguaje.getSelectedItem().toString().equals("C++") )
								|| (ext.equals("java") && splenguaje.getSelectedItem().toString().equals("Java") )){
							fileName.setVisibility(View.VISIBLE);
					        bDelete.setVisibility(View.VISIBLE);
					        bChooseFile.setVisibility(View.GONE);
					        campoCode.setVisibility(View.GONE);
					        fileName.setText(name);
					        bDelete.setText("Cancel");
						}else if( !ext.equals("c") && !ext.equals("cpp") && !ext.equals("java")){
							Toast.makeText(getActivity(), "Extensión de Archivo no reconocida", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(getActivity(), "El lenguaje escogido no concuerda con el archivo", Toast.LENGTH_SHORT).show();
						}
						
					}
				});
				
				//You can change the default filename using the public variable "Default_File_Name"
				FileOpenDialog.Default_File_Name = "";
				FileOpenDialog.chooseFile_or_Dir();
				
				/////////////////////////////////////////////////////////////////////////////////////////////////

			}
		});
          
        bEnviar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
				task.execute(true);
				
			}
		});
		       
        bDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				bChooseFile.setVisibility(View.VISIBLE);
				fileName.setVisibility(View.GONE);
		        bDelete.setVisibility(View.GONE);
		        campoCode.setVisibility(View.VISIBLE);
			}
		});
        
        
        this.fileName.setVisibility(View.GONE);
        this.bDelete.setVisibility(View.GONE);
        
		this.ws= new CallerWS();
        path = this.ws.getPath();
        MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
		task.execute(false);	
        return rootView;
    }
	
	 @Override
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
	                ARG_SECTION_NUMBER));
	 }
	 
	 
		private class MyAsyncTask extends AsyncTask<Boolean, Void, ProblemWSType>{
			
			private ProgressDialog pDlg = null;
			private Context mContext = null;
		    private String processMessage = "Processing...";
		    private CallerWS ws;
		    private WSquery path;
		    private ProblemWSType prob = null;
		    private Boolean b;
		    private int error = -1;
		    private String codeQuotes ="";
		    
			public MyAsyncTask(Context mContext, String processMessage) {

			   this.mContext = mContext;
			   this.processMessage = processMessage;
		       this.ws= new CallerWS();
		       this.path = this.ws.getPath();
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
			protected ProblemWSType doInBackground(Boolean... params) {
				b = params[0];
				if(!params[0]){
					path.cleanQuery();
					path.addType(type.problem);
					path.addID(numProb);
					this.ws.setPath(path);
			 		String respuesta = ws.getCall(token.getString("TOKEN"));
			 		Traductor tradu = new Traductor(respuesta);
			 		try{
			 		  	prob = tradu.getProblema();
			 	    } catch (Exception e) {
			 		// TODO Auto-generated catch block
			 			e.printStackTrace();
			 		}
				}else{
					if(m_chosen != null || !code.getText().toString().equals("")){
						
						if (m_chosen != null){
							try {
								codeQuotes = new Scanner(new File(m_chosen)).useDelimiter("\\Z").next();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else codeQuotes = code.getText().toString();
						//codeQuotes = codeQuotes.replace("\"", "\\\"");
						JSONObject json= new JSONObject();
						path.cleanQuery();
						path.addType(type.currentuser);
						path.addType(type.submissions);
						path.addType(type.problem);
						path.addID(numProb);				
						try {
							if (splenguaje.getSelectedItem().toString().equals("C++")) json.put("language", "CPP");
							else json.put("language", splenguaje.getSelectedItem().toString().toUpperCase());		
							json.put("code", codeQuotes);
							json.put("comment", comentario.getText().toString());
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						path.setJson(json);
						ws.setPath(path);
						String response = ws.postCall(token.getString("TOKEN"));
						if (response.equals("204") || response.equals("200")){
							error = 0;
						}else error = 2;
						
					}else{
						error = 1;
					}
				}
			 	return prob;
			}
			
			@Override
		    protected void onPostExecute(ProblemWSType prob) { 
		    	
				if(!b){
					txtProblemName.setText("Problema: "+prob.title);
					txtProblemNum.setText("Número: "+prob.num);
					txtLenguaje.setText("Lenguaje: ");
					txtComentario.setText("Comentario: ");
					txtCF.setText("Archivo: ");
					txtCode.setText("Código: ");
				}
				if (error != 0){
					if (error == 1) Toast.makeText(getActivity(), "ERROR: Eliga un archivo o escriba el código", Toast.LENGTH_LONG).show();
					if (error == 2){
						if (m_chosen!=null) Toast.makeText(getActivity(), "ERROR: El archivo no se pudo leer.", Toast.LENGTH_LONG).show();
						else Toast.makeText(getActivity(), "ERROR: El texto escrito no parece código fuente.", Toast.LENGTH_LONG).show();
					}
				}else{
					Toast.makeText(getActivity(), "Solución Enviada!", Toast.LENGTH_SHORT).show();
					MainActivity.numTransaction = 0;
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
							UltEnv_Fragment.newInstance(0,token.getString("TOKEN"),true)).addToBackStack(null).commit();
				}
		    	
		    	pDlg.dismiss();
		    }
		}
	 
}
