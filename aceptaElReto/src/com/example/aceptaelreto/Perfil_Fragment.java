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
//import com.example.aceptaelreto.MainActivity;


















import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;




















import acr.estructuras.SubmissionWSType;
import acr.estructuras.UserWSType;
import android.app.Activity;
import android.app.FragmentManager;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Perfil_Fragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
    
	private TextView txtNick;
	private TextView txtCorreo;
	private TextView txtNombreCompleto;
	private TextView txtNacimiento;
	private TextView txtGenero;
	private TextView txtPais;
	private TextView txtInstitucion;
	private Button btnEditProfile;
	private NetworkImageView img;
	private Bundle token;
	private static int idUserSearch;
	private ListView list;
	private ArrayList<String> probEnv;
	private ArrayAdapter adaptador;
	private ArrayList<Info_Envios> info;
	
    public static Perfil_Fragment newInstance(int sectionNumber, String tk, int id) {
        Perfil_Fragment fragment = new Perfil_Fragment();
        idUserSearch = id;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        args.putInt("IdUserSearch", id);
        fragment.setArguments(args);
        return fragment;
    }
    
    public Perfil_Fragment() {
    	 
    }
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		token = this.getArguments();
		probEnv = new ArrayList<String>();
		info =new ArrayList<Info_Envios>();
		
		View rootView = inflater.inflate(R.layout.perfil_info, container, false);  
        txtNick = (TextView)rootView.findViewById(R.id.txtNick);
        txtCorreo = (TextView)rootView.findViewById(R.id.txtCorreo);
        txtNombreCompleto = (TextView)rootView.findViewById(R.id.txtNombreCompleto);
        txtNacimiento = (TextView)rootView.findViewById(R.id.txtNacimiento);
		txtGenero = (TextView)rootView.findViewById(R.id.txtGenero);
		txtPais = (TextView)rootView.findViewById(R.id.txtPais);
		txtInstitucion = (TextView)rootView.findViewById(R.id.txtInstitucion);
		img = (NetworkImageView)rootView.findViewById(R.id.avatar);
		list = (ListView)rootView.findViewById(R.id.listProbEnv);
		//adaptador = new MyArrayAdapter(getActivity(),);
		
		btnEditProfile = (Button)rootView.findViewById(R.id.btnEditProfile);
		/*btnEditProfile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), EditInfoActivity.class);
				intent.putExtra("WALKER", walker);
				startActivityForResult(intent, Constants.RESULT_EDIT);
			}
		});
		*/

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
	    private String[] estrucType;
	    private UserWSType perfil = null;
	    private SubmissionWSType subs = null;
	    
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
			
			estrucType = params;
			if (idUserSearch != 0){
				path.addType(type.user);
				path.addID(idUserSearch);
			}
			else{
				path.addType(type.currentuser);
			}
		    this.ws.setPath(path);
			String respuesta = ws.getCall(token.getString("TOKEN"));
			Traductor tradu = new Traductor(respuesta);
			try{
				perfil = tradu.getUser();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//submission WS
			path.addType(type.submissions);
			this.ws.setPath(path);
			respuesta = ws.getCall(token.getString("TOKEN"));
			tradu = new Traductor(respuesta);
			try{
				subs = tradu.getSubmission();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			requestQueueImagen = Volley.newRequestQueue(getActivity().getApplicationContext());
			imageLoader = new ImageLoader(requestQueueImagen, new BitmapLRUCache());
			format = new SimpleDateFormat("dd/MM/yyyy");
			return perfil;
		}
		
		@Override
	    protected void onPostExecute(UserWSType perfil) { 
	    	
			if (perfil.email == null){
		    	txtNacimiento.setVisibility(View.GONE);
		    	txtCorreo.setVisibility(View.GONE);
		    	txtGenero.setVisibility(View.GONE);
		    	img.setImageUrl(perfil.avatar, imageLoader);
		    	txtNick.setText("Nick: "+perfil.nick);	
		    	txtNombreCompleto.setText("Nombre: "+perfil.name);
		    	txtPais.setText("País: "+perfil.country.name);
		    	txtInstitucion.setText("Institución: "+perfil.institution.name);
		    }else{
		    	Date date = perfil.birthday;
		    	String nac = format.format(date);		
		    	txtNacimiento.setText("Fecha de Nacimiento: "+nac);
		    	img.setImageUrl(perfil.avatar, imageLoader); 	
		    	txtCorreo.setText("Correo: "+perfil.email);
		    	txtNick.setText("Nick: "+perfil.nick);	
		    	txtNombreCompleto.setText("Nombre: "+perfil.name);
		    	txtGenero.setText("Genero: "+perfil.gender);
		    	txtPais.setText("País: "+perfil.country.name);
		    	txtInstitucion.setText("Institución: "+perfil.institution.name);
		    }
			
			if(subs != null){
				
			}
	    	
	    	pDlg.dismiss();
	    }
		
	}

}
