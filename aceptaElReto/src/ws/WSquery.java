package ws;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import android.R.string;


public class WSquery {
	/*
	 * Clase que a traves de metodos nos construye cualquier URL que 
	 * acepta nuestro WS.
	 * Ir contruyendo la URL por medio de los metodos para luego por 
	 * medio del caller llamar al WS y obtener la respuesta.
	 */
	
	private String url="http://acr2.programame.com/ws/";
	private String login1="session?user=";
	private String login2="&password=";
	private String login3="&app=";
	private String app_id="2015-2015";
	private String query;
	private String str="?start=";
	private String size="&size=";
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
	public void addStartSize(int start, int size){
		this.query=this.query+this.str+Integer.toString(start)+this.size+Integer.toString(size)+"/";
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
	public void login(String usr, String pass){
		this.query=this.url+this.login1+usr+this.login2+pass+this.login3+this.app_id+"/";
	}
	public URI getURI() {
	    return UriBuilder.fromUri(query).build();
	  }
	
}
