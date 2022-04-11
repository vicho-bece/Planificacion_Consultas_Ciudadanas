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
    
    //Buscar consulta por su key para obtener los datos de una consulta
    public Consulta buscarConsulta(int key){
        
        Consulta consulta = treemap.get(key);
        return consulta;
    }
    
    //Insertar consulta desde teclado
    public void insertaConsulta() throws IOException{
        
        //Creo las variables para obtener los datos desde teclado
        Consulta consulta = new Consulta();
        BufferedReader campo = new BufferedReader (new InputStreamReader(System.in));
        int key;
        
        
        //Se imprime mensajes de requerimientos. Nombre del enunciado, fecha y una clave...
        System.out.println("Ingrese un enuciado...");
        consulta.setEnunciado(campo.readLine());
        System.out.println("Ingrese la fecha de hoy\n Formato: dd/mm/aaaa");
        consulta.setFecha(campo.readLine());
        System.out.println("Ingrese un numero (no repetido) como clave de esta consulta...");
        key = Integer.parseInt(campo.readLine());
        
        //Se crea una lista vacio de registro votantes...
        ListaVotantes lista = new ListaVotantes();
        consulta.setList(lista);
        
        //Verifica que tal clave ingresada no sea repetida
        while( treemap.containsKey(key) )
        {
            //Se le pide ingresar una nueva clave
            System.out.println("Esta clave esta ocupado, favor de ingresar otro numero...");
            key = Integer.parseInt(campo.readLine());
        }
        
        //Al salir del ciclo se inserta el dato en el treemap
        treemap.put(key, consulta);
    }
    
    //Mostrar todas las consultas existentes..
    public void mostrarConsultas(){
        
        //Invoco variable de iteracion
        Iterator it = treemap.keySet().iterator();
        //Variables para acceder a los datos.
        Consulta consulta;
        int key;
        ArrayList<FormatoBinario> listAux;
        
        //Pregunto si el treemap esta vacio...
        if( treemap.isEmpty() )
        {
            System.out.println("No hay consultas existentes...");
            return;
        }
            
        //Recorre cada mapa de la coleccion
        while(it.hasNext()){
            
            //Obtiene la clave, los datos de la consulta y los imprime...
            key = (int) it.next();
            consulta = treemap.get(key);
            
            System.out.println("\nCLAVE: " + key);
            System.out.println("ENUNCIADO: " + consulta.getEnunciado());
            System.out.println("FECHA: " + consulta.getFecha());
            System.out.println("SI: " + consulta.getContSi());
            System.out.println("NO: " + consulta.getContNo() + "\n");
            listAux = (ArrayList)consulta.getList().getLista();
            
            System.out.println("\nEl metodo para contar votos no esta implementado todavia\n");
            //Pregunto si el registro de votantes esta vacio
            if( listAux.isEmpty() )
                System.out.println("No hay registro de votantes todavia...\n");
            else
            {
                //Muestro el rut y su voto...
                for(int i = 0; i < listAux.size(); i++)
                {
                    FormatoBinario voto = listAux.get(i);
                    System.out.println((i+1) + "- " + voto.getRut() + " " + voto.isVoto());
                }
            }
        }
    }
    
    
    //Buscar consulta por la fecha
    public Consulta buscarConsulta(String date) throws IOException{ 
       
        //Instacio variables para acceder a los datos...
        int key;
        Consulta consulta;
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        String answer;
        
        //Recorro cada treemap existente...
        for(Map.Entry<Integer, Consulta> set : treemap.entrySet()){
            
            //obtengo el valor tipo consulta
            consulta = set.getValue();
            //obtengo su key
            key = set.getKey();
            //Verifica si una consulta coincide con la fecha...
            if(Objects.equals(date, consulta.getFecha()))
            {
                //Se le pregunta al usuario si es la consulta encontrada
                System.out.println("Es esta consulta la que busca?\n");
                System.out.println(consulta.getEnunciado() + "\n");
                System.out.print("Coloque SI para seleccionar o NO para seguir buscando Respuesta:\n  ");
                answer = campo.readLine();
                if(Objects.equals(answer, "SI"))
                {
                    //se accede a la funcion para obtener la consulta
                    consulta = buscarConsulta(key);
                    return consulta;
                }        
            }
        }
        //En caso de no encontrar la consulta retorna null...
        consulta = null;
        return consulta;
        }
          
    //Funcion para leer los participantes de una consulta
    public void cargarVotos(int num) throws IOException {
        
        //Directorio 
        File dir = new File("./Datos_CSV/Votos/");
        //Nombre del archivo
        String nombre [] = dir.list();
        //Archivo
        CSV fileVotos;
        
        FormatoBinario formato;
        Consulta consulta;
        ListaVotantes lista;
        
        String linea;
       //Busco el archivo.csv
        for(String open : nombre)
            if(open.endsWith(".csv") == true)
            {
                //Obtengo el archivo y lo leo
                fileVotos = new CSV("./Datos_CSV/Votos/", open);
                linea = fileVotos.firstLine();
                //Pregunto si son los votos de tal consulta...
                if(num == Integer.parseInt(fileVotos.get_csvField(linea, 0)) )  
                {
                    linea = fileVotos.nextLine();
                    consulta = treemap.get(num);
                    lista = consulta.getList();
                    while(true)
                    {
                        //End Of File
                        if(linea == null) break;
                    
                        //Registro cada voto con rut y su respuesta
                        formato = new FormatoBinario();
                        formato.setRut(fileVotos.get_csvField(linea, 0));
                        formato.setVoto(Boolean.parseBoolean(fileVotos.get_csvField(linea, 1)));
                        //Se agrega a la lista
                        lista.agregarVoto(formato);
                    
                        linea = fileVotos.nextLine();
                    }
                    treemap.get(num).setList(lista);
                }
                     
            }   
    }
}
