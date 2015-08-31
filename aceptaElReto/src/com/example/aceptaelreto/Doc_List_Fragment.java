package com.example.aceptaelreto;

import ws.CallerWS;
import ws.Traductor;
import ws.WSquery;
import ws.WebServiceTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import acr.estructuras.CountryWSType;
import acr.estructuras.NewSession;
import acr.estructuras.ResponseList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
 /*
  * clase que genera el fragment de 
  */
public class Doc_List_Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
     
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    private String[] groups;
    private String[][] children;
    private static int listSelect;
    Bundle token;
 
    public static Doc_List_Fragment newInstance(int sectionNumber, String tk, int opc) {
    	Doc_List_Fragment fragment = new Doc_List_Fragment();
    	listSelect = opc;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("TOKEN", tk);
        fragment.setArguments(args);
        return fragment;
    }
    
    public Doc_List_Fragment() {
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.faq_layout, container, false);
        
        token = this.getArguments();
        
        expListView = (ExpandableListView) rootView.findViewById(R.id.faq_list);
        prepareListData(listSelect);
        listAdapter = new ExpandableListAdapter(getActivity(), groups, children);
        expListView.setAdapter(listAdapter);
        
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
    
    
    private void prepareListData(int opc) {
    	
    	switch(opc){
    	case 1:
    		groups = new String[] { "�Qu� es �Acepta el reto!?", "�Qu� tipo de problemas hay?", "�C�mo comprueba el juez en linea si una soluci�n es correcta?", 
        			"�Sobre qu� plataforma se ejecutan los env�os?","�Qu� compilador usa el juez?","�Qu� bibliotecas puedo utilizar en mis soluciones?",
        			"�Tengo que comprobar que la entrada es correcta?","�Puedo probar mi programa de alguna forma con la entrada de ejemplo del enunciado antes de mandarlo?",
        			"Cuando ejecuto mi soluci�n, �tengo que escribir toda la salida despu�s de leer toda la entrada?","En la entrada de ejemplo hay n�meros con decimales con el punto como separador, pero en mi ordenador s�lo funciona si uso la coma. �Qu� puedo hacer?",
        			"La �ltima linea de la salida, �debe terminar con un '\n'?","He enviado mi soluci�n y el juez me contesta <'Veredicto'>. �Qu� significa?",
        			"�Qu� restricciones en ejecuci�n tienen las soluciones enviadas?","�Qu� significa 'Tiempo m�ximo' y 'Memoria m�xima' en los enunciados de los problemas?",
        			"�Puedo ver el c�digo fuente de mis env�os anteriores?",
        			"�Pueden otros usuarios ver mi c�digo fuente, o puedo ver yo el de los dem�s?","�Para qu� sirve el campo 'comentario' de un env�o?",
        			"�Qu� significan las estad�sticas en las tablas de los problemas y las categor�as?","�Por qu� las categor�as a las que pertenece un problema no son visibles directamente en el enunciado del problema?",
        			"�Para qu� sirve la opci�n Recordar usuario en el formulario de login?"};

            children = new String [][] {
                { " �Acepta el reto! es un almac�n ('repositorio') de problemas de programaci�n en espa�ol, con un juez en l�nea incorporado. "
                		+ "Cualquier usuario puede resolver los problemas propuestos y enviar su soluci�n al juez para comprobar si es correcta." },
                { " Todos los problemas del almac�n son aplicaciones de consola que reciben datos por la entrada est�ndar y env�an "
                		+ "los resultados por la salida est�ndar. Desde el punto de vista de programaci�n, esto significa que deben leer del teclado y escribir texto en la pantalla.\n\n"
                		+"El objetivo es que los usuarios pongan en pr�ctica sus habilidades de programaci�n b�sica, de algoritmos y estructuras de datos. Por tanto, ninguno de los "
                		+ "problemas hace uso de interfaces gr�ficos de usuario, ni ficheros.\n\n"+"Dicho esto, el abanico de problemas es amplio y toca una gran variedad de temas. "
                		+ "Puedes comprobarlo recorriendo las categor�as." },
                { " Para cada problema, el juez dispone de un conjunto de casos de prueba que mantiene en secreto. Cuando recibe el c�digo de una supuesta soluci�n, lo compila y "
                		+ "lo ejecuta, envi�ndole por la entrada est�ndar los casos de prueba que posee. \n\n El programa escribir� en la salida est�ndar los resultados, que ser�n "
                		+ "comparados por el juez con los resultados correctos, especificados por el autor del problema. El veredicto emitido depender� del resultado de esa comparaci�n." },
                { " La evaluaci�n de los env�os se realiza en una plataforma Intel 80x86 (32 bits) sobre un sistema operativo GNU/Linux." },
                { " El juez en linea compila utilizando gcc para C/C++ y OpenJDK 1.7 para Java. La compilaci�n de C++ incluye el flag -std=c++11 para soporte del est�ndar de 2011." },
                { " Si programas en C/C++ podr�s hacer uso �nicamente de la biblioteca matem�tica, junto con cualquier otra biblioteca est�ndar que no requiera el enlazado de nada particular "
                		+ "para la construcci�n del ejecutable final. Ejemplos de ficheros de cabecera permitidos son stdio.h, stdlib.h, string.h y math.h. En el caso de C++, tambi�n"
                		+ " se puede hacer uso de la biblioteca est�ndar (iostream, vector, etc�tera).\n\n En Java, se puede hacer uso de cualquier paquete est�ndar salvo los relacionados "
                		+ "con el interfaz gr�fico (awt, swing, etc�tera). Tampoco se permite utilizar la clase java.lang.Thread.\n\n "
                		+ "Ten en cuenta que algunas bibliotecas o paquetes podr�an incluirse sin dar error de compilaci�n, pero luego fallar en ejecuci�n porque el juez no permita su uso." },
                { " No. El objetivo principal de la gran mayor�a de los problemas es que pongas en pr�ctica tus conocimientos de programaci�n, no que pierdas tiempo en detalles poco importantes "
                		+ "como la entrada.\n\n Si en el enunciado de un problema dice, por ejemplo, que cada caso de prueba estar� compuesto de un n�mero positivo, no necesitas comprobar que"
                		+ " es as�; al fin y al cabo, �qu� har�as si no lo fuera? Por tanto, puedes confiar plenamente en que la entrada seguir� el formato especificado y no preocuparte de "
                		+ "reaccionar dignamente si no es as�.\n\n La contrapartida es que el juez exige lo mismo en la salida. Tu programa debe ser totalmente estricto en el formato de la "
                		+ "salida. No a�adas texto adicional para ser 'amigable' con el usuario (tipo 'Introduce el siguiente n�mero'), porque despistar�n al juez y no dar� por bueno el resultado. "
                		+ "Sigue a pies juntillas el formato de salida, inclu�dos los espacios." },
                { " No es que puedas, �es que debes! Antes de enviar tu soluci�n al juez on-line deber�as probarla en tu ordenador para tener una cierta esperanza de que est� bien.\n\n Para eso,"
                		+ " puedes descargarte la entrada y salida de ejemplo del enunciado. Basta con que pases el rat�n por encima de los ejemplos, y ver�s aparecer una 'barra de herramientas' "
                		+ "para seleccionar todo (y poderlo copiar), o para descargarte un archivo comprimido con los dos ficheros (el ejemplo de la entrada y de la salida).\n\n A partir de ah�,"
                		+ " dependiendo de la opci�n que elijas, tendr�s que actuar de una manera u otra. Quiz� lo m�s sencillo sea lanzar tu programa en una consola, pegar el texto copiado de "
                		+ "la entrada y luego comprobar manualmente que la salida es correcta.\n\n La otra alternativa es que hagas uso de las redirecciones del shell. Por ejemplo en GNU/Linux: \n\n"
                		+ "$ ./a.out < sample.in > miSalida \n"
                		+"$ diff sample.out miSalida \n\n"
                		+"Consulta la documentaci�n de tu sistema operativo para obtener m�s informaci�n. Ten en cuenta que los ficheros de ejemplo descargados tienen los saltos de linea en modo "
                		+ "Unix, por lo que podr�as ver falsas diferencias que desaparecer�n al enviar tu c�digo al juez on-line." },
                { " �No! Todos los enunciados contienen un ejemplo de entrada y un ejemplo de salida que se separan por claridad y simplicidad. Pero las soluciones enviadas no tienen que leer toda "
                		+ "la entrada, procesarla y luego escribir toda la salida. �M�s bien al contrario! Hacerlo as� requerir�a que el programa utilizara m�s memoria para guardar toda la entrada "
                		+ "antes de empezar a hacer algo �til.\n\n Te animamos a que tus programas tengan, a alto nivel, un esquema similar a �ste: \n\n"
                		+ "mientras(leerSiguienteCasoDePrueba()) procesarCasoActual();\n\n De ese modo, si pruebas tu programa de manera interactiva (escribiendo los casos de prueba por teclado) "
                		+ "al introducir cada uno, el programa responder� inmediatamente sin esperar a leer los siguientes. Posteriormente, el juez on-line ser� capaz cuando pruebe tu c�digo de "
                		+ "independizar la entrada y la salida sin problema." },
                { " Algunos problemas tienen que leer de la entrada n�meros con decimales, y en el ejemplo de entrada los n�meros que salen utilizan el punto como separador (2.00 o 15.33). Sin embargo,"
                		+ " si programas en Java es posible que para probar tu soluci�n tengas que utilizar una coma (2,00 o 15,33).\n\n En principio, puedes ignorar este problema. El juez est�"
                		+ " configurado de manera que tu programa leer�, sin hacer nada especial, los n�meros decimales usando el punto en lugar de la coma.\n\n Si, a pesar de todo, quieres probar "
                		+ "que funciona, puedes imitar la configuraci�n del juez en tu propio ordenador, y as� podr�s utilizar los ficheros de ejemplo del ejercicio sin modificaci�n. Para eso tienes"
                		+ " dos opciones. La primera es configurar de manera adecuada las clases que utilices para leer la entrada. Por ejemplo, si usas la clase Scanner puedes hacer algo as�:\n\n"
                		+ "in = new java.util.Scanner(System.in);\n in.useLocale(java.util.Locale.UK);\n\n La otra opci�n es configurar la m�quina virtual de Java en el momento de lanzarla:\n\n"
                		+ "java -Duser.language=en ClaseConLaSolucion" },
                { " S�. Salvo que el enunciado diga lo contrario, la �ltima linea de la salida ser� igual que cualquier otra, por lo que deber� terminar con un fin de linea ('\n'). Si tu soluci�n"
                		+ " es correcta salvo por este detalle, el juez dar� como veredicto PE (Presentation Error, error de presentaci�n)." },
                { " El juez emite un veredicto por cada env�o, en funci�n de su correcci�n. El resultado m�s deseado es AC (Accepted, aceptado), pero hay otros, como WA (Wrong answer, respuesta incorrecta)"
                		+ " o RTE (Runtime error, error durante la ejecuci�n).\n\n Puedes ver una explicaci�n detallada de cada uno y, m�s importante, algunas pistas sobre por qu� pueden aparecer en"
                		+ " la secci�n de veredictos." },
                { " Cuando ejecuta una supuesta soluci�n, el juez en linea realiza un control exhaustivo sobre las operaciones que �sta realiza, y pone l�mites estrictos a su ejecuci�n.\n\n Por ejemplo, "
                		+ "el juez denegar� cualquier intento de acceso a ficheros, a la red o a operaciones del sistema operativo diferentes a las de la lectura y escritura por la entrada y salida est�ndar. "
                		+ "Si el programa trata de hacer cualquiera de estas cosas (consideradas potencialmente peligrosas), detendr� inmediatamente la ejecuci�n y dar� como veredicto RF (Restricted Function, "
                		+ "funci�n restringida). Ten en cuenta que cualquier intento de ejecutar otro proceso tambi�n ser� prohibido. Eso ocurre autom�ticamente si en C/C++ pones c�digo como: \n\n system ('pause');"
                		+ "Si programas en C/C++, otra posible causa del veredicto RF es el uso de fflush(stdin). Existe cierta tendencia a utilizarlo despu�s de los scanf() para consumir el resto de la linea, "
                		+ "especialmente si m�s adelante se va a hacer uso de gets() (funci�n desaconsejada, por otro lado). Sin embargo, el estandar ISO (secci�n 7.9.5.2) s�lo especifica el comportamiento de fflush()"
                		+ " para ficheros de entrada. Puedes leer m�s detalles aqu�. \n\n El equivalente a fflush(stdin) en C++ es cin.sync() que tampoco debe utilizarse (tambi�n genera como veredicto RF). "
                		+ "La sincronizaci�n de cin suele utilizarse para 'consumir el resto de la entrada', �til normalmente para recuperarse ante una entrada no esperada. Sin embargo, su uso est� desaconsejado, "
                		+ "pues el comportamiento exacto no es est�ndar, y podr�a incluso variar en la misma plataforma dependiendo de si se ha redirigido o no la entrada. Adem�s, dado que el juez siempre "
                		+ "utiliza entradas correctas no debes preocuparte de 'arreglar' la entrada nunca. Si est�s completamente convencido de que lo necesitas, entonces plant�ate utilizar cin.get() o cin.ignore(...)"
                		+ " en su lugar para evitar que el juez cancele tu ejecuci�n.\n\n Por �ltimo, se imponen restricciones de tiempo m�ximo de uso de procesador y de memoria. Los l�mites espec�ficos depender�n "
                		+ "del problema, y se muestran en la parte superior de su enunciado." },
                { " Cada problema impone una limitaci�n 'en tiempo y en espacio' a las soluciones para poderlas considerar v�lidas. En concreto, limita el tiempo m�ximo de uso de procesador y la cantidad m�xima de "
                		+ "memoria de los que puede hacer uso el programa. La limitaci�n de tiempo se especifica en segundos, y la de memoria en KiB (1024 bytes).\n\n Cuando te enfrentes a un problema, presta "
                		+ "atenci�n a ambos valores. En ocasiones, la dificultad del problema est� precisamente en esos l�mites, exigiendo soluciones imaginativas para no romperlos.\n\n Algunos problemas indican "
                		+ "un rango en el tiempo y/o en la memoria m�xima. Eso se debe a que muchos problemas contienen m�s de una bater�a de pruebas que los env�os deben superar, por lo que cada soluci�n recibida"
                		+ " se ejecutar� m�s de una vez. Si los l�mites para las diferentes bater�as de prueba son diferentes, se muestra el rango en el que oscilan." },
                { " S�. El juez muestra diferentes listas de env�os recibidos: �ltimos env�os, �ltimos env�os de un problema concreto (en las estad�sticas), �ltimos env�os de un usuario, o �ltimos env�os de un "
                		+ "usuario para un problema. En todas ellas, la primera columna es el identificador del env�o. Si es tuyo, ser� un enlace que te llevar� a los detalles de ese env�o, donde podr�s ver el "
                		+ "c�digo fuente asociado." },
                { " No. El c�digo fuente de un env�o s�lo puede verlo el usuario que lo mand�." },
                { " Cuando realizas un env�o, puedes a�adir un comentario en texto. El juez no utilizar� en ning�n caso ese texto, y no ser� visible para otros usuarios.\n\n Es �til para diferenciar ese env�o "
                		+ "de otros. Por ejemplo, si resuelves el mismo problema de varias formas, puedes a�adir alguna indicaci�n en el comentario asociado a cada env�o para record�rtelo.\n\n El comentario "
                		+ "de un env�o se puede cambiar a posteriori en la p�gina de los detalles de un env�o, algo que, obviamente, no se puede hacer con el c�digo (necesitar�s hacer un env�o nuevo). Esta "
                		+ "posibilidad es muy �til especialmente en los env�os fallidos. Si mandas una soluci�n y el juez te responde, por ejemplo, WA (Wrong Answer), es interesante que, cuando descubras por "
                		+ "qu� esa soluci�n estaba mal, te lo resumas a ti mismo a modo de documentaci�n en el comentario asociado a ella. As� en el futuro podr�s volver y saber qu� le pasaba sin tener que "
                		+ "analizar el c�digo de nuevo.\n\n Los comentarios de los env�os se ven en la lista de tus �ltimos env�os (generales o de un problema espec�fico). No son visibles en la lista de"
                		+ " todos los env�os recibidos por el juez, pues ah� se muestran tambi�n los de otros usuarios, y no podr�s ver sus comentarios." },
                { " La columna AC/Env�os indica la relaci�n entre el n�mero de env�os aceptados y el total de env�os. Esta informaci�n es �til para hacernos una idea sobre la dificultad del problema: cuanto "
                		+ "m�s alto sea el porcentaje de env�os aceptados m�s f�cil ser�, normalmente, un problema. El porcentaje da una pista de lo f�cil que es conseguir resolver el problema a la primera.\n\n "
                		+ "La columna AC/Usuarios (que s�lo aparece en las listas de problemas) indica el n�mero de usuarios que han resuelto correctamente un problema de todos los que lo han intentado. "
                		+ "El porcentaje de aceptados en este caso indica la probabilidad de que, al final, resuelvas el problema. Su inverso es el porcentaje de usuarios que, de momento, han dado el problema "
                		+ "por imposible.\n\n El n�mero de aceptados en ambas columnas no tiene por qu� ser igual. Si un usuario env�a el mismo problema correctamente dos veces, contar� por dos en la primera "
                		+ "columna, y por uno en la segunda.\n\n Por ejemplo, imagina un problema f�cil, pero que tiene alguna trampa sutil dif�cil de ver a la primera. Podr�a ocurrir que la mayor�a de los "
                		+ "usuarios que lo intentan fallaran en su primer env�o, pero luego se dieran cuenta del error y lo resolvieran al segundo. En ese caso la relaci�n AC/Env�os ser�a del 50% (un env�o "
                		+ "v�lido de cada dos), mientras que la relaci�n AC/Usuarios ser�a del 100% (todos los usuarios terminan consiguiendo resolverlo, aunque sea a la segunda)." },
                { " Las categor�as a las que pertenece un problema pueden constituir una gran ayuda a la hora de enfrentarse a un problema, pudiendo incluso arruinar el objetivo del propio problema de forzar a "
                		+ "reflexionar.\n\n Debido a ello, las categor�as no son visibles en el enunciado del problema para evitar que se lean 'por error' sin desearlo.\n\n Ten en cuenta que no pretendemos "
                		+ "con esto ocultar las categor�as de los problemas, como muestra el hecho de la existencia del recorrido por categor�as. Pero cuando alguien usa el recorrido por categor�as est� "
                		+ "expl�citamente buscando algo concreto, y no consideramos que encontrar un problema asociado a una categor�a determinada sea arruinarlo." },
                { " Si activas esta casilla de verificaci�n, tu navegador recordar� tu nombre del usuario tras abrir la sesi�n. As�, la pr�xima vez que vuelvas no tendr�s que escribirlo.\n\n Esta opci�n es"
                		+ " c�moda, y sigue siendo bastante segura porque la contrase�a tendr�s que escribirla de todas formas. No obstante, en ordenadores compartidos se aconseja no activarla, para que "
                		+ "usuarios posteriores no puedan conocer tu nombre de usuario e intentar un ataque de diccionario o de fuerza bruta sobre la contrase�a o se entretengan en reinici�rtela si usaste "
                		+ "como identificador tu direcci�n de correo.\n\n Siempre puedes conseguir que el navegador olvide tu nombre de usuario si haces login en cualquier momento con la casilla de "
                		+ "verificaci�n desmarcada. La pr�xima vez que vuelvas, tendr�s que escribir tambi�n tu nombre de usuario." }
            };
    		break;
    	case 2:
    		groups = new String[] { "Veredictos posibles", "Accepted (AC): aceptado", "Presentation error (PE): Error de presentaci�n" , "Wrong answer (WA): respuesta incorrecta",
					"Compilation error (CE): Error de compilaci�n", "Run-time error (RTE): Error durante la ejecuci�n", 
					"Time limit exceeded (TLE): Tiempo l�mite superado", "Memory limit exceeded (MLE): L�mite de memoria superado",
					"Output limit exceeded (OLE): L�mite de salida superado", "Restricted function (RF): Funci�n restringida",
					"In queue (IQ): en cola", "Internal error (IE): Error interno" };

    		children = new String [][] {
    				{ " Cuando se eval�a un env�o, el juez emite un veredicto con el resultado. S�lo uno de ellos indica que el problema se ha resuelto correctamente. Los dem�s son"
    					+ " diferentes formas de equivocarse. Saber los distintos significados de cada veredicto ayuda en la b�squeda de una soluci�n.\n\n A continuaci�n se"
    					+ " explican las diferentes respuestas que puede dar el juez." },
    				{ " La soluci�n enviada para el problema ha superado todos los casos de prueba, por lo que el juez la considera v�lida.\n\n �Enhorabuena!" },
    				{ " La soluci�n enviada no ha superado correctamente los casos de prueba, pero debido �nicamente a diferencias en los saltos de l�nea, espacios o tabuladores.\n\n "
    					+ "El juez no considera el env�o como correcto; sin embargo te da una indicaci�n de que la soluci�n est� muy cerca de serlo. Deber�s revisar que la salida "
    					+ "sigue exactamente el formato que se indica en el enunciado, poniendo especial cuidado en los separadores. En particular, ten en cuenta que, salvo que el "
    					+ "enunciado diga lo contrario, la �ltima l�nea tambi�n deber� terminar con un '\n'." },
    				{ " La soluci�n enviada no ha superado los casos de prueba. El programa se ha ejecutado completamente, pero no da el resultado esperado.\n\n Revisa concienducamente"
    					+ " el enunciado y tu soluci�n. Comprueba que, como m�nimo, tu programa funciona con los casos de ejemplo, y pru�balo manualmente con otros casos que se te "
    					+ "ocurran. Busca especialmente los casos l�mite del enunciado, por ejemplo el n�mero m�s alto que puede tener la entrada, el m�s bajo, etc�tera.\n\n �Este "
						+ "es el veredicto que menos pistas da! Al fin y al cabo, hay muchas maneras de equivocarse al programar y que el c�digo no haga exactamente lo que uno quiere." },
					{ " El juez ni siquiera ha sido capaz de compilar el c�digo que has enviado. Tienes alg�n error de sint�xis en el c�digo que hace imposible ejecutarlo. Si accedes a la "
						+ "informaci�n detallada del env�o (pulsando sobre su identificador) podr�s ver la salida del compilador.\n\n Intenta compilar el programa con los compiladores "
						+ "que utiliza el juez para probar localmente el c�digo antes de enviarlo, y no utilices librer�as que no sean est�ndar. Por ejemplo, si programas en Windows,"
						+ " no incluyas windows.h o no uses conio.h.\n\n En los programas en Java, este veredicto aparece tambi�n si el c�digo compila pero luego no puede ejecutarse."
						+ " En concreto, la clase debe ser p�blica (da igual el nombre y el paquete). Adem�s, obviamente, el main debe ser p�blico, est�tico y recibir un array de Strings." },
					{ " El juez ha sido capaz de compilar tu programa, pero durante la ejecuci�n �ste ha terminado de manera abrupta antes de procesar todos los casos de prueba.\n\n Si programas"
						+ " en C o C++, aseg�rate de que acabas la funci�n main() con un return 0 (acabar devolviendo cualquier otra cosa se considera una finalizaci�n incorrecta). Otros "
						+ "motivos de Runtime error al programar en C/C++ son divisiones por 0, accesos con punteros inv�lidos, accesos a elementos de un array fuera de rango o desbordamientos"
						+ " de pila en recursiones demasiado profundas.\n\n Si programas en Java, la causa principal de este veredicto es la finalizaci�n debido a una excepci�n no controlada."
						+ " Los motivos por los que pueden surgir excepciones son muchas, algunas de las cuales ya han sido mencionadas en el caso de C/C++. Otra posible causa habitual es "
						+ "intentar leer de la entrada en el formato incorrecto (por ejemplo intentar leer un n�mero cuando seg�n el enunciado viene una cadena o no hay m�s entrada).\n\n Si "
						+ "sufres un Runtime error busca posibles escenarios en los que podr�a suceder alguna de las cosas anteriores, por ejemplo probando tu programa con entradas l�mite. "
						+ "Pero ten en cuenta que las anteriores son s�lo algunas de las posibles causas de este veredicto. �Hay muchas m�s razones por las que un programa podr�a acabar inesperadamente!" },
					{ " El juez ha sido capaz de compilar tu c�digo y de ejecutarlo. Pero ha tardado m�s tiempo del permitido en dar una respuesta, por lo que se ha cancelado su ejecuci�n.\n\n Si tu "
						+ "programa ha sido capaz de contestar a varios casos de prueba, no se habr� comprobado si eran o no correctos. Es posible que un Time limit exceeded oculte en realidad "
						+ "un Wrong Answer en el caso de que se hubiera dejado terminar al programa.\n\n Si sufres este veredicto, planteate la eficiencia de tu c�digo. Seguramente haya alguna "
						+ "forma mejor de resolverlo.\n\n Ten en cuenta que un Time limit exceeded no se soluciona con optimizaciones menores, como usar rotaciones para multiplicar o dividir "
						+ "por dos, o ajustar alguna expresi�n para hacer una sola operaci�n aritm�tica en lugar de dos o tres. Tampoco se debe al lenguaje de programaci�n.\n\n Los problemas "
						+ "(y casos de prueba) est�n planteados con rangos de tiempo lo suficientemente generosos como para que peque�as ineficiencias en el c�digo no signifiquen que se vaya "
						+ "a sufrir un error por tiempo m�ximo superado. Por tanto, ante un Time limit exceeded deber�s plantearte aproximaciones diferentes al problema, intentando por ejemplo"
						+ " eliminar bucles enteros por operaciones sencillas. Otra posible causa de este veredicto en Java es la creaci�n de gran cantidad de objetos (t�picamente cadenas) "
						+ "cuando el problema puede resolverse sin ellos.\n\n Los enunciados de los problemas informan del tiempo m�ximo que se permite usar a las soluciones. A veces da una"
						+ " pista sobre el tipo de algoritmo que debes utilizar.\n\n Existe una �ltima posibilidad para este veredicto que no tiene relaci�n directa con el algoritmo, sino "
						+ "con la detecci�n del fin de la entrada. Si el enunciado dice, por ejemplo, que la entrada terminar� con un 0 y tu programa no detecta esa entrada correctamente, "
						+ "entrar� en un bucle leyendo de una entrada inexistente, y no acabar� nunca. En ese caso, el programa podr�a estar bien hecho en general, pero al no ser capaz de"
						+ " detectar cu�ndo terminar consumir� todo el tiempo disponible y sufrir� este veredicto." },
					{ " El juez ha compilado correctamente tu c�digo y lo ha ejecutado, pero �ste ha superado el l�mite de memoria m�ximo que se le permite utilizar, por lo que ha cancelado"
						+ " su ejecuci�n.\n\n Si tu programa ha sido capaz de contestar a varios casos de prueba, no se habr� comprobado si eran o no correctos. Es posible que un Memory "
						+ "limit exceeded oculte en realidad un Wrong Answer en el caso de que se hubiera dejado terminar al programa.\n\n Si tu soluci�n sufre este veredicto, revisa la "							
						+ "cantidad de memoria que est�s utilizando. Utilizar 4 variables en un problema que se puede resolver s�lo con 3 no es una raz�n suficiente para exceder la m�xima "
						+ "cantidad de memoria permitida. Tendr�s por tanto que buscar usos de memoria grandes, por lo que lo primero que tendr�s que revisar es el tama�o de los arras "
						+ "que est�s usando, y plantearte si se pueden hacer m�s peque�os o, incluso, si realmente son necesarios o hay soluciones alternativas que no los usan.\n\n Los "
						+ "enunciados de los problemas informan de la cantidad m�xima de memoria que se permite usar a las soluciones. Especialmente en los problemas en los que te veas "							
						+ "en la obligaci�n de utilizar arrays grandes consulta el valor para tener alguna garant�a de que no sufrir�s este veredicto." },
					{ " El juez ha compilado correctamente el c�digo que has enviado, pero durante la ejecuci�n ha escrito m�s salida de la m�xima permitida, y se ha cancelado su ejecuci�n.\n\n "
						+ "El l�mite de la salida permitida en el juez es considerablemente superior a la necesaria en cada una de las bater�as de prueba; si sufres este veredicto, por tanto,"
						+ "se debe a que tu programa escribe mucho m�s de lo que deber�a.\n\n Esto puede deberse a que por cada caso de prueba est�s escribiendo cosas que no se piden, "
						+ "como mensajes al usuario o de pruebas. Otro posible motivo es que no detectes correctamente el final de la entrada y tu programa insista en leer el siguiente "
						+ "caso de prueba y en dar una soluci�n para �l, que generar� salida err�nea indefinidamente, superando el l�mite. Este �ltimo tipo de error tambi�n es una "
						+ "fuente de veredictos TLE (Time limit exceeded, tiempo l�mite superado)." },
					{ " Tu programa ha realizado alguna operaci�n que el juez ha considerado ofensiva o peligrosa para el servidor y ha cancelado su ejecuci�n. Si sufres este veredicto, "
						+ "seguramente sepas por qu�...\n\n Si no es as�, puedes consultar las preguntas frecuentes para m�s informaci�n.\n\n Algunas veces (en realidad muy pocas veces)"
						+ " este veredicto puede encubrir un RTE (Run-time error, error durante la ejecuci�n). Si haces una soluci�n en C/C++ que sufre alg�n tipo de desbordamiento de"
						+ " buffer que pone al programa a ejecutar c�digo arbitrario, la casualidad podr�a hacer que ese c�digo intentara ejecutar alguna operaci�n no permitida que t� "
						+ "no has escrito. Si est�s convencido de que tu programa no hace nada que pueda considerarse peligroso para el juez (y has le�do la entrada de la FAQ), es "
						+ "posible que �sta sea la causa de tu RF, y tengas en realidad un RTE. En ese caso, comprueba, entre otras cosas, que los arrays tienen el tama�o suficiente para los l�mites del enunciado." },
					{ " El juez ha recibido tu c�digo, pero todav�a no lo ha evaluado. �Ten paciencia y dale un poco de tiempo!" },
					{ " El juez ha sufrido alg�n problema al probar tu c�digo que no es achacable a ti. Es posible que el juez est� en mantenimiento temporalmente, que haya alg�n error "
						+ "en los casos de prueba o que, en el peor de los casos, tengamos alg�n error de programaci�n en el juez.\n\n Intenta repetir el env�o m�s adelante para probar "
						+ "otra vez. Dado que el error no ser� culpa tuya, normalmente no ser� necesario que realices ning�n cambio a tu c�digo."}
    				};
    		break;
    	case 3:
    		groups = new String[] { "Historia","Fechas significativas (2015)", "Fechas significativas (2014)" };

    		children = new String [][] {
    				{ " �Acepta el reto! nace como una iniciativa de dos profesores de la Facultad de Inform�tica de la Universidad Complutense de Madrid. En 2011 hab�an contribu�do a poner en "
    						+ "marcha ProgramaMe, el concurso de programaci�n para alumnos de Ciclos Formativos de Formaci�n Profesional. Adem�s, hab�an desarrollado varios problemas similares a "
    						+ "los de los concursos para realizar pruebas de evaluaci�n cont�nua a sus alumnos de grado.\n\n Todos los problemas creados dorm�an pl�cidamente en un caj�n y no "
    						+ "pod�an ser aprovechados por estudiantes posteriores m�s all� de resolverlos sin poder probar su correcci�n. Debido a eso, decidieron poner en marcha el desarrollo "
    						+ "de un juez on-line similar a otros existentes (el m�s conocido el de la Universidad de Valladolid) pero centrado en problemas en espa�ol para alumnos de Ciclos "
    						+ "Formativos y primeros a�os de Universidad.\n\n En el curso acad�mico 2013-14 se ponen manos a la obra con la implementaci�n del backend, y delegan el frontend "
    						+ "web a estudiantes que desarrollan dos versiones preliminares del sitio. Ambas sirven como prueba de concepto y permiten poner a prueba la infraestructura. "
    						+ "En los �ltimos meses de 2013, sustituyen esas pruebas de concepto por una implementaci�n nueva del frontend, y en febrero de 2014 �Acepta el reto! ve finalmente la luz."},
    				{ " 23 de julio\n A�adido soporte para C++11. Desde el env�o 38711 el compilador de C++ incluye el flag -std=c++11. Prueba de ello es que el env�o inmediatamente anterior, "
    						+ "el 38710 result� en CE con el mismo c�digo.\n\n 21 de mayo\n Optimizada entrada/salida en C/C++. Desde el env�o 36542 los tiempos de ejecuci�n de las soluciones "
    						+ "en esos dos lenguajes se ven mejorados, especialmente en problemas con muchos casos de prueba o donde hay mucha alternancia entre lecturas y escrituras. Prueba de "
    						+ "ello es que el env�o inmediatamente anterior, el 36541 result� en TLE con el mismo c�digo.\n\n 17 de mayo\n A�adida esta p�gina \n\n 16 de marzo\n Env�o n�mero "
    						+ "30.000\n\n 21 de febrero\n Registro del usuario n�mero 1.000" },
    				{ "  24 de septiembre\n Env�o n�mero 10.000\n\n 14 de abril\nAlcanzados los 100 problemas publicados\n\n  6 de marzo\n Env�o n�mero 1.000\n\n 2 de marzo\n Registro del usuario n�mero 100\n\n "
    						+ "17 de febrero\n Puesta en marcha y primer env�o"}};
    		break;
    	case 4:
    		groups = new String[] { "Qui�nes somos","Equipo", "Antiguos miembros del equipo", "Colaboradores", "P�ginas amigas" };

    		children = new String [][] {
    				{ " �Acepta el reto! es una web lanzada desde la Facultad de Inform�tica de la Universidad Complutense de Madrid, por un grupo de profesores del Grupo de Aplicaciones de Inteligencia "
    						+ "Artificial (GAIA)." },
    				{ " Los nombres detr�s de �Acepta el reto! son:\n\n - Marco Antonio G�mez Mart�n\n - Pedro Pablo G�mez Mart�n" },
    				{ " En el desarrollo de �Acepta el reto! tambi�n han colaborado:\n\n - Luis Hern�ndez Ya�ez \n - J�ssica Mart�n Jab�n\n - Javier Mart�n Moreno-Manzanaro \n - Pablo Su�rez D�az \n "
    						+ " - Luis Mar�a Costero Valero\n - Jes�s Javier Domenech Arellano" },
    				{ " Group GAIA\n Facultad de Inform�tica (UCM)\n Universidad Complutense de Madrid"},
    				{ " - ProgramaMe, el concurso de programaci�n para Ciclos Formativos\n\n - Juez en linea de la Universidad de Valladolid\n\n - Mucho por programar: acertijos programables"}};
    		break;
    	}
    	

    }
}



