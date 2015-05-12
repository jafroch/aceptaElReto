package WS;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;


public class wsclient {
  public wsclient() {
 /*   ClientConfig config = new DefaultClientConfig();
    Client client = Client.create(config);
    WebResource service = client.resource(getBaseURI());
    
    // create one todo
    Todo todo = new Todo("3", "Blabla");
    ClientResponse response = service.path("rest").path("todos")
        .path(todo.getId()).accept(MediaType.APPLICATION_XML)
        .put(ClientResponse.class, todo);
    
    // Return code should be 201 == created resource
    System.out.println(response.getStatus());
 
    // Get the Todos
    System.out.println(service.path("rest").path("todos")
        .accept(MediaType.TEXT_XML).get(String.class));
    
    // Get JSON for application 
    System.out.println(service.path("rest").path("todos")
        .accept(MediaType.APPLICATION_JSON).get(String.class));
    
    // Get XML for application
    System.out.println(service.path("rest").path("todos")
        .accept(MediaType.APPLICATION_XML).get(String.class));

    // Get the Todo with id 1
    System.out.println(service.path("rest").path("todos/1")
        .accept(MediaType.APPLICATION_XML).get(String.class));
    
    // get Todo with id 1
    service.path("rest").path("todos/1").delete();
    
    // Get the all todos, id 1 should be deleted
    System.out.println(service.path("rest").path("todos")
        .accept(MediaType.APPLICATION_XML).get(String.class));

    // create a Todo
    Form form = new Form();
    form.add("id", "4");
    form.add("summary", "Demonstration of the client lib for forms");
    response = service.path("rest").path("todos")
        .type(MediaType.APPLICATION_FORM_URLENCODED)
        .post(ClientResponse.class, form);
    System.out.println("Form response " + response.getEntity(String.class));
    
    // Get the all todos, id 4 should be created
    System.out.println(service.path("rest").path("todos")
        .accept(MediaType.APPLICATION_XML).get(String.class));
	*/
  }

  private static URI getBaseURI() {
    return UriBuilder.fromUri("https://www.aceptaelreto.com/ws/").build();
  }
  public WebResource Service(WSquery path){
	ClientConfig config = new DefaultClientConfig();
	Client client = Client.create(config);
	return client.resource(path.getURI());  
  }
  
  public ClientResponse getQuery(WSquery path){
	  
	  	Client client = Client.create();
		WebResource webResource = client
		   .resource(path.getQuery());

		ClientResponse response = webResource.accept("application/xml")
                 .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		return response;
		
	
  }
  //public ClientResponse deleteQuery(WSquery path){}
  
  public ClientResponse postQuery(WSquery path){
	  Client client = Client.create();
	  
		WebResource webResource = client
		   .resource(path.getQuery());

		String input = "aqui va la estructura del objeto a meter";

		ClientResponse response = webResource.type("application/xml")
		   .post(ClientResponse.class, input);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
		
		return response;
  }
   
}