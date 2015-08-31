package com.example.aceptaelreto;


import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import acr.estructuras.CategoryWSType;
import acr.estructuras.SubmissionWSType;
import acr.estructuras.SubmissionsListWSType;
import acr.estructuras.UserWSType;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Perfil_Fragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private static UserWSType perfil = null;
	private TextView txtNick;
	private TextView txtCorreo;
	private TextView txtNombreCompleto;
	private TextView txtNacimiento;
	private TextView txtGenero;
	private TextView txtPais;
	private TextView txtInstitucion;
	private TextView noEnvios;
	private Button btnEditProfile;
	private NetworkImageView img;
	private TableLayout tableEnv;
	private Bundle token;
	private static int idUserSearch;
	private int myId;

	
	
	private ArrayList<String> nameProb = new ArrayList<String>();
	private ArrayList<Integer> numProb = new ArrayList<Integer>();

	
    public static Perfil_Fragment newInstance(int sectionNumber, String tk, int id) {
        Perfil_Fragment fragment = new Perfil_Fragment();
        idUserSearch = id;
        perfil = null;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        return fragment;
    }
    
    public static Perfil_Fragment newInstance(UserWSType user, String tk) {
        Perfil_Fragment fragment = new Perfil_Fragment();
        perfil = user;
        idUserSearch = perfil.id;
        Bundle args = new Bundle();
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        return fragment;
    }
    
    public Perfil_Fragment() {
    	 
    }
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		token = this.getArguments();
		
		View rootView = inflater.inflate(R.layout.perfil_info, container, false);
		
		
        txtNick = (TextView)rootView.findViewById(R.id.txtNick);
        txtCorreo = (TextView)rootView.findViewById(R.id.txtCorreo);
        txtNombreCompleto = (TextView)rootView.findViewById(R.id.txtNombreCompleto);
        txtNacimiento = (TextView)rootView.findViewById(R.id.txtNacimiento);
		txtGenero = (TextView)rootView.findViewById(R.id.txtGenero);
		txtPais = (TextView)rootView.findViewById(R.id.txtPais);
		txtInstitucion = (TextView)rootView.findViewById(R.id.txtInstitucion);
		noEnvios = (TextView)rootView.findViewById(R.id.noEnvios);
		img = (NetworkImageView)rootView.findViewById(R.id.avatar);
		tableEnv = (TableLayout)rootView.findViewById(R.id.tableE);
		tableEnv.setStretchAllColumns(true);
		tableEnv.bringToFront();
		
		
		btnEditProfile = (Button)rootView.findViewById(R.id.btnEditProfile);
		btnEditProfile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
						PerfilEdit_Fragment.newInstance(idUserSearch,token.getString("TOKEN"))).addToBackStack(null).commit();
			}
		});

	    MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
		task.execute("");	

		
        return rootView;
    }
	
	
	 @Override
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
	                ARG_SECTION_NUMBER));
	 }
	
	public static Drawable LoadImageFromWebOperations(String url) {
	    try {
	        InputStream is = (InputStream) new URL(url).getContent();
	        Drawable d = Drawable.createFromStream(is, "src name");
	        return d;
	    } catch (Exception e) {
	        return null;
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
					
				String respuesta;
				Traductor tradu;
				
				if(perfil==null){
					path.cleanQuery();
					if (idUserSearch != 0){
						path.addType(type.user);
						path.addID(idUserSearch);
					}
					else{
						path.addType(type.currentuser);
					}
				    this.ws.setPath(path);
					respuesta = ws.getCall(token.getString("TOKEN"));
					tradu = new Traductor(respuesta);
					try{
						perfil = tradu.getUser();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//submission WS
				path.cleanQuery();
				path.addType(type.user);
				path.addID(perfil.id);
				path.addType(type.submissions);
				this.ws.setPath(path);
				respuesta = ws.getCall(token.getString("TOKEN"));
				tradu = new Traductor(respuesta);
				try{
					subs = tradu.getSubmissionList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sublist = (ArrayList<SubmissionWSType>) subs.submissionlist;
				if (sublist != null){
					SubmissionWSType aux = null;
					for (int i=0;i<sublist.size();i++){
						aux = sublist.get(i);
						nameProb.add(i, aux.problem.title);
						numProb.add(i, aux.problem.num);
						
					}
				}
				
				requestQueueImagen = Volley.newRequestQueue(getActivity().getApplicationContext());
				imageLoader = new ImageLoader(requestQueueImagen, new BitmapLRUCache());
				format = new SimpleDateFormat("dd/MM/yyyy");
			
			
			return perfil;
		}
		
		@Override
	    protected void onPostExecute(UserWSType perfil) { 
	    	
			if(perfil !=null){
				if (perfil.email == null){
			    	txtNacimiento.setVisibility(View.GONE);
			    	txtCorreo.setVisibility(View.GONE);
			    	txtGenero.setVisibility(View.GONE);
			    	btnEditProfile.setVisibility(View.GONE);
			    	img.setImageUrl(perfil.avatar, imageLoader);
			    	txtNick.setText("Nick: "+perfil.nick);	
			    	txtNombreCompleto.setText("Nombre: "+perfil.name);
			    	txtPais.setText("Pa�s: "+perfil.country.name);
			    	txtInstitucion.setText("Instituci�n: "+perfil.institution.name);
			    }else{
			    	Date date = perfil.birthday;
			    	String nac = format.format(date);		
			    	txtNacimiento.setText("Fecha de Nacimiento: "+nac);
			    	img.setImageUrl(perfil.avatar, imageLoader); 	
			    	txtCorreo.setText("Correo: "+perfil.email);
			    	txtNick.setText("Nick: "+perfil.nick);	
			    	txtNombreCompleto.setText("Nombre: "+perfil.name);
			    	txtGenero.setText("Genero: "+perfil.gender);
			    	txtPais.setText("Pa�s: "+perfil.country.name);
			    	txtInstitucion.setText("Instituci�n: "+perfil.institution.name);
			    	myId = perfil.id;
			    }
				
				if(sublist != null){
					noEnvios.setVisibility(View.GONE);
					Resources resource = mContext.getResources();
					//Tabla �ltimos Env�os
					
					for(int i = 0; i < sublist.size(); i++){
						TableRow tr =  new TableRow(mContext);
						final int aux= i;
						tr.setOnClickListener(new OnClickListener(){

							@Override
							public void onClick(View v) {
								if (aux>0) getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
										  ProbMenuFragment.newInstance(numProb.get(aux-1),token.getString("TOKEN"))).addToBackStack(null).commit();
			
							}
						});
					    TextView c1  = new TextView(mContext);
					    c1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
						if (i==0){
							c1.setText("Problemas"); 
							c1.setTextColor(resource.getColor(R.color.white));	
							c1.setGravity(Gravity.CENTER);
							tr.setBackgroundColor(resource.getColor(R.color.background));
						    tr.addView(c1);
						    tr.setPadding(1, 1, 1, 1);
						    tableEnv.addView(tr);
						}else{
							if (i==1){
								c1.setText(Integer.toString(numProb.get(i-1))+"-"+nameProb.get(i-1));
								c1.setGravity(Gravity.CENTER);	
							    tr.addView(c1);
							    tr.setPadding(1, 1, 1, 1);
							    tableEnv.addView(tr);
							}
							else{

								if (!numProb.get(i-2).equals(numProb.get(i-1))){
									c1.setText(Integer.toString(numProb.get(i-1))+"-"+nameProb.get(i-1));
									c1.setGravity(Gravity.CENTER);	
								    tr.addView(c1);
								    tr.setPadding(1, 1, 1, 1);
								    tableEnv.addView(tr);
								}
							}

						}
					   	
					    
					}
				}else{
					tableEnv.setVisibility(View.GONE);
				}
			}
			
	    	pDlg.dismiss();
	    }
		
	}

}
