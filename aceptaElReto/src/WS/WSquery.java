package WS;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import android.R.string;


public class WSquery {
	
	private String url="https://www.aceptaelreto.com/ws/";
	private String login1="session?user=";
	private String login2="&password=";
	private String login3="&app=";
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
	public URI getURI() {
	    return UriBuilder.fromUri(query).build();
	  }
	
}
