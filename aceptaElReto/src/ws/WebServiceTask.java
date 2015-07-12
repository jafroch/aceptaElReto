package ws;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.entity.InputStreamEntity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;





 @SuppressWarnings("deprecation")
public class WebServiceTask extends AsyncTask<String, Integer, String> {
	 
    public static final int POST_TASK = 1;
    public static final int GET_TASK = 2; 
    public static final int PUT_TASK = 3;
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
    
    
    public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	private String Response;
    private URI uriInfo;
    private String FileName;

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
    

    @Override
    protected void onPreExecute() {
        //hideKeyboard();
    	showProgressDialog();
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
        //CookieStore cookieStore = new BasicCookieStore();     
        // Create local HTTP context
        //HttpContext localContext = new BasicHttpContext();
        // Bind custom cookie store to the local context
        //localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore); 
        //CookieManager cookieManager= CookieManager.getInstance();
     
        HttpResponse response = null;        
        try {
            switch (taskType) {

            case POST_TASK:
                HttpPost httppost = new HttpPost(url);
                // Add parameters
                httppost.setEntity(new UrlEncodedFormEntity(params));
                int executeCount = 0;
    			do
    			{
    				pDlg.setMessage("Logging in.. ("+(executeCount+1)+"/5)");
    				// Execute HTTP Post Request
    				executeCount++;
    				response = httpclient.execute(httppost);//response = httpclient.execute(httppost,localContext);
    				responseCode = response.getStatusLine().getStatusCode();   			
    				// If you want to see the response code, you can Log it
    				// out here by calling:
    				// Log.d("256 Design", "statusCode: " + responseCode)
    			} while (executeCount < 5 && responseCode == 408);          
                uriInfo = httppost.getURI();               
                break;
            case GET_TASK:
                HttpGet httpget = new HttpGet(url);
                response = httpclient.execute(httpget);//response = httpclient.execute(httpget,localContext);
                responseCode = response.getStatusLine().getStatusCode();
                httpget.getRequestLine();
                uriInfo = httpget.getURI();                    
                break;
            case PUT_TASK:
                HttpPut httpput = new HttpPut(url);
                File file = new File(this.FileName);
                InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(file), -1);
                reqEntity.setContentType("binary/octet-stream");
                reqEntity.setChunked(true); // Send in multiple parts if needed
                httpput.setEntity(reqEntity);
                response = httpclient.execute(httpput);//response = httpclient.execute(httpput,localContext);
                responseCode = response.getStatusLine().getStatusCode();
                httpput.getRequestLine();
                uriInfo = httpput.getURI();                    
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