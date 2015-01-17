package es.acr.ws.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa un país.
 *
 * @author Marco Antonio Gomez Martin
 */
@XmlRootElement(name="institution")
@XmlType(propOrder={"id", "name", "country", "url", "logo"})
public class InstitutionWSType {
	
	// Identificador para referenciarlo rápidamente
	@XmlElement
	public int id;
	
	// Nombre de la institución
	@XmlElement
	public String name;
	
	// Pais
	@XmlElement
	public CountryWSType country;
	
	// URL
	@XmlElement(name="url")
	public String url;
	
	/** Ruta al fichero con el logo. */
	@XmlElement(name="logo")
	public String logo;

	/** Constructor sin parámetros. No inicializa nada...
	 * Está para que funcione el marshall/unmarshall.
	 */
	public InstitutionWSType() {}
}
