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
public class Ciudadano {
    
    /**
     * nombre: la identidad del ciudadano
     * sexo: el sexo del ciudadano. 
     * habilitado: el permiso de sufragar del ciudadano
     * 
     */
    private String nombre;
    private boolean sexo; //True = Hombre, False = Mujer
    private boolean habilitado; //True = Sufragar, False = NO Sufragar
    
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
    
    /**
     * Metodo para mostrar los datos de un ciudadano.
     * Nombre, Sexo y Habilitado
     */
    public void mostrarCiudadano(){
        
        System.out.println("NOMBRE: " + getNombre());
        if( isSexo() )
            System.out.println("SEXO: Hombre");
        else
            System.out.println("SEXO: Mujer");
        
        if( isHabilitado() )
            System.out.println("PERMISO PARA SUGRAGAR: Habilitado");
        else
            System.out.println("PERMISO PARA SUGRAGAR: NO Habilitado");
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

