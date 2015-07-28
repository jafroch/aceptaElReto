package acr.estructuras;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Clase que representa el resultado de un envío.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="submissionResult")
public enum SubmissionResultWSType {

	// IMPORTANTE: los símbolos aquí utilizados están
	// cableados en otras partes de los servicios web
	// (por ejemplo, en las estadísticas de los problemas),
	// así que no deben cambiarse, para que el marshalling
	// resultado sea coherente con esos otros servicios web.
	
	/** Esperando a que un juez lo evalue */ IQ, 
	/** Solución correcta */ AC,
	/** Solución con problemas de formato */ PE,
	/** Solución incorrecta */ WA,
	/** Limite de tiempo superado */ TL,
	/** Límite de memoria superado */ ML,
	/** Límite de salida superado */ OL,
	/** Uso de función no permitida */ RF,
	/** Error de ejecución */ RTE,
	/** Error de compilación */ CE,
	/** Error interno... */ IR;
	
}