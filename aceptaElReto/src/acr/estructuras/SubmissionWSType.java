package acr.estructuras;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Clase usada por los WS cuando tienen que responder con
 * informaci�n de un env�o. No contiene la informaci�n "comprometida"
 * de la misma, sino �nicamente la informaci�n p�blica.
 * 
 * Tiene algunos atributos Integer para poder hacerlos null y
 * no darlos. De esta forma admite dar informaci�n de submissions
 * con m�s o menos detalle. En concreto:
 * 
 * - Para el usuario del env�o, permite no indicarlo, indicar su
 * id o indicar una informaci�n m�nima (id y nombre).
 * - Para el problema, permite no indicarlo, indicar su id o
 * indicar una informaci�n m�nima (id y nombre).
 * 
 * De esta forma, los servicios web que devuelven la lista de
 * env�os de un usuario determinado, pueden obviar la informaci�n
 * del usuario. Los que devuelven la lista de env�os de un problema
 * pueden eliminar la parte del problema pero detallar el usuario.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="submission")
//@XmlType(propOrder={"num", "userId", "user", "problemId", "problem","result", "additionalInfo", "language", "executionTime","memoryUsed", "ranking", "submissionDate", "comment"})
public class SubmissionWSType {
	
	// N�mero de env�o
	public int num;

	// Info m�nima del problema que intenta resolver
	public ProblemWSType problem;
	
	// Id del problema que intenta resolver
	public Integer problemId;

	// Info m�nima del usuario que hace el env�o
	public UserWSType user;
	
	// Identificador del usuario que hizo el env�o
	public Integer userId;

	// Resultado del env�o
	public SubmissionResultWSType result;
	
	// Mensaje adicional del env�o
	public String additionalInfo;
	
	// Lenguaje del env�o
	public LanguageWSType language;
	
	// Tiempo de ejecuci�n (si termin�...)
	// Es un Float para que pueda ser null.
	public Float executionTime;
	
	// Memoria consumida (si se ejecut�...) en Kbs.
	// Es un Integer para que pueda ser null.
	public Integer memoryUsed;
	
	// Posici�n en el ranking de env�os en el momento
	// del env�o.
	public Integer ranking;
	
	// Comentario realizado por el usuario al env�o
	public String comment;
	
	// Hora del env�o
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

