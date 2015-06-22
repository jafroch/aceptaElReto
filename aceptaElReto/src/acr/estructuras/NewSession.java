package acr.estructuras;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;

/**
 * Clase devuelta por el servicio Web de login para
 * indicar la clave de sesión, así como información
 * adicional sobre el usuario.
 *
 * @author Marco Antonio Gomez Martin
 */
@XmlRootElement(name="sessionInfo")
public class NewSession {

	// Token de la sesión
	@Element
	public String token;
	
	// Id del usuario
	@Element
	public int id;
	
	// Nombre completo
	@Element
	public String name;
	
	// Role del usuario
	@Element
	public UserRoleWSType role;
	
	// Nick utilizado para hacer login.
	@Element
	public String nick;
	
	// Lista de permisos
	@ElementListUnion(value = { @ElementList })
	public List<String> accessList;
	
}
