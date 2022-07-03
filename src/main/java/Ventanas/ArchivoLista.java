/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Codigo.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *Clase que implementa metodos de esta Interfaz para patron STRATEGY
 * @author vicho
 */
public class ArchivoLista implements Strategy_ArchivoSalida {
    /**
     * Construtor sin parametros
     */
    public ArchivoLista() {
    }
    
    /**
     * Metodo para generar archivo CSV de salida con Votos de una Consulta.
     * Solo puede contener un SOLO formato de respuestas en un mismo archivo CSV
     * @param coleccion La lista de Votantes de una Consulta
     * @param num La clave de la Consulta que inserta en la 1era linea del archivo
     * @throws IOException Error de Input/OutPut
     */
    @Override
    public void generarArchivo(Object coleccion, int num) throws IOException{
        
        if( ((ArrayList)coleccion).isEmpty() ) return;
        
        try
        {
            //Directorio para los votos y variable tipo archivo
            String ruta = ("./Salida_CSV/Votos/Voto" +(int)num+ ".csv");
            File newfile = new File(ruta);
            
            //Pregunto si no existe un archivo
            if(!newfile.exists())
                newfile.createNewFile();//Se crea uno nuevo
            
            //Archivo de escritura y buffer de escritura en el archivo
            FileWriter archivoEscritura = new FileWriter(newfile);
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            FormatoLista voto;
            
            //Escribo la key en la primera, en referencia a los archivos de lectura
            escribirDatos.write((int)num + "\n");
            
            //Recorro la lista de votantes
            for(int i = 0; i < ((ArrayList)coleccion).size(); i++)
            {
                voto = (FormatoLista)((ArrayList)coleccion).get(i);
                escribirDatos.write(voto.formato_CSV());
            }
       
            escribirDatos.close();//Cierro el archivo
                    
        }
        catch(Exception e)//Encuentra el error
        {
            e.printStackTrace();//Imprime dicho error
        }
    }
}
