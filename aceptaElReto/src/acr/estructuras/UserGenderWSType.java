/**
 * 
 */
package acr.estructuras;

import org.simpleframework.xml.Root;

/**
 * Género de un usuario
 * 
 * @author Marco Antonio Gomez Martin
 *
 */
@Root(name = "Gender")
public enum UserGenderWSType {
	
	MALE,
	FEMALE
	;
}
