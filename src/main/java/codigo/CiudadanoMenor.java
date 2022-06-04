package codigo;
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
     * Metodo para mostrar los datos de un ciudadano
     * Nombre, Sexo, Habilitado y edad
     */
    @Override
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
        
        LocalDate actual = LocalDate.now();
        LocalDate birth = LocalDate.parse(nacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Period periodo = Period.between(birth, actual);
        
        System.out.println("EDAD: " + periodo.getYears());
    }
    
    /**
     * Sobreescritura de la clase Ciudadano
     * Metodo para generar un cadena en formato CSV con los datos de un ciudadano
     * @return  String con los datos separados en ';'
     */
    @Override
    public String formato_csv(){
        return (getNacimiento() + ";" + isSexo() + ";" + isHabilitado() + ";" + getNombre() + "\n");
    }
    
}
