package acr.estructuras;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Clase usada por los WS cuando tienen que responder con
 * información de un envío. No contiene la información "comprometida"
 * de la misma, sino únicamente la información pública.
 * 
 * Tiene algunos atributos Integer para poder hacerlos null y
 * no darlos. De esta forma admite dar información de submissions
 * con más o menos detalle. En concreto:
 * 
 * - Para el usuario del envío, permite no indicarlo, indicar su
 * id o indicar una información mínima (id y nombre).
 * - Para el problema, permite no indicarlo, indicar su id o
 * indicar una información mínima (id y nombre).
 * 
 * De esta forma, los servicios web que devuelven la lista de
 * envíos de un usuario determinado, pueden obviar la información
 * del usuario. Los que devuelven la lista de envíos de un problema
 * pueden eliminar la parte del problema pero detallar el usuario.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="submission")
//@XmlType(propOrder={"num", "userId", "user", "problemId", "problem","result", "additionalInfo", "language", "executionTime","memoryUsed", "ranking", "submissionDate", "comment"})
public class SubmissionWSType {
	
	// Número de envío
	public int num;

	// Info mínima del problema que intenta resolver
	public ProblemWSType problem;
	
	// Id del problema que intenta resolver
	public Integer problemId;

	// Info mínima del usuario que hace el envío
	public UserWSType user;
	
	// Identificador del usuario que hizo el envío
	public Integer userId;

	// Resultado del envío
	public SubmissionResultWSType result;
	
	// Mensaje adicional del envío
	public String additionalInfo;
	
	// Lenguaje del envío
	public LanguageWSType language;
	
	// Tiempo de ejecución (si terminó...)
	// Es un Float para que pueda ser null.
	public Float executionTime;
	
	// Memoria consumida (si se ejecutó...) en Kbs.
	// Es un Integer para que pueda ser null.
	public Integer memoryUsed;
	
	// Posición en el ranking de envíos en el momento
	// del envío.
	public Integer ranking;
	
	// Comentario realizado por el usuario al envío
	public String comment;
	
	// Hora del envío
	public Date submissionDate;
	
	
	
	public String toStringVerbose() {
		StringBuilder ret = new StringBuilder();
		ret.append("ID: " + num + "\n");
		if (problem != null) {
			ret.append("PID: " + problem.num + "\n");
			ret.append("TITLE: " + problem.title + "\n");
		} else if (problemId != null) {
			ret.append("PID: " + problemId.intValue() + "\n");
		}
		if (user != null) {
			ret.append("UID: " + user.id + "\n");
			ret.append("USER: " + user.nick + "\n");
		} else if (userId != null) {
			ret.append("UID: " + userId.intValue() + "\n");
		}
		if (result != null)
			ret.append("RES: " + result + "\n");
		if (executionTime != null)
			ret.append("TIME: " + executionTime + "\n");
		if (memoryUsed != null)
			ret.append("MEM: " + memoryUsed + "\n");
		if (language != null)
			ret.append("LANG: " + language + "\n");
		if (additionalInfo != null)
			ret.append("INFO: " + additionalInfo + "\n");
		if (comment != null)
			ret.append("COMMENT: " + comment + "\n");
		return ret.toString();
	}
}

