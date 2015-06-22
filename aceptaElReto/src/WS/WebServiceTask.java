package WS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.example.aceptaelreto.LoginActivity;
import com.sun.jersey.server.impl.model.parameter.HttpContextInjectableProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;




 public class WebServiceTask extends AsyncTask<String, Integer, String> {
	 
    public static final int POST_TASK = 1;
    public static final int GET_TASK = 2;     
    private static final String TAG = "WebServiceTask";
    // connection timeout, in milliseconds (waiting to connect)
    private static final int CONN_TIMEOUT = 3000;
    // socket timeout, in milliseconds (waiting for data)
    private static final int SOCKET_TIMEOUT = 5000;
    private int taskType = GET_TASK;
    private Context mContext = null;
    private String processMessage = "Processing...";
    private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    private ProgressDialog pDlg = null;
    private String Response;
    private LoginActivity activity;
	private String  id = "2015-2015";
    private URI uriInfo;

    public WebServiceTask(int taskType, Context mContext, String processMessage) {

        this.taskType = taskType;
        this.mContext = mContext;
        this.processMessage = processMessage;
    }

    public void addNameValuePair(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }

    public void showProgressDialog() {  
        pDlg = new ProgressDialog(mContext);
        pDlg.setMessage(processMessage);
        pDlg.setProgressDrawable(mContext.getWallpaper());
        pDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDlg.setCancelable(false);
        pDlg.show();
    }
    public void setShowPDLG(){
    	pDlg.show();
    }

    @Override
    protected void onPreExecute() {
        //hideKeyboard();
    	showProgressDialog();
    }
    
    public void LoginTask(LoginActivity activity, ProgressDialog progressDialog){
		this.activity = activity;
		this.pDlg = progressDialog;
	}

    protected String doInBackground(String... urls) {
        String url = urls[0];
        String result = "";
        HttpResponse response = doResponse(url);
        if (response == null) {
            return result;
        } else {
            try {
                result = inputStreamToString(response.getEntity().getContent());

            } catch (IllegalStateException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);

            } catch (IOException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            }

        }
        
        return result;
    }

    @Override
    protected void onPostExecute(String response) { 	
        this.Response=response;
    	pDlg.dismiss();
    }
     
    // Establish connection and socket (data retrieval) timeouts
    private HttpParams getHttpParams() {   
        HttpParams htpp = new BasicHttpParams();   
        HttpConnectionParams.setConnectionTimeout(htpp, CONN_TIMEOUT);
        HttpConnectionParams.setSoTimeout(htpp, SOCKET_TIMEOUT);   
        return htpp;
    }
     
    private HttpResponse doResponse(String url) {         
        // Use our connection and data timeouts as parameters for our
        // DefaultHttpClient
        HttpClient httpclient = new DefaultHttpClient(getHttpParams());
        int responseCode=0;
        // Create a local instance of cookie store
        CookieStore cookieStore = new BasicCookieStore();     
        // Create local HTTP context
        HttpContext localContext = new BasicHttpContext();
        // Bind custom cookie store to the local context
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);        
        HttpResponse response = null;        
        try {
            switch (taskType) {

            case POST_TASK:
                HttpPost httppost = new HttpPost("http://acr2.programame.com/ws/session");
                // Add parameters
                this.addNameValuePair("user", "jafroch@gmail.com");
                this.addNameValuePair("password", "testtest");
                this.addNameValuePair("app", "2015-2015");
                httppost.setEntity(new UrlEncodedFormEntity(params));
                int executeCount = 0;
    			do
    			{
    				pDlg.setMessage("Logging in.. ("+(executeCount+1)+"/5)");
    				// Execute HTTP Post Request
    				executeCount++;
    				response = httpclient.execute(httppost,localContext);
    				responseCode = response.getStatusLine().getStatusCode();   			
    				List<Cookie> cookies = cookieStore.getCookies();
    				Log.e("CustomHttpClient","Cookies size= " + cookies.size());
    		            for (int i = 0; i < cookies.size(); i++) {
    		                Cookie cookie = cookies.get(i);
    		                Log.e("CustomHttpClient","Local cookie: " + cookie);
    		                //mSessionCookie = cookie;
    		                Log.e("CustomHttpClient",""+cookie.getValue());
    		            }
    				// If you want to see the response code, you can Log it
    				// out here by calling:
    				// Log.d("256 Design", "statusCode: " + responseCode)
    			} while (executeCount < 5 && responseCode == 408);          
                uriInfo = httppost.getURI();               
                break;
            case GET_TASK:
                HttpGet httpget = new HttpGet(url);
                response = httpclient.execute(httpget,localContext);
                responseCode = response.getStatusLine().getStatusCode();
				List<Cookie> cookies = cookieStore.getCookies();
				 Log.e("CustomHttpClient","Cookies size= " + cookies.size());
		            for (int i = 0; i < cookies.size(); i++) {
		                Cookie cookie = cookies.get(i);
		                Log.e("CustomHttpClient","Local cookie: " + cookie);
		                //mSessionCookie = cookie;
		                Log.e("CustomHttpClient",""+cookie.getValue());
		            }
                httpget.getRequestLine();
                uriInfo = httpget.getURI();                    
                break;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return response;
    }
     
    private String inputStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            // Read response until the end
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        // Return full string
        this.Response=total.toString();
        return total.toString();
    }
    public String getResponse(){
    	return this.Response;
    }
}