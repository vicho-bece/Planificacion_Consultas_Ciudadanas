package codigo;

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
        
    }
    
    /**
     * Constructor con parametros
     * @param enunciado enunciado para la consulta
     * @param fecha fecha realizada
     */
    public Consulta(String enunciado, String fecha){
        
        //Con parametros
        this.enunciado = enunciado;
        this.fecha = fecha;
     
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
     * Metodo abstracto para contar votos
     */
    public abstract void contarVotos();
}
