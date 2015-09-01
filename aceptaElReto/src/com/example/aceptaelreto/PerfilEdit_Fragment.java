package com.example.aceptaelreto;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONException;
import org.json.JSONObject;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import Tools.BitmapLRUCache;
import acr.estructuras.CountryWSType;
import acr.estructuras.InstitutionWSType;
import acr.estructuras.ListCountryWSType;
import acr.estructuras.SubmissionWSType;
import acr.estructuras.SubmissionsListWSType;
import acr.estructuras.UserWSType;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;


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
	private Spinner spinGenero;
	private Spinner spinPais;
	private Spinner spinInstitucion;
	private LinearLayout editInstitucion;
	private EditText editTxtNombreCompleto;
	private EditText editTxtNacimineto;
	private int PICK_IMAGE_REQUEST = 1;
	private Uri uri;
    private Boolean envio;
    private Boolean rdy;
    private Boolean select;
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	private ArrayList<NameValuePair> paisParams = new ArrayList<NameValuePair>();
	private ArrayList<String> paislist = new ArrayList<String>();
	private ArrayList<String> paiscode = new ArrayList<String>();
	private ArrayList<String> instlist = new ArrayList<String>();
	private ArrayList<Integer> instids = new ArrayList<Integer>();
	private Bundle token;
	private static int idUserSearch;
	private String countrycode; 
	private int count = 0;
	private int instPos = -1;
	
	private static final int RESULT_CROP = 3;

    private static int RESULT_LOAD_IMAGE = 1;

    String encodedImage = "";
    String encodedImg = "";
	
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
		select = false;
		View rootView = inflater.inflate(R.layout.perfil_edit, container, false);
		MainActivity.numTransaction += 1;
		
        txtNombreCompleto = (TextView)rootView.findViewById(R.id.txtNombreCompleto);
        txtNacimiento = (TextView)rootView.findViewById(R.id.txtNacimiento);
		txtGenero = (TextView)rootView.findViewById(R.id.txtGenero);
		txtPais = (TextView)rootView.findViewById(R.id.txtPais);
		txtInstitucion = (TextView)rootView.findViewById(R.id.txtInstitucion);
		txtNick = (TextView)rootView.findViewById(R.id.txtNick);
        txtCorreo = (TextView)rootView.findViewById(R.id.txtCorreo);
        img = (NetworkImageView)rootView.findViewById(R.id.avatar);
        editInstitucion = (LinearLayout)rootView.findViewById(R.id.editInstitucion);
        editTxtNombreCompleto = (EditText)rootView.findViewById(R.id.editTxtNombreCompleto);
        editTxtNacimineto = (EditText)rootView.findViewById(R.id.editTxtNacimineto);
        
        //Spinners
        
		spinGenero = (Spinner)rootView.findViewById(R.id.spinGenero);
		spinPais = (Spinner)rootView.findViewById(R.id.spinPais);
		spinInstitucion = (Spinner)rootView.findViewById(R.id.spinInstitucion);
		editInstitucion.setVisibility(View.GONE);
		
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.genero_opc, android.R.layout.simple_spinner_item);     
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinGenero.setAdapter(adapter);	
		
		adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, paislist);   
	    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinPais.setAdapter(adapter1);
	    
	    adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, instlist);   
	    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinInstitucion.setAdapter(adapter2);
	    
	    //Caso Spinner País -> Actualiza Spinner Institución
	    spinPais.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
            	count++;
            	if(count>1){
            		int code = (int) spinPais.getSelectedItemId();
    	    		countrycode = paiscode.get(code);
    	    		select = true;
    	    		MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
    				task.execute("inst");
            	}
	    		
            }

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
								
			}

        });
	    
	    spinInstitucion.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
            		instPos = arg2;
            }

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
								
			}

        });

	    //Botones
	    
	    btnEditPhoto = (Button)rootView.findViewById(R.id.btnEditPhoto);
		btnEditPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	                startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});
	    
	    btnActualizar = (Button)rootView.findViewById(R.id.btnActualizar);
	    btnActualizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				envio = true;
				MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
				task.execute("");	
			}
		});
	    
	    //Llamada por default
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
	
	//Recolecta la información del cambio de foto
	 
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		 
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            /*Uri selectedImage = data.getData();
            if (selectedImage != null){
                crop(selectedImage);
            }
        } else if (requestCode == RESULT_CROP && resultCode == Activity.RESULT_OK && data != null){*/
            // Get the Image from data

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            // Get the cursor
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            // Move to first row
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            encodedImg = cursor.getString(columnIndex);
            cursor.close();
            
            // Set the Image in ImageView after decoding the String
            Bitmap bm = BitmapFactory.decodeFile(encodedImg);
            img.setImageBitmap(bm);

            // Send JSON
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG,40,baos);

            // bitmap object
            byte[] byteImage_photo = baos.toByteArray();

            //generate base64 string of image
             encodedImage = Base64.encodeToString(byteImage_photo, Base64.DEFAULT);
             
             MyAsyncTask task = new MyAsyncTask(getActivity(),"GETting data...");
 	    	task.execute("imageChange");
            }
		    
		    	
		
	}	 

	private void crop(Uri photoUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setData(photoUri);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_CROP);
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
	    private ListCountryWSType countries = null;
	    private ArrayList<InstitutionWSType> institutions = null;
	    private int pos = 0, inst = 0;
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
			pos = 0;
			inst = 0;
			// false = mostrar perfil edit / true = enviar cambios			
			if (!envio){
				if (params[0].equals("imageChange")){
									
					JSONObject json= new JSONObject();
					path.cleanQuery();
					path.addType(type.user);
					path.addID(idUserSearch);
					path.addType(type.avatar);
					try {
						json.put("avatar", encodedImage);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					path.setFileLocal("encoded");
					path.setJson(json);
					ws.setPath(path);
					String respuesta = ws.postCall(token.getString("TOKEN"));

				}
				else if (params[0].equals("")){
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
					ArrayList<CountryWSType> listcountries = null;
					try{
 						listcountries = tradu.getPaises();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					CountryWSType aux = null;
					for(int i=0;i<listcountries.size();i++){
						aux = listcountries.get(i);
						paisParams.add(new BasicNameValuePair(aux.nombre, aux.code));
					}
					
					Comparator<NameValuePair> comp = new Comparator<NameValuePair>() {        
					    @Override
					    public int compare(NameValuePair p1, NameValuePair p2) {
					      return p1.getName().compareTo(p2.getName());
					    }
					};

					Collections.sort(paisParams, comp);
					for(int i=0;i<listcountries.size();i++){
						if (paisParams.get(i).getName().equals(perfil.country.nombre)) pos = i; 
						paislist.add(i, paisParams.get(i).getName());
						paiscode.add(i, paisParams.get(i).getValue());
					}
					
					requestQueueImagen = Volley.newRequestQueue(getActivity().getApplicationContext());
					imageLoader = new ImageLoader(requestQueueImagen, new BitmapLRUCache());
					format = new SimpleDateFormat("dd/MM/yyyy");
				}
				if (params[0].equals("inst") || perfil.institution != null){
					//Relleno Array Institución
    	    		rdy = true;
					path.cleanQuery();
					path.addType(type.institution);
					path.addType(type.country);
					if (select) path.addFree(countrycode);
					else path.addFree(paiscode.get(pos));
					this.ws.setPath(path);
					String respuesta = ws.getCall(token.getString("TOKEN"));
					if (!respuesta.equals("{}")){
						Traductor tradu = new Traductor(respuesta);
						try{
							institutions = tradu.getInstituciones();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}		
					if (institutions!=null){
						for(int i=0;i<institutions.size();i++){
							if (perfil!=null && institutions.get(i).name.equals(perfil.institution.name)) inst = i;
							instlist.add(i, institutions.get(i).name);
							instids.add(i, institutions.get(i).id);
					    }
					}
					
					
					
				}
			}
			else{
				String birthday = "";
				if (!editTxtNacimineto.getText().toString().equals("")){
					Date date = null;
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					try {
						date = df.parse(editTxtNacimineto.getText().toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
					birthday = df2.format(date);
				}
				JSONObject json= new JSONObject();
				path.addParam("name", "JoseLorenzo");
				path.addType(type.user);
				path.addID(idUserSearch);
				path.addType(type.profile);
				try {
					
					if(!editTxtNombreCompleto.getText().toString().equals("")) json.put("name", editTxtNombreCompleto.getText().toString());
					
					if (spinGenero.getSelectedItem().toString().equals("Hombre"))json.put("gender", "MALE");
					else json.put("gender", "FEMALE");
					
					if(instPos!=-1) json.put("institutionId", Integer.toString(instids.get(instPos)));
					
					json.put("countryCode", countrycode);
					
					json.put("mailNotification", "NEVER");
					
					if(!editTxtNacimineto.getText().toString().equals("")) json.put("birthday", birthday);
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				path.setJson(json);
				ws.setPath(path);
				String response = ws.putCall(token.getString("TOKEN"));
				
					MainActivity.numTransaction = 0;
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
							Perfil_Fragment.newInstance(0,token.getString("TOKEN"),idUserSearch)).addToBackStack(null).commit();
				
			}
			
			return perfil;
		}
		
		@Override
	    protected void onPostExecute(UserWSType perfil) { 
			if (!rdy || perfil!=null){
				String nac = "dd/MM/yyyy";
				if (perfil.birthday!=null){
					Date date = perfil.birthday;
					nac = format.format(date);
				}		    	
			    txtNacimiento.setText("Fecha de Nacimiento: ");
			    img.setImageUrl(perfil.avatar, imageLoader); 	
			    txtCorreo.setText("Correo: "+perfil.email);
			    txtNick.setText("Nick: "+perfil.nick);	
			    txtNombreCompleto.setText("Nombre: ");
			    txtGenero.setText("Genero: ");
			    txtPais.setText("País: ");
			    txtInstitucion.setText("Institución: ");
			    editTxtNombreCompleto.setHint(perfil.name);
			    editTxtNacimineto.setHint(nac);
			    adapter1.notifyDataSetChanged();
			    if (pos!=0){
			    	spinPais.setSelection(pos);
			    	countrycode = paiscode.get(pos);
			    }
			}
			if (rdy){
				editInstitucion.setVisibility(View.VISIBLE);
				adapter2.notifyDataSetChanged();
				countrycode = paiscode.get(pos);
			    if (institutions==null){
			    	txtInstitucion.setText("No se han encontrado instituciones");
			    	spinInstitucion.setVisibility(View.GONE);
			    }else{
			    	if (inst!=0 && !select) spinInstitucion.setSelection(inst);
			    	txtInstitucion.setText("Institución: ");
			    	spinInstitucion.setVisibility(View.VISIBLE);
			    }
 
			}
			
	    	pDlg.dismiss();
	    }
		
	}

}
