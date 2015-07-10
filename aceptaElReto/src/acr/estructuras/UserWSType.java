package acr.estructuras;

import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
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
	@Element(name="email", required = false)
	public String email;
	
	// Nombre completo
	@Element(name="name",required = false)
	public String name;
	
	// Género
	@Element(name="gender",required = false)
	public UserGenderWSType gender;	
		
	// País
	@Element(name="country",required = false)
	public CountryWSType country;

	// Institución a la que pertenece
	@Element(name="institution",required = false)
	public String institution;
		
	// Tipo de notificación por mail utilizada
	@Element(name="mailNotification",required = false)
	public MailNotificationWSType mailNotification;
	
	// Role del usuario
	@Element(name="role",required = false)
	public UserRoleWSType role;

	// Fecha de nacimiento
	@Element(name="birthday",required = false)
	public Date birthday;
	
	// Fecha de registro
	@Element(name="registrationDate",required = false)
	public Date registrationDate;
	
	// Lista de permisos
	@ElementList(name = "accessList",required = false)
	public List<String> accessList;
	
	/** Ruta (URL) al fichero con el logo. */
	@Element(name="avatar",required = false)
	public String avatar;
	
}
