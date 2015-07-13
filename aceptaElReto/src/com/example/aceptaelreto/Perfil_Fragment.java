package com.example.aceptaelreto;



import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Perfil_Fragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private CallerWS ws;
    private WSquery path;
    
	private TextView txtNick;
	private TextView txtCorreo;
	private TextView txtNombreCompleto;
	private TextView txtNacimiento;
	private TextView txtGenero;
	private TextView txtPais;
	private TextView txtInstitucion;
	private Button btnEditProfile;
	private NetworkImageView img;
	
    public static Perfil_Fragment newInstance(int sectionNumber) {
        Perfil_Fragment fragment = new Perfil_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    
    public Perfil_Fragment() {
    	 
    }
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.perfil_info, container, false);
        
        txtNick = (TextView)rootView.findViewById(R.id.txtNick);
        txtCorreo = (TextView)rootView.findViewById(R.id.txtCorreo);
        txtNombreCompleto = (TextView)rootView.findViewById(R.id.txtNombreCompleto);
        txtNacimiento = (TextView)rootView.findViewById(R.id.txtNacimiento);
		txtGenero = (TextView)rootView.findViewById(R.id.txtGenero);
		txtPais = (TextView)rootView.findViewById(R.id.txtPais);
		txtInstitucion = (TextView)rootView.findViewById(R.id.txtInstitucion);
		img = (NetworkImageView)rootView.findViewById(R.id.avatar);
		
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
		this.setPerfil();
        return rootView;
    }
	
	 @Override
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
	                ARG_SECTION_NUMBER));
	 }
	
	public void setPerfil(){
		
		path.addType(type.user);
	    path.addID(50);
	    this.ws.setPath(path);
		String respuesta = ws.getCall(getActivity());
		Traductor tradu = new Traductor(respuesta);
		UserWSType perfil = null;
		try{
			perfil = tradu.getUser();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		if (perfil.role.name()!=null){
			String DATE_FORMAT = "dd/MM/yyyy";
			Date date = perfil.birthday;
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			this.txtNacimiento.setText(sdf.format(date));
			this.txtCorreo.setText("Correo: "+perfil.email);
		}
		else{
			this.txtNacimiento.setText(" ");
			this.txtCorreo.setText(" ");
		}*/
		RequestQueue requestQueueImagen = Volley.newRequestQueue(getActivity().getApplicationContext());
		ImageLoader imageLoader = new ImageLoader(requestQueueImagen, new BitmapLRUCache());
		img.setImageUrl(perfil.avatar, imageLoader);
		this.txtNacimiento.setText("Fecha de Nacimiento: ");
		this.txtCorreo.setText("Correo: ");
		this.txtGenero.setText("G�nero: ");
		this.txtNick.setText("Nick: "+perfil.nick);	
		this.txtNombreCompleto.setText("Nombre: "+perfil.name);
		//this.txtGenero.setText("Genero: "+perfil.gender.name());
		this.txtPais.setText("Pa�s: "+perfil.country.name);
		this.txtInstitucion.setText("Instituci�n: "+perfil.institution.name);
		
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

}