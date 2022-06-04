package codigo;


import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicho
 */

/**
 * Clase tipo interfaz:
 * 
 * Utiliza para mostrar los datos de una consulta,
 * obtener la cantidada total de votos a partir de los contadores 
 * y obtener la opcion mas votada de la consulta
 * 
 */

public interface funcionalidadConsulta {
    public void mostrarConsulta(boolean lista) throws IOException;
    public int sumaVotos() throws IOException;
    public String opcionMasVotada() throws IOException;
}
