/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.io.*;
import java.util.*;
/**
 *
 * @author vicho
 */
public class CiudadanoPorRut {
    
    private HashMap<String, Ciudadano> Map;
    //HashMap cuya clave es el rut en formato String y el valor la clase Ciudadano
    
    //Constructor...
    public CiudadanoPorRut(){
        this.Map = new HashMap();
    }
    
    //Getter...
    public HashMap<String, Ciudadano> getMap() {
        return Map;
    }

    //Setter
    public void setMap(HashMap<String, Ciudadano> Map) {
        this.Map = Map;
    }
    
    //Funcion para agregar Ciudadanos al HashMap leyendo los datos desde teclado
    public void agregarCiudadanos(CiudadanoPorRut pp,Ciudadano ciudadanoDatos) throws IOException{
        
        //Instancio la variable tipo CiudadanoPorRut para acceder a los mapas registrados
        
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        
        //Leo el nombre(String), sexo(bool)y habilitado(bool)
        System.out.println("Ingrese nombre...");
        ciudadanoDatos.setNombre(campo.readLine());
        System.out.println("Ingrese sexo, true = hombre, false = mujer");
        ciudadanoDatos.setSexo(Boolean.parseBoolean(campo.readLine()));
        System.out.println("true = habilitado, false = no habilitado");
        ciudadanoDatos.setHabilitado(Boolean.parseBoolean(campo.readLine()));
        
        //Leo rut
        System.out.println("Ingrese rut");
        String nuevoRut = campo.readLine();
        
        //Compruebo si el rut(key) ya existe en el mapa
        if(this.Map.containsKey(nuevoRut))
            System.out.println("Este Rut ya esta ocupado...");
        else
        {
            //Ingreso al ciudadano al mapa con el rut como key
            Map.put(nuevoRut, ciudadanoDatos);
        }
    }
    
    //Funcion para agregar ciudadanos a partir de un archivo CSV
    public void agregarCiudadanos() throws IOException {
        
        //Declaro variable para tener el directorio dentro del proyecto
        File dir = new File("./Datos_CSV/Ciudadano/");
        //variable para guardar nombre del archivo
        String nombre [] = dir.list();
        //variable para tener el archivo
        CSV fileCiudadano;
        
        //Declaro las variable tipo clase para acceder a cada atributo
        CiudadanoPorRut mapa = new CiudadanoPorRut();
        mapa.getMap();
        Ciudadano persona;
        String linea;//para leer cada linea del archivo
        
        //Busco el archivo tipo csv
        for(String open : nombre)
            if(open.endsWith(".csv") == true)
            {
                //obtengo el archivo y se lee la primera linea
                fileCiudadano = new CSV("./Datos_CSV/Ciudadano/", open);
                linea = fileCiudadano.firstLine();
                
                while(true)
                {
                    //End Of File...
                    if(linea == null)
                        break;
                    
                    //pregunta si el rut esta desocupado
                    if( !(this.Map.containsKey(fileCiudadano.get_csvField(linea,0))) )
                    {
                        //guardo los atributos del ciudadano
                        persona = new Ciudadano();
                        
                        persona.setNombre(fileCiudadano.get_csvField(linea,1));
                        persona.setSexo(Boolean.parseBoolean(fileCiudadano.get_csvField(linea,2)));
                        persona.setHabilitado(Boolean.parseBoolean(fileCiudadano.get_csvField(linea,3)));
                        
                        //ingreso el ciudadano al mapa
                        Map.put(fileCiudadano.get_csvField(linea,0), persona);
                        mapa.setMap(Map);
                    }
                    else
                        System.out.println("RUT :" + fileCiudadano.get_csvField(linea,0) + " Ya existe...\n");
            
                    linea = fileCiudadano.nextLine();
                }  
            }   
    }        
    
    //Metodo para mostrar los ciudadanos
    public void mostrarCiudadanos(){
        
        //Variable tipo iterador y ciudadano para acceder a los datos
        Iterator it = Map.keySet().iterator();
        Ciudadano ciudadano;
        String key;
        
        //Pregunto si el mapa esta vacio...
        if( Map.isEmpty() )
        {
            System.out.println("No ciudadanos registrados...");
            return;
        }
        
        //Recorre cada mapa de la coleccion
        while(it.hasNext()){
            
            //Obtiene la clave, los datos del ciudadano y los imprime...
            key = (String) it.next();
            ciudadano = Map.get(key);
            
            System.out.println("RUT: " + key);
            System.out.println("NOMBRE: " + ciudadano.getNombre());
            
            if(ciudadano.isSexo())
                System.out.println("SEXO: Hombre");
            else
                System.out.println("SEXO: Mujer");
            
            if(ciudadano.isHabilitado())
                System.out.println("PERMISO PARA SUGRAGAR: Habilitado\n");
            else
                System.out.println("PERMISO PARA SUFRAGAR: No Habilitado\n");
        }  
    }
}
   
