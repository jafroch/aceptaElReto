/**
 * 
 */
package acr.estructuras;


import org.simpleframework.xml.Root;

/**
 * Clase utilizada por los servicios web para devolver o recibir
 * información sobre el perfile/role de un usuario.
 * 
 * @author Marco Antonio Gomez Martin
 */
@Root(name="role")
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
