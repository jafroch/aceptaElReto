package ws;

import android.app.Activity;
import android.app.Fragment;
/*
 * Clase que se usa para hacer las llamadas al WS, 
 * para ello se modifica el path con la url que queremos
 * y se invocan los metodos GET o POST,
 * que devuelven un String con la respuesta en formato
 * JSON.
 * 
 */
public class CallerWS {
	WSquery path;
	WebServiceTask wst;
	
	public CallerWS( ) {
		// TODO Auto-generated constructor stub
		this.path= new WSquery();
		
	}
	
	public WSquery getPath() {
		return path;
	}

	public void setPath(WSquery path) {
		this.path = path;
	}

	public String getCall(String token){

        wst = new WebServiceTask(WebServiceTask.GET_TASK, "GETting data...",token);
        wst.Execute(new String[] { this.path.getQuery() });
        
        return wst.getResponse();
	}
	public String postCall(String token){

        wst = new WebServiceTask(WebServiceTask.POST_TASK, "POSTINGting data...",token);
        /*for(int i=0;i<this.path.getParamsNames().size();i++){
        	wst.addNameValuePair(this.path.getParamsNames().get(i), this.path.getParamsValues().get(i));
        }*/
        wst.setFileName(this.path.getFileLocal());
        wst.setJson(this.path.getJson());
        wst.Execute(new String[] {  this.path.getQuery() });
        return wst.getResponse();
	}
	public String putCall(String token){

        wst = new WebServiceTask(WebServiceTask.PUT_TASK, "PUTTINGting data...",token);
        wst.setFileName(this.path.getFileLocal());
        wst.setJson(this.path.getJson());
        wst.Execute(new String[] {  this.path.getQuery() });
        return wst.getResponse();
	}
	
	public String getResponse(){
		return this.wst.getResponse();
	}
}
