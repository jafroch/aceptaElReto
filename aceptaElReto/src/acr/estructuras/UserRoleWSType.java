/**
 * 
 */
package acr.estructuras;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase utilizada por los servicios web para devolver o recibir
 * información sobre el perfile/role de un usuario.
 * 
 * @author Marco Antonio Gomez Martin
 */
@XmlRootElement(name="role")
@XmlEnum
public enum UserRoleWSType {

	// IMPORTANTE: no deben cambiarse para que el marshalling
	// no se vea alterado...
	
	STUDENT,
	TEACHER,
	PROBLEM_AUTHOR,
	ADMIN,
	DAEMON
	;
}
