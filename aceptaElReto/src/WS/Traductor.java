package WS;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

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
		
		
	public String getJSON() {
			return JSON;
		}


		public void setJSON(String jSON) {
			JSON = jSON;
		}


	public Traductor(String json) {
		// TODO Auto-generated constructor stub
		this.JSON=json;
	}

	public NewSession getSession() throws Exception{
		
		Serializer serial = new Persister();
		NewSession session = serial.read(NewSession.class, this.JSON);
    	return session;
	}
	public CategoryWSType getCategoria(){
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, CategoryWSType.class);
	}
	public CountryWSType getPais(){
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, CountryWSType.class);
	}
	public ProblemDetailsList getListaDetalles(){
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, ProblemDetailsList.class);
	}
	public ProblemWSType getProblema(){
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, ProblemWSType.class);
	}
	public UserGenderWSType getGenero(){
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, UserGenderWSType.class);
	}
	public UserRoleWSType getRol(){
		Gson gson = new GsonBuilder().create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, UserRoleWSType.class);
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
