/**
 * 
 */
package es.acr.ws.responses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Género de un usuario
 * 
 * @author Marco Antonio Gomez Martin
 *
 */
@XmlType(name = "Gender")
@XmlEnum
public enum UserGenderWSType {
	
	MALE,
	FEMALE
	;
}
