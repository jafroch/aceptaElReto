package acr.estructuras;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Clase usada por los WS cuando tienen que responder con
 * informaci�n de una categor�a.
 * 
 * Tiene todos los atributos imaginables sobre categor�as,
 * aunque dependiendo del WS concreto, se rellenar�n m�s
 * o menos.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="category")
public class CategoryWSType {
	
	/**
	 * Vac�a todos los campos (los pone a null), excepto
	 * las subcategor�as. �til para WS que utilizan una
	 * categor�a dummy para devolver una lista de categor�as.
	 */
	public void vaciaInfo() {
		this.id = null;
		this.name = this.desc = null;
		this.numOfProblems = this.numOfAllProblems = this.totalSubs = null;

		this.ac = this.pe = this.wa = this.tl = this.ml = this.ol = this.rf = this.rte = this.ce = this.ir = null; 

		this.c = this.cpp = this.java = null;
	}
	
	
	/** Identificador de la categor�a */
	public Integer id;
	
	/** Nombre de la categor�a */
	public String name;
	
	/** Descripci�n de la categor�a */
	public String desc;
	
	/** Lista de categor�as hijas */
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


	/** Lista de problemas directamente pertenecientes a la categor�a. */
	@Element(name="problems", required = false)
	public ProblemDetailsList problems;
	
	/** 
	 * Ruta desde la ra�z del �rbol hasta esta categor�a.
	 * El primer elemento es la categor�a ra�z. El �ltimo es la
	 * categor�a PADRE al objeto. Contienen �nicamente id y name.
	 */
	@Element(name="path", required = false)
	public List<CategoryWSType> path_lista;
	//public CategoryWSType[] path;

	// ////
	// Estad�sticas. Son mantenidas por un proceso en segundo plano.
	// Por tanto, deben verse como un dato "volatil" y que puede
	// no estar actualizado. Incluso el n�mero de problemas puede
	// ser incorrecto, si se a�adieron m�s problemas tras la
	// �ltima actualizaci�n de las estad�sticas.
	// ////
	
	// ////
	// Estad�sticas de la categor�a (sin incluir subcategor�as)
	// ////
	
	/** N�mero de problemas pertenecientes directamente a
	la categor�a */
	public Integer numOfProblems;
	
	// ////
	// Estad�sticas de la categor�a y subcategor�as
	// ////
	
	/** N�mero de problemas en la categor�a y subcategor�as. */
	public Integer numOfAllProblems;

	// ///
	// Estad�sticas de veredictos
	// ///
	
	/** Env�os totales del problema */
	public Integer totalSubs;

	/** N�mero de env�os aceptados */
	public Integer ac;

	/** N�mero de env�os con PE */
	public Integer pe;

	/** N�mero de env�os con WA */
	public Integer wa;

	/** N�mero de env�os con TL */
	public Integer tl;

	/** N�mero de env�os con ML */
	public Integer ml;

	/** N�mero de env�os con OL */
	public Integer ol;

	/** N�mero de env�os con RF */
	public Integer rf;

	/** N�mero de env�os con RTE */
	public Integer rte;

	/** N�mero de env�os con CE */
	public Integer ce;

	/** N�mero de env�os con IR */
	public Integer ir;

	// ////
	// Estad�sticas de lenguajes
	// ////
	
	/** N�mero de env�os en C */
	public Integer c;
	
	/** N�mero de env�os en C++ */
	public Integer cpp;
	
	/** N�mero de env�os en Java */
	public Integer java;	

	/** Constructor sin par�metros. No inicializa nada...
	 * Est� para que funcione el marshall/unmarshall.
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