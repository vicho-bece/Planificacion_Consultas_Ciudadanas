/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigo;

/**
 *
 * @author vicho
 */
/**
 * Clase padre de los 2 Formatos de respuesta
 * @author vicho
 */
public abstract class FormatoLista {
    /**
     * Metodo para convertir los datos de un Votante en CSV
     * @return String con los datos de un Votante
     */
    public abstract String formato_CSV();
}
