package acr.estructuras;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que representa un problema.
 *
 * @author Marco Antonio Gomez Martin
 */
@XmlRootElement(name="problem")
public class ProblemWSType {

	// Código/número de problema
	@XmlElement(name="num")	
	public Integer num;

	// Nombre del problema
	public String title;

	// Ruta absoluta donde están los ficheros visibles de un
	// problema:
	
	// Fichero PDF con el enunciado.
	public String pdfLink;
	
	// Fichero .zip con los samples del enunciado.
	public String samplesLink;
	
	// Fichero con el index.html
	public String htmlLink;
	
	// Fichero con el html "embebible" en otro html
	public String htmlEmLink;
	
	// Fichero con el html "embebible" con los créditos
	public String htmlEmCreditsLink;
	
	// Directorio 'base' de todos los ficheros
	// de los enunciados. Solo es establecido en situaciones
	// muy concretas (cuando el problema está 'naciendo'
	// y el cliente que llama al WS canProcessDrafts (es decir
	// es el que montará esos enunciados)
	public String statementsRoot;
	
	/** Categoría-volumen al que pertenece el problema. */
	public CategoryWSType volume;
	
	// ///
	// Estadísticas de veredictos
	// ///
	
	// Envíos totales del problema
	public Integer totalSubs;

	// Número de usuarios que han hecho algún envío
	public Integer totalUsers;
	
	// Número de envíos aceptados
	public Integer ac;

	// Número de usuarios (distintos) con AC
	public Integer dacu;

	// Número de envíos con PE
	public Integer pe;

	// Número de envíos con WA
	public Integer wa;

	// Número de envíos con TL
	public Integer tl;

	// Número de envíos con ML
	public Integer ml;

	// Número de envíos con OL
	public Integer ol;

	// Número de envíos con RF
	public Integer rf;

	// Número de envíos con RTE
	public Integer rte;

	// Número de envíos con CE
	public Integer ce;

	// Número de envíos con IR
	public Integer ir;

	// ////
	// Estadísticas de lenguajes
	// ////
	
	// Número de envíos en C
	public Integer c;
	
	// Número de envíos en C++
	public Integer cpp;
	
	// Número de envíos en Java
	public Integer java;

}
