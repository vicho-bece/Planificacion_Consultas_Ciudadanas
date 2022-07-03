package Codigo;



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
public class ConsultaPorID {
    
    /**
     * treemap: Coleccion de Consultas multiples y binarias
     */
    private TreeMap<Integer, Consulta> treemap;
    
    /**
     * Constructor sin parametros
     */
    public ConsultaPorID(){
        this.treemap = new TreeMap();
    }

    /**
     * Getter de treemap
     * 
     * @return retorna la coleccion de consultas
     */
    public TreeMap<Integer, Consulta> getTreemap() {
        return treemap;
    }
    
    /**
     * Setter de treemap
     * 
     * @param consulta una consulta tipo binaria/multiple
     * @param key la clave de la consulta
     */
    public void agregarConsultas(Consulta consulta, Integer key){
        treemap.put(key, consulta);
    }
    
    //METODOS PARA ELEMENTOS DEL TREEMAP
    
    
    /**
     * Metodo para agregar datos iniciales a la Coleccion de Consultas
     */
    public void datosINICIAL(){
        Consulta consulta = new ConsultaMultiple();
        consulta.setEnunciado("Estas de acuerdo con el nuevo gobierno?");
        consulta.setFecha("02/05/2022");
        
        agregarConsultas(consulta, 1);
        
        consulta = new ConsultaBinaria();
        consulta.setEnunciado("aaa");
        consulta.setFecha("02/05/2022");
        agregarConsultas(consulta, 2);
    }
    
    /**
     * Metodo para agregar una consulta con datos de los parametros entregados
     * @param opcion Tipo de la Consulta
     * @param enunciado Enunciado de la Consulta
     * @param fecha Fecha estimada/realizada
     * @param clave Un clave numerica para identificarlo
     * @return Un String que indica clave repetida o exito de la operacion
     */
    public String agregarConsulta(String opcion, String enunciado, String fecha, int clave) {
        
        Consulta cc;
        
        if(opcion.equals("1"))
            cc = new ConsultaBinaria();
        else
            cc = new ConsultaMultiple();
        
        cc.setEnunciado(enunciado);
        cc.setFecha(fecha);
        cc.setCheck(false);
        //Asegura que no se repita para agregar a la coleccion
        if(treemap.containsKey(clave))
            return "El rut: " + clave + " ya se encuentra ocupado";
        
        agregarConsultas(cc, clave);
        return "La consulta se agrego exitosamente";
    }
    
    /**
     * Metodo para mostrar todas las consultas de la Coleccion
     * @return Un String que contiene todas las consultas con sus datos [no incluye lista]
     */
    public String mostrarConsultas() {
        
        String mostrar = "";
        Object aux = null; //No necesito una consulta en especifico
        
        //Paso por esta sentencia para evitar errores
        if( !evitarCaidas(aux) )return "La coleccion esta vacia";
        
        Consulta consultas;
        
        //Muestro todas las consultas segun la estrucutra de ella
        for(Map.Entry<Integer, Consulta> consulta : treemap.entrySet())
        {
            consultas = consulta.getValue();
            mostrar += "\nCLAVE: " + consulta.getKey() + consultas.mostrarConsulta(false) + "\n"; 
        }
        
        return mostrar;
    }
    
    /**
     * Metodo para modificar los datos de una Consulta sea Multiple/Binaria
     * Si los votos fueron contados, solo permite agregar la resolucion de la Consulta
     * @param key La clave de la Consulta
     * @param enunciado Nuevo enunciado de la Consulta
     * @param fecha Fecha estimada
     * @param resolucion Conclusion de la Consulta, en caso de que los votos fueron contados
     * @return Un String con el resultado de la operacion segun sea la situacion
     */
    public String modificarConsulta(int key, String enunciado, String fecha, String resolucion){
        
        //Prevenir errores y preguntas por la consulta dada key
        if( !evitarCaidas(key) )return "La consulta esta vacia o bien no hay consulta"
                + " asociado a la clave: " + key;
        
        Consulta consulta = treemap.get(key);
        String mostrar = "";
        
        //Pregunto por el tipo de consulta
        if( consulta.isCheck() )
        {
                //Agrego un texto en enunciado
                String aux = consulta.getEnunciado() +". RESOLUCION: "+ resolucion;
                consulta.setEnunciado(aux);
                return "ACTUALIZACION: " + consulta.mostrarConsulta(false);
            
        }
          
        //En caso de no contar votos
        mostrar += "DATOS ANTERIORES: " + consulta.mostrarConsulta(false);
        consulta.setEnunciado(enunciado);
        consulta.setFecha(fecha);
        mostrar += "\n\nDATOS ACTUALES: " + consulta.mostrarConsulta(false);
        
        return mostrar;
    }
   
    /**
     * Metodo para eliminar un Consulta de la Coleccion con la clave numerica
     * @param key Clave de una Consulta
     * @return Un String con posibles fallos en la operacion o se elimina la consulta mostrando
     * sus datos [incluye lista]
     */
    public String eliminarConsulta(int key) {
        
        //Evitar caidas
        if( !evitarCaidas(key) ) return "La coleccion esta vacia o bien "
                + "no hay consulta asociadoa a la clave: " + key;
        
        String mostrar = "";
        //Muestra la consulta
        mostrar += "\nLa sgte. consulta sera ELIMINADA:";
        mostrar += "\nCLAVE: " + key;
        mostrar += treemap.remove(key).mostrarConsulta(true);
        
        return mostrar;
    }
    
    /**
     * Metodo para buscar una Consulta de la Coleccion con la clave
     * @param key Clave de la Consulta
     * @return Un String que no esta la consulta o muestra los datos de la consulta [no incluye lista]
     */
    public String buscarConsulta(int key) {
        
        if( !evitarCaidas(key) ) return "No hay consulta asociada a la clave: " + key;
        
        Consulta consulta = treemap.get(key);
        return consulta.mostrarConsulta(false);
    }
    
    /**
     * Metodo para buscar una o mas Consulta de la Coleccion con filtro a la fecha
     * @param date Fecha para buscar
     * @return Un String con la coleccion vacia, no hay Consultas asociadas a la fecha
     * o entre 1 o mas Consultas con sus datos [no incluye lista]
     */
    public String buscarConsulta(String date) {
        
        
        Object aux = null;
        if( !evitarCaidas(aux) ) return "La coleccion se encuentra vacia";
        
        Consulta consultas;
        String mostrar = "";
        //Reviso cada consulta de la coleccion, si hay coincidencia se imprime sus datos
        for(Map.Entry<Integer, Consulta> consulta : treemap.entrySet())
        {
            consultas = consulta.getValue();
            
            if(consultas.getFecha().equals(date))
                mostrar += "CLAVE: " + consulta.getKey() + consultas.mostrarConsulta(false);
        }
        if(mostrar.equals(""))
            return "No hay consultas con la fecha: " + date;
        
        return mostrar;
    } 
    
    //METODOS PARA LOS ELEMENTOS DE LA LISTA
    /**
     * Metodo para agregar un Voto, por Input, de una Consulta asociado a la clave
     * @param key Clave de la consulta
     * @param rut Rut del Votante
     * @param voto Respuesta que opto
     * @return Indica posibles errores o el resultado de la operacion
     */
    public String agregarVoto(int key, String rut, int voto){
        
        if( !evitarCaidas(key) ) return "La coleccion esta vacia o bie no hay "
                + "consulta asociado a la clave: " + key;
        
        return treemap.get(key).agregarVotante(rut, voto);
    }
    
    /**
     * Metodo para insertar los Votos de una Consulta, cuyos datos se obtienen desde 
     * un archivo formato CSV
     * @param key La clave de la consulta para insertar los votos y para buscar los votos correspondientes
     * @return Un String posibles errores / Termino la ejecucion de la operacion y los 
     * Votantes que se eliminaron para prevenir votos repetidos
     * @throws IOException Error de Input/OutPut
     */
    public String cargarVotos(int key) throws IOException{
        
        //Evito las caidas
        if( !evitarCaidas(key) ) return "La coleccion esta vacia o bien no hay"
                + "consulta asociada a la clave: " + key;
        
        //Aseguro el tipo de consulta
        
         //Directorio donde se encuentra los archivos de votos (idealmente)
        File dir = new File("./Datos_CSV/Votos/");
        
        //Nombre del archivo
        String nombre [] = dir.list();
        
        //Archivo csv
        CSV fileVotos;
        String mostrar = "Lista de votos eliminados por repetidos:";           
        String rut, linea;//Para leer cada linea del archivo
        int multiple;
        boolean binario;
        Consulta consulta = treemap.get(key);
        
       //Busco el archivo.csv
        for(String open : nombre)
            //Pregunto si el nombre del archivo termina en ".csv"
            if(open.endsWith(".csv") == true)
            {
                //Obtengo el archivo y lo leo
                fileVotos = new CSV("./Datos_CSV/Votos/", open);
                linea = fileVotos.firstLine();
                
                //Pregunto si son los votos de tal consulta...
                if(key == Integer.parseInt(fileVotos.get_csvField(linea, 0)) )  
                {  
                    //Paso a la sgte linea
                    linea = fileVotos.nextLine();
                    
                    try
                    {
                        
                        while(linea != null)
                        {
                            multiple = Integer.parseInt(fileVotos.get_csvField(linea, 1));
                            rut = fileVotos.get_csvField(linea, 0);
                            FormatoMultiple mm = new FormatoMultiple(rut, multiple);
                            
                            if( !consulta.eliminarVotos(rut, mm) )
                                mostrar += "\nEl rut: " + rut + " se repitio en el voto";
                                
                            linea = fileVotos.nextLine();
                        }
                        
                        fileVotos.close();
                        break;
                        
                    }
                    catch(Exception e)
                    {
                        
                        while(linea != null)
                        {
                            
                            rut = (fileVotos.get_csvField(linea, 0));
                            binario = (Boolean.parseBoolean(fileVotos.get_csvField(linea, 1)));
                            FormatoBinario bb = new FormatoBinario(rut, binario);
                            
                            if( !consulta.eliminarVotos(rut, bb) )
                                mostrar += "\nEl rut: " + rut + " se repitio en el voto";
                                
                            linea = fileVotos.nextLine();
                        }
                        
                        fileVotos.close();
                        break;
                        
                    }
                }
                fileVotos.close();     
            }
        if(mostrar.equals("Lista de votos eliminados por repetidos:"))
            mostrar = "No hay votos repetidos...\n";
        
        return "Se ejecuto correctamente el metodo\n" + mostrar;
    }
    
    /**
     * Metodo para buscar un Votante de la Lista de un Consulta.
     * Se llama al metodo interior segun el tipo de la Consulta
     * @param key Clave de la Consulta
     * @param rut Rut del Votante
     * @return Un String con posibles errores o el resultado de la operacion
     */
    public String infoVontanteEnConsulta(int key, String rut) {
        //Reviso si esta la consulta
      
        if ( !evitarCaidas(key) ) return "La coleccion esta vacia o bien no hay "
                + "consulta asociada a la clave: " + key;
        Consulta consulta = treemap.get(key);
        
        return "\nLa consulta con la clave: " + key + " le informata a continuacion."
                + (consulta.buscarVotante(rut));
        
    }
    /**
     * Metodo para mostrar los datos de la Consulta incluyendo la lista de Votantes. 
     * 
     * @param key Clave de la Consulta
     * @return Un String con posibles errores o la informacion de la Consulta
     */
    public String imprimirVotos(int key) {
        
        //Reviso si esta la consulta
        if ( !evitarCaidas(key) ) return "La coleccion esta vacia o bien la"
                + " clave: " + key + " no esta asociado a una consulta";
        
        String mostrar = "";
        //Imprimo datos
        Consulta consulta = treemap.get(key);
        mostrar += "\nCLAVE: " + key + consulta.mostrarConsulta(true);
        
        return mostrar;
    }
    
    
   /**
    * Metodo para modificar la respuesta de un Votante con el rut, de una Consulta
    * con Clave asociada. Se llama el metodo interior segun el tipo de Consulta
    * @param key Clave de la Consulta
    * @param rut Rut del Votante
    * @param opcion Respuesta a cambiar
    * @return Resultado de la operacion
    */
    public String modificarVotos(int key, String rut, int opcion) {
        
        if( !evitarCaidas(key) ) return "La coleccion esta vacia o bien no hay"
                + " consulta asociada con la clave: " + key;
        
        return treemap.get(key).modificarVoto(opcion, rut);
    }
    
    /**
     * Metodo para eliminar un Votante, con rut, de la lista de una Consulta dada clave.
     * Se llama al metodo interior segun el tipo de Consulta
     * @param key Clave de la Consulta
     * @param rut Rut del votante
     * @return El resultado de la operacion
     */
   public String eliminarVoto(int key, String rut){
       
       if( !evitarCaidas(key) ) return "La coleccion esta vacia o bien no hay consulta"
               + " asociado a la clave:" + key;
       
       
       return treemap.get(key).eliminarVotante(rut);
   }
    
   /**
    * Metodo para eliminar Votos mal intencionados de una Consulta de la Coleccion.
    * Se llama al metodo interior segun el tipo de la Consulta que responde y emplea el uso de la Coleccion
    * de Ciudadanos que contiene los Ciudadanos registrados
    * @param key Clave de una Consulta
    * @param ciudadanos Coleccion de Ciudadanos
    * @return Un String con los Votantes eliminados
    */
    public String eliminarVoto(int key, HashMap<String, Ciudadano> ciudadanos){
        
        return treemap.get(key).eliminarVotos(ciudadanos);  
    }
 
    
    //METODOS DEL NEGOCIO
    
    /**
     * Metodo para encontrar la Consulta con menos Votos de la Coleccion
     * @return Solo una consulta de la Coleccion
     */
    public String consultaMenosVotos() {
        
        if( !evitarCaidas(null) ) return "La coleccion se encuentra vacia";
        
        //Variables de tipo aux
        int key = 0, menosVotos = -1, numVotos;
       
        //Reviso cada consulta
        for(Map.Entry<Integer, Consulta> consultas : treemap.entrySet())
        {
            
            numVotos = consultas.getValue().sumaVotos();
            //Exception del primera consulta
            if(menosVotos == -1)
            {
                //Reemplazo los datos
                key = consultas.getKey();
                menosVotos = numVotos;
            }
            else
                if(numVotos < menosVotos) //Compara con el numero de votos
                {
                    //Reemplazo los datos 
                    key = consultas.getKey();
                    menosVotos = numVotos;
                }
        }
        
        return treemap.get(key).mostrarConsulta(false);
    }
    
    /**
     * Metodo para mostrar la opcion mas Votada de todas las Consulta de la Coleccion
     * @return Un String que contiene la opcion mas votada de todas de la Consultas
     */
    public String opcionesMasVotadas() {
        
        if ( !evitarCaidas(null) ) return "La coleccion se encuentra vacia";
        
        Consulta consulta;
        String mostrar = "";
        //Reviso cada consulta
        for(Map.Entry<Integer, Consulta> consultas : treemap.entrySet())
        {
            consulta = consultas.getValue();
            mostrar += ("\nCLAVE: " + consultas.getKey() + "\n" + 
                    consulta.getEnunciado() + "\nOPCION MAS VOTADA: "
                    + consulta.opcionMasVotada());
        }
        
        return mostrar;
    }
    
    //METODOS ADICIONALES
    
    /**
     * Metodo para contar los Votos de una Consulta de la Coleccion con la clave asociada
     * @param key Clave de una Consulta
     * @return Indica que se contaron los votos
     */
    public String contarVOTOS(int key) {
        
        treemap.get(key).contarVotos();
        return "Los votos fueron contados exitosamente...";
    }
    
    /**
     * Metodo para prevenir errores de ejecucion relacionado con la Coleccion de Consultas
     * Verifica si la coleccion esta vacia o no contiene una Consulta asociada a la clave
     * [Siempre y cuando la clave no tenga valor NULL]
     * @param key La clave de la Consulta o valor NULL
     * @return Indica que todo esta en orden o posible falla
     */
    public boolean evitarCaidas(Object key){
        
        //Si la coleccion esta vacia
        if( treemap.isEmpty() )
            return false;
        
        //En caso que necesite la key
        if(key != null)
            //Pregunto si esta en la coleccion
            if( !treemap.containsKey((int)key) )
                return false;
        
        return true;
    }
    
    /**
     * Metodo para verificar que el String del parametro, su contenido sea solo 
     * dato numerico
     * @param contenido El String que almaceno desde Input
     * @return true = no es dato numerico / false = es un dato numerico
     */
    public boolean datoNUMERICO(String contenido){
        
        if(contenido.equals(""))
            return true;
        
        try{
            Integer.parseInt(contenido);
        } catch(Exception e){
            return true;
        }
        
        return false;
    }
}
