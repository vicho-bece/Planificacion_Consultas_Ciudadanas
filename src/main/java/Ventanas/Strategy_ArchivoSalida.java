/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.io.IOException;

/**
 *
 * @author vicho
 */
public interface Strategy_ArchivoSalida {
    public void generarArchivo(Object coleccion, int num) throws IOException;
}
