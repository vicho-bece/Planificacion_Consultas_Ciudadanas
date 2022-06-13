package Codigo;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicho
 */
public class FormatoMultiple {
    
    /**
     * rut: rut del votante
     * voto: el voto del votante del 1 al 5
     */
    private String rut;
    private int voto;
    
    
    /**
     * Constructor sin parametros
     */
    public FormatoMultiple(){}
    
    /**
     * Constructor con parametros
     * @param rut: rut del votante
     * @param voto: voto que realizo
     */
    public FormatoMultiple(String rut, int voto) {
        this.rut = rut;
        this.voto = voto;
    }
    
    
    /**
     * Getter de rut
     * 
     * @return retorna el rut del votante 
     */
    public String getRut() {
        return rut;
    }
    
    /**
     * Setter de rut
     * 
     * @param rut rut del votante
     */
    public void setRut(String rut) {
        this.rut = rut;
    }
    
    /**
     * Getter de voto
     * 
     * @return retorna el voto del votante 
     */
    public int getVoto() {
        return voto;
    }
    
    /**
     * Setter de voto
     * 
     * @param voto voto que realizo
     */
    public void setVoto(int voto) {
        this.voto = voto;
    }
    
    
}
