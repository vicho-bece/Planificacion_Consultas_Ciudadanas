/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.io.IOException;

/**
 * PATRON STRATEGY
 * Interfaz para los archivos de Salida para la Coleccion de Ciudadanos, Consultas
 * y los Votos de una Consulta
 * @author vicho
 */
public interface Strategy_ArchivoSalida {
    /**
     * Metodo para generar un archivo de salida en CSV
     * @param coleccion Una colecccion de muchos elementos [Ciudadanos, Consultas o Votos de una Consulta] 
     * @param num Para la Coleccion Ciudadanos y Consultas es 0, sin embargo para los 
     * Votos corresponde a la Clave de la consulta
     * @throws IOException Error de Input/OutPut
     */
    public void generarArchivo(Object coleccion, int num) throws IOException;
}
