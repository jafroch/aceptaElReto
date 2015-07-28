package acr.estructuras;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Clase que representa el resultado de un env�o.
 *
 * @author Marco Antonio Gomez Martin
 */
@Root(name="submissionResult")
public enum SubmissionResultWSType {

	// IMPORTANTE: los s�mbolos aqu� utilizados est�n
	// cableados en otras partes de los servicios web
	// (por ejemplo, en las estad�sticas de los problemas),
	// as� que no deben cambiarse, para que el marshalling
	// resultado sea coherente con esos otros servicios web.
	
	/** Esperando a que un juez lo evalue */ IQ, 
	/** Soluci�n correcta */ AC,
	/** Soluci�n con problemas de formato */ PE,
	/** Soluci�n incorrecta */ WA,
	/** Limite de tiempo superado */ TL,
	/** L�mite de memoria superado */ ML,
	/** L�mite de salida superado */ OL,
	/** Uso de funci�n no permitida */ RF,
	/** Error de ejecuci�n */ RTE,
	/** Error de compilaci�n */ CE,
	/** Error interno... */ IR;
	
}