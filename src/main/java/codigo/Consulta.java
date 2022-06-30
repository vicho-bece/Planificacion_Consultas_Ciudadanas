package Codigo;

import java.util.ArrayList;



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
 * 
 * Clase abstracta y padre, e implementa esta interfaz
 * 
 */
public abstract class Consulta implements funcionalidadConsulta{
    
    
    /**
     * fecha: Fecha de la consulta realizada
     * enunciado: Enunciado de la consulta propuesta
     */
    private String enunciado; 
    private String fecha; 
    private boolean check;
    
    /**
     * Constructor sin parametros
     */
     public Consulta(){        
    }
    
    
     /**
      * Constructor para instanciar una variable
      * @param consulta una consulta con datos almacenados
      */
    public Consulta(Consulta consulta){
        this.enunciado = consulta.enunciado;
        this.fecha = consulta.fecha;
        this.check = consulta.check;
    }
    
    
    public Consulta(String enunciado, String fecha, boolean check){
        
        //Con parametros
        this.enunciado = enunciado;
        this.fecha = fecha;
        this.check = check;
    }
    /**
     * Getter de enunciado
     * @return Se retorna el enunciado tipo String
     */
    public String getEnunciado() {
        return enunciado;
    }
    
    /**
     * Setter de enunciado
     * @param enunciado para setear 
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    
    /**
     * Getter de fecha
     * @return Se retorna la fecha tipo String 
     */
    public String getFecha() {
        return fecha;
    }
    
    /**
     * Setter de fecha
     * @param fecha para setear 
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    
    /**
     * Metodo abstracto para contar votos
     */
    public abstract void contarVotos();
    
    public abstract String modificarVoto(int opcion, String rut);
    
    public abstract String formatoCSV();
    
    public abstract ArrayList getListaVotantes();
}
