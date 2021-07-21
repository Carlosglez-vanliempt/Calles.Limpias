/*
        CARLOS GONZÁLEZ VAN LIEMPT
            Proyecto de Calles Limpias de Nieve
            Fundamentos de Programación y computadores
*/

import java.io.*;
import java.util.Scanner;

public class CallesLimpias {

    public static void crearCalle(String ruta, String nombreMunicipio){

        Scanner teclado = new Scanner(System.in);

        File archivoMunicipio = new File(ruta);
        if(archivoMunicipio.exists()){ //Comprueba que el Municipio esté creado
            System.out.println("El Municipio que desea crear ya está dado de alta!"); //De ser cierto, avisa al usuario
        }else{
            System.out.println("El Municipio que desea crear no está dado de alto. Porfavor los datos del mismo:");
            System.out.print("-> ");
            crearFichero(ruta,"IDcalle;Nombre calle;Estado;Información Extra\n" ); //En caso negativo crea el fichero Muninipio
            escribirMunicipioLista(nombreMunicipio); //Escribe los datos del Municipio en la Lista
        }

        String[][] nuevaLinea = new String[1][4];
        for(int i=0; i<nuevaLinea.length; i++){
            for(int j=0; j<nuevaLinea[i].length; j++){
                if(j==0){
                    nuevaLinea[i][j] = String.valueOf(contarFilas(ruta));
                }
                if(j==1){
                    System.out.println("Introduzca el nombre de la calle de " + nombreMunicipio + ":");
                    System.out.print("-> ");
                    nuevaLinea[i][j] = teclado.next();
                }
                if(j==2){
                    System.out.println("Introduzca el estado en el que se encuentra la calle:");
                    System.out.print("-> ");
                    nuevaLinea[i][j] = teclado.next();
                }
                if(j==3){
                    System.out.println("""
                            ¿Desea añadir alguna información extra?
                            -> 1 - Si
                            -> 2 - No""");
                    System.out.print("--> ");
                    String decisionInfoExtra;
                    while(!((teclado.hasNext("1"))||(teclado.hasNext("2")))){
                        System.out.println("Opción Incorrecta. Porfavor Revise...");
                        System.out.print("-> ");
                            teclado.next();

                    }decisionInfoExtra = teclado.next();
                    if(decisionInfoExtra.equals("1")){
                        System.out.println("Introduzca la Información Extra que desee:");
                        System.out.print("-> ");
                        nuevaLinea[i][j] = teclado.next();

                    }else{
                        System.out.println("Información por defecto.");
                        nuevaLinea[i][j] = "No presenta ninguna información extra";
                    }

                }
            }
        }
        escribirEnFichero(nuevaLinea, ruta,true); //Escribe los datos en el fichero correspondiente fichero dle Municipio
    }

    public static void crearMunicipio (){
        Scanner teclado = new Scanner(System.in);
        System.out.println("¿Qué municipio desea dar de alta?");
        System.out.print("-> ");
        String nombreMunicipio = teclado.next();
        String ruta = nombreMunicipio + ".csv";
        File archivoMunicipio = new File(ruta);

        if(archivoMunicipio.exists()){ //Comprueba que el Municipio esté creado
            System.out.println("El Municipio que desea crear ya está dado de alta!"); //De ser cierto, avisa al usuario
        }else{
            System.out.println("El Municipio que desea crear no está dado de alta.\n Porfavor introduzca los datos del mismo.");
            crearFichero(ruta,"IDcalle;Nombre calle;Estado;Información Extra\n" ); //En caso negativo crea el fichero Muninipio
            escribirMunicipioLista(nombreMunicipio); //Escribe los datos del Municipio en la Lista
          }
        }

    public static void crearMunicipiosinPedirNombre (String nombreMunicipio){
        String ruta = nombreMunicipio + ".csv";
        File archivoMunicipio = new File(ruta);

        if(archivoMunicipio.exists()){ //Comprueba que el Municipio esté creado
            System.out.println("El Municipio que desea crear ya está dado de alta!"); //De ser cierto, avisa al usuario
        }else{
            System.out.println("El Municipio que desea crear no está dado de alta.\n Porfavor introduzca los datos del mismo.");
            crearFichero(ruta,"IDcalle;Nombre calle;Estado;Información Extra\n" ); //En caso negativo crea el fichero Muninipio
            escribirMunicipioLista(nombreMunicipio); //Escribe los datos del Municipio en la Lista
        }
    }

    public static void crearFichero(String ruta, String texto){

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(ruta, true));
            bw.write(texto); // escribe el contenido indicado como parametro en el documento indicado
            bw.flush(); // guarda el archivo
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { // Cierra el BufferedWriter
            if (bw != null)
                try {
                    bw.close();
                } catch (IOException ioe) {
                    System.out.println("No se ha podido cerrar el lector de ficheros.");
                }
        }
    }

    public static void modificarCalle(int opcionModificarCalle, String nombreMunicipio){

        Scanner teclado = new Scanner(System.in);
        String [][] fichero_a_Modificar = leerFichero(nombreMunicipio + ".csv");
        String modificacion;
        String calle;

        switch (opcionModificarCalle) {
            case 0 -> //Atrás
                    System.out.println("Volviendo al menu principal...");
            case 1 -> {//Modificar Calle
                System.out.println("¿A qué calle desea cambiarle el nombre?");
                System.out.print("->");
                calle = teclado.next();
                for (int i = 0; i < fichero_a_Modificar.length; i++) {

                    if (fichero_a_Modificar[i][1].matches(calle)) {
                        System.out.println("Actualmente tenemos este nombre: " + calle + ".\n" +
                                "Escriba como quiere renombrarla:");
                        System.out.print("->");
                        modificacion = teclado.next();
                        fichero_a_Modificar[i][1] = modificacion;
                        escribirEnFichero(fichero_a_Modificar, nombreMunicipio + ".csv", false);
                    }
                }
            }
            case 2 -> {//Modificar Estado de la Calle
                System.out.println("¿A qué calle desea cambiarle el estado?");
                System.out.print("->");
                calle = teclado.next();
                for (int i = 0; i < fichero_a_Modificar.length; i++) {

                    if (fichero_a_Modificar[i][1].matches(calle)) {
                        System.out.println("Actualmente la calle " + calle + " está " + fichero_a_Modificar[i][2] + ".\n" +
                                "Escriba la modificación de estado:");
                        System.out.print("->");

                        modificacion = teclado.next();
                        fichero_a_Modificar[i][2] = modificacion;
                        escribirEnFichero(fichero_a_Modificar, nombreMunicipio + ".csv", false);
                    }
                }
            }
            case 3 -> {//Modificar Información Extra
                System.out.println("¿A qué calle desea cambiarle la Información Extra?");
                System.out.print("->");
                calle = teclado.next();
                for (int i = 0; i < fichero_a_Modificar.length; i++) {

                    if (fichero_a_Modificar[i][1].matches(calle)) {
                        System.out.println("Actualmente tenemos esta información: " + fichero_a_Modificar[i][3] + ".\n" +
                                "Escriba la información nueva que desea guardar:");
                        System.out.print("->");
                        modificacion = teclado.next();
                        fichero_a_Modificar[i][3] = modificacion;
                        escribirEnFichero(fichero_a_Modificar, nombreMunicipio + ".csv", false);
                    }
                }
            }
        }
    }

        public static void modificarMunicipio(int opcionModificarMunicipio, String nombreMunicipio){

            Scanner teclado = new Scanner(System.in);
            String [][] fichero_a_Modificar = leerFichero("listaMunicipios.csv");
            String modificacion;

                switch (opcionModificarMunicipio){
                    case 0: //Atrás
                        System.out.println("Volviendo al menu principal...");
                        break;
                    case 1://Modificar Nombre
                        for(int i=0; i<fichero_a_Modificar.length; i++){

                            if(fichero_a_Modificar[i][1].matches(nombreMunicipio)){
                                System.out.println("Actualmente tenemos este nombre: " + nombreMunicipio + ".\n" +
                                        "Escriba como quiere renombrarlo:");
                                System.out.print("->");
                                modificacion = teclado.next();
                                fichero_a_Modificar[i][1] = modificacion;
                                File fichero_a_Renombrar = new File(nombreMunicipio + ".csv");
                                File fichero_Renombrado = new File(modificacion + ".csv");
                                fichero_a_Renombrar.renameTo(fichero_Renombrado);
                                escribirEnFichero(fichero_a_Modificar,"listaMunicipios.csv", false);
                            }
                        }
                        break;
                    case 2://Modificar Código Postal
                        for(int i=0; i<fichero_a_Modificar.length; i++){

                            if(fichero_a_Modificar[i][1].matches(nombreMunicipio)){
                                System.out.println("Actualmente tenemos este código postal: " + fichero_a_Modificar[i][2] + ".\n" +
                                        "Escriba como quiere modificarlo:");
                                System.out.print("->");
                                modificacion = teclado.next();
                                fichero_a_Modificar[i][2] = modificacion;
                                escribirEnFichero(fichero_a_Modificar,"listaMunicipios.csv", false);
                            }
                        }
                        break;
                    case 3://Modificar Información Extra
                        for(int i=0; i<fichero_a_Modificar.length; i++){

                            if(fichero_a_Modificar[i][1].matches(nombreMunicipio)){
                                System.out.println("Actualmente tenemos esta información: " + fichero_a_Modificar[i][3] + ".\n" +
                                        "Escriba la información nueva que desea guardar:");
                                System.out.print("->");
                                modificacion = teclado.next();
                                fichero_a_Modificar[i][3] = modificacion;
                                escribirEnFichero(fichero_a_Modificar,"listaMunicipios.csv", false);
                            }
                        }
                        break;
                }
            }

            public static void eliminarCalle (String nombreMunicipio){
                Scanner teclado = new Scanner(System.in);
                String [][] fichero_a_Modificar = leerFichero(nombreMunicipio + ".csv");

                String calle;
                System.out.println("¿Qué calle desea eliminar de " + nombreMunicipio + "?");
                System.out.print("->");
                calle = teclado.next();
                int i=0;
                while(!fichero_a_Modificar[i][1].equals(calle)){
                    i++;
                }
                int numero_fila = i;
                String [][] fichero_Nuevo =  eliminarFila( fichero_a_Modificar, numero_fila);
                escribirEnFichero(fichero_Nuevo, nombreMunicipio + ".csv", false);
            }

            public static void eliminarMunicipio (String nombreMunicipio){
              String [][] fichero_a_Modificar = leerFichero("listaMunicipios.csv"); //Lee la Lista para borrar el municipio de la misma
                int i=0;
                while(!fichero_a_Modificar[i][1].equals(nombreMunicipio)){
                  i++;
                }
             int numero_fila = i;
                String [][] fichero_Nuevo =  eliminarFila( fichero_a_Modificar, numero_fila);
                escribirEnFichero(fichero_Nuevo, "listaMunicipios.csv", false);
                File municipio = new File(nombreMunicipio + ".csv");
                municipio.delete();
              }

         public static String[][] eliminarFila(String[][] fichero_a_Modificar, int numero_fila) {
               if (numero_fila < 0 || numero_fila >= fichero_a_Modificar.length) {
                  return fichero_a_Modificar;
               } else {
                  String[][] nueva = new String[fichero_a_Modificar.length - 1][fichero_a_Modificar[0].length];
                  int i;
                 for (i = 0; i < numero_fila; i++) {
                     nueva[i] = fichero_a_Modificar[i];
                    }
                   for (; i < nueva.length; i++) {
                     nueva[i] = fichero_a_Modificar[i + 1];
                  }
                   return nueva;
                     }
                    }

        public static void escribirMunicipioLista(String nombreMunicipio){

        Scanner teclado = new Scanner(System.in);
        String[][] nuevaLineaLista = new String[1][4];

            for(int i=0; i<nuevaLineaLista.length; i++){
                for(int j=0; j<nuevaLineaLista[i].length; j++){
                    if(j==0){
                        nuevaLineaLista[i][j] = String.valueOf(contarFilas("listaMunicipios.csv"));
                    }
                    if(j==1){
                        nuevaLineaLista[i][j] = nombreMunicipio;
                    }
                    if(j==2){
                        System.out.println("Introduzca el Código Postal de " + nombreMunicipio +":");
                        System.out.print("-> ");
                        nuevaLineaLista[i][j] = teclado.next();
                    }
                    if(j==3){
                        System.out.println("""
                                ¿Desea añadir alguna información extra?
                                -> 1 - Si
                                -> 2 - No""");
                        System.out.print("--> ");
                        String decisionInfoExtra;
                        while((teclado.hasNext("1"))||(!teclado.hasNext("2"))){
                            System.out.println("Opción Incorrecta. Porfavor Revise...");
                            System.out.print("-> ");
                            teclado.next();

                        }decisionInfoExtra = teclado.next();
                        if(decisionInfoExtra.equals("1")){
                            System.out.println("Introduzca la Información Extra que desee:");
                            nuevaLineaLista[i][j] = teclado.next();

                        }else{
                            nuevaLineaLista[i][j] = "No presenta ninguna información extra";
                        }
                    }
                }
            }
            escribirEnFichero(nuevaLineaLista, "listaMunicipios.csv", true);
        }

          public static void escribirEnFichero (String [][] nuevaLinea, String ruta, boolean NoSobreEscribir){

        try {
            FileWriter fr = new FileWriter(ruta,NoSobreEscribir);
            BufferedWriter escribir = new BufferedWriter(fr);
            for(int i=0;i<nuevaLinea.length;i++){
                for(int j=0; j<nuevaLinea[i].length; j++){
                    escribir.write(nuevaLinea[i][j]);
                    if(j<3){
                        escribir.write(";");
                    }


                }
                escribir.write("\n");
            }
            System.out.println("Guardado con éxito!");
            escribir.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar los datos en el fichero");
        }

    }


    public static String[][] leerFichero(String ruta){

        String[][] matriz_Fichero = {};
        File fichero = new File(ruta);

        try {
            Scanner sc = new Scanner(fichero);
            matriz_Fichero = new String[contarFilas(ruta)][contarColumnas(ruta)];

            int i=0;
            String fila;
            String [] array_fila;

            while (sc.hasNextLine()){
                fila = sc.nextLine();
                array_fila = fila.split(";");

                System.arraycopy(array_fila, 0, matriz_Fichero[i], 0, array_fila.length);
                i++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error en la lectura del fichero");
        }

        return matriz_Fichero;
    }

    public static void imprimirFichero(String[][] archivo){
        //Imprimir la matriz
        System.out.println("-------------------------------------------------- |");
        for (int i = 0; i < archivo.length; i++) {

            for (int j = 0; j < archivo[i].length; j++) {
                System.out.print(archivo[i][j] + "\t\t");
            }

            System.out.println();
            if(i==0){
                System.out.println("-------------------------------------------------- |");
            }
        }System.out.println("--------------------------------------------------");
    }


    public static int contarFilas(String ruta){
        int numeroFilas = 0;
        try { //Lector utilizado para contar las filas de la Lista de Municipios
            FileReader fr = new FileReader(ruta);
            BufferedReader contarLineas = new BufferedReader(fr);
            while(contarLineas.readLine()!=null){
                numeroFilas++;
            }
            return numeroFilas;
        } catch (IOException e) { //Excepciones en caso de que la lectura del fichero falle
            System.out.println("Ha ocurrido un error en la lectura de la Lista de Municipios");
        }

        return numeroFilas;
    }

    public static int contarColumnas(String ruta) {
        int numeroColumnas;
        String fila = "";

        File fichero = new File(ruta);
        try {
            Scanner sc = new Scanner(fichero);
            if (sc.hasNextLine()) {
                fila = sc.nextLine(); // se guarda la primera linea del archivo en el string fila
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error, no se puede abrir el fichero.");
        }
        numeroColumnas = fila.split(";").length; //se separa el contenido del string y se crea un array en con cada elemento
        // separado por ';' en el sting y el numero de elementos de ese String corresponde
        // al numero de columnas que debe tener la matriz

        return numeroColumnas;
    }


    public static int menuMain(){

        int opcion;
        Scanner teclado = new Scanner(System.in);

        //Informar de las opciones
        System.out.print(

                """

                        ---------------- Bienvenido a Calles Limpias ----------------
                         --> 0 - Salir\s
                         --> 1 - Consultar
                         --> 2 - Añadir
                         --> 3 - Modificar
                         --> 4 - Eliminar
                        Opción:"""

        );
        //recoger la opción

       while(!teclado.hasNextInt(1 | 2 | 3 | 4)){ //Verifica que el valor introducido es válido para este Menu
           System.out.println("Opción Incorrecta, porfavor revise...");
           System.out.print("-> ");
           teclado.next(); //Limpiamos la basura del Scanner
       }
       opcion = teclado.nextInt();
        return opcion;


    }

    public static int menuConsultar(){

        int opcionConsultar;
        Scanner teclado = new Scanner(System.in);

        //Informar de las opciones
        System.out.print(

                """

                        ---------------- Consultar ----------------
                         --> 0 - Atrás
                         --> 1 - Calles
                         --> 2 - Lista Municipios
                        Opción:"""

        );
        //recoger la opción

        while(!teclado.hasNextInt(1 | 2)){ //Verifica que el valor introducido es válido para este Menu
            System.out.println("Opción Incorrecta, porfavor revise...");
            System.out.print("->");
            teclado.next(); //Limpiamos la basura del Scanner
        }
        opcionConsultar = teclado.nextInt();
        return opcionConsultar;


    }

    public static int menuAñadir(){

        int opcionAñadir;
        Scanner teclado = new Scanner(System.in);

        //Informar de las opciones
        System.out.print(

                """

                        ---------------- Añadir ----------------
                         --> 0 - Atrás
                         --> 1 - Calle
                         --> 2 - Municipio
                        Opción:"""

        );
        //recoger la opción

        while(!teclado.hasNextInt(1 | 2)){ //Verifica que el valor introducido es válido para este Menu
            System.out.println("Opción Incorrecta, porfavor revise...");
            System.out.print("->");
            teclado.next(); //Limpiamos la basura del Scanner
        }
        opcionAñadir = teclado.nextInt();
        return opcionAñadir;


    }

    public static int menuModificar(){

        int opcionModificar;
        Scanner teclado = new Scanner(System.in);

        //Informar de las opciones
        System.out.print(

                """

                        ---------------- Modificar ----------------
                         --> 0 - Atrás
                         --> 1 - Calle
                         --> 2 - Municipio
                        Opción:"""

        );
        //recoger la opción

        while(!teclado.hasNextInt(1 | 2)){ //Verifica que el valor introducido es válido para este Menu
            System.out.println("Opción Incorrecta, porfavor revise...");
            System.out.print("->");
            teclado.next(); //Limpiamos la basura del Scanner
        }
        opcionModificar = teclado.nextInt();
        return opcionModificar;


    }

    public static int menuModificarCalle( String nombreMunicipio){

        int opcionModificarCalle;
        Scanner teclado = new Scanner(System.in);

        //Informar de las opciones
        System.out.print(

                "\n---------------- Modificar " + nombreMunicipio + " ----------------" + "\n" +
                        " --> 0 - Atrás" + "\n" +
                        " --> 1 - Calle" + "\n" +
                        " --> 2 - Estado de calle" + "\n" +
                        " --> 3 - Información Extra" + "\n" +
                        "Opción:"

        );
        //recoger la opción

        while(!teclado.hasNextInt(1 | 2 | 3)){ //Verifica que el valor introducido es válido para este Menu
            System.out.println("Opción Incorrecta, porfavor revise...");
            System.out.print("->");
            teclado.next(); //Limpiamos la basura del Scanner
        }
        opcionModificarCalle = teclado.nextInt();
        return opcionModificarCalle;
    }

    public static int menuModificarMunicipio( String nombreMunicipio){

        int opcionModificarMunicipio;
        Scanner teclado = new Scanner(System.in);

        //Informar de las opciones
        System.out.print(

                "\n---------------- Modificar " + nombreMunicipio + " ----------------" + "\n" +
                        " --> 0 - Atrás" + "\n" +
                        " --> 1 - Nombre Localidad" + "\n" +
                        " --> 2 - Código Postal" + "\n" +
                        " --> 3 - Información Extra" + "\n" +
                        "Opción:"

        );
        //recoger la opción

        while(!teclado.hasNextInt(1 | 2 | 3)){ //Verifica que el valor introducido es válido para este Menu
            System.out.println("Opción Incorrecta, porfavor revise...");
            System.out.print("->");
            teclado.next(); //Limpiamos la basura del Scanner
        }
        opcionModificarMunicipio = teclado.nextInt();
        return opcionModificarMunicipio;
    }

    public static int menuEliminar(){

        int opcionEliminar;
        Scanner teclado = new Scanner(System.in);

        //Informar de las opciones
        System.out.print(

                """

                        ---------------- Eliminar  ----------------
                         --> 0 - Atrás
                         --> 1 - Calle
                         --> 2 - Municipio
                        Opción:"""

        );
        //recoger la opción

        while(!teclado.hasNextInt(1 | 2)){ //Verifica que el valor introducido es válido para este Menu
            System.out.println("Opción Incorrecta, porfavor revise...");
            System.out.print("->");
            teclado.next(); //Limpiamos la basura del Scanner
        }
        opcionEliminar = teclado.nextInt();
        return opcionEliminar;
    }

    public static void pulsaCualquierTeclaParaContinuar() {
        System.out.println("pulsa la tecla enter para continuar...");
        try {
            System.in.read(); //espera una entrada por el usuario para continuar la ejecucion del programa
        } catch (Exception e) {
        }
    }

    public static void main (String[] args){

        int opcion = -1;
        File archivoLista = new File("listaMunicipios.csv");
        if(!archivoLista.exists()){ //Comprueba si la Lista existe
            crearFichero("listaMunicipios.csv","IDlocalidad; Nombre localidad; Código Postal; Información Extra\n" ); //Si es negativo lo crea
        }//En ambos casos sigue el curso normal

        while(opcion!=0){
            opcion = menuMain();

            switch (opcion){

                case 1: //CONSULTAR
                    System.out.println("Accediendo al menu de Consultar...");
                    int opcionConsultar = -1;
                    String ruta;
                    String nombreMunicipio;
                    while(opcionConsultar!=0){
                        opcionConsultar = menuConsultar();
                        Scanner tecladoConsultar = new Scanner(System.in);
                        switch (opcionConsultar) {
                            case 0 -> //Atrás
                                    System.out.println("Volviendo al menu principal...");
                            case 1 -> { //Consultar Calles por cada municipio
                                System.out.println("¿De qué Municipio desea consultar las calles?");
                                System.out.print("-> ");
                                nombreMunicipio = tecladoConsultar.next();
                                ruta = nombreMunicipio + ".csv";
                                File archivoCalle = new File(ruta);
                                if (!archivoCalle.exists()) {
                                    System.out.println("El Municipio " + nombreMunicipio + " no se encuentra en nuestros datos.");
                                    break;
                                }
                                String[][] archivo = leerFichero(ruta);
                                imprimirFichero(archivo);
                                pulsaCualquierTeclaParaContinuar();
                            }
                            case 2 -> {//Consultar todos los Municipios
                                File archivoListaMunicipios = new File("listaMunicipios.csv");
                                if (!archivoListaMunicipios.exists()) {
                                    System.out.println("No se ha podido encontrar la lista de Municipios");
                                    break;
                                }
                                String[][] ficheroLista = leerFichero("listaMunicipios.csv");
                                imprimirFichero(ficheroLista);
                                pulsaCualquierTeclaParaContinuar();
                            }
                        }
                    }
            break;

                case 2: //AÑADIR
                    System.out.println("Accediendo al menu de Añadir...");
                    int opcionAñadir = -1;
                        while(opcionAñadir!=0){
                           opcionAñadir = menuAñadir();
                            Scanner tecladoAñadir = new Scanner(System.in);
                            switch (opcionAñadir) {
                                case 0 -> //Atrás
                                        System.out.println("Volviendo al menu principal...");
                                case 1 -> { // AÑADIR CALLE
                                    System.out.println("¿De qué Municipio desea añadir una Calle?:");
                                    System.out.print("-> ");
                                    nombreMunicipio = tecladoAñadir.next();
                                    ruta = nombreMunicipio + ".csv";
                                    crearCalle(ruta, nombreMunicipio);
                                }
                                case 2 ->//AÑADIR MUNICIPIO
                                        crearMunicipio();
                            }//FIN Switch Añadir
                        }


            break;
                case 3: //MODIFICAR
                    System.out.println("Accediendo al menu de Modificar...");
                    Scanner tecladoModificar = new Scanner(System.in);
                    int opcionMunicipios = -1;
                    while(opcionMunicipios!=0){
                        opcionMunicipios = menuModificar();
                        switch (opcionMunicipios) {
                            case 0 -> //Atrás
                                    System.out.println("Volviendo al menu principal...");
                            case 1 -> { //MODIFICAR CALLE
                                int opcionModificarCalle = -1;
                                System.out.println("¿De qué Municipio es la calle que desea modificar?:");
                                System.out.print("-> ");
                                nombreMunicipio = tecladoModificar.next();
                                ruta = nombreMunicipio + ".csv";
                                File archivoCalle = new File(ruta);
                                if (!archivoCalle.exists()) {
                                    crearMunicipiosinPedirNombre(nombreMunicipio);
                                    break;
                                }
                                while (opcionModificarCalle != 0) {
                                    opcionModificarCalle = menuModificarCalle(nombreMunicipio);
                                    modificarCalle(opcionModificarCalle, nombreMunicipio);
                                }
                            }
                            case 2 -> { //MODIFICAR MUNICIPIO
                                int opcionModificarMunicipio = -1;
                                System.out.println("¿Qué Municipio desea modificar?:");
                                System.out.print("-> ");
                                nombreMunicipio = tecladoModificar.next();
                                File archivoMunicipios = new File(nombreMunicipio + ".csv");
                                if (!archivoMunicipios.exists()) {
                                    crearMunicipiosinPedirNombre(nombreMunicipio);
                                    break;
                                }
                                while (opcionModificarMunicipio != 0) {
                                    opcionModificarMunicipio = menuModificarMunicipio(nombreMunicipio);
                                    modificarMunicipio(opcionModificarMunicipio, nombreMunicipio);
                                }
                            }
                        }//FIN Switch modificarMunicipios
                    }
            break;
                case 4://ELIMINAR
                    System.out.println("Accediendo al menu de Eliminar...");
                    int opcionEliminar = -1;
                    while(opcionEliminar!=0){
                        opcionEliminar = menuEliminar();
                        Scanner tecladoEliminar = new Scanner(System.in);
                        switch (opcionEliminar) {
                            case 0 -> //Atrás
                                    System.out.println("Volviendo al menu principal...");
                            case 1 -> { // ELIMINAR CALLE
                                System.out.println("¿De qué Municipio desea eliminar una Calle?");
                                System.out.print("-> ");
                                nombreMunicipio = tecladoEliminar.next();
                                ruta = nombreMunicipio + ".csv";
                                File archivoCalle = new File(ruta);
                                if (!archivoCalle.exists()) {
                                    System.out.println("La calle que intenta eliminar no existe");
                                    break;
                                }
                                eliminarCalle(nombreMunicipio);
                            }
                            case 2 -> {//ELIMINAR MUNICIPIO
                                System.out.println("¿Qué Municipio desea eliminar?");
                                System.out.print("-> ");
                                nombreMunicipio = tecladoEliminar.next();
                                ruta = nombreMunicipio + ".csv";
                                File archivoMunicipio = new File(ruta);
                                if (!archivoMunicipio.exists()) {
                                    System.out.println("El Municipio que intenta eliminar no existe");
                                    break;
                                }
                                eliminarMunicipio(nombreMunicipio);
                            }
                        }
                    }
            break;
   }// FIN Main Switch
  }
 }
}