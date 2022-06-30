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
public class FormatoBinario extends FormatoLista {
    
    /**
     * rut: rut del votante, no necesariamente tiene que estar en el mapa
     * 
     * voto: opciones de voto, true = si y false = no
     */
    private String rut; 
    private boolean voto; 
    
    /**
     * Constructor sin parametros
     */
    public FormatoBinario()
    {    
    }
    
    
    /**
     * Constructor con parametros
     * @param formato un voto con datos del rut y el voto que realizo
     */
    public FormatoBinario(FormatoBinario formato)
    {
        this.rut = formato.rut;
        this.voto = formato.voto;
    }
    /**
     * Constructor con parametros
     * 
     * @param rut del votantes
     * @param voto  su opcion
     */
    public FormatoBinario(String rut, boolean voto)
    {
        this.rut = rut;
        this.voto = voto;
    }
        
    
    /**
     * Getter de rut
     * 
     * @return retorna el rut en String 
     */
    public String getRut() {
        return rut;
    }

    /**
     * Setter de rut
     * 
     * @param rut el rut del votante
     */
    public void setRut(String rut) {
        this.rut = rut;
    }
    
    /**
     * Getter de voto
     * 
     * @return retorna el voto tipo boolean 
     */
    public boolean isVoto() {
        return voto;
    }

    /**
     * Setter de voto
     * 
     * @param voto el voto que realizo el votante 
     */
    public void setVoto(boolean voto) {
        this.voto = voto;
    }

    @Override
    public String formato_CSV(){
        return (getRut() + ";" + isVoto() + "\n");
    }
}
