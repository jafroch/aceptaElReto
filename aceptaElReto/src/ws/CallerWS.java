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

	public String getCall(Activity frag){

        wst = new WebServiceTask(WebServiceTask.GET_TASK, frag, "GETting data...");
        wst.execute(new String[] { this.path.getQuery() });
        String out=null;
        while(out==null){
        	out=wst.getResponse();
        }
        return out;
	}
	public String postCall(Activity frag){

        wst = new WebServiceTask(WebServiceTask.POST_TASK, frag, "POSTINGting data...");
        for(int i=0;i<this.path.getParamsNames().size();i++){
        	wst.addNameValuePair(this.path.getParamsNames().get(i), this.path.getParamsValues().get(i));
        }
        wst.execute(new String[] {  this.path.getQuery() });
        String out=null;
        while(out==null){
        	out=wst.getResponse();
        }
        return out;
	}
	public String putCall(Activity frag){

        wst = new WebServiceTask(WebServiceTask.PUT_TASK, frag, "PUTTINGting data...");
        wst.setFileName(this.path.getFileLocal());
        wst.execute(new String[] {  this.path.getQuery() });
        String out=null;
        while(out==null){
        	out=wst.getResponse();
        }
        return out;
	}
	
	public String getResponse(){
		return this.wst.getResponse();
	}
}
