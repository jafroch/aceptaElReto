package acr.estructuras;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa un país.
 *
 * @author Marco Antonio Gomez Martin
 */
@XmlRootElement(name="country")
@XmlType(propOrder={"code", "nombre", "name"})
public class CountryWSType {
	
	/** Código ISO */
	@XmlElement(name="code")
	public String code;
	
	/** Nombre en español */
	@XmlElement(name="nombre")
	public String nombre;
	
	/** Nombre en inglés */
	@XmlElement(name="name")
	public String name;
	
	/** Constructor sin parámetros. No inicializa nada...
	 * Está para que funcione el marshall/unmarshall.
	 */
	public CountryWSType() {}
}
