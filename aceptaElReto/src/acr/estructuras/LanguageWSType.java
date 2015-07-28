package acr.estructuras;

import org.simpleframework.xml.Root;

/**
 * Tipo utilizado por los servicios web para indicar un
 * lenguaje de programaci�n soportado por ACR.
 * 
 * @author Marco Antonio Gomez Martin
 */
@Root(name = "Language")
public enum LanguageWSType {

	// IMPORTANTE: no sobrepases los tres caracteres en el acr�nimo;
	// el acr�nimo es la 'clave' en la BBDD y hay hueco para tres char.
	/* ANSI C */ C,
	/* C++ */ CPP,
	/* Java */ JAVA
	;
}