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
public class Ciudadano {
    
    /**
     * nombre: la identidad del ciudadano
     * sexo: el sexo del ciudadano. True = Hombre, False = Mujer
     * habilitado: el permiso de sufragar del ciudadano. True = Sufragar, False = NO Sufragar
     * 
     */
    private String nombre;
    private boolean sexo, habilitado; 
    
    
    /**
     * Constructor sin parametros
     */
    public Ciudadano(){
        
    }
    
    /**
     * Constructor con parametros
     * 
     * @param ciudadano dato tipo Ciudadano que contiene los 3 atributos 
     */
    public Ciudadano(Ciudadano ciudadano)
    {
        this.nombre = ciudadano.nombre;
        this.sexo = ciudadano.sexo;
        this.habilitado = ciudadano.habilitado;
    }
    
    /**
     * Constructor con parametros
     * 
     * @param name nombre del ciudadano
     * @param sex sexo del ciudadano
     * @param permitido el permiso de sufragar
     */
    public Ciudadano(String name, boolean sex, boolean permitido){
        this.nombre = name;
        this.sexo = sex; 
        this.habilitado = permitido;
    }
    
    /**
     * Getter de nombre
     * 
     * @return retorna el nombre del ciudadano en String 
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Setter de nombre
     * 
     * @param nombre ingresa el nombre del ciudadano
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Getter de sexo
     * 
     * @return retorna el sexo del ciudadano
     */
    public boolean isSexo() {
        return sexo;
    }
    
    /**
     * Setter de sexo
     * 
     * @param sexo ingresa el sexo del ciudadano
     */
    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
    
    /**
     * Getter de habilitado
     * 
     * @return retorna el permiso de sufragar 
     */
    public boolean isHabilitado() {
        return habilitado;
    }
    
    /**
     * Setter de habilitado
     * 
     * @param habilitado ingresa el permiso de sufragar
     */
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
    
    public String mostrarCiudadano(){
        
        String mostrar = "";
        
        mostrar += "\nNOMBRE: " + getNombre();
        
        if( isSexo() )
            mostrar += "\nSEXO: Hombre";
        else
            mostrar += "\nSEXO: Mujer";
        
        if( isHabilitado() )
            mostrar += "\nPERMISO PARA SUFRAGAR: Habilitado";
        else
            mostrar += "\nPERMISO PARA SUFRAGAR: NO Habilitado";
        
        return mostrar;
    }
    
    /**
     * Metodo para generar una cadena en formato CSV con los datos de un ciudadano
     * 
     * @return Un Striung con los datos del ciudadano separados en ';' 
     */
    public String formato_csv(){
        return (getNombre() + ";" + isSexo() + ";" + isHabilitado() + "\n");
    }
}

