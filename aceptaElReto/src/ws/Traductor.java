package ws;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import acr.estructuras.*;

public class Traductor {
		
	/*
	 * Clase que nos sirve para transformar un JSON en lo que queremos, 
	 * Hay que notar que solo lo hara correctamente si invocamos el metodo
	 * que coincide con el STRING JSON que corresponde.
	 * Pej:
	 * Si llamamos para obtener una lista de paises tendremos que invocar
	 * el metodo getPaises, ya que cualquier otro no nos devolvera lo que
	 * queremos.
	 */
		String JSON;
		private RegistryMatcher m;
		
		
	public String getJSON() {
			return JSON;
		}


		public void setJSON(String jSON) {
			JSON = jSON;
		}


	public Traductor(String json) {
		// TODO Auto-generated constructor stub
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ", Locale.US);
		m = new RegistryMatcher();
		m.bind(Date.class, new DateFormatTransformer(format));
		
		
		this.JSON=json;
		
	}
	public UserWSType getUser() throws Exception{
		if(this.JSON.startsWith("<?xml")){
			Serializer serial = new Persister(m);
			UserWSType data = serial.read(UserWSType.class, this.JSON);
	    	return data;
		}else{
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, UserWSType.class);
		}
	}
	public NewSession getSession() throws Exception{
		if(this.JSON.contains("<?xml")){
			Serializer serial = new Persister(m);
			NewSession data = serial.read(NewSession.class, this.JSON);
	    	return data;
		}else{
			Gson gson = new GsonBuilder().create();
	    	JsonParser parser = new JsonParser();
	    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
	    	return gson.fromJson(data, NewSession.class);
		}
	}
	public CategoryWSType getCategoria() throws Exception{
		if(this.JSON.contains("<?xml")){
			Serializer serial = new Persister(m);
			CategoryWSType data = serial.read(CategoryWSType.class, this.JSON);
	    	return data;
		}else{
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, CategoryWSType.class);
		}
	}
	public CountryWSType getPais() throws Exception{
		if(this.JSON.contains("<?xml")){
			Serializer serial = new Persister(m);
			CountryWSType data = serial.read(CountryWSType.class, this.JSON);
	    	return data;
		}else{
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, CountryWSType.class);
	}
	}
	public ProblemDetailsList getListaDetalles() throws Exception{
		if(this.JSON.contains("<?xml")){
			Serializer serial = new Persister(m);
			ProblemDetailsList data = serial.read(ProblemDetailsList.class, this.JSON);
	    	return data;
		}else{
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, ProblemDetailsList.class);
		}
	}
	public ProblemWSType getProblema()throws Exception{
		if(this.JSON.contains("<?xml")){
			Serializer serial = new Persister(m);
			ProblemWSType data = serial.read(ProblemWSType.class, this.JSON);
	    	return data;
		}else{
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, ProblemWSType.class);
		}
	}
	public UserGenderWSType getGenero()throws Exception{
		if(this.JSON.contains("<?xml")){
			Serializer serial = new Persister(m);
			UserGenderWSType data = serial.read(UserGenderWSType.class, this.JSON);
	    	return data;
		}else{
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, UserGenderWSType.class);
		}
	}
	public UserRoleWSType getRol()throws Exception{
		if(this.JSON.contains("<?xml")){
			Serializer serial = new Persister(m);
			UserRoleWSType data = serial.read(UserRoleWSType.class, this.JSON);
	    	return data;
		}else{
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, UserRoleWSType.class);
		}
	}

	public ArrayList<CategoryWSType> getCategorias(){
		JSONObject jsonobject;
    	JSONArray jsonArray = null;
		try {
			jsonobject = new JSONObject(this.JSON);
			//ponemos country ya que el obj tiene un campo que es la lista de elems llamado country.
			jsonArray = jsonobject.getJSONArray("country");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	//then get the type for list and parse using gson as
    	Type listType = new TypeToken<List<CategoryWSType>>(){}.getType();
    	List<CategoryWSType> List = new Gson().fromJson(jsonArray.toString(), listType);
    	return (ArrayList<CategoryWSType>) List;
	}
	public ArrayList<CountryWSType> getPaises(){
		JSONObject jsonobject;
    	JSONArray jsonArray = null;
		try {
			jsonobject = new JSONObject(this.JSON);
			//ponemos country ya que el obj tiene un campo que es la lista de elems llamado country.
			jsonArray = jsonobject.getJSONArray("country");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	//then get the type for list and parse using gson as
    	Type listType = new TypeToken<List<CountryWSType>>(){}.getType();
    	List<CountryWSType> List = new Gson().fromJson(jsonArray.toString(), listType);
    	return (ArrayList<CountryWSType>) List;
	}
	public ArrayList<ProblemDetailsList> getListasDetalles(){
		JSONObject jsonobject;
    	JSONArray jsonArray = null;
		try {
			jsonobject = new JSONObject(this.JSON);
			//ponemos country ya que el obj tiene un campo que es la lista de elems llamado country.
			jsonArray = jsonobject.getJSONArray("country");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	//then get the type for list and parse using gson as
    	Type listType = new TypeToken<List<ProblemDetailsList>>(){}.getType();
    	List<ProblemDetailsList> List = new Gson().fromJson(jsonArray.toString(), listType);
    	return (ArrayList<ProblemDetailsList>) List;
	}
	public ArrayList<ProblemWSType> getProblemas(){
		JSONObject jsonobject;
    	JSONArray jsonArray = null;
		try {
			jsonobject = new JSONObject(this.JSON);
			//ponemos country ya que el obj tiene un campo que es la lista de elems llamado country.
			jsonArray = jsonobject.getJSONArray("country");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	//then get the type for list and parse using gson as
    	Type listType = new TypeToken<List<ProblemWSType>>(){}.getType();
    	List<ProblemWSType> List = new Gson().fromJson(jsonArray.toString(), listType);
    	return (ArrayList<ProblemWSType>) List;
	}
	public ArrayList<UserGenderWSType> getGeneros(){
		JSONObject jsonobject;
    	JSONArray jsonArray = null;
		try {
			jsonobject = new JSONObject(this.JSON);
			//ponemos country ya que el obj tiene un campo que es la lista de elems llamado country.
			jsonArray = jsonobject.getJSONArray("country");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	//then get the type for list and parse using gson as
    	Type listType = new TypeToken<List<UserGenderWSType>>(){}.getType();
    	List<UserGenderWSType> List = new Gson().fromJson(jsonArray.toString(), listType);
    	return (ArrayList<UserGenderWSType>) List;
	}
	public ArrayList<UserRoleWSType> getRoles(){
		JSONObject jsonobject;
    	JSONArray jsonArray = null;
		try {
			jsonobject = new JSONObject(this.JSON);
			//ponemos country ya que el obj tiene un campo que es la lista de elems llamado country.
			jsonArray = jsonobject.getJSONArray("country");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	//then get the type for list and parse using gson as
    	Type listType = new TypeToken<List<UserRoleWSType>>(){}.getType();
    	List<UserRoleWSType> List = new Gson().fromJson(jsonArray.toString(), listType);
    	return (ArrayList<UserRoleWSType>) List;
	}
}
