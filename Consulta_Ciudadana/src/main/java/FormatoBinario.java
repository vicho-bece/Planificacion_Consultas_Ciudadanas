

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicho
 */
public class FormatoBinario {
    
    private String rut; //Rut del votante, no necesariamente tiene que estar en el mapa
    //de CiudadanoPorRut...
    private boolean voto; //true = si, false = no
    
    //Constructor
    public FormatoBinario()
    {    
    }
    
    //Constructores con parametros
    public FormatoBinario(FormatoBinario formato)
    {
        this.rut = formato.rut;
        this.voto = formato.voto;
    }
    
    public FormatoBinario(String rut, boolean voto)
    {
        this.rut = rut;
        this.voto = voto;
    }
        
    //Metodos de Getter y Setter
    
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public boolean isVoto() {
        return voto;
    }

    public void setVoto(boolean voto) {
        this.voto = voto;
    }

    
}
