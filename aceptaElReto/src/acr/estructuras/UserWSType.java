package acr.estructuras;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase utilizada por los WS para devolver información de un usuario.
 * Dependiendo del WS concreto, rellenará más o menos información.
 *
 * @author Marco Antonio Gomez Martin
 */
@XmlRootElement(name="user")
@XmlType(propOrder={"id", "nick", "email", "name", "gender", "country", "institution", "mailNotification", "role", "birthday", "registrationDate", "accessList", "avatar"})
public class UserWSType {

	// Identificador del usuario.
	public Integer id;
	
	// Nombre completo
	public String name;
	
	// Nick utilizado para hacer login.
	public String nick;

	// Género
	public UserGenderWSType gender;

	// Dirección de correo electrónico
	public String email;
	
	// País
	public CountryWSType country;

	// Role del usuario
	public UserRoleWSType role;
	
	// Tipo de notificación por mail utilizada
	public MailNotificationWSType mailNotification;
	
	// Institución a la que pertenece
	public InstitutionWSType institution;

	// Fecha de nacimiento
	public Date birthday;
	
	// Fecha de registro
	public Date registrationDate;
	
	// Lista de permisos
	public List<String> accessList;
	
	/** Ruta (URL) al fichero con el logo. */
	public String avatar;
	
}
