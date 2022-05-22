/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
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
    
    /**
     * Metodo para agregar Ciudadanos al HashMap leyendo los datos desde teclado
     *  
     * @param ciudadanoDatos un ciudadano sin datos almacenados
     * @throws IOException excepciones de input/output
     */
    public void agregarCiudadanos(Ciudadano ciudadanoDatos) throws IOException{
        
        //Instancio la variable de lectura desde teclado
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        
        //Ingresa el metodo para completar los datos desde teclado
        ciudadanoDatos = ingresarDatosCiudadano();
        
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
                    
                    //Pregunta si el rut ya existe
                    if( !(this.mapaCiudadano.containsKey(fileCiudadano.get_csvField(linea,0))) )
                    {
                        persona = new Ciudadano();
                        
                        //Guardo los atributos del ciudadano                        
                        persona.setNombre(fileCiudadano.get_csvField(linea,1));
                        persona.setSexo(Boolean.parseBoolean(fileCiudadano.get_csvField(linea,2)));
                        persona.setHabilitado(Boolean.parseBoolean(fileCiudadano.get_csvField(linea,3)));
                        
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
            ciudadano = mapa.getValue();
            System.out.println("\nRUT: " + mapa.getKey());
            valorCiudadano(ciudadano);
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
        valorCiudadano(ciudadano);
        
        //Se reemplaza los datos antiguos por nuevos datos ingresados y los imprime
        ciudadano = ingresarDatosCiudadano();
        mapaCiudadano.replace(rut, ciudadano);
        System.out.println("\nNuevos datos: ");
        valorCiudadano(ciudadano);
    }
    
    /**
     * Metodo para mostrar el nombre, sexo y permiso de sufragio del ciudadano
     * @param ciudadano un ciudadano con datos almacenados
     * @throws IOException excepciones de input/output
     */
    public void valorCiudadano(Ciudadano ciudadano) throws IOException{
            
        //Imprime el nombre, sexo y permiso para sufragar
            System.out.println("NOMBRE: " + ciudadano.getNombre());
            
            if(ciudadano.isSexo())
                System.out.println("SEXO: Hombre");
            else
                System.out.println("SEXO: Mujer");
            
            if(ciudadano.isHabilitado())
                System.out.println("PERMISO PARA SUGRAGAR: Habilitado");
            else
                System.out.println("PERMISO PARA SUFRAGAR: No Habilitado");
    }
    
    /**
     * Metodo para leer el nombre(String), sexo(bool)y habilitado(bool)
     * @return retorna un ciudadano con datos ingresados
     * @throws IOException excepciones de input/output
     */
    public Ciudadano ingresarDatosCiudadano() throws IOException{
        
        //Declarar variable tipo Ciudadano y un lector
        Ciudadano ciudadano = new Ciudadano();
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        
        //Se lee nombre, sexo y habilitado...
        System.out.println("\nIngrese nombre...");
        ciudadano.setNombre(campo.readLine());
        
        //Para los datos tipo booleano hay excepciones con respecto los datos
        //que se ingreasn en la misma consola...
        
        System.out.println("\nIndique el sexo\ntrue = hombre, false = mujer.\n"
                + "Si ingresa otro valor diferente a esos 2, se tomara como mujer");
        ciudadano.setSexo(Boolean.parseBoolean(campo.readLine()));
        
        System.out.println("\nIndique el permiso de sufragar\ntrue = habilitado, false = no habilitado.\n"
                + "Si ingresa otro valor diferente a esos 2, se tomara como No habilitado");
        ciudadano.setHabilitado(Boolean.parseBoolean(campo.readLine()));
        
        return ciudadano;
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
        valorCiudadano(mapaCiudadano.remove(rut));
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
                //Escribo en el archivo su RUT(key), NOMBRE, SEXO(boolean) y HABILITADO(boolean)
                //En formato csv
                ciudadano = mapa.getValue();
                escribirDatos.write(mapa.getKey() + ";" + ciudadano.getNombre() + ";"
                        + ciudadano.isSexo() + ";" + ciudadano.isHabilitado() + "\n");
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
       
        //quita los guiones y los pasa a int, despues los va comparando y imprime el mas joven
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


   
