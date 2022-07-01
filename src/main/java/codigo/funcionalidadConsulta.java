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


public interface funcionalidadConsulta {
    public String mostrarConsulta(boolean lista) ;
    public int sumaVotos();
    public String opcionMasVotada();
    public boolean eliminarVotos(String rut, Object lista);
    public boolean eliminarVotos(HashMap<String, Ciudadano> ciudadanos);
    public String buscarVotante(String rut);
    public String eliminarVotante(String rut);
    public String agregarVotante(String rut, int voto);
}
