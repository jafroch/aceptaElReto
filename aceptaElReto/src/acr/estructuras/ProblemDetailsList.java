package acr.estructuras;

import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;



/**
 * Clase utilizada como respuesta en servicios web que devuelven
 * listas de problemas con su id, nombre/título y estadísticas.
 * 
 * @author Marco Antonio Gomez Martin
 */
@Root(name="problemList")
public class ProblemDetailsList extends ResponseList<ProblemWSType> {
	
	public ProblemDetailsList() {}
	public ProblemDetailsList(List<ProblemWSType> l) { super(l); }
	public ProblemDetailsList(List<ProblemWSType> l, UriInfo uri) { super(l, uri); }

	@Element(name="problem")
	public List<ProblemWSType> getProblems() {
		return internalList;
	}

	// Límites de la lista
	
	private static final int MAX_ELEMS = 100;
	
	public static int size(UriInfo request) {
		return sizeFromUriClamped(request, MAX_ELEMS);
	}
	
	// Devuelve -1 si no se especificó size en la uri.
	public static int sizeNoLimit(UriInfo request) {
		// -1 porque el método suma uno para la consulta de la BBDD...
		int ret = sizeFromUriClamped(request, Integer.MAX_VALUE - 1); 
		if (ret == Integer.MAX_VALUE)
			return -1;
		else
			return ret;
	}
	
	protected int getMaxResults() {
		return MAX_ELEMS;
	}
}
