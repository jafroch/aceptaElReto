package acr.estructuras;

import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;



/**
 * Clase utilizada por los WS para devolver información de un usuario.
 * Dependiendo del WS concreto, rellenará más o menos información.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="user")
//@XmlType(propOrder={"id", "nick", "email", "name", "gender", "country", "institution", "mailNotification", "role", "birthday", "registrationDate", "accessList", "avatar"})
public class UserWSType {

	// Identificador del usuario.
	@Element(name="id")
	public Integer id;
	
	// Nick utilizado para hacer login.
	@Element(name="nick")
	public String nick;

	// Dirección de correo electrónico
	@Element(name="email")
	public String email;
	
	// Nombre completo
	@Element(name="name")
	public String name;
	
	// Género
	@Element(name="gender")
	public UserGenderWSType gender;	
		
	// País
	@Element(name="country")
	public CountryWSType country;

	// Institución a la que pertenece
	@Element(name="institution")
	public String institution;
		
	// Tipo de notificación por mail utilizada
	@Element(name="mailNotification")
	public MailNotificationWSType mailNotification;
	
	// Role del usuario
	@Element(name="role")
	public UserRoleWSType role;

	// Fecha de nacimiento
	@Element(name="birthday")
	public Date birthday;
	
	// Fecha de registro
	@Element(name="registrationDate")
	public Date registrationDate;
	
	// Lista de permisos
	@Element(name="accessList")
	public List<String> accessList;
	
	/** Ruta (URL) al fichero con el logo. */
	@Element(name="avatar")
	public String avatar;
	
}
