package acr.estructuras;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;

import acr.estructuras.Config;



/**
 * Clase genérica de lista que se devuelve en los servicios Web; tiene
 * además métodos útiles para gestionar solicitudes paginadas de esas
 * listas (con el número indicando el primer elemento y el tamaño de página).
 * 
 * Si un servicio Web devuelve una lista de una clase X, implementará un clase
 * del estilo XList extends ResponseList<X>, y tendrá que:
 * 
 * - Implementar de manera trivial los constructores sin parámetros,
 * con el parámetro List<X> y con List<X> y UriInfo, llamando al super.
 * - Añadir el @XmlRootElement(name="xxxx") al principio indicando qué
 * nombre queremos que aparezca en la raiz del XML cuando se haga el
 * marshall.
 * - Añadir un método para acceder a la propiedad de la lista, indicando
 * con una anotación el nombre que tendrá en el XML:
 * 
 * 	@XmlElement(name="submission")
 * public List<X> getSubmissions() {
 * 		return internalList;
 * 	} 
 * 
 * Además, si se quiere cambiar el número máximo de elementos que
 * el servicio web devolverá en la lista, por ejemplo en 10, se debe:
 * 
 *  - Implementar el método estático:
 *  
 *  public static int size(UriInfo request) {
 *  	return sizeFromUriClamped(request, 10);
 *  }
 *  
 *  - Implementar el método no estático:
 *  
 *  protected int getMaxResults() {
 *  	return 10;
 *  }
 *  
 *  Obviamente, para evitar el número mágico lo mejor es declarar
 *  una variable estática final con él...	
 * 
 * @author Marco Antonio Gomez Martin
 */
public class ResponseList<T> {
	
	/** Constructor con la lista vacía. */
	public ResponseList() { internalList = new java.util.ArrayList<T>();}
	
	/** Constructor con una lista dada como parámetro. */
	public ResponseList(List<T> l) { internalList = l; }
	
	/**
	 * Constructor del objeto
	 * @param l Lista que contendrá (puede ser recortado el último
	 * elemento).
	 * @param request Solicitud HTTP que se utilizó para generar la lista.
	 * De esta forma, utiliza los parámetros de la consulta HTTP
	 * start y size para por un lado comprobar si hay que truncar la lista
	 * (en ese caso, la lista del parámetro deberá tener únicamente
	 * un elemento más de los solicitados) e inicializar sus
	 * atributos prevLink y nextLink.
	 */
	public ResponseList(List<T> l, UriInfo request) {
		
		if (request == null) {
			internalList = l;
			return;
		}
		
		int start = startFromUri(request);
		int size;
		// Le quitamos 1 porque devuelve lo solicitado a la BBDD,
		// no lo pedido por el usuario.
		size = sizeFromUriClamped(request, getMaxResults()) - 1;
		
		boolean hayNext = false;
		boolean hayAnt = start > 0;
		if (l.size() > size) {
			// Hay siguiente; quitamos el último
			l.remove(l.size() - 1);
			hayNext = true;
		}
		
		// Montamos
		internalList = l;
		
		if (hayNext) {
			
			UriBuilder builder = request.getRequestUriBuilder();//Config.getUriBuilder();
			builder.path(request.getRequestUri().getPath());
			builder.queryParam("start", (start + size) + 1);
			builder.queryParam("size", size);
			this.setNextLink(builder.build().toString());
		}
		
		if (hayAnt) {
			
			UriBuilder builder =  request.getRequestUriBuilder();//Config.getUriBuilder();
			builder.path(request.getRequestUri().getPath());
			int nuevoStart = start - size;
			if (nuevoStart < 0)
				nuevoStart = 0;
			if (nuevoStart + size > start)
				size = start - nuevoStart;
			builder.queryParam("start", nuevoStart + 1);
			builder.queryParam("size", size);
			this.setPrevLink(builder.build().toString());
		}
	}
	
	String nextLink;
	
	String prevLink;
	
	@XmlElement(name="nextLink")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}

	@XmlElement(name="prevLink")
	public String getPrevLink() {
		return prevLink;
	}

	public void setPrevLink(String prevLink) {
		this.prevLink = prevLink;
	}

	public void setList(List<T> list) {
		this.internalList = list;
	}

	protected List<T> internalList;
/*	
	public static void extendQuery(Query q, UriInfo request) {
		
		q.setFirstResult(startFromUri(request));
		q.setMaxResults(size(request));
	}
*/	
	/**
	 * Devuelve el comienzo que hay que pasar a las consultas del modelo,
	 * extraídas a partir del análisis de la URI.
	 * Puede generar una excepción de BAD_REQUEST si el parámetro
	 * start no es un número bien formado.
	 * @param request
	 * @return
	 */
	public static int start(UriInfo request) {
		return startFromUri(request);
		//return getStartPoint(request);
	}
	
	/**
	 * Devuelve el parámetro "size" que hay que pasar a las consultas
	 * del modelo para que la gestión de la lista interna y de los
	 * enlaces funcione, teniendo en cuenta lo solicitado en la URI.
	 * 
	 * Las clases hija pueden cambiar el método si quieren limitar
	 * el tamaño máximo de las listas independientemente de la
	 * solicitud, utilizando el método sizeFromUriClamped(uri, maxSize),
	 * especificando un parámetro maxSize distinto. En ese caso
	 * para que todo siga funcionando se deberá también sobreescribir
	 * el método getMaxResults() para que concuerde con el
	 * maxSize pasado a sizeFromUriChanged..
	 *
	 * @param request
	 * @return
	 */
	public static int size(UriInfo request) {
		return sizeFromUriClamped(request, MAX_RESULTS);
	}
	
	/**
	 * Zero-based, aunque en la uri sea 1-based :)
	 * @param request
	 * @return
	 */
	protected static int startFromUri(UriInfo request) {
		String start_str = request.getQueryParameters().getFirst("start");
		
		try {
			if (start_str == null)
				return 0;
			else {
				int ret = Integer.parseInt(start_str) - 1;
				if (ret < 0)
					throw new WebApplicationException(Response.Status.BAD_REQUEST);
				return ret;
			}
		} catch (NumberFormatException ex) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
	}
	
	/**
	 * Devuelve el tamaño de la consulta que hay que pasarle a la base de
	 * datos para que todo funcione. En concreto, comprueba el parámetro
	 * size de la URI y si es más grande que maxSize lo ignora y trabaja
	 * con maxSize. Si no hay parámetro en la URI, usa maxSize.
	 * 
	 * Devuelve en realidad UNO M�?S que lo indicado, para utilizar el
	 * auxiliar para saber si se debe proporcionar un puntero al
	 * siguiente o no.
	 * 
	 * @param request
	 * @param maxSize
	 * @return
	 */
	protected static int sizeFromUriClamped(UriInfo request, int maxSize) {
		String size_str = request.getQueryParameters().getFirst("size");
		
		try {
			if (size_str == null)
				return maxSize + 1;
			else {
				int ret = Integer.parseInt(size_str);
				if (ret > maxSize)
					ret = maxSize;
				return ret + 1;
			}
		} catch (NumberFormatException ex) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
	}

	protected int getMaxResults() {
		return MAX_RESULTS;
	}
	
	private static final int MAX_RESULTS = 500;
	
}
