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
import java.util.Map;

/**
 * Clase que implementa metodos de esta Interfaz para patron STRATEGY
 * @author vicho
 */
public class ArchivoConsulta implements Strategy_ArchivoSalida {
    /**
     * Constructor sin parametros
     */
    public ArchivoConsulta() {
    }
    
    
    /**
     * Metodo para generar archivo CSV de salida con Consultas de la Coleccion.
     * Puede contener entre 1 y 2 tipos de Consultas en un mismo archivo CSV
     * @param coleccion Coleccion de Consultas
     * @param num Un 0, es irrelevante este parametro
     * @throws IOException Error de Input/OutPut
     */
    @Override
    public void generarArchivo(Object coleccion, int num) throws IOException{
        
        try
        {
            String ruta = "./Salida_CSV/Consulta/Consultas.csv";
            File nuevoArchivo = new File(ruta);
            ArchivoLista list = new ArchivoLista();
            //Verifico que no exista
            if(!nuevoArchivo.exists())
                nuevoArchivo.createNewFile();//Crea uno nuevo
            
            //Archivo de escritura y buffer de escritura en el archivo
            FileWriter archivoEscritura = new FileWriter(nuevoArchivo);
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            
            int clave;
            Consulta consulta;
            
            //Recorro la coleccion
            for(Map.Entry<Integer, Consulta> tree: ((ConsultaPorID)coleccion).getTreemap().entrySet() )
            {                   
                clave = tree.getKey();
                consulta = tree.getValue();

                //Escribo en el archivo
                escribirDatos.write(clave + ";" + consulta.formatoCSV());
                list.generarArchivo(consulta.getListaVotantes(), clave);
                //Aprovecho generar un archivo con respecto a la lista de vontantes
            }
            escribirDatos.close();//Cierro el archivo
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
    }
}
