package Codigo;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.text.SimpleDateFormat;
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
    
    /**
     * Metodo para poblar la coleccion de Ciudadanos con datos iniciales
     */
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
     * Metodo para agregar un Ciudadano a la coleccion de Ciudadano, cuyos parametros
     * permite construir el Ciudadano dependiendo el caso
     * 
     * @param nombre Nombre/Identidad del Ciudadano
     * @param sexo Sexo del Ciudadano
     * @param habilitado Habilitado para sufragar
     * @param fecha Fecha de nacimiento
     * @param rut Rut del Ciudadano para identificarlo
     * @return Retorna un mensaje (String) que indica el resultado de la operacion
     */
    public String agregarCiudadanos(String nombre, Boolean sexo, Boolean habilitado, String fecha, String rut){
        
        Ciudadano ciudadanoDatos;
        
        //Verifico si es menor de edad
        if(calcularEDAD(fecha) < 18)
            //De forma automatica, no hablita el sufragio al ciudadano
            ciudadanoDatos = new CiudadanoMenor(fecha, nombre, sexo, false);   
        else
            //En este caso, adquiere el contenido de habilitado
            ciudadanoDatos = new Ciudadano(nombre, sexo, habilitado);
            
        if(mapaCiudadano.containsKey(rut))
            return "El rut: " + rut + " ya se encuentra ocupado...";
        
        setMap(rut, ciudadanoDatos);
        return "Se agrego el ciudadano de forma exitosa";
    }
    
    /**
     * Metodo para agregar Ciudadanos desde un archivo de formato CSV
     * @return Retorna un mensaje (String) que indica el termino de la operacion y
     * los Ciudadanos que se repitieron por RUT
     * @throws IOException Error de Input/OutPut
     */
    public String agregarCiudadanos() throws IOException {
        
        //Declaro variable para tener el directorio dentro del proyecto
        File dir = new File("./Datos_CSV/Ciudadano/");
        
        //variable para guardar nombre del archivo
        String nombre [] = dir.list();
        
        //Archivo csv
        CSV fileCiudadano;
        
        //Declaro la variable tipo Ciudadano para ingresar datos a cada atributo
        Ciudadano persona;
        String linea;//para leer cada linea del archivo
        String repetidos = "";
        
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
                        
                        //Pregunto si el String contiene ese caracter(se utiliza para las fechas)
                        if( !cadena.contains("/") )
                            persona = new Ciudadano(cadena, sexo, habilitado);
                        else
                        {
                            name = fileCiudadano.get_csvField(linea, 4);
                            
                            //Se calcula la edad que determina el tipo de Ciudadano
                            if(calcularEDAD(cadena) < 18)
                                persona = new CiudadanoMenor(cadena, name, sexo, false);
                            else
                                persona = new Ciudadano(name, sexo, habilitado);
                            
                        }
                        //Ingreso el ciudadano al mapa
                        setMap(fileCiudadano.get_csvField(linea,0), persona);
                    }
                    else
                        repetidos += "\nRUT :" + fileCiudadano.get_csvField(linea,0) + " Ya existe...";
                    
                    //Paso a la sgte linea
                    linea = fileCiudadano.nextLine();
                }
                //CLOSE FILE
                fileCiudadano.close();
            }
        
        return "Ya termino de ejecutar esta funcion\n" + repetidos;
    }        
    
    /**
     * Metodo para mostrar todos los Ciudadanos que contiene la Coleccion de Ciudadanos
     * @return Un String con todos los Ciudadanos y su informacion
     */
    public String mostrarCiudadanos(){
        
        //Variable tipo ciudadano para acceder a los datos
        Ciudadano ciudadano;
        String mostrar = "";
 
        //Pregunto si el mapa esta vacio...
        if( vacioCiudadanos() ) return "La coleccion esta vacia";
        
        //Recorre cada mapa de la coleccion mostrando la key y contenido
        for(Map.Entry<String, Ciudadano> mapa : mapaCiudadano.entrySet())
        {
            ciudadano = mapa.getValue();
            mostrar += "\nRUT: " + mapa.getKey() + ciudadano.mostrarCiudadano() + "\n";
        }
        
        return mostrar;
    }
    
    /**
     * Metodo para modificar un Ciudadano a partir del rut asociado
     * @param nombre Nombre del Ciudadano
     * @param sexo Sexo del Ciudadano
     * @param habilitado Habilitado para sufragar
     * @param fecha Fecha de nacimiento
     * @param rut Rut del Ciudadano que desea modificar la informacion
     * @return Un mensaje de que la operacion tuvo fallas o muestra los cambios del Ciudadano
     */
    public String modificarCiudadano(String nombre, Boolean sexo, Boolean habilitado, String fecha, String rut){
        
        //Pregunto primero si la coleccion esta vacia y despues si el ciudadano
        //asociado al rut esta dentro de la coleccion
        
        if( vacioCiudadanos() ) return "La coleccion esta vacia";
        if( !rutCiudadano(rut) ) return "La coleccion No contiene ese rut:" + rut;
        
        //Imprimo los datos inciales
        
        String cambios = "";
        Ciudadano ciudadano = mapaCiudadano.get(rut);
        cambios += "\nDATOS ACTUALES:\nRUT: " + rut + ciudadano.mostrarCiudadano();
   
        if(calcularEDAD(fecha) < 18)
            ciudadano = new CiudadanoMenor(fecha, nombre, sexo, false);   
        else
            ciudadano = new Ciudadano(nombre, sexo, habilitado);
        
        //Se reemplaza los datos antiguos por nuevos datos ingresados y los imprime
        mapaCiudadano.replace(rut, ciudadano);
        cambios += "\nNuevos datos: " + ciudadano.mostrarCiudadano();
        
        return cambios;
    }
  
    
    /**
     * Metodo para eliminar un Ciudadano con el rut asociado
     * @param rut Rut del Ciudadano a eliminar
     * @return Un mensaje que indica posibles fallos en la operacion o que se elimino el
     * Ciudadano y mostrando su informacion
     */
    public String eliminarCiudadano(String rut) {
        
        //Pregunta si la coleccion esta vacia y si no esta el ciudadano asociado
        // al rut ingresado...
        if( vacioCiudadanos() ) return "La coleccion Ciudadadano esta vacia";
        if( !rutCiudadano(rut) ) return "La coleccion NO contiene este rut: " + rut;
        
        
        Ciudadano ciudadano = mapaCiudadano.remove(rut);
        ciudadano.mostrarCiudadano();
        
        return "\nEl siguiente ciudadano fue eliminado:\nRUT: " + rut + ciudadano.mostrarCiudadano();
    }
    
    /**
     * Metodo para verificar que la coleccion este vacia
     * @return True = si esta vacio / False = contiene minimo un Ciudadano 
     */
    public boolean vacioCiudadanos() {
        //Pregunto si esta vacia la coleccion
        if(mapaCiudadano.isEmpty())
            return true;
        
        return false;
    }
    
    /**
     * Metodo para verificar que el Ciudadano dado rut se no encuentra en la coleccion
     * @param rut Rut del Ciudadano para averiguar
     * @return False = No esta en la Coleccion / True = Contiene el Ciudadano 
     */
    public boolean rutCiudadano(String rut){
        //Pregunto si la coleccion contiene el rut entregado
        if(!mapaCiudadano.containsKey(rut))
            return false;
        
        return true;
    }
    
    /**
     * Metodo para calcular la Edad de un Ciudadano con la fecha de nacimiento.
     * Permite saber el tipo de Ciudadano
     * @param fecha Fecha de nacimiento 
     * @return La edad del Ciudadano en años
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
     * Metodo para mostrar el Ciudadano mas viejo de la Coleccion
     * @return Un String que indica tal Ciudadano o alguna falla en la operacion
     */
    public String mostrarCiudadanoMasViejo()   {
        
        //Pregunto si el mapa esta vacio...
        if( vacioCiudadanos() ) return "La coleccion Ciudadano esta vacia";
        
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
        
        return "El rut del ciudadano mas viejo es: " + respaldoDefinitivo ;
        
    }
    
    /**
     * Metodo para mostrar todos los Ciudadanos de la Coleccion que esten HABILITADOS para sufragar
     * @return Un String con todos los Ciudadanos Habilitados
     */
    public String mostrarHabilitados() {
        
        //Verifico si esta vacia la coleccion
        if( vacioCiudadanos() ) return "La coleccion esta vacia";
        
        //Variable ciudadano y Arraylist de ciudadanos
        Ciudadano ciudadano;
        String habilitados = "";
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
            return "\nNo hay ciudadanos habilitados\n";
        
        
        //Imprime todos los ciudadanos guardados en la lista
        for(int i = 0; i < habilitado.size(); i++)
            habilitados += "\n" + habilitado.get(i).getNombre();
        
        return habilitados;
    }
    
    /**
     * Metodo para verificar que la fecha sea del formato dd/MM/yyyy
     * @param fecha Una fecha desde Input
     * @return true = formato inadecuado / false = formato correcto
     */
    public boolean formatoFECHA( String fecha ){
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        
        try{
            Date test = formato.parse(fecha);
        } catch (Exception e){
            return true;
        }
        
        return false;
    }
    
}


   
