package WS;

import android.app.Activity;
import android.app.Fragment;

public class CallerWS {
	WSquery path;
	
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

        WebServiceTask wst = new WebServiceTask(WebServiceTask.GET_TASK, frag, "GETting data...");
       
        wst.execute(new String[] { this.path.getQuery() });
        
        return wst.getResponse();
        
	}
	public String postCall(Activity frag){

        WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK, frag, "POSTINGting data...");
       
        wst.execute(new String[] {  this.path.getQuery() });
        
        return wst.getResponse();
	}
}