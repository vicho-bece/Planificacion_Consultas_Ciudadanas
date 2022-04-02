package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicho
 */
public class Ciudadano {
    
    //ATRIBUTOS...
    private String nombre; //identidad del ciudadano
    private boolean sexo; //True = Hombre, False = Mujer
    private boolean habilitado; //habilitado para sufragar
    
    //Constructor por defecto
    public Ciudadano(){
        
    }
    
    //Constructor para instanciar una variable
    public Ciudadano(Ciudadano ciudadano)
    {
        this.nombre = ciudadano.nombre;
        this.sexo = ciudadano.sexo;
        this.habilitado = ciudadano.habilitado;
    }
    
    //Constructor ciudadano con parametros
    public Ciudadano(String name, boolean sex, boolean permitido){
        this.nombre = name;
        this.sexo = sex; 
        this.habilitado = permitido;
    }
    
    //Getter de nombre
    public String getNombre() {
        return nombre;
    }
    
    //Setter de nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Getter de sexo
    public boolean isSexo() {
        return sexo;
    }
    
    //Setter de sexo
    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
    
    //Getter de habilitado
    public boolean isHabilitado() {
        return habilitado;
    }
    
    //Setter de habilitado
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
}

