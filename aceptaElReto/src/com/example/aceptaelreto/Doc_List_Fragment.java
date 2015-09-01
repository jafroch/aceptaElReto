package com.example.aceptaelreto;

import Tools.ExpandableListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
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
    		groups = new String[] { "¿Qué es ¡Acepta el reto!?", "¿Qué tipo de problemas hay?", "¿Cómo comprueba el juez en linea si una solución es correcta?", 
        			"¿Sobre qué plataforma se ejecutan los envíos?","¿Qué compilador usa el juez?","¿Qué bibliotecas puedo utilizar en mis soluciones?",
        			"¿Tengo que comprobar que la entrada es correcta?","¿Puedo probar mi programa de alguna forma con la entrada de ejemplo del enunciado antes de mandarlo?",
        			"Cuando ejecuto mi solución, ¿tengo que escribir toda la salida después de leer toda la entrada?","En la entrada de ejemplo hay números con decimales con el punto como separador, pero en mi ordenador sólo funciona si uso la coma. ¿Qué puedo hacer?",
        			"La última linea de la salida, ¿debe terminar con un '\n'?","He enviado mi solución y el juez me contesta <'Veredicto'>. ¿Qué significa?",
        			"¿Qué restricciones en ejecución tienen las soluciones enviadas?","¿Qué significa 'Tiempo máximo' y 'Memoria máxima' en los enunciados de los problemas?",
        			"¿Puedo ver el código fuente de mis envíos anteriores?",
        			"¿Pueden otros usuarios ver mi código fuente, o puedo ver yo el de los demás?","¿Para qué sirve el campo 'comentario' de un envío?",
        			"¿Qué significan las estadísticas en las tablas de los problemas y las categorías?","¿Por qué las categorías a las que pertenece un problema no son visibles directamente en el enunciado del problema?",
        			"¿Para qué sirve la opción Recordar usuario en el formulario de login?"};

            children = new String [][] {
                { " ¡Acepta el reto! es un almacén ('repositorio') de problemas de programación en español, con un juez en línea incorporado. "
                		+ "Cualquier usuario puede resolver los problemas propuestos y enviar su solución al juez para comprobar si es correcta." },
                { " Todos los problemas del almacén son aplicaciones de consola que reciben datos por la entrada estándar y envían "
                		+ "los resultados por la salida estándar. Desde el punto de vista de programación, esto significa que deben leer del teclado y escribir texto en la pantalla.\n\n"
                		+"El objetivo es que los usuarios pongan en práctica sus habilidades de programación básica, de algoritmos y estructuras de datos. Por tanto, ninguno de los "
                		+ "problemas hace uso de interfaces gráficos de usuario, ni ficheros.\n\n"+"Dicho esto, el abanico de problemas es amplio y toca una gran variedad de temas. "
                		+ "Puedes comprobarlo recorriendo las categorías." },
                { " Para cada problema, el juez dispone de un conjunto de casos de prueba que mantiene en secreto. Cuando recibe el código de una supuesta solución, lo compila y "
                		+ "lo ejecuta, enviándole por la entrada estándar los casos de prueba que posee. \n\n El programa escribirá en la salida estándar los resultados, que serán "
                		+ "comparados por el juez con los resultados correctos, especificados por el autor del problema. El veredicto emitido dependerá del resultado de esa comparación." },
                { " La evaluación de los envíos se realiza en una plataforma Intel 80x86 (32 bits) sobre un sistema operativo GNU/Linux." },
                { " El juez en linea compila utilizando gcc para C/C++ y OpenJDK 1.7 para Java. La compilación de C++ incluye el flag -std=c++11 para soporte del estándar de 2011." },
                { " Si programas en C/C++ podrás hacer uso únicamente de la biblioteca matemática, junto con cualquier otra biblioteca estándar que no requiera el enlazado de nada particular "
                		+ "para la construcción del ejecutable final. Ejemplos de ficheros de cabecera permitidos son stdio.h, stdlib.h, string.h y math.h. En el caso de C++, también"
                		+ " se puede hacer uso de la biblioteca estándar (iostream, vector, etcétera).\n\n En Java, se puede hacer uso de cualquier paquete estándar salvo los relacionados "
                		+ "con el interfaz gráfico (awt, swing, etcétera). Tampoco se permite utilizar la clase java.lang.Thread.\n\n "
                		+ "Ten en cuenta que algunas bibliotecas o paquetes podrían incluirse sin dar error de compilación, pero luego fallar en ejecución porque el juez no permita su uso." },
                { " No. El objetivo principal de la gran mayoría de los problemas es que pongas en práctica tus conocimientos de programación, no que pierdas tiempo en detalles poco importantes "
                		+ "como la entrada.\n\n Si en el enunciado de un problema dice, por ejemplo, que cada caso de prueba estará compuesto de un número positivo, no necesitas comprobar que"
                		+ " es así; al fin y al cabo, ¿qué harías si no lo fuera? Por tanto, puedes confiar plenamente en que la entrada seguirá el formato especificado y no preocuparte de "
                		+ "reaccionar dignamente si no es así.\n\n La contrapartida es que el juez exige lo mismo en la salida. Tu programa debe ser totalmente estricto en el formato de la "
                		+ "salida. No añadas texto adicional para ser 'amigable' con el usuario (tipo 'Introduce el siguiente número'), porque despistarán al juez y no dará por bueno el resultado. "
                		+ "Sigue a pies juntillas el formato de salida, incluídos los espacios." },
                { " No es que puedas, ¡es que debes! Antes de enviar tu solución al juez on-line deberías probarla en tu ordenador para tener una cierta esperanza de que esté bien.\n\n Para eso,"
                		+ " puedes descargarte la entrada y salida de ejemplo del enunciado. Basta con que pases el ratón por encima de los ejemplos, y verás aparecer una 'barra de herramientas' "
                		+ "para seleccionar todo (y poderlo copiar), o para descargarte un archivo comprimido con los dos ficheros (el ejemplo de la entrada y de la salida).\n\n A partir de ahí,"
                		+ " dependiendo de la opción que elijas, tendrás que actuar de una manera u otra. Quizá lo más sencillo sea lanzar tu programa en una consola, pegar el texto copiado de "
                		+ "la entrada y luego comprobar manualmente que la salida es correcta.\n\n La otra alternativa es que hagas uso de las redirecciones del shell. Por ejemplo en GNU/Linux: \n\n"
                		+ "$ ./a.out < sample.in > miSalida \n"
                		+"$ diff sample.out miSalida \n\n"
                		+"Consulta la documentación de tu sistema operativo para obtener más información. Ten en cuenta que los ficheros de ejemplo descargados tienen los saltos de linea en modo "
                		+ "Unix, por lo que podrías ver falsas diferencias que desaparecerán al enviar tu código al juez on-line." },
                { " ¡No! Todos los enunciados contienen un ejemplo de entrada y un ejemplo de salida que se separan por claridad y simplicidad. Pero las soluciones enviadas no tienen que leer toda "
                		+ "la entrada, procesarla y luego escribir toda la salida. ¡Más bien al contrario! Hacerlo así requeriría que el programa utilizara más memoria para guardar toda la entrada "
                		+ "antes de empezar a hacer algo útil.\n\n Te animamos a que tus programas tengan, a alto nivel, un esquema similar a éste: \n\n"
                		+ "mientras(leerSiguienteCasoDePrueba()) procesarCasoActual();\n\n De ese modo, si pruebas tu programa de manera interactiva (escribiendo los casos de prueba por teclado) "
                		+ "al introducir cada uno, el programa responderá inmediatamente sin esperar a leer los siguientes. Posteriormente, el juez on-line será capaz cuando pruebe tu código de "
                		+ "independizar la entrada y la salida sin problema." },
                { " Algunos problemas tienen que leer de la entrada números con decimales, y en el ejemplo de entrada los números que salen utilizan el punto como separador (2.00 o 15.33). Sin embargo,"
                		+ " si programas en Java es posible que para probar tu solución tengas que utilizar una coma (2,00 o 15,33).\n\n En principio, puedes ignorar este problema. El juez está"
                		+ " configurado de manera que tu programa leerá, sin hacer nada especial, los números decimales usando el punto en lugar de la coma.\n\n Si, a pesar de todo, quieres probar "
                		+ "que funciona, puedes imitar la configuración del juez en tu propio ordenador, y así podrás utilizar los ficheros de ejemplo del ejercicio sin modificación. Para eso tienes"
                		+ " dos opciones. La primera es configurar de manera adecuada las clases que utilices para leer la entrada. Por ejemplo, si usas la clase Scanner puedes hacer algo así:\n\n"
                		+ "in = new java.util.Scanner(System.in);\n in.useLocale(java.util.Locale.UK);\n\n La otra opción es configurar la máquina virtual de Java en el momento de lanzarla:\n\n"
                		+ "java -Duser.language=en ClaseConLaSolucion" },
                { " Sí. Salvo que el enunciado diga lo contrario, la última linea de la salida será igual que cualquier otra, por lo que deberá terminar con un fin de linea ('\n'). Si tu solución"
                		+ " es correcta salvo por este detalle, el juez dará como veredicto PE (Presentation Error, error de presentación)." },
                { " El juez emite un veredicto por cada envío, en función de su corrección. El resultado más deseado es AC (Accepted, aceptado), pero hay otros, como WA (Wrong answer, respuesta incorrecta)"
                		+ " o RTE (Runtime error, error durante la ejecución).\n\n Puedes ver una explicación detallada de cada uno y, más importante, algunas pistas sobre por qué pueden aparecer en"
                		+ " la sección de veredictos." },
                { " Cuando ejecuta una supuesta solución, el juez en linea realiza un control exhaustivo sobre las operaciones que ésta realiza, y pone límites estrictos a su ejecución.\n\n Por ejemplo, "
                		+ "el juez denegará cualquier intento de acceso a ficheros, a la red o a operaciones del sistema operativo diferentes a las de la lectura y escritura por la entrada y salida estándar. "
                		+ "Si el programa trata de hacer cualquiera de estas cosas (consideradas potencialmente peligrosas), detendrá inmediatamente la ejecución y dará como veredicto RF (Restricted Function, "
                		+ "función restringida). Ten en cuenta que cualquier intento de ejecutar otro proceso también será prohibido. Eso ocurre automáticamente si en C/C++ pones código como: \n\n system ('pause');"
                		+ "Si programas en C/C++, otra posible causa del veredicto RF es el uso de fflush(stdin). Existe cierta tendencia a utilizarlo después de los scanf() para consumir el resto de la linea, "
                		+ "especialmente si más adelante se va a hacer uso de gets() (función desaconsejada, por otro lado). Sin embargo, el estandar ISO (sección 7.9.5.2) sólo especifica el comportamiento de fflush()"
                		+ " para ficheros de entrada. Puedes leer más detalles aquí. \n\n El equivalente a fflush(stdin) en C++ es cin.sync() que tampoco debe utilizarse (también genera como veredicto RF). "
                		+ "La sincronización de cin suele utilizarse para 'consumir el resto de la entrada', útil normalmente para recuperarse ante una entrada no esperada. Sin embargo, su uso está desaconsejado, "
                		+ "pues el comportamiento exacto no es estándar, y podría incluso variar en la misma plataforma dependiendo de si se ha redirigido o no la entrada. Además, dado que el juez siempre "
                		+ "utiliza entradas correctas no debes preocuparte de 'arreglar' la entrada nunca. Si estás completamente convencido de que lo necesitas, entonces plantéate utilizar cin.get() o cin.ignore(...)"
                		+ " en su lugar para evitar que el juez cancele tu ejecución.\n\n Por último, se imponen restricciones de tiempo máximo de uso de procesador y de memoria. Los límites específicos dependerán "
                		+ "del problema, y se muestran en la parte superior de su enunciado." },
                { " Cada problema impone una limitación 'en tiempo y en espacio' a las soluciones para poderlas considerar válidas. En concreto, limita el tiempo máximo de uso de procesador y la cantidad máxima de "
                		+ "memoria de los que puede hacer uso el programa. La limitación de tiempo se especifica en segundos, y la de memoria en KiB (1024 bytes).\n\n Cuando te enfrentes a un problema, presta "
                		+ "atención a ambos valores. En ocasiones, la dificultad del problema está precisamente en esos límites, exigiendo soluciones imaginativas para no romperlos.\n\n Algunos problemas indican "
                		+ "un rango en el tiempo y/o en la memoria máxima. Eso se debe a que muchos problemas contienen más de una batería de pruebas que los envíos deben superar, por lo que cada solución recibida"
                		+ " se ejecutará más de una vez. Si los límites para las diferentes baterías de prueba son diferentes, se muestra el rango en el que oscilan." },
                { " Sí. El juez muestra diferentes listas de envíos recibidos: últimos envíos, últimos envíos de un problema concreto (en las estadísticas), últimos envíos de un usuario, o últimos envíos de un "
                		+ "usuario para un problema. En todas ellas, la primera columna es el identificador del envío. Si es tuyo, será un enlace que te llevará a los detalles de ese envío, donde podrás ver el "
                		+ "código fuente asociado." },
                { " No. El código fuente de un envío sólo puede verlo el usuario que lo mandó." },
                { " Cuando realizas un envío, puedes añadir un comentario en texto. El juez no utilizará en ningún caso ese texto, y no será visible para otros usuarios.\n\n Es útil para diferenciar ese envío "
                		+ "de otros. Por ejemplo, si resuelves el mismo problema de varias formas, puedes añadir alguna indicación en el comentario asociado a cada envío para recordártelo.\n\n El comentario "
                		+ "de un envío se puede cambiar a posteriori en la página de los detalles de un envío, algo que, obviamente, no se puede hacer con el código (necesitarás hacer un envío nuevo). Esta "
                		+ "posibilidad es muy útil especialmente en los envíos fallidos. Si mandas una solución y el juez te responde, por ejemplo, WA (Wrong Answer), es interesante que, cuando descubras por "
                		+ "qué esa solución estaba mal, te lo resumas a ti mismo a modo de documentación en el comentario asociado a ella. Así en el futuro podrás volver y saber qué le pasaba sin tener que "
                		+ "analizar el código de nuevo.\n\n Los comentarios de los envíos se ven en la lista de tus últimos envíos (generales o de un problema específico). No son visibles en la lista de"
                		+ " todos los envíos recibidos por el juez, pues ahí se muestran también los de otros usuarios, y no podrás ver sus comentarios." },
                { " La columna AC/Envíos indica la relación entre el número de envíos aceptados y el total de envíos. Esta información es útil para hacernos una idea sobre la dificultad del problema: cuanto "
                		+ "más alto sea el porcentaje de envíos aceptados más fácil será, normalmente, un problema. El porcentaje da una pista de lo fácil que es conseguir resolver el problema a la primera.\n\n "
                		+ "La columna AC/Usuarios (que sólo aparece en las listas de problemas) indica el número de usuarios que han resuelto correctamente un problema de todos los que lo han intentado. "
                		+ "El porcentaje de aceptados en este caso indica la probabilidad de que, al final, resuelvas el problema. Su inverso es el porcentaje de usuarios que, de momento, han dado el problema "
                		+ "por imposible.\n\n El número de aceptados en ambas columnas no tiene por qué ser igual. Si un usuario envía el mismo problema correctamente dos veces, contará por dos en la primera "
                		+ "columna, y por uno en la segunda.\n\n Por ejemplo, imagina un problema fácil, pero que tiene alguna trampa sutil difícil de ver a la primera. Podría ocurrir que la mayoría de los "
                		+ "usuarios que lo intentan fallaran en su primer envío, pero luego se dieran cuenta del error y lo resolvieran al segundo. En ese caso la relación AC/Envíos sería del 50% (un envío "
                		+ "válido de cada dos), mientras que la relación AC/Usuarios sería del 100% (todos los usuarios terminan consiguiendo resolverlo, aunque sea a la segunda)." },
                { " Las categorías a las que pertenece un problema pueden constituir una gran ayuda a la hora de enfrentarse a un problema, pudiendo incluso arruinar el objetivo del propio problema de forzar a "
                		+ "reflexionar.\n\n Debido a ello, las categorías no son visibles en el enunciado del problema para evitar que se lean 'por error' sin desearlo.\n\n Ten en cuenta que no pretendemos "
                		+ "con esto ocultar las categorías de los problemas, como muestra el hecho de la existencia del recorrido por categorías. Pero cuando alguien usa el recorrido por categorías está "
                		+ "explícitamente buscando algo concreto, y no consideramos que encontrar un problema asociado a una categoría determinada sea arruinarlo." },
                { " Si activas esta casilla de verificación, tu navegador recordará tu nombre del usuario tras abrir la sesión. Así, la próxima vez que vuelvas no tendrás que escribirlo.\n\n Esta opción es"
                		+ " cómoda, y sigue siendo bastante segura porque la contraseña tendrás que escribirla de todas formas. No obstante, en ordenadores compartidos se aconseja no activarla, para que "
                		+ "usuarios posteriores no puedan conocer tu nombre de usuario e intentar un ataque de diccionario o de fuerza bruta sobre la contraseña o se entretengan en reiniciártela si usaste "
                		+ "como identificador tu dirección de correo.\n\n Siempre puedes conseguir que el navegador olvide tu nombre de usuario si haces login en cualquier momento con la casilla de "
                		+ "verificación desmarcada. La próxima vez que vuelvas, tendrás que escribir también tu nombre de usuario." }
            };
    		break;
    	case 2:
    		groups = new String[] { "Veredictos posibles", "Accepted (AC): aceptado", "Presentation error (PE): Error de presentación" , "Wrong answer (WA): respuesta incorrecta",
					"Compilation error (CE): Error de compilación", "Run-time error (RTE): Error durante la ejecución", 
					"Time limit exceeded (TLE): Tiempo límite superado", "Memory limit exceeded (MLE): Límite de memoria superado",
					"Output limit exceeded (OLE): Límite de salida superado", "Restricted function (RF): Función restringida",
					"In queue (IQ): en cola", "Internal error (IE): Error interno" };

    		children = new String [][] {
    				{ " Cuando se evalúa un envío, el juez emite un veredicto con el resultado. Sólo uno de ellos indica que el problema se ha resuelto correctamente. Los demás son"
    					+ " diferentes formas de equivocarse. Saber los distintos significados de cada veredicto ayuda en la búsqueda de una solución.\n\n A continuación se"
    					+ " explican las diferentes respuestas que puede dar el juez." },
    				{ " La solución enviada para el problema ha superado todos los casos de prueba, por lo que el juez la considera válida.\n\n ¡Enhorabuena!" },
    				{ " La solución enviada no ha superado correctamente los casos de prueba, pero debido únicamente a diferencias en los saltos de línea, espacios o tabuladores.\n\n "
    					+ "El juez no considera el envío como correcto; sin embargo te da una indicación de que la solución está muy cerca de serlo. Deberás revisar que la salida "
    					+ "sigue exactamente el formato que se indica en el enunciado, poniendo especial cuidado en los separadores. En particular, ten en cuenta que, salvo que el "
    					+ "enunciado diga lo contrario, la última línea también deberá terminar con un '\n'." },
    				{ " La solución enviada no ha superado los casos de prueba. El programa se ha ejecutado completamente, pero no da el resultado esperado.\n\n Revisa concienducamente"
    					+ " el enunciado y tu solución. Comprueba que, como mínimo, tu programa funciona con los casos de ejemplo, y pruébalo manualmente con otros casos que se te "
    					+ "ocurran. Busca especialmente los casos límite del enunciado, por ejemplo el número más alto que puede tener la entrada, el más bajo, etcétera.\n\n ¡Este "
						+ "es el veredicto que menos pistas da! Al fin y al cabo, hay muchas maneras de equivocarse al programar y que el código no haga exactamente lo que uno quiere." },
					{ " El juez ni siquiera ha sido capaz de compilar el código que has enviado. Tienes algún error de sintáxis en el código que hace imposible ejecutarlo. Si accedes a la "
						+ "información detallada del envío (pulsando sobre su identificador) podrás ver la salida del compilador.\n\n Intenta compilar el programa con los compiladores "
						+ "que utiliza el juez para probar localmente el código antes de enviarlo, y no utilices librerías que no sean estándar. Por ejemplo, si programas en Windows,"
						+ " no incluyas windows.h o no uses conio.h.\n\n En los programas en Java, este veredicto aparece también si el código compila pero luego no puede ejecutarse."
						+ " En concreto, la clase debe ser pública (da igual el nombre y el paquete). Además, obviamente, el main debe ser público, estático y recibir un array de Strings." },
					{ " El juez ha sido capaz de compilar tu programa, pero durante la ejecución éste ha terminado de manera abrupta antes de procesar todos los casos de prueba.\n\n Si programas"
						+ " en C o C++, asegúrate de que acabas la función main() con un return 0 (acabar devolviendo cualquier otra cosa se considera una finalización incorrecta). Otros "
						+ "motivos de Runtime error al programar en C/C++ son divisiones por 0, accesos con punteros inválidos, accesos a elementos de un array fuera de rango o desbordamientos"
						+ " de pila en recursiones demasiado profundas.\n\n Si programas en Java, la causa principal de este veredicto es la finalización debido a una excepción no controlada."
						+ " Los motivos por los que pueden surgir excepciones son muchas, algunas de las cuales ya han sido mencionadas en el caso de C/C++. Otra posible causa habitual es "
						+ "intentar leer de la entrada en el formato incorrecto (por ejemplo intentar leer un número cuando según el enunciado viene una cadena o no hay más entrada).\n\n Si "
						+ "sufres un Runtime error busca posibles escenarios en los que podría suceder alguna de las cosas anteriores, por ejemplo probando tu programa con entradas límite. "
						+ "Pero ten en cuenta que las anteriores son sólo algunas de las posibles causas de este veredicto. ¡Hay muchas más razones por las que un programa podría acabar inesperadamente!" },
					{ " El juez ha sido capaz de compilar tu código y de ejecutarlo. Pero ha tardado más tiempo del permitido en dar una respuesta, por lo que se ha cancelado su ejecución.\n\n Si tu "
						+ "programa ha sido capaz de contestar a varios casos de prueba, no se habrá comprobado si eran o no correctos. Es posible que un Time limit exceeded oculte en realidad "
						+ "un Wrong Answer en el caso de que se hubiera dejado terminar al programa.\n\n Si sufres este veredicto, planteate la eficiencia de tu código. Seguramente haya alguna "
						+ "forma mejor de resolverlo.\n\n Ten en cuenta que un Time limit exceeded no se soluciona con optimizaciones menores, como usar rotaciones para multiplicar o dividir "
						+ "por dos, o ajustar alguna expresión para hacer una sola operación aritmética en lugar de dos o tres. Tampoco se debe al lenguaje de programación.\n\n Los problemas "
						+ "(y casos de prueba) están planteados con rangos de tiempo lo suficientemente generosos como para que pequeñas ineficiencias en el código no signifiquen que se vaya "
						+ "a sufrir un error por tiempo máximo superado. Por tanto, ante un Time limit exceeded deberás plantearte aproximaciones diferentes al problema, intentando por ejemplo"
						+ " eliminar bucles enteros por operaciones sencillas. Otra posible causa de este veredicto en Java es la creación de gran cantidad de objetos (típicamente cadenas) "
						+ "cuando el problema puede resolverse sin ellos.\n\n Los enunciados de los problemas informan del tiempo máximo que se permite usar a las soluciones. A veces da una"
						+ " pista sobre el tipo de algoritmo que debes utilizar.\n\n Existe una última posibilidad para este veredicto que no tiene relación directa con el algoritmo, sino "
						+ "con la detección del fin de la entrada. Si el enunciado dice, por ejemplo, que la entrada terminará con un 0 y tu programa no detecta esa entrada correctamente, "
						+ "entrará en un bucle leyendo de una entrada inexistente, y no acabará nunca. En ese caso, el programa podría estar bien hecho en general, pero al no ser capaz de"
						+ " detectar cuándo terminar consumirá todo el tiempo disponible y sufrirá este veredicto." },
					{ " El juez ha compilado correctamente tu código y lo ha ejecutado, pero éste ha superado el límite de memoria máximo que se le permite utilizar, por lo que ha cancelado"
						+ " su ejecución.\n\n Si tu programa ha sido capaz de contestar a varios casos de prueba, no se habrá comprobado si eran o no correctos. Es posible que un Memory "
						+ "limit exceeded oculte en realidad un Wrong Answer en el caso de que se hubiera dejado terminar al programa.\n\n Si tu solución sufre este veredicto, revisa la "							
						+ "cantidad de memoria que estás utilizando. Utilizar 4 variables en un problema que se puede resolver sólo con 3 no es una razón suficiente para exceder la máxima "
						+ "cantidad de memoria permitida. Tendrás por tanto que buscar usos de memoria grandes, por lo que lo primero que tendrás que revisar es el tamaño de los arras "
						+ "que estés usando, y plantearte si se pueden hacer más pequeños o, incluso, si realmente son necesarios o hay soluciones alternativas que no los usan.\n\n Los "
						+ "enunciados de los problemas informan de la cantidad máxima de memoria que se permite usar a las soluciones. Especialmente en los problemas en los que te veas "							
						+ "en la obligación de utilizar arrays grandes consulta el valor para tener alguna garantía de que no sufrirás este veredicto." },
					{ " El juez ha compilado correctamente el código que has enviado, pero durante la ejecución ha escrito más salida de la máxima permitida, y se ha cancelado su ejecución.\n\n "
						+ "El límite de la salida permitida en el juez es considerablemente superior a la necesaria en cada una de las baterías de prueba; si sufres este veredicto, por tanto,"
						+ "se debe a que tu programa escribe mucho más de lo que debería.\n\n Esto puede deberse a que por cada caso de prueba estás escribiendo cosas que no se piden, "
						+ "como mensajes al usuario o de pruebas. Otro posible motivo es que no detectes correctamente el final de la entrada y tu programa insista en leer el siguiente "
						+ "caso de prueba y en dar una solución para él, que generará salida errónea indefinidamente, superando el límite. Este último tipo de error también es una "
						+ "fuente de veredictos TLE (Time limit exceeded, tiempo límite superado)." },
					{ " Tu programa ha realizado alguna operación que el juez ha considerado ofensiva o peligrosa para el servidor y ha cancelado su ejecución. Si sufres este veredicto, "
						+ "seguramente sepas por qué...\n\n Si no es así, puedes consultar las preguntas frecuentes para más información.\n\n Algunas veces (en realidad muy pocas veces)"
						+ " este veredicto puede encubrir un RTE (Run-time error, error durante la ejecución). Si haces una solución en C/C++ que sufre algún tipo de desbordamiento de"
						+ " buffer que pone al programa a ejecutar código arbitrario, la casualidad podría hacer que ese código intentara ejecutar alguna operación no permitida que tú "
						+ "no has escrito. Si estás convencido de que tu programa no hace nada que pueda considerarse peligroso para el juez (y has leído la entrada de la FAQ), es "
						+ "posible que ésta sea la causa de tu RF, y tengas en realidad un RTE. En ese caso, comprueba, entre otras cosas, que los arrays tienen el tamaño suficiente para los límites del enunciado." },
					{ " El juez ha recibido tu código, pero todavía no lo ha evaluado. ¡Ten paciencia y dale un poco de tiempo!" },
					{ " El juez ha sufrido algún problema al probar tu código que no es achacable a ti. Es posible que el juez esté en mantenimiento temporalmente, que haya algún error "
						+ "en los casos de prueba o que, en el peor de los casos, tengamos algún error de programación en el juez.\n\n Intenta repetir el envío más adelante para probar "
						+ "otra vez. Dado que el error no será culpa tuya, normalmente no será necesario que realices ningún cambio a tu código."}
    				};
    		break;
    	case 3:
    		groups = new String[] { "Historia","Fechas significativas (2015)", "Fechas significativas (2014)" };

    		children = new String [][] {
    				{ " ¡Acepta el reto! nace como una iniciativa de dos profesores de la Facultad de Informática de la Universidad Complutense de Madrid. En 2011 habían contribuído a poner en "
    						+ "marcha ProgramaMe, el concurso de programación para alumnos de Ciclos Formativos de Formación Profesional. Además, habían desarrollado varios problemas similares a "
    						+ "los de los concursos para realizar pruebas de evaluación contínua a sus alumnos de grado.\n\n Todos los problemas creados dormían plácidamente en un cajón y no "
    						+ "podían ser aprovechados por estudiantes posteriores más allá de resolverlos sin poder probar su corrección. Debido a eso, decidieron poner en marcha el desarrollo "
    						+ "de un juez on-line similar a otros existentes (el más conocido el de la Universidad de Valladolid) pero centrado en problemas en español para alumnos de Ciclos "
    						+ "Formativos y primeros años de Universidad.\n\n En el curso académico 2013-14 se ponen manos a la obra con la implementación del backend, y delegan el frontend "
    						+ "web a estudiantes que desarrollan dos versiones preliminares del sitio. Ambas sirven como prueba de concepto y permiten poner a prueba la infraestructura. "
    						+ "En los últimos meses de 2013, sustituyen esas pruebas de concepto por una implementación nueva del frontend, y en febrero de 2014 ¡Acepta el reto! ve finalmente la luz."},
    				{ " 23 de julio\n Añadido soporte para C++11. Desde el envío 38711 el compilador de C++ incluye el flag -std=c++11. Prueba de ello es que el envío inmediatamente anterior, "
    						+ "el 38710 resultó en CE con el mismo código.\n\n 21 de mayo\n Optimizada entrada/salida en C/C++. Desde el envío 36542 los tiempos de ejecución de las soluciones "
    						+ "en esos dos lenguajes se ven mejorados, especialmente en problemas con muchos casos de prueba o donde hay mucha alternancia entre lecturas y escrituras. Prueba de "
    						+ "ello es que el envío inmediatamente anterior, el 36541 resultó en TLE con el mismo código.\n\n 17 de mayo\n Añadida esta página \n\n 16 de marzo\n Envío número "
    						+ "30.000\n\n 21 de febrero\n Registro del usuario número 1.000" },
    				{ "  24 de septiembre\n Envío número 10.000\n\n 14 de abril\nAlcanzados los 100 problemas publicados\n\n  6 de marzo\n Envío número 1.000\n\n 2 de marzo\n Registro del usuario número 100\n\n "
    						+ "17 de febrero\n Puesta en marcha y primer envío"}};
    		break;
    	case 4:
    		groups = new String[] { "Quiénes somos","Equipo", "Antiguos miembros del equipo", "Colaboradores", "Páginas amigas" };

    		children = new String [][] {
    				{ " ¡Acepta el reto! es una web lanzada desde la Facultad de Informática de la Universidad Complutense de Madrid, por un grupo de profesores del Grupo de Aplicaciones de Inteligencia "
    						+ "Artificial (GAIA)." },
    				{ " Los nombres detrás de ¡Acepta el reto! son:\n\n - Marco Antonio Gómez Martín\n - Pedro Pablo Gómez Martín" },
    				{ " En el desarrollo de ¡Acepta el reto! también han colaborado:\n\n - Luis Hernández Yañez \n - Jéssica Martín Jabón\n - Javier Martín Moreno-Manzanaro \n - Pablo Suárez Díaz \n "
    						+ " - Luis María Costero Valero\n - Jesús Javier Domenech Arellano" },
    				{ " Group GAIA\n Facultad de Informática (UCM)\n Universidad Complutense de Madrid"},
    				{ " - ProgramaMe, el concurso de programación para Ciclos Formativos\n\n - Juez en linea de la Universidad de Valladolid\n\n - Mucho por programar: acertijos programables"}};
    		break;
    	}
    	

    }
}



