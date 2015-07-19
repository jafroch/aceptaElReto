package acr.estructuras;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;



/**
 * Clase que representa un pa�s.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="institution")

public class InstitutionWSType {
	
	// Identificador para referenciarlo r�pidamente
	@Element(name="id")
	public int id;
	
	// Nombre de la instituci�n
	@Element(name="name")
	public String name;
	
	// Pais
	@Element(name="country",required = false)
	public CountryWSType country;
	
	// URL
	@Element(name="url", required = false)
	public String url;
	
	/** Ruta al fichero con el logo. */
	@Element(name="logo", required = false)
	public String logo;

	/** Constructor sin par�metros. No inicializa nada...
	 * Est� para que funcione el marshall/unmarshall.
	 */
	public InstitutionWSType() {}
}