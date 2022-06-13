package Codigo;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author vicho
 */
public class CiudadanoPorRut {
    
    /**
     * mapaCiudadano: Coleccion de Ciudadano, HashMap cuya clave es el rut
     * en formato String y el valor la clase Ciudadano
     */
    private HashMap<String, Ciudadano> mapaCiudadano;
    
    /**
     * Constructor sin parametros
     */
    public CiudadanoPorRut(){
        this.mapaCiudadano = new HashMap();
    }
    
    /**
     * Getter de mapaCiudadano
     * 
     * @return retorna la coleccion de ciudadanos 
     */
    public HashMap<String, Ciudadano> getMap() {
        return mapaCiudadano;
    }

    /**
     * Setter de mapaCiudadano
     *
     * @param ciudadano los datos de un ciudadano
     * @param rut el rut como key
     * 
     */
    public void setMap(String rut, Ciudadano ciudadano) {
        mapaCiudadano.put(rut, ciudadano);
    }
    
    public void datosINICIALES(){
        
        Ciudadano ciudadano = new Ciudadano();
        
        ciudadano.setNombre("Mr Juan");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(true);
        setMap("12345678-9", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Vladimir Putin");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(true);
        setMap("6666666-6", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Barack Obama");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(true);
        setMap("0000000-0", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Michelle Obama");
        ciudadano.setSexo(false);
        ciudadano.setHabilitado(true);
        setMap("10000000-0", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Donald Trump");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(false);
        setMap("9231456-k", ciudadano);
        
        CiudadanoMenor mm = new CiudadanoMenor();
        mm.setNombre("Jack Sparrow");
        mm.setSexo(true);
        mm.setNacimiento("12/06/2022");
        if( calcularEDAD(mm.getNacimiento()) < 18 )
            mm.setHabilitado(false);
        else
            mm.setHabilitado(true);
        
        setMap("100000000-0", mm);
    }
    
    /**
     * Metodo para agregar Ciudadanos al HashMap leyendo los datos desde teclado
     *  
     * @param ciudadanoDatos un ciudadano sin datos almacenados
     * @throws IOException excepciones de input/output
     */
    public void agregarCiudadanos(Ciudadano ciudadanoDatos) throws IOException{
        
        //Instancio la variable de lectura desde teclado
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        
        //Variables para almacenar datos de input
        String fecha, nombre;
        Boolean sexo, habilitado;
        
        //Se pide los datos al usuario
        System.out.println("\nIngrese nombre y apellido");
        nombre = campo.readLine();
        
        System.out.println("\nIndique el sexo\ntrue = hombre, false = mujer.\n"
                + "Si ingresa otro valor diferente a esos 2, se tomara como mujer");
        sexo = Boolean.parseBoolean(campo.readLine());
        
        System.out.println("Ingrese la fecha de nacimiento (formato dd/mm/yyyy)");
        fecha = campo.readLine();
        
        //Verifico si es menor de edad
        if(calcularEDAD(fecha) < 18)
            //De forma automatica, no hablita el sufragio al ciudadano
            ciudadanoDatos = new CiudadanoMenor(fecha, nombre, sexo, false);   
        else
        {
            //En este caso, se debe escoger
            System.out.println("\nIndique el permiso de sufragar\ntrue = habilitado, false = no habilitado.\n"
                + "Si ingresa otro valor diferente a esos 2, se tomara como No habilitado");
            habilitado = Boolean.parseBoolean(campo.readLine());
            
            ciudadanoDatos = new Ciudadano(nombre, sexo, habilitado);
        }
            
        //Ingresar un rut como clave unica
        System.out.println("\nIngrese un rut unico para identificar");
        String nuevoRut = campo.readLine();
        
        //Ciclo para asegurar un Rut unico
        while(mapaCiudadano.containsKey(nuevoRut))
        {
            System.out.println("\nEl Rut " + nuevoRut + " esta OCUPADO.Favor de ingresar otro");
            nuevoRut = campo.readLine();
        }
        
        //Ingresa el dato Ciudadano al mapa
        setMap(nuevoRut, ciudadanoDatos);
    }
    
    /**
     * Metodo para agregar ciudadanos a partir de un archivo CSV
     * @throws IOException excepciones de input/output
     */
    public void agregarCiudadanos() throws IOException {
        
        //Declaro variable para tener el directorio dentro del proyecto
        File dir = new File("./Datos_CSV/Ciudadano/");
        
        //variable para guardar nombre del archivo
        String nombre [] = dir.list();
        
        //Archivo csv
        CSV fileCiudadano;
        
        //Declaro la variable tipo Ciudadano para ingresar datos a cada atributo
        Ciudadano persona;
        String linea;//para leer cada linea del archivo
        
        //Busco el archivo tipo csv
        for(String open : nombre)
            //Verifico si el nombre del archivo termina en ".csv"
            if(open.endsWith(".csv") == true)
            {
                //obtengo el archivo y se lee la primera linea
                fileCiudadano = new CSV("./Datos_CSV/Ciudadano/", open);
                linea = fileCiudadano.firstLine();
                
                //EOF?
                while(linea != null)
                {
                    //Variables para almacenar datos
                    String cadena, name;
                    Boolean sexo, habilitado;
                    
                    //Pregunta si el rut ya existe
                    if( !(this.mapaCiudadano.containsKey(fileCiudadano.get_csvField(linea,0))) )
                    {
                        //Guardo los datos        
                        cadena = fileCiudadano.get_csvField(linea,1);
                        sexo = Boolean.parseBoolean(fileCiudadano.get_csvField(linea,2));
                        habilitado = Boolean.parseBoolean(fileCiudadano.get_csvField(linea,3));
                        
                        
                        if( cadena.contains(" ") )
                            persona = new Ciudadano(cadena, sexo, habilitado);
                        else
                        {
                            name = fileCiudadano.get_csvField(linea, 4);
                            
                            if(calcularEDAD(cadena) < 18)
                                habilitado = false;
                            
                            persona = new CiudadanoMenor(cadena, name, sexo, habilitado);
                        }
                        //Ingreso el ciudadano al mapa
                        setMap(fileCiudadano.get_csvField(linea,0), persona);
                    }
                    else
                        System.out.println("RUT :" + fileCiudadano.get_csvField(linea,0) + " Ya existe...");
                    
                    //Paso a la sgte linea
                    linea = fileCiudadano.nextLine();
                }
                //CLOSE FILE
                fileCiudadano.close();
            }   
    }        
    
    /**
     * /Metodo para mostrar los ciudadanos que hay en la coleccion
     * @throws IOException excepciones de input/output
     */
    public void mostrarCiudadanos() throws IOException{
        
        //Variable tipo ciudadano para acceder a los datos
        Ciudadano ciudadano;
 
        //Pregunto si el mapa esta vacio...
        if( vacioCiudadanos() ) return;
        
        //Recorre cada mapa de la coleccion mostrando la key y contenido
        for(Map.Entry<String, Ciudadano> mapa : mapaCiudadano.entrySet())
        {
            System.out.println("\nRUT: " + mapa.getKey());
            ciudadano = mapa.getValue();
            ciudadano.mostrarCiudadano();
        }
    }
    
    /**
     * Metodo para modificar los datos de un ciudadano dado rut
     * @param rut el rut de un ciudadano
     * @throws IOException excepciones de input/output
     */
    public void modificarCiudadano(String rut) throws IOException{
        
        //Pregunto primero si la coleccion esta vacia y despues si el ciudadano
        //asociado al rut esta dentro de la coleccion
        
        if( vacioCiudadanos() ) return;
        if( !rutCiudadano(rut) ) return;
        
        //Imprimo los datos inciales
        Ciudadano ciudadano = mapaCiudadano.get(rut);
        System.out.println("\nDATOS ACTUALES:\nRUT: "+ rut);
        ciudadano.mostrarCiudadano();
        
        //Se hace el mismo procedimiento en AgregarCiudadano(parametro) para 
        //obtener los datos de input
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        
        String fecha, nombre;
        Boolean sexo, habilitado;
        
        System.out.println("\nIngrese nombre y apellido");
        nombre = campo.readLine();
        
        System.out.println("\nIndique el sexo\ntrue = hombre, false = mujer.\n"
                + "Si ingresa otro valor diferente a esos 2, se tomara como mujer");
        sexo = Boolean.parseBoolean(campo.readLine());
        
        System.out.println("Ingrese la fecha de nacimiento (formato dd/mm/yyyy)");
        fecha = campo.readLine();
        
        if(calcularEDAD(fecha) < 18)
            ciudadano = new CiudadanoMenor(fecha, nombre, sexo, false);   
        else
        {
            System.out.println("\nIndique el permiso de sufragar\ntrue = habilitado, false = no habilitado.\n"
                + "Si ingresa otro valor diferente a esos 2, se tomara como No habilitado");
            habilitado = Boolean.parseBoolean(campo.readLine());
            
            ciudadano = new Ciudadano(nombre, sexo, habilitado);
        }
        
        //Se reemplaza los datos antiguos por nuevos datos ingresados y los imprime
        mapaCiudadano.replace(rut, ciudadano);
        System.out.println("\nNuevos datos: ");
        ciudadano.mostrarCiudadano();
    }
  
    
    /**
     * Metodo para eliminar un ciudadano
     * @param rut el rut de un ciudadano
     * @throws IOException excepciones de input/output
     */
    public void eliminarCiudadano(String rut) throws IOException {
        
        //Pregunta si la coleccion esta vacia y si no esta el ciudadano asociado
        // al rut ingresado...
        if( vacioCiudadanos() ) return;
        if( !rutCiudadano(rut) ) return;
        
        //Si esta, se elimina el ciudadano e imprime sus datos
        System.out.println("\nEl siguiente ciudadano fue eliminado");
        System.out.println("RUT:" + rut);
        Ciudadano ciudadano = mapaCiudadano.remove(rut);
        ciudadano.mostrarCiudadano();
    }
    
    /**
     * Metodo para verificar si la coleccion esta vacia.
     * @return true en caso de que este vacia y false que hay al menos 1
     * @throws IOException excepciones de input/output
     */
    public boolean vacioCiudadanos() throws IOException{
        //Pregunto si esta vacia la coleccion
        if(mapaCiudadano.isEmpty())
        {
            System.out.println("No hay ciudadanos registrados");
            return true;
        }
        return false;
    }
    
    /**
     * Metodo para verificar que el ciudadano asociado al rut se encuentra dentro
     * de la coleccion
     * @param rut rut del ciudadano
     * @return false que no lo encontro y true si lo encontro.
     * @throws IOException excepciones de input/output
     */
    public boolean rutCiudadano(String rut) throws IOException{
        //Pregunto si la coleccion contiene el rut entregado
        if(!mapaCiudadano.containsKey(rut))
        {
            System.out.println("No hay ciudadano con rut: " + rut);
            return false;
        }
        return true;
    }
    
    /**
     * Metodo para calcular la edad de un ciudadano con la fecha de nacimiento
     * y la fecha actual
     * 
     * @param fecha fecha de nacimiento
     * @return La edad en formato Integer
     */
    public int calcularEDAD(String fecha)
    {
        //Transformo el formato String a LocalDate
        LocalDate birth = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate actual = LocalDate.now();
        
        //Calculo la diferencia
        Period periodo = Period.between(birth, actual);
        
        return periodo.getYears();
    }
    /**
     * Metodo para generar archivo.csv a paritr de lo que contenga la coleccion
     * @throws IOException excepciones de input/output
     */
    public void archivoCiudadano() throws IOException{
        
        //Estrucutra de intento para prevenir errores de ejecucion
        try
        {
            //Declaro una variable string que contenga el directorio de salido y nombre del archivo
            //Y variable tipo Archivo
            String ruta = "./Salida_CSV/Ciudadano/CIUDADANOS.csv";        
            File nuevoArchivo = new File(ruta);
            
            //Pregunto si existe el archivo (CIUDADANOS.csv en este caso)
            if(!nuevoArchivo.exists())
                nuevoArchivo.createNewFile(); //Se crea el archivo
            
            //Declaro variable tipo Archivo/Escritura para modificar dentro del archivo
            //este mismo apunta hacia el dato tipo Archivo
            FileWriter archivoEscritura = new FileWriter(nuevoArchivo);
            
            //Declaro una variable tipo escritura para escribir en el Archivo/Escritura
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            
            //Variable para obtener los datos
            Ciudadano ciudadano;
            
            //Recorro la coleccion
            for(Map.Entry<String, Ciudadano> mapa: mapaCiudadano.entrySet())
            {
                //Escribo en el archivo su RUT(key) y los datos del ciudadano segun el caso
                //En formato csv
                ciudadano = mapa.getValue();
                escribirDatos.write(mapa.getKey() + ";" + ciudadano.formato_csv());
            }
            //Cierro el archivo.
            escribirDatos.close();
        }
        catch(Exception e)//En caso de encontrar un error
        {
            e.printStackTrace();//Imprime el supuesto error en la consola
        }
            
    }
    
    /**
     * Metodo para mostrar el ciudadano mas viejo
     * @throws IOException excepciones de input/output
     */
    public void mostrarCiudadanoMasViejo()  throws IOException {
        
        //Pregunto si el mapa esta vacio...
        if( vacioCiudadanos() ) return;
        
        //Variables tipo aux
        String rut,respaldo,respaldoDefinitivo = "";
         int joven= 999999999, comparar;
       
        //quita los guiones y los pasa a int, despues los va comparando y imprime el mas viejo
        for(Map.Entry<String, Ciudadano> mapa : mapaCiudadano.entrySet())
        {
            rut= mapa.getKey();
            respaldo = rut;
            rut = rut.replace("-", "");
            rut = rut.replace("k", "");
            
            comparar = Integer.parseInt(rut);
            if(comparar<joven){
                
                joven = comparar;
                respaldoDefinitivo=respaldo;
            }            
        }
        
        System.out.println("El rut del ciudadano mas viejo es: " + respaldoDefinitivo );
        
    }
    
    /**
     * Metodo para mostrar todos los ciudadanos habilitados
     * @throws IOException excepciones de input/output
     */
    public void mostrarHabilitados() throws IOException{
        
        //Verifico si esta vacia la coleccion
        if( vacioCiudadanos() ) return;
        
        //Variable ciudadano y Arraylist de ciudadanos
        Ciudadano ciudadano;
        ArrayList<Ciudadano> habilitado = new ArrayList<Ciudadano>();
        
        //Retorna un ciudadano de la coleccion, si este esta habilitado se agrega al
        //Arraylist
        for(Map.Entry<String, Ciudadano> mapa : mapaCiudadano.entrySet())
        {
            ciudadano = mapa.getValue();
            if( ciudadano.isHabilitado() ) habilitado.add(ciudadano);
        }
        
        //Hago exception en caso de que el tamaño de la lista sea 0
        if(habilitado.isEmpty())
        {
            System.out.println("\nNo hay ciudadanos habilitados\n");
            return;
        }
        
        //Imprime todos los ciudadanos guardados en la lista
        for(int i = 0; i < habilitado.size(); i++)
            System.out.println(habilitado.get(i).getNombre());
    }
    
}


   
