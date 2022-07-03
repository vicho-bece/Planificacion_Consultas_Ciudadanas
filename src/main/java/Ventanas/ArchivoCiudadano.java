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
import java.util.*;


/**
 * Clase que implementa metodos de esta Interfaz para patron STRATEGY
 * @author vicho
 */
public class ArchivoCiudadano implements Strategy_ArchivoSalida {
    /**
     * Constructor sin parametros
     */
    public ArchivoCiudadano() {
    }
    
    
    /**
     * Metodo para generar archivo CSV de salida con Ciudadanos de la Coleccion.
     * Puede contener entre 1 y 2 tipos de Ciudadanos en un mismo archivo CSV
     * @param coleccion Coleccion de Ciudadanos
     * @param num Un 0, es irrelevante este parametro
     * @throws IOException Error de Input/OutPut
     */
    @Override
    public void generarArchivo(Object coleccion, int num) throws IOException{
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
            for(Map.Entry<String, Ciudadano> mapa: ((CiudadanoPorRut)coleccion).getMap().entrySet())
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
}
