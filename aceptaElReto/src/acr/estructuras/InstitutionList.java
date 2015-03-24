package acr.estructuras;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//import es.acr.model.institutions.Institution;
//import es.acr.ws.responses.casttomodel.InstitutionWSTypeBuilder;
import acr.estructuras.ResponseList;

/**
 * Clase utilizada para devolver una lista de instituciones.
 * 
 * @author Marco Antonio Gomez Martin
 */
@XmlRootElement(name="institutions")
public class InstitutionList extends ResponseList<InstitutionWSType> {
	
	public static InstitutionList buildFromInstitutionList(List<InstitutionWSType> model, UriInfo uri) {
		List<InstitutionWSType> l = new ArrayList<InstitutionWSType>(model.size());
		for (InstitutionWSType o : model) {
			l.add(new InstitutionWSTypeBuilder(o).build());
		}
		return new InstitutionList(l, uri);
	}
	
	public InstitutionList() {}
	public InstitutionList(List<InstitutionWSType> l) { super(l); }
	public InstitutionList(List<InstitutionWSType> l, UriInfo uri) { super(l, uri); }

	@XmlElement(name="institution")
	public List<InstitutionWSType> getInstitutions() {
		return internalList;
	}
}
