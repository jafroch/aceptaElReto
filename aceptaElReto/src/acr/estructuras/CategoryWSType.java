package acr.estructuras;

import java.util.List;
import org.simpleframework.xml.Root;

/**
 * Clase usada por los WS cuando tienen que responder con
 * información de una categoría.
 * 
 * Tiene todos los atributos imaginables sobre categorías,
 * aunque dependiendo del WS concreto, se rellenarán más
 * o menos.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="category")
public class CategoryWSType {
	
	/**
	 * Vacía todos los campos (los pone a null), excepto
	 * las subcategorías. Útil para WS que utilizan una
	 * categoría dummy para devolver una lista de categorías.
	 */
	public void vaciaInfo() {
		this.id = null;
		this.name = this.desc = null;
		this.numOfProblems = this.numOfAllProblems = this.totalSubs = null;

		this.ac = this.pe = this.wa = this.tl = this.ml = this.ol = this.rf = this.rte = this.ce = this.ir = null; 

		this.c = this.cpp = this.java = null;
	}
	
	
	/** Identificador de la categoría */
	public Integer id;
	
	/** Nombre de la categoría */
	public String name;
	
	/** Descripción de la categoría */
	public String desc;
	
	/** Lista de categorías hijas */
	public List<CategoryWSType> subcats;

	/** Lista de problemas directamente pertenecientes a la categoría. */
	public ProblemDetailsList problems;
	
	/** 
	 * Ruta desde la raíz del árbol hasta esta categoría.
	 * El primer elemento es la categoría raíz. El último es la
	 * categoría PADRE al objeto. Contienen únicamente id y name.
	 */
	public List<CategoryWSType> path;
	

	// ////
	// Estadísticas. Son mantenidas por un proceso en segundo plano.
	// Por tanto, deben verse como un dato "volatil" y que puede
	// no estar actualizado. Incluso el número de problemas puede
	// ser incorrecto, si se añadieron más problemas tras la
	// última actualización de las estadísticas.
	// ////
	
	// ////
	// Estadísticas de la categoría (sin incluir subcategorías)
	// ////
	
	/** Número de problemas pertenecientes directamente a
	la categoría */
	public Integer numOfProblems;
	
	// ////
	// Estadísticas de la categoría y subcategorías
	// ////
	
	/** Número de problemas en la categoría y subcategorías. */
	public Integer numOfAllProblems;

	// ///
	// Estadísticas de veredictos
	// ///
	
	/** Envíos totales del problema */
	public Integer totalSubs;

	/** Número de envíos aceptados */
	public Integer ac;

	/** Número de envíos con PE */
	public Integer pe;

	/** Número de envíos con WA */
	public Integer wa;

	/** Número de envíos con TL */
	public Integer tl;

	/** Número de envíos con ML */
	public Integer ml;

	/** Número de envíos con OL */
	public Integer ol;

	/** Número de envíos con RF */
	public Integer rf;

	/** Número de envíos con RTE */
	public Integer rte;

	/** Número de envíos con CE */
	public Integer ce;

	/** Número de envíos con IR */
	public Integer ir;

	// ////
	// Estadísticas de lenguajes
	// ////
	
	/** Número de envíos en C */
	public Integer c;
	
	/** Número de envíos en C++ */
	public Integer cpp;
	
	/** Número de envíos en Java */
	public Integer java;	

	/** Constructor sin parámetros. No inicializa nada...
	 * Está para que funcione el marshall/unmarshall.
	 */
	public CategoryWSType() {}
}
