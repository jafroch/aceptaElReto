package acr.estructuras;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;



/**
 * Clase que representa un país.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="institution")

public class InstitutionWSType {
	
	// Identificador para referenciarlo rápidamente
	@Element
	public int id;
	
	// Nombre de la institución
	@Element
	public String name;
	
	// Pais
	@Element
	public CountryWSType country;
	
	// URL
	@Element(name="url")
	public String url;
	
	/** Ruta al fichero con el logo. */
	@Element(name="logo")
	public String logo;

	/** Constructor sin parámetros. No inicializa nada...
	 * Está para que funcione el marshall/unmarshall.
	 */
	public InstitutionWSType() {}
}
