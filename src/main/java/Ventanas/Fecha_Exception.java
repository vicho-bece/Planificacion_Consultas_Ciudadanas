/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

/**
 * Clase que extiende de Exception
 * Error al ingresar el formato de fecha inadecuado
 * @author vicho
 */
public class Fecha_Exception extends Exception {
    /**
     * Constructor
     */
    public Fecha_Exception() {
        super("\n El formato de la Fecha no corresponde a lo indicado");
    }
    
}
