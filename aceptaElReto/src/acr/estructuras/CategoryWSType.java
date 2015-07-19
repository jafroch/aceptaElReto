package acr.estructuras;

import java.util.List;

import org.simpleframework.xml.Element;
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
	public List<CategoryWSType> subcats_lista;
	//public CategoryWSType[] subcats;

	public List<CategoryWSType> getSubcats() {
		return subcats_lista;
	}

	public void setSubcats(List<CategoryWSType> subcats) {
		this.subcats_lista = subcats;
	}

	public List<CategoryWSType> getPath() {
		return path_lista;
	}

	public void setPath(List<CategoryWSType> path) {
		this.path_lista = path;
	}


	/** Lista de problemas directamente pertenecientes a la categoría. */
	@Element(name="problems", required = false)
	public ProblemDetailsList problems;
	
	/** 
	 * Ruta desde la raíz del árbol hasta esta categoría.
	 * El primer elemento es la categoría raíz. El último es la
	 * categoría PADRE al objeto. Contienen únicamente id y name.
	 */
	@Element(name="path", required = false)
	public List<CategoryWSType> path_lista;
	//public CategoryWSType[] path;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ProblemDetailsList getProblems() {
		return problems;
	}

	public void setProblems(ProblemDetailsList problems) {
		this.problems = problems;
	}

	public Integer getNumOfProblems() {
		return numOfProblems;
	}

	public void setNumOfProblems(Integer numOfProblems) {
		this.numOfProblems = numOfProblems;
	}

	public Integer getNumOfAllProblems() {
		return numOfAllProblems;
	}

	public void setNumOfAllProblems(Integer numOfAllProblems) {
		this.numOfAllProblems = numOfAllProblems;
	}

	public Integer getTotalSubs() {
		return totalSubs;
	}

	public void setTotalSubs(Integer totalSubs) {
		this.totalSubs = totalSubs;
	}

	public Integer getAc() {
		return ac;
	}

	public void setAc(Integer ac) {
		this.ac = ac;
	}

	public Integer getPe() {
		return pe;
	}

	public void setPe(Integer pe) {
		this.pe = pe;
	}

	public Integer getWa() {
		return wa;
	}

	public void setWa(Integer wa) {
		this.wa = wa;
	}

	public Integer getTl() {
		return tl;
	}

	public void setTl(Integer tl) {
		this.tl = tl;
	}

	public Integer getMl() {
		return ml;
	}

	public void setMl(Integer ml) {
		this.ml = ml;
	}

	public Integer getOl() {
		return ol;
	}

	public void setOl(Integer ol) {
		this.ol = ol;
	}

	public Integer getRf() {
		return rf;
	}

	public void setRf(Integer rf) {
		this.rf = rf;
	}

	public Integer getRte() {
		return rte;
	}

	public void setRte(Integer rte) {
		this.rte = rte;
	}

	public Integer getCe() {
		return ce;
	}

	public void setCe(Integer ce) {
		this.ce = ce;
	}

	public Integer getIr() {
		return ir;
	}

	public void setIr(Integer ir) {
		this.ir = ir;
	}

	public Integer getC() {
		return c;
	}

	public void setC(Integer c) {
		this.c = c;
	}

	public Integer getCpp() {
		return cpp;
	}

	public void setCpp(Integer cpp) {
		this.cpp = cpp;
	}

	public Integer getJava() {
		return java;
	}

	public void setJava(Integer java) {
		this.java = java;
	}
	
}
