package acr.estructuras;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyWebTarget;



public class Config extends JerseyWebTarget {

	protected Config(UriBuilder uriBuilder, ClientConfig clientConfig) {
		super(uriBuilder, clientConfig);
		// TODO Auto-generated constructor stub
	}
	public UriBuilder getUriBuilder(){

		return super.getUriBuilder();
	}
}
