package acr.estructuras;

import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;



/**
 * Clase utilizada por los WS para devolver informaci�n de un usuario.
 * Dependiendo del WS concreto, rellenar� m�s o menos informaci�n.
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

	// Direcci�n de correo electr�nico
	@Element(name="email", required = false)
	public String email;
	
	// Nombre completo
	@Element(name="name",required = false)
	public String name;
	
	// G�nero
	@Element(name="gender",required = false)
	public UserGenderWSType gender;	
		
	// Pa�s
	@Element(name="country",required = false)
	public CountryWSType country;

	// Instituci�n a la que pertenece
	@Element(name="institution",required = false)
	public InstitutionWSType institution;
		
	// Tipo de notificaci�n por mail utilizada
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
	@ElementListUnion(value = { @ElementList })
	public List<String> accessList;
	
	/** Ruta (URL) al fichero con el logo. */
	@Element(name="avatar",required = false)
	public String avatar;
	
}
