/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicho
 */

//Clase abstracta y padre, e implementa esta interfaz
public abstract class Consulta implements funcionalidadConsulta{
    
    private String enunciado; //Enunciado de la consulta propuesta
    private String fecha; //Fecha estimada a realizar
    
    //Constructor
     public Consulta(){        
    }
    
    //Constructor para instanciar una variable
    public Consulta(Consulta consulta){
        this.enunciado = consulta.enunciado;
        this.fecha = consulta.fecha;
        
    }
    
    //Constructor de consulta
    public Consulta(String enunciado, String fecha){
        
        //Con parametros
        this.enunciado = enunciado;
        this.fecha = fecha;
     
    }
    
    //Getter del enunciado
    public String getEnunciado() {
        return enunciado;
    }
    
    //Setter del enunciado
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    
    //Getter de fecha
    public String getFecha() {
        return fecha;
    }
    
    //Setter de fecha
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    //Metodo abstracto para contar los votos
    public abstract void contarVotos();
}
