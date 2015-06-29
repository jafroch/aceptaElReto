package acr.estructuras;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Clase que representa un país.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="country")
//@XmlType(propOrder={"code", "nombre", "name"})
public class CountryWSType {
	
	/** Código ISO */
	@Element(name="code")
	public String code;
	
	/** Nombre en español */
	@Element(name="nombre")
	public String nombre;
	
	/** Nombre en inglés */
	@Element(name="name")
	public String name;
	
	/** Constructor sin parámetros. No inicializa nada...
	 * Está para que funcione el marshall/unmarshall.
	 */
	public CountryWSType() {}
}
