/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

/**
 * Clase que extiende de Exception
 * Error en ingresar el contenido o [ENTER] a un dato Numerico
 * @author vicho
 */
public class DatoNumerico_Exception extends Exception{
    
    /**
     * Constructor
     */
    public DatoNumerico_Exception() {
        super("\nIngreso el [ENTER] o el contenido no corresponde a un dato NUMERICO!");
    }
    
}
