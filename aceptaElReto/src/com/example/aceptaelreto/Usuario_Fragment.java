package com.example.aceptaelreto;



import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
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


public class Usuario_Fragment extends Fragment{
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private CallerWS ws;
    private WSquery path;
    
	private TextView txtNick;
	private TextView txtNombreCompleto;
	private TextView txtPais;
	private TextView txtInstitucion;
	private NetworkImageView img;
	private Bundle token;
	private static int numUser;
	
    public static Usuario_Fragment newInstance(int sectionNumber, String tk) {
        Usuario_Fragment fragment = new Usuario_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        numUser = sectionNumber;
        fragment.setArguments(args);
        return fragment;
    }
    
    public Usuario_Fragment() {
    	 
    }
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		token = this.getArguments();
		View rootView = inflater.inflate(R.layout.usuario_info, container, false);  
		
        txtNick = (TextView)rootView.findViewById(R.id.txtNick);
        txtNombreCompleto = (TextView)rootView.findViewById(R.id.txtNombreCompleto);
		txtPais = (TextView)rootView.findViewById(R.id.txtPais);
		txtInstitucion = (TextView)rootView.findViewById(R.id.txtInstitucion);
		img = (NetworkImageView)rootView.findViewById(R.id.avatar);
		
		this.ws= new CallerWS();
        path = this.ws.getPath();
		this.setUsuario();
        return rootView;
    }
	
	 @Override
	 public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
	                ARG_SECTION_NUMBER));
	 }
	
	public void setUsuario(){
		/*
		path.addType(type.user);
		path.addID(numUser);
	    this.ws.setPath(path);
		String respuesta = ws.getCall(getActivity(),token.getString("TOKEN"));
		Traductor tradu = new Traductor(respuesta);
		UserWSType usuario = null;
		try{
			usuario = tradu.getUser();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestQueue requestQueueImagen = Volley.newRequestQueue(getActivity().getApplicationContext());
		ImageLoader imageLoader = new ImageLoader(requestQueueImagen, new BitmapLRUCache());
		img.setImageUrl(usuario.avatar, imageLoader); 	
		this.txtNick.setText("Nick: "+usuario.nick);	
		this.txtNombreCompleto.setText("Nombre: "+usuario.name);
		this.txtPais.setText("País: "+usuario.country.name);
		this.txtInstitucion.setText("Institución: "+usuario.institution.name);
		*/
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
