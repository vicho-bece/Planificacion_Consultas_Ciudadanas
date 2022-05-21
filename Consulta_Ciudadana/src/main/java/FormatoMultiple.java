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
    
    private String rut; //Rut del votante
    private int voto; //El voto del votante (del 1 al 5)
    
    
    //Constructores
    public FormatoMultiple(){}

    public FormatoMultiple(String rut, int voto) {
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

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
    
    
}
