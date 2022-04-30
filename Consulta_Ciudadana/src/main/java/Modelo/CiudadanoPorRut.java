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
    
    private HashMap<String, Ciudadano> mapaCiudadano;//Coleccion de Ciudadano
    //HashMap cuya clave es el rut en formato String y el valor la clase Ciudadano
    
    //Constructor...
    public CiudadanoPorRut(){
        this.mapaCiudadano = new HashMap();
    }
    
    //Getter...
    public HashMap<String, Ciudadano> getMap() {
        return mapaCiudadano;
    }

    //Setter
    public void setMap(HashMap<String, Ciudadano> mapaCiudadano) {
        this.mapaCiudadano = mapaCiudadano;
    }
    
    //Metodo para agregar Ciudadanos al HashMap leyendo los datos desde teclado
    //Recibe de parametro un dato tipo Ciudadano
    public void agregarCiudadanos(Ciudadano ciudadanoDatos) throws IOException{
        
        //Instancio la variable de lectura desde teclado
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        
        //Ingresa el metodo para completar los datos desde teclado
        ciudadanoDatos = ingresarDatosCiudadano();
        
        //Ingresar un rut como clave unica
        System.out.println("\nIngrese un rut unico para identificar");
        String nuevoRut = campo.readLine();
        
        //Ciclo para asegurar un Rut unico
        while(mapaCiudadano.containsKey(nuevoRut))
        {
            System.out.println("\nEl Rut " + nuevoRut + " esta OCUPADO.Favor de ingresar otro");
            nuevoRut = campo.readLine();
        }
        
        //Ingresa el dato Ciudadano al mapa
        mapaCiudadano.put(nuevoRut, ciudadanoDatos);
    }
    
    //Metodo para agregar ciudadanos a partir de un archivo CSV
    public void agregarCiudadanos() throws IOException {
        
        //Declaro variable para tener el directorio dentro del proyecto
        File dir = new File("./Datos_CSV/Ciudadano/");
        
        //variable para guardar nombre del archivo
        String nombre [] = dir.list();
        
        //Archivo csv
        CSV fileCiudadano;
        
        //Declaro la variable tipo Ciudadano para ingresar datos a cada atributo
        Ciudadano persona;
        String linea;//para leer cada linea del archivo
        
        //Busco el archivo tipo csv
        for(String open : nombre)
            //Verifico si el nombre del archivo termina en ".csv"
            if(open.endsWith(".csv") == true)
            {
                //obtengo el archivo y se lee la primera linea
                fileCiudadano = new CSV("./Datos_CSV/Ciudadano/", open);
                linea = fileCiudadano.firstLine();
                
                //EOF?
                while(linea != null)
                {
                    
                    //Pregunta si el rut ya existe
                    if( !(this.mapaCiudadano.containsKey(fileCiudadano.get_csvField(linea,0))) )
                    {
                        persona = new Ciudadano();
                        
                        //Guardo los atributos del ciudadano                        
                        persona.setNombre(fileCiudadano.get_csvField(linea,1));
                        persona.setSexo(Boolean.parseBoolean(fileCiudadano.get_csvField(linea,2)));
                        persona.setHabilitado(Boolean.parseBoolean(fileCiudadano.get_csvField(linea,3)));
                        
                        //Ingreso el ciudadano al mapa
                        mapaCiudadano.put(fileCiudadano.get_csvField(linea,0), persona);
                    }
                    else
                        System.out.println("RUT :" + fileCiudadano.get_csvField(linea,0) + " Ya existe...");
                    
                    //Paso a la sgte linea
                    linea = fileCiudadano.nextLine();
                }
                //CLOSE FILE
                fileCiudadano.close();
            }   
    }        
    
    //Metodo para mostrar los ciudadanos que hay en la coleccion
    public void mostrarCiudadanos() throws IOException{
        
        //Variable tipo ciudadano para acceder a los datos
        Ciudadano ciudadano;
 
        //Pregunto si el mapa esta vacio...
        if( vacioCiudadanos() ) return;
        
        //Recorre cada mapa de la coleccion mostrando la key y contenido
        for(Map.Entry<String, Ciudadano> mapa : mapaCiudadano.entrySet())
        {
            ciudadano = mapa.getValue();
            System.out.println("\nRUT: " + mapa.getKey());
            valorCiudadano(ciudadano);
        }
    }
    
    //Metodo para modificar los datos de un ciudadano dado rut
    //Recibe de parametro un dato tipo String 
    public void modificarCiudadano(String rut) throws IOException{
        
        //Pregunto primero si la coleccion esta vacia y despues si el ciudadano
        //asociado al rut esta dentro de la coleccion
        
        if( vacioCiudadanos() ) return;
        if( !rutCiudadano(rut) ) return;
        
        //Imprimo los datos inciales
        Ciudadano ciudadano = mapaCiudadano.get(rut);
        System.out.println("\nDATOS ACTUALES:\nRUT: "+ rut);
        valorCiudadano(ciudadano);
        
        //Se reemplaza los datos antiguos por nuevos datos ingresados y los imprime
        ciudadano = ingresarDatosCiudadano();
        mapaCiudadano.replace(rut, ciudadano);
        System.out.println("\nNuevos datos: ");
        valorCiudadano(ciudadano);
    }
    
    //Metodo para mostrar el nombre, sexo y permiso de sufragio del ciudadano
    //Recibe de parametro un dato tipo Ciudadano 
    public void valorCiudadano(Ciudadano ciudadano) throws IOException{
            
        //Imprime el nombre, sexo y permiso para sufragar
            System.out.println("NOMBRE: " + ciudadano.getNombre());
            
            if(ciudadano.isSexo())
                System.out.println("SEXO: Hombre");
            else
                System.out.println("SEXO: Mujer");
            
            if(ciudadano.isHabilitado())
                System.out.println("PERMISO PARA SUGRAGAR: Habilitado");
            else
                System.out.println("PERMISO PARA SUFRAGAR: No Habilitado");
    }
    
    //Metodo para leer el nombre(String), sexo(bool)y habilitado(bool)
    //Retorna el dato tipo Ciudadano
    public Ciudadano ingresarDatosCiudadano() throws IOException{
        
        //Declarar variable tipo Ciudadano y un lector
        Ciudadano ciudadano = new Ciudadano();
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        
        //Se lee nombre, sexo y habilitado...
        System.out.println("\nIngrese nombre...");
        ciudadano.setNombre(campo.readLine());
        
        //Para los datos tipo booleano hay excepciones con respecto los datos
        //que se ingreasn en la misma consola...
        
        System.out.println("\nIndique el sexo\ntrue = hombre, false = mujer.\n"
                + "Si ingresa otro valor diferente a esos 2, se tomara como mujer");
        ciudadano.setSexo(Boolean.parseBoolean(campo.readLine()));
        
        System.out.println("\nIndique el permiso de sufragar\ntrue = habilitado, false = no habilitado.\n"
                + "Si ingresa otro valor diferente a esos 2, se tomara como No habilitado");
        ciudadano.setHabilitado(Boolean.parseBoolean(campo.readLine()));
        
        return ciudadano;
    }
    
    //Metodo para eliminar un ciudadano
    //Recibe un dato tipo String, supuestamente es una key
    public void eliminarCiudadano(String rut) throws IOException {
        
        //Pregunta si la coleccion esta vacia y si no esta el ciudadano asociado
        // al rut ingresado...
        if( vacioCiudadanos() ) return;
        if( !rutCiudadano(rut) ) return;
        
        //Si esta, se elimina el ciudadano e imprime sus datos
        System.out.println("\nEl siguiente ciudadano fue eliminado");
        System.out.println("RUT:" + rut);
        valorCiudadano(mapaCiudadano.remove(rut));
    }
    
    //Metodo para verificar si la coleccion esta vacia.
    //Retorna true en caso de que este vacia y false que hay al menos 1
    public boolean vacioCiudadanos() throws IOException{
        //Pregunto si esta vacia la coleccion
        if(mapaCiudadano.isEmpty())
        {
            System.out.println("No hay ciudadanos registrados");
            return true;
        }
        return false;
    }
    
    //Metodo para verificar que el ciudadano asociado al rut se encuentra dentro
    //de la coleccion. Retorna false que no lo encontro y true si lo encontro.
    //Recibe de parametro un dato tipo String, key
    public boolean rutCiudadano(String rut) throws IOException{
        //Pregunto si la coleccion contiene el rut entregado
        if(!mapaCiudadano.containsKey(rut))
        {
            System.out.println("No hay ciudadano con rut: " + rut);
            return false;
        }
        return true;
    }
    
    
    //REVISAR
    //Metodo para generar archivo.csv
    public void archivoCiudadano() throws IOException{
        
        try
        {
            
            String ruta = "./Salida_CSV/Ciudadano/CIUDADANOS.csv";        
            File nuevoArchivo = new File(ruta);
        
            if(!nuevoArchivo.exists())
                nuevoArchivo.createNewFile();
        
            FileWriter archivoEscritura = new FileWriter(nuevoArchivo);
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            Ciudadano ciudadano;
            
            for(Map.Entry<String, Ciudadano> mapa: mapaCiudadano.entrySet())
                {
                ciudadano = mapa.getValue();
                escribirDatos.write(mapa.getKey() + ";" + ciudadano.getNombre() + ";"
                        + ciudadano.isSexo() + ";" + ciudadano.isHabilitado() + "\n");
                }
            escribirDatos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            
    }
    
}


   
