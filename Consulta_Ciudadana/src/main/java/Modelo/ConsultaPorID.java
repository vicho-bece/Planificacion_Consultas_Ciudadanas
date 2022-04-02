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
           
    //public void contarVotos(int key){}
    //public void contarVotos(ArrayList votos){}
}
