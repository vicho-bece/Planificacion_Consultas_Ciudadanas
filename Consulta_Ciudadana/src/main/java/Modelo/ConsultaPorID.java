/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.util.*;
import java.io.*;
/**
 *
 * @author vicho
 */
public class ConsultaPorID {
    
    private TreeMap<Integer, Consulta> treemap;//Coleccion de consulta
    //Treemap, la key es un dato tipo int y el valor es un dato tipo Consulta
    
    //Constructor
    public ConsultaPorID(){
        this.treemap = new TreeMap();
    }
    
    //Getter y Setter
    public TreeMap<Integer, Consulta> getTreemap() {
        return treemap;
    }

    public void setTreemap(TreeMap<Integer, Consulta> treemap) {
        this.treemap = treemap;
    }
    
    //METODOS PARA LOS ELEMENTOS DEL TREEMAP
    
    //Metodo para agregar nueva consulta desde teclado
    public void agregarConsulta() throws IOException{
        
        //Variable lector
        BufferedReader campo = new BufferedReader (new InputStreamReader(System.in));
        
        //Clase tipo consulta, obtiene los datos desde este metodo
        Consulta consulta = ingresarDatosConsulta();
        
        //Ingreso de una clave unica
        System.out.println("Ingrese un numero (no repetido) como clave de esta consulta...");
        int nuevaKey = Integer.parseInt(campo.readLine());
        
        //Verifica que tal clave ingresada no sea repetida
        while( treemap.containsKey(nuevaKey) )
        {
            //Se le pide ingresar una nueva clave
            System.out.println("Esta clave esta ocupado, favor de ingresar otro numero...");
            nuevaKey = Integer.parseInt(campo.readLine());
        }
        
        //Al salir del ciclo se inserta el dato en el treemap
        treemap.put(nuevaKey, consulta);
    }
    
    //Metodo para mostrar todas las consultas existentes..
    public void mostrarConsultas() throws IOException {
        
        //Variable para acceder a los datos.
        Consulta consulta;
          
        //Pregunto si el treemap esta vacio...
        if( vacioConsultas() )
            return;
        
        //Recorre cada mapa de la coleccion
        for(Map.Entry<Integer, Consulta> mapa : treemap.entrySet())
        {
            //Se imprime la key y los valores
            System.out.println("CLAVE:  " + mapa.getKey());
            consulta = mapa.getValue();
            valorConsulta(consulta);  
        }
    }
    
    //Metodo para modificar los datos de una consulta
    //Recibe como parametro un dato numerico (Key de una consulta)
    public void modificarConsulta(int num) throws IOException{
        
        //Paso por estas estructuras para evitar errores de ejecucion
        if( vacioConsultas() )return;
        if( !claveConsulta(num)) return;
        
        Consulta consulta = treemap.get(num);
        
        //Pregunto si la consulta cuya lista de votantes NO esta VACIA
        /*
        La intencion de esta estructura de control es para evitar cambiar el enunciado
        completo con votos guardados en la lista (FRAUDE ELECTORAL)
        */
        if( !consulta.getLista().isEmpty() )
        {
            //Solo se agrega un texto adicional para indicar la resolucion con 
            //respecto al enunciado
            System.out.println("Como la consulta ya tiene los votos, favor de"
                    + "escribir la resolucion");
            BufferedReader campo = new BufferedReader (new InputStreamReader(System.in));
            String resolucion = (consulta.getEnunciado() + "\nRESOLUCION:"
                    + campo.readLine());
            
            consulta.setEnunciado(resolucion);
            return;
        }
        
        //Se reemplaza los datos antiguos por los ingresados (solo enunciado y fecha)
        //Llega aqui solo si la lista de votantes esta VACIA
        
        //DATOS ACUTALES
        System.out.println("DATOS ACTUALES:\nCLAVE: " + num);
        valorConsulta(consulta);
        
        //NUEVOS DATOS
        consulta = ingresarDatosConsulta();
        treemap.replace(num, consulta);
        System.out.println("NUEVOS DATOS:");
        valorConsulta(consulta);
    }
    
    //Metodo para imprimir los datos de la consulta, NO incluye la lista
    //Recibe como parametro un dato tipo Consulta para ver sus contenidos
    public void valorConsulta(Consulta consulta) throws IOException{
        
        //Imprime el enunciado, fecha y numero de votos de Si/No
        System.out.println("ENUNCIADO: " + consulta.getEnunciado());
        System.out.println("FECHA: " + consulta.getFecha());
        System.out.println("SI: " + consulta.getContSi());
        System.out.println("NO: " + consulta.getContNo() + "\n");
    }
    
    //Metodo para ingresar los datos de una consulta
    //Retorna un dato tipo Consulta
    public Consulta ingresarDatosConsulta() throws IOException{
        
        //Declaro variable tipo consulta y lector
        Consulta consulta = new Consulta();
        BufferedReader campo = new BufferedReader (new InputStreamReader(System.in));
        
        System.out.println("Ingrese un enuciado...");
        consulta.setEnunciado(campo.readLine());
        System.out.println("Ingrese la fecha de la votacion\n Formato: dd/mm/aaaa");
        consulta.setFecha(campo.readLine());
        
        return consulta;
    }
    
    //Metodo para eliminar una consulta de la coleccion
    //Recibe como parametro un dato tipo int (Key de una consulta)
    public void eliminarConsulta(int num) throws IOException{
        
        //Paso por estas estructuras para evitar errores de ejecucion
        if( vacioConsultas() )return;
        if( !claveConsulta(num)) return;
        
        
        //Elimino la consulta e imprimo sus datos
        System.out.println("Se elimino la siguiente consulta");
        System.out.println("CLAVE: " + num);
        valorConsulta(treemap.remove(num));
    }
    
    //Buscar consulta por su key para obtener los datos de una consulta
    //Recibo una "key de una consulta" de tipo int
    public void buscarConsulta(int key) throws IOException {
        
        //Paso por estas estructuras para evitar errores de ejecucion
        if( vacioConsultas() )return;
        if( !claveConsulta(key)) return;
        
        //Obtengo la consulta e imprimo solo los datos de la consulta
        Consulta consulta = treemap.get(key);
        valorConsulta(consulta);
    }
    
    //Buscar la o las consultas filtro por dada fecha e imprimo 0 hasta N consulta
    //Recibe una fecha de tipo String, Formato dd/mm/aaaa (incluye '/')
    public void buscarConsulta(String date) throws IOException{ 
       
        //Pregunto si la coleccion esta vacia
        if( vacioConsultas() )return;
        
        //Declaro variables tipo int (guarda la key) y consulta para acceder a los datos
        int key;
        Consulta consulta;
        
        
        //Recorro cada treemap existente...
        for(Map.Entry<Integer, Consulta> set : treemap.entrySet()){
            
            //obtengo el valor tipo consulta
            consulta = set.getValue();
            
            //obtengo su key
            key = set.getKey();
            
            //Verifica si una consulta coincide con la fecha dada...
            if(Objects.equals(date, consulta.getFecha()))
            {
                 System.out.println("CLAVE: " + key);
                 valorConsulta(consulta);
            }
        }
    }
    
    //Metodo para generar archivo.csv de la coleccion consulta
    public void archivoConsulta() throws IOException{
        //Intentar ejecutar
        try
        {
            //Directorio para las consultas, variable tipo archivo
            String ruta = "./Salida_CSV/Consulta/CONSULTAS.csv";
            File nuevoArchivo = new File(ruta);
            
            //Verifico que no exista
            if(!nuevoArchivo.exists())
                nuevoArchivo.createNewFile();//Crea uno nuevo
            
            //Archivo de escritura y buffer de escritura en el archivo
            FileWriter archivoEscritura = new FileWriter(nuevoArchivo);
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            
            Consulta consulta;
            
            //Recorro la coleccion
            for(Map.Entry<Integer, Consulta> mapa: treemap.entrySet())
            {                         
                consulta = mapa.getValue();
                
                //Pregunto si la lista de votantes NO esta vacia
                if(!consulta.getLista().isEmpty())
                    archivoListaVotantes(mapa.getKey(), consulta.getLista());
                    //Aprovecho la instancia de crear un archivo de dicha lista
                
                //Escribo en el archivo la Key, eunciado, fecha, numero de SI y No       
                escribirDatos.write(mapa.getKey() + ";" + consulta.getEnunciado() +
                        ";" + consulta.getFecha() + ";" + consulta.getContSi() + 
                        ";" + consulta.getContNo() + "\n");
            }
            escribirDatos.close();//Cierro el archivo
                    
        }
        catch(Exception e)//Encuentra un error
        {
            e.printStackTrace();//Imprime dicho error
        }
    }
    
    
    //METODO PARA LOS ELEMENTOS DE LA LISTA
    
    
    //Metodo para imprimir los datos de una sola consulta y sus Votos
    //Recibo una key de una consulta tipo int
    public void imprimirVotos(int num) throws IOException{
        
        //Paso por estas estructuras para evitar errores de ejecucion
        if( vacioConsultas() )return;
        if( !claveConsulta(num)) return;
        
        //Se imprime los datos de la consulta...
        Consulta consulta = treemap.get(num);
        valorConsulta(consulta);
        
         //Verifica si la lista esta vacia (sin votos ingresados)
        if( consulta.getLista().isEmpty() )
            System.out.println("No hay registro de votantes todavia...\n");
        else
        {
            System.out.println("LISTA DE VOTANTES REGISTRADOS:");
            //Recorro la lista y muestro el rut y su voto...
            for(int i = 0; i < consulta.getLista().size(); i++)
            {
                FormatoBinario voto = consulta.getLista().get(i);
                System.out.println((i+1) + ") " + voto.getRut() + " " + voto.isVoto());
            }
        }
    }
    
    //Metodo para invertir el voto de un votante dado rut ingresado
    //Tambien recibo una key de una consulta donde se encuentra el voto
    public void modificarVoto(int num, String rut) throws IOException {
        
        //Paso por estas estructuras para evitar errores de ejecucion
        if( vacioConsultas() )return;
        if( !claveConsulta(num)) return;
        
        //Variables tipo Consulta y Formatobinario para acceder a los datos
        Consulta consulta = treemap.get(num);
        FormatoBinario voto;
        
        //Recorro la lista para buscar al votante
        for(int i = 0; i < consulta.getLista().size(); i++)
        {
            voto = consulta.getLista().get(i);
            //Pregunto si el votante coincide con el rut
            if(voto.getRut().equals(rut))
            {
                //Se invierte el voto si rome el ciclo
                boolean aux = !voto.isVoto();
                voto.setVoto(aux);
                return;
            }
        }
        System.out.println("No se encontro el voto o la lista esta vacia\n");
    }
    
    //Funcion para leer los votantes que participaron en una consulta
    //Recibo una key de una consulta que coincide con el archivo
    //SE ASUME QUE SOLO HAY UN ARCHIVO
    public void cargarVotos(int num) throws IOException {
        
        //Paso por estas estructuras para evitar errores de ejecucion
        if( vacioConsultas() )return;
        if( !claveConsulta(num)) return;
        
        //Directorio donde se encuentra los archivos de votos (idealmente)
        File dir = new File("./Datos_CSV/Votos/");
        
        //Nombre del archivo
        String nombre [] = dir.list();
        
        //Archivo csv
        CSV fileVotos;
        
        //Instancia variable tipo FormatoBinario
        FormatoBinario formato;
        String linea;//Para leer cada linea del archivo
        
       //Busco el archivo.csv
        for(String open : nombre)
            //Pregunto si el nombre del archivo termina en ".csv"
            if(open.endsWith(".csv") == true)
            {
                //Obtengo el archivo y lo leo
                fileVotos = new CSV("./Datos_CSV/Votos/", open);
                linea = fileVotos.firstLine();
                
                //Pregunto si son los votos de tal consulta...
                if(num == Integer.parseInt(fileVotos.get_csvField(linea, 0)) )  
                {
                    //Paso a la sgte linea
                    linea = fileVotos.nextLine();
                    
                    //EOF?
                    while(linea != null)
                    {
                    
                        //Registro cada voto con rut y su respuesta
                        formato = new FormatoBinario();
                        formato.setRut(fileVotos.get_csvField(linea, 0));
                        formato.setVoto(Boolean.parseBoolean(fileVotos.get_csvField(linea, 1)));
                        
                        //Verifica si hay voto repetido
                        if(eliminarVotos(formato.getRut(), treemap.get(num).getLista()))
                            treemap.get(num).agregarVoto(formato); //Se agrega el voto a la lista
                    
                        linea = fileVotos.nextLine();
                    }
                    fileVotos.close();
                    break;//Finaliza la busqueda de archivo
                }
                fileVotos.close();     
            }   
    }
    
    //Metodo para eliminar votos repetidos, dado un rut del votante de una lista dada
    public boolean eliminarVotos(String rut, ArrayList<FormatoBinario> list)
    {
        //Recorro para buscar el rut repetido. Si esta retorno false y en caso 
        //contario retorno true
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).getRut().equals(rut))
            {
                System.out.println("VOTO REPETIDO:" + rut);
                return false;
            }
                
        
        return true;
    }
    
    //Metodo para eliminar votos de los votantes que no estan coleccion del mapa y esta No habilitados
    //Retorna true cuando termino de revisar la lista y false alguna de las 2 "if"
    public boolean eliminarVotos(int num, HashMap<String, Ciudadano> mapaCiudadano) throws IOException{
        
        //Paso por estas estructuras para evitar errores de ejecucion
        if( vacioConsultas() )return false;
        if( !claveConsulta(num)) return false;
        
        Consulta consulta = treemap.get(num);
        FormatoBinario voto;
        
        //Recorro la lista
        for(int i = 0; i < consulta.getLista().size(); i++)
        {
            voto = consulta.getLista().get(i);
            //Pregunto si el votante esta en la coleccion del mapa
            if(mapaCiudadano.containsKey(voto.getRut()))
            {
                //Pregunto si esta habilitado
                if( !mapaCiudadano.get(voto.getRut()).isHabilitado() )
                {
                    //Se elimina si esta NO HABILITADO
                    System.out.println("VOTO ELIMINADO");
                    consulta.getLista().remove(i);
                    i--;
                }
            }
            else
            {
                //Se elimina si NO ESTA EN LA COLECCION
                System.out.println("VOTO ELIMINADO...");
                consulta.getLista().remove(i);
                i--;
            }
                
        }
        
        return true;
    }
    
    //Metodo para contar los votos de una consulta (despues de revisar la lista)
    public void contarVotos(int num) throws IOException {
        
        //Paso por las estructuras de control para eviar error de ejecucion
        if( vacioConsultas() )return;
        if( !claveConsulta(num)) return;
        
        //Variable para acceder a los datos
        Consulta consulta = treemap.get(num);
        FormatoBinario voto;
           
        //Recorro la lista
        for(int i = 0; i < consulta.getLista().size(); i++)
        {
            voto = consulta.getLista().get(i);
            //Aumento por unidad los contadores dependiendo del voto (TRUE O FALSE)
            if(voto.isVoto())
                consulta.setContSi();
            else
                consulta.setContNo();
        }
    }
    
    //Los sgtes metodos son para evitar errores de ejecucion
    //Metodo para verificar que la coleccion esta vacio
    //Retorna true en caso de que este vacio, caso contrario retorna false
    public boolean vacioConsultas() throws IOException{
        if(treemap.isEmpty())
        {
            System.out.println("No hay consultas guardadas");
            return true;
        }
        return false;
    }
    
    //Metodo para verificar que la clave de la consulta este en la coleccion
    //Retorna true en caso de que lo encuentra, false no lo encuentra
    public boolean claveConsulta(int num) throws IOException{
        if( !treemap.containsKey(num) )
        {
            System.out.println("No hay consulta asociada con clave: " + num);
            return false;
        }
        return true;
    }
    
    //Metodo para generar archivo.csv de la lista de votantes dada la consulta
    //Recibo la lista y key de la consulta
    public void archivoListaVotantes(int num, ArrayList<FormatoBinario> lista) throws IOException{
        //Aplico el intento
        try
        {
            //Directorio para los votos y variable tipo archivo
            String ruta = ("./Salida_CSV/Voto/VOTOS" +num+ ".csv");
            File newfile = new File(ruta);
            
            //Pregunto si no existe un archivo
            if(!newfile.exists())
                newfile.createNewFile();//Se crea uno nuevo
            
            //Archivo de escritura y buffer de escritura en el archivo
            FileWriter archivoEscritura = new FileWriter(newfile);
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            FormatoBinario voto;
            
            //Escribo la key en la primera, en referencia a los archivos de lectura
            escribirDatos.write(num + "\n");
            
            //Recorro la lista de votantes
            for(int i = 0; i < lista.size(); i++)
            {
                //Escribo el rut y su voto
                voto = lista.get(i);
                escribirDatos.write(voto.getRut() + ";" + voto.isVoto() + "\n");
            }
       
            escribirDatos.close();//Cierro el archivo
                    
        }
        catch(Exception e)//Encuentra el error
        {
            e.printStackTrace();//Imprime dicho error
        }      
    }
}
