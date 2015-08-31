package com.example.aceptaelreto;


import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;

//import com.example.aceptaelreto.MainActivity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import Tools.BitmapLRUCache;
import android.widget.AdapterView.OnItemSelectedListener;
import acr.estructuras.CategoryWSType;
import acr.estructuras.CountryWSType;
import acr.estructuras.InstitutionWSType;
import acr.estructuras.ListCountryWSType;
import acr.estructuras.ListInstitutionWSType;
import acr.estructuras.SubmissionWSType;
import acr.estructuras.SubmissionsListWSType;
import acr.estructuras.UserWSType;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


public class PerfilEdit_Fragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private TextView txtNick;
	private TextView txtCorreo;
	private TextView txtNombreCompleto;
	private TextView txtNacimiento;
	private TextView txtGenero;
	private TextView txtPais;
	private TextView txtInstitucion;
	private Button btnEditPhoto;
	private Button btnActualizar;
	private NetworkImageView img;
	private int PICK_IMAGE_REQUEST = 1;
	private Uri uri;
    private Boolean envio;
    private Boolean rdy;
	
	private Spinner spinGenero;
	private Spinner spinPais;
	private Spinner spinInstitucion;
	
	private ArrayList<String> paislist = new ArrayList<String>();
	private ArrayList<String> paiscode = new ArrayList<String>();
	private ArrayList<String> instlist = new ArrayList<String>();

	private Bundle token;
	private static int idUserSearch;
	private String countrycode; 
	
    public static PerfilEdit_Fragment newInstance(int id, String tk) {
    	PerfilEdit_Fragment fragment = new PerfilEdit_Fragment();
        idUserSearch = id;
        Bundle args = new Bundle();
        args.putString("TOKEN", tk);
        args.putInt("IdUserSearch", id);
        fragment.setArguments(args);
        return fragment;
    }
    
    public PerfilEdit_Fragment() {
    	 
    }
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		token = this.getArguments();
		envio = false; 
		rdy = false;
		
		View rootView = inflater.inflate(R.layout.perfil_edit, container, false);
		
		
        txtNombreCompleto = (TextView)rootView.findViewById(R.id.txtNombreCompleto);
        txtNacimiento = (TextView)rootView.findViewById(R.id.txtNacimiento);
		txtGenero = (TextView)rootView.findViewById(R.id.txtGenero);
		txtPais = (TextView)rootView.findViewById(R.id.txtPais);
		txtInstitucion = (TextView)rootView.findViewById(R.id.txtInstitucion);
		txtNick = (TextView)rootView.findViewById(R.id.txtNick);
        txtCorreo = (TextView)rootView.findViewById(R.id.txtCorreo);
        img = (NetworkImageView)rootView.findViewById(R.id.avatar);
        
        //Spinners
        
		spinGenero = (Spinner)rootView.findViewById(R.id.spinGenero);
		spinPais = (Spinner)rootView.findViewById(R.id.spinPais);
		spinInstitucion = (Spinner)rootView.findViewById(R.id.spinInstitucion);
		
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.genero_opc, android.R.layout.simple_spinner_item);     
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinGenero.setAdapter(adapter);	
		
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, paislist);   
	    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinPais.setAdapter(adapter1);
	    
	    //Caso Spinner País -> Actualiza Spinner Institución
	    spinPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	    	
	    	@Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
	    		int code = (int) spinPais.getSelectedItemId();
	    		countrycode = paiscode.get(code);
	    		rdy = true;
	    		MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
				task.execute();
            }

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
								
			}

        });
	    
	    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, instlist);   
	    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinInstitucion.setAdapter(adapter2);

	    //Botones
	    
	    btnEditPhoto = (Button)rootView.findViewById(R.id.btnEditPhoto);
		btnEditPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				// Show only images, no videos or anything else
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				// Always show the chooser (if there are multiple options available)
				startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
			}
		});
	    
	    btnActualizar = (Button)rootView.findViewById(R.id.btnActualizar);
	    btnActualizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				envio = true;
				MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
				task.execute();	
			}
		});
	    
	    //Llamada por default
	    MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
		task.execute();	

		
        return rootView;
    }
	
	 @Override
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
	                ARG_SECTION_NUMBER));
	 }
	
	//Recolecta la información del cambio de foto
	 
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		 
		if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
		    uri = data.getData();
		    MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
	    	task.execute("imageChange");	
		}
	}	 
	
	private class MyAsyncTask extends AsyncTask<String, Void, UserWSType>{
		
		private ProgressDialog pDlg = null;
		private Context mContext = null;
	    private String processMessage = "Processing...";
	    private RequestQueue requestQueueImagen;
	    private ImageLoader imageLoader;
	    private CallerWS ws;
	    private WSquery path;
	    private SimpleDateFormat format;
	    private UserWSType perfil = null;
	    private ArrayList<CountryWSType> countries = null;
	    private ArrayList<InstitutionWSType> institutions = null;
	    
	    private SubmissionsListWSType subs = null;
	    private ArrayList<SubmissionWSType> sublist;
	    
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
	        //hideKeyboard();
	    	showProgressDialog();

	    }
		
		@Override
		protected UserWSType doInBackground(String... params) {
			
			
			// false = mostrar perfil edit / true = enviar cambios			
			if (!envio){
				if (params[0].equals("imageChange")){
					path.cleanQuery();
					path.addType(type.user);
					path.addID(idUserSearch);
					path.addType(type.avatar);
					path.addParam("avatar", uri.getPath());
					ws.setPath(path);
					String respuesta = ws.postCall(token.getString("TOKEN"));
				}
				else{
					path.addType(type.currentuser);
					this.ws.setPath(path);
					String respuesta = ws.getCall(token.getString("TOKEN"));
					Traductor tradu = new Traductor(respuesta);
					try{
						perfil = tradu.getUser();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Relleno Array País 
					path.cleanQuery();
					path.addType(type.country);
					this.ws.setPath(path);
					respuesta = ws.getCall(token.getString("TOKEN"));
					tradu = new Traductor(respuesta);
					try{
						countries = tradu.getPaises();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					for(int i=0;i<countries.size();i++){
						paislist.add(i, countries.get(i).nombre);
						paiscode.add(i, countries.get(i).code);
					}
					
					//Relleno Array Institución
					if(rdy){
						path.cleanQuery();
						path.addType(type.institution);
						path.addType(type.country);
						path.addFree(countrycode);
						this.ws.setPath(path);
						respuesta = ws.getCall(token.getString("TOKEN"));
						tradu = new Traductor(respuesta);
						try{
							institutions = tradu.getInstituciones();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						for(int i=0;i<institutions.size();i++){
							instlist.add(i, institutions.get(i).name);
						}
					}
					requestQueueImagen = Volley.newRequestQueue(getActivity().getApplicationContext());
					imageLoader = new ImageLoader(requestQueueImagen, new BitmapLRUCache());
					format = new SimpleDateFormat("dd/MM/yyyy");
				}
			}
			else{
				
				JSONObject json= new JSONObject();
				path.addParam("name", "JoseLorenzo");
				//path.addType(type.currentuser);
				path.addType(type.user);
				path.addID(42504);
				path.addType(type.profile);
				try {
					json.put("name", "JoseLorenzo");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				path.setJson(json);
				ws.setPath(path);
				ws.putCall(token.getString("TOKEN"));
				
				path.cleanQuery();
				
			}
			
			return perfil;
		}
		
		@Override
	    protected void onPostExecute(UserWSType perfil) { 
	    		
		    txtNacimiento.setText("Fecha de Nacimiento: ");
		    img.setImageUrl(perfil.avatar, imageLoader); 	
		    txtCorreo.setText("Correo: "+perfil.email);
		    txtNick.setText("Nick: "+perfil.nick);	
		    txtNombreCompleto.setText("Nombre: ");
		    txtGenero.setText("Genero: ");
		    txtPais.setText("País: ");
		    txtInstitucion.setText("Institución: ");
		    
	    	pDlg.dismiss();
	    }
		
	}

}
