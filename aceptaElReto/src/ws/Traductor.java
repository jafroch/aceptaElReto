package ws;

import java.io.StringReader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
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
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(data, UserWSType.class);
		}
	}
	public SubmissionsListWSType getSubmissionList() throws Exception{
		if(this.JSON.startsWith("<?xml")){
			Serializer serial = new Persister(m);
			SubmissionsListWSType data = serial.read(SubmissionsListWSType.class, this.JSON);
	    	return data;
		}else{
			Gson gson = this.getGsonbuilderAdapterSubmissionList();
	    	JsonParser parser = new JsonParser();
	    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
	    	return gson.fromJson(data, SubmissionsListWSType.class);
		}
	}
	public SubmissionWSType getSubmission() throws Exception{
		if(this.JSON.startsWith("<?xml")){
			Serializer serial = new Persister(m);
			SubmissionWSType data = serial.read(SubmissionWSType.class, this.JSON);
	    	return data;
		}else{
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
	    	JsonParser parser = new JsonParser();
	    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
	    	return gson.fromJson(data, SubmissionWSType.class);
		}
	}
	public NewSession getSession() throws Exception{
		if(this.JSON.contains("<?xml")){
			Serializer serial = new Persister(m);
			NewSession data = serial.read(NewSession.class, this.JSON);
	    	return data;
		}else{
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
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
		Gson gson = this.getGsonbuilderAdapterCategory();
    	JsonParser parser = new JsonParser();
    	JsonObject data = parser.parse(this.JSON).getAsJsonObject();
    	return gson.fromJson(this.JSON, CategoryWSType.class);
		}
	}
	public CountryWSType getPais() throws Exception{
		if(this.JSON.contains("<?xml")){
			Serializer serial = new Persister(m);
			CountryWSType data = serial.read(CountryWSType.class, this.JSON);
	    	return data;
		}else{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
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
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
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
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
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
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
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
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
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
			jsonArray = jsonobject.getJSONArray("subcats");
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
	
	private Gson getGsonbuilderAdapterCategory(){
		GsonBuilder b = new GsonBuilder();
		b.registerTypeAdapter(CategoryWSType.class, new JsonDeserializer<CategoryWSType>() {
		    @Override
		    public CategoryWSType deserialize(JsonElement arg0, Type arg1,
		        JsonDeserializationContext arg2) throws JsonParseException {
		        JsonObject appleObj = arg0.getAsJsonObject();
		        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
		        // Construct an apple (this shouldn't try to parse the seeds stuff
		        CategoryWSType a = g.fromJson(arg0, CategoryWSType.class);
		        if(appleObj.has("subcats")){
		        List<CategoryWSType> subcats = null;
		        // Check to see if we were given a list or a single seed
		        if (appleObj.get("subcats").isJsonArray()) {
		            // if it's a list, just parse that from the JSON
		        	subcats = g.fromJson(appleObj.get("subcats"),
		                    new TypeToken<List<CategoryWSType>>() {
		                    }.getType());
		        } else {
		            // otherwise, parse the single seed,
		            // and add it to the list
		        	CategoryWSType single = g.fromJson(appleObj.get("subcats"), CategoryWSType.class);
		        	subcats = new ArrayList<CategoryWSType>();
		        	subcats.add(single);
		        }
		        // set the correct subcats list
		        a.setSubcats(subcats);
		        }
		        //path
		        if(appleObj.has("path")){
		        List<CategoryWSType> path = null;
		        if (appleObj.get("path").isJsonArray()) {
		            // if it's a list, just parse that from the JSON
		        	path = g.fromJson(appleObj.get("path"),
		                    new TypeToken<List<CategoryWSType>>() {
		                    }.getType());
		        } else {
		            // otherwise, parse the single seed,
		            // and add it to the list
		        	CategoryWSType single = g.fromJson(appleObj.get("path"), CategoryWSType.class);
		        	path = new ArrayList<CategoryWSType>();
		        	path.add(single);
		        }
		        // set the correct seed list
		        a.setPath(path);
		        }
		       /* a.setAc(g.fromJson(appleObj.get("Ac"), Integer.class));
		        a.setC(g.fromJson(appleObj.get("C"), Integer.class));
		        a.setCe(g.fromJson(appleObj.get("Ce"), Integer.class));
		        a.setCpp(g.fromJson(appleObj.get("Cpp"), Integer.class));
		        a.setDesc(g.fromJson(appleObj.get("Desc"), String.class));
		        a.setId(g.fromJson(appleObj.get("Id"), Integer.class));
		        a.setIr(g.fromJson(appleObj.get("Ir"), Integer.class));
		        a.setJava(g.fromJson(appleObj.get("Java"), Integer.class));
		        a.setMl(g.fromJson(appleObj.get("Ml"), Integer.class));
		        a.setName(g.fromJson(appleObj.get("Name"), String.class));
		        a.setNumOfAllProblems(g.fromJson(appleObj.get("NumOfAllProblems"), Integer.class));
		        a.setNumOfProblems(g.fromJson(appleObj.get("NumOfProblems"), Integer.class));
		        a.setOl(g.fromJson(appleObj.get("Ol"), Integer.class));
		        a.setPe(g.fromJson(appleObj.get("Pe"), Integer.class));
		        a.setProblems(g.fromJson(appleObj.get("Problems"), ProblemDetailsList.class));
		        a.setRf(g.fromJson(appleObj.get("Rf"), Integer.class));
		        a.setRte(g.fromJson(appleObj.get("Rte"), Integer.class));
		        a.setTl(g.fromJson(appleObj.get("Tl"), Integer.class));
		        a.setTotalSubs(g.fromJson(appleObj.get("TotalSubs"), Integer.class));
		        a.setWa(g.fromJson(appleObj.get("Wa"), Integer.class));
		        */
		        return a;
		    }
		});
		return b.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
	}
	private Gson getGsonbuilderAdapterSubmissionList(){
		GsonBuilder b = new GsonBuilder();
		b.registerTypeAdapter(SubmissionsListWSType.class, new JsonDeserializer<SubmissionsListWSType>() {
		    @Override
		    public SubmissionsListWSType deserialize(JsonElement arg0, Type arg1,
		        JsonDeserializationContext arg2) throws JsonParseException {
		        JsonObject appleObj = arg0.getAsJsonObject();
		        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
		        // Construct an apple (this shouldn't try to parse the seeds stuff
		        SubmissionsListWSType a = g.fromJson(arg0, SubmissionsListWSType.class);
		        if(appleObj.has("submission")){
		        List<SubmissionWSType> subcats = null;
		        // Check to see if we were given a list or a single seed
		        if (appleObj.get("submission").isJsonArray()) {
		            // if it's a list, just parse that from the JSON
		        	subcats = g.fromJson(appleObj.get("subcats"),
		                    new TypeToken<List<SubmissionWSType>>() {
		                    }.getType());
		        } else {
		            // otherwise, parse the single seed,
		            // and add it to the list
		        	SubmissionWSType single = g.fromJson(appleObj.get("submission"), SubmissionWSType.class);
		        	subcats = new ArrayList<SubmissionWSType>();
		        	subcats.add(single);
		        }
		        // set the correct subcats list
		        a.setSubmissionlist(subcats);
		        }
		        return a;
		    }
		});
		return b.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ").create();
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonRequired
{
}
class AnnotatedDeserializer<T> implements JsonDeserializer<T>
{

    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
    {
        T pojo = new Gson().fromJson(je, type);

        Field[] fields = pojo.getClass().getDeclaredFields();
        for (Field f : fields)
        {
            if (f.getAnnotation(JsonRequired.class) != null)
            {
                try
                {
                    f.setAccessible(true);
                    if (f.get(pojo) == null)
                    {
                        throw new JsonParseException("Missing field in JSON: " + f.getName());
                    }
                }
                catch (IllegalArgumentException ex)
                {
                    Logger.getLogger(AnnotatedDeserializer.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IllegalAccessException ex)
                {
                    Logger.getLogger(AnnotatedDeserializer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return pojo;

    }
}