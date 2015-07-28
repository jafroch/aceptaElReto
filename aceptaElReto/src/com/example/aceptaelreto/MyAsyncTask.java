package com.example.aceptaelreto;

import java.text.SimpleDateFormat;
import java.util.Date;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WSquery.type;
import acr.estructuras.UserWSType;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

class MyAsyncTask<T> extends AsyncTask<String, Void, T>{
	
	private ProgressDialog pDlg = null;
	private Context mContext = null;
    private String processMessage = "Processing...";
    private RequestQueue requestQueueImagen;
    private ImageLoader imageLoader;
    private CallerWS ws;
    private WSquery path;
    private SimpleDateFormat format;
    private String[] estrucType;
    private T perfil = null;
    
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
	protected T doInBackground(String... params) {
		
		return perfil;
	}
	
	@Override
    protected void onPostExecute(T perfil) { 
    	pDlg.dismiss();
    }
	
}
