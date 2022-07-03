package Codigo;





import java.util.HashMap;

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
 * Intefaz para la clase Consulta
 * 
 * Metodos para:
 * 1) Mostrar una consulta con/sin la lista de Vontantes
 * 2) Obtener la suma de Votos
 * 3) Obtener la opcion mas votada
 * 4) Eliminar votos repetidos / votos no validos
 * 5) Buscar un votante de la lista de Votantes
 * 6) Eliminar un votante de la lista de Votantes
 * 7) Agregar un votante a la lista de Votantes
 * 
 * @author vicho
 */
public interface funcionalidadConsulta {
    public String mostrarConsulta(boolean lista) ;
    public int sumaVotos();
    public String opcionMasVotada();
    public boolean eliminarVotos(String rut, Object voto);
    public String eliminarVotos(HashMap<String, Ciudadano> ciudadanos);
    public String buscarVotante(String rut);
    public String eliminarVotante(String rut);
    public String agregarVotante(String rut, int voto);
}
