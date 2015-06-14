package WS;

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
        
        return wst.getResponse();
        
	}
	public String postCall(Activity frag){

        wst = new WebServiceTask(WebServiceTask.POST_TASK, frag, "POSTINGting data...");
       
        wst.execute(new String[] {  this.path.getQuery() });
        
        return wst.getResponse();
	}
}
