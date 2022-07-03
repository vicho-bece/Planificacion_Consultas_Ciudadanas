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
     * check: Indica si la consulta ya conto sus votos
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
    
    /**
     * Constructor con 3 parametros
     * @param enunciado Enunciado de la Consulta
     * @param fecha Fecha que se realizo la Consulta
     * @param check Verificacion que se conto los votos
     */
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
    
    /**
     * Getter de check
     * @return Verificacion que se contaron los votos en booleano
     */
    public boolean isCheck() {
        return check;
    }
    
    /**
     * Setter de check
     * @param check El estado actual de conteo de votos
     */
    public void setCheck(boolean check) {
        this.check = check;
    }
    
    /**
     * Metodo abstracto para contar votos
     */
    public abstract void contarVotos();
    
    /**
     * Metodo abstracto para modificar un Voto con el rut del votante
     * @param opcion Respuesta que opto
     * @param rut Rut del Votante
     * @return El resultado de la operacion
     */
    public abstract String modificarVoto(int opcion, String rut);
    
    /**
     * Metodo para guardar la informacion de una Consulta en formato CSV
     * @return Un String con la informacion de una Consulta
     */
    public abstract String formatoCSV();
    
    /**
     * Metodo para obtener la lista de Votantes
     * @return un ArrayList que contiene los votos
     */
    public abstract ArrayList getListaVotantes();
}
