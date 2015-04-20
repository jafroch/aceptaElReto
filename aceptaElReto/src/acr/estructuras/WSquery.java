package acr.estructuras;

import android.R.string;
import com.sun.jersey.api.client.Client;
import jersey.

public class WSquery {
	
	private static String url="https://www.aceptaelreto.com/ws/";
	private static String login1="session?user=";
	private static String login2="&password=";
	private static String login3="&app=";
	private String query;
	public enum type{
		allproblems,
		best,
		cat,
		code,
		comment,
		country,
		institution,
		login,
		paths,
		problems,
		problem,
		ranking,
		submission,
		user,
		volume,
	}
	
	public WSquery() {
		// TODO Auto-generated constructor stub
		this.query=this.url;
	}
	public void addType(type tipo){
		this.query=this.query+tipo.toString()+"/";
	}
	public void addID(int id){
		this.query=this.query+Integer.toString(id)+"/";
	}
	public void addNumSubCat(int md){
		this.query=this.query+"?md="+Integer.toString(md)+"/";
	}
	public void addFree(String str){
		this.query=this.query+str+"/";
	}
	public String getQuery(){
		return this.query;
	}
	public void login(String usr, String pass,String idApp){
		this.query=this.url+this.login1+usr+this.login2+pass+this.login3+idApp+"/";
	}
	public getQuery(){
		try {
			 
			Client client = Client.create();
	 
			WebResource webResource = client
			   .resource("http://localhost:8080/RESTfulExample/rest/json/metallica/get");
	 
			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);
	 
			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
	 
			String output = response.getEntity(String.class);
	 
			System.out.println("Output from Server .... \n");
			System.out.println(output);
	 
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
	}
	
}
