package Codigo;


import java.time.*;
import java.time.format.DateTimeFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicho
 */
public class CiudadanoMenor extends Ciudadano {
    
    /**
     * nacimiento: la fecha de nacimiento de un ciudadano
     */
    private String nacimiento;
    
    /**
     * Constructor sin parametros
     */
    public  CiudadanoMenor(){}
    
    /**
     * Constructor con parametros
     * 
     * @param nacimiento fecha de nacimiento
     * 
     * "SUPER"
     * @param name nombre del ciudadano
     * @param sex sexo del ciudadano
     * @param permitido habilitado para sufragar
     */
    public CiudadanoMenor(String nacimiento, String name, boolean sex, boolean permitido) {
        super(name, sex, permitido);
        this.nacimiento = nacimiento;
    }

    
    /**
     * Getter de la fecha de nacimiento
     * @return Tal fecha en formato String
     */
    public String getNacimiento() {
        return nacimiento;
    }
    
    /**
     * Setter de la fecha de nacimiento
     * @param nacimiento fecha de nacimiento
     */
    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }
    
    /**
     * Sobreescritura de la clase Ciudadano
     * 
     * Metodo para mostrar la informacion de un CiudadanoMenor.
     * Se emplea el java.time para calcular la edad y meses con respecto a la fecha de nacimiento
     * @return Un String con los datos del CiudadanoMenor
     */
    @Override
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
        
        
        LocalDate actual = LocalDate.now();
        LocalDate birth = LocalDate.parse(nacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Period periodo = Period.between(birth, actual);
        
        mostrar += "\nEDAD:" + periodo.getYears() + " MESES: " + periodo.getMonths();
        
        return mostrar;
    }
    
    /**
     * Sobreescritura de la clase Ciudadano
     * 
     * Metodo para generar un cadena en formato CSV con los datos de un ciudadano
     * @return  String con los datos separados en ';'
     */
    @Override
    public String formato_csv(){
        return (getNacimiento() + ";" + isSexo() + ";" + isHabilitado() + ";" + getNombre() + "\n");
    }
    
}
