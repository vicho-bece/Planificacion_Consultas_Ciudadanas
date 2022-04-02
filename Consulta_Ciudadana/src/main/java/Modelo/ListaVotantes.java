/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author vicho
 */
public class ListaVotantes {
    
    private ArrayList<FormatoBinario> lista;//Coleccion tipo lista
    
    //Constructores de la clase
    public ListaVotantes(){
        lista = new ArrayList();
    }
    
    public ListaVotantes(ArrayList lista){
        lista = new ArrayList();
        lista.addAll(lista);
    }
    
    //Getter de la lista
    public ArrayList<FormatoBinario> getLista() {
        return lista;
    }
    
    //Setter para ingresar una lista dentro de la coleccion
    public void agregarVoto(FormatoBinario formato){
        lista.add(formato);
    }
    
    //Funcion para leer los participantes de una consulta
    public void cargarVotos() throws IOException {
        
        //Directorio 
        File dir = new File("./Datos_CSV/Votos/");
        //Nombre del archivo
        String nombre [] = dir.list();
        //Archivo
        CSV fileVotos;
        
        FormatoBinario formato;
        
        String linea;
       //Busco el archivo.csv
        for(String open : nombre)
            if(open.endsWith(".csv") == true)
            {
                //Obtengo el archivo y lo leo
                fileVotos = new CSV("./Datos_CSV/Votos/", open);
                linea = fileVotos.firstLine();
                
                while(true)
                {
                    //End Of File
                    if(linea == null) break;
                    
                    //Registro cada voto con rut y su respuesta
                    formato = new FormatoBinario();
                    formato.setRut(fileVotos.get_csvField(linea, 0));
                    formato.setVoto(Boolean.parseBoolean(fileVotos.get_csvField(linea, 1)));
                    //Se agrega a la lista
                    agregarVoto(formato);
                    
                    linea = fileVotos.nextLine();
                }  
            }   
    }

}
