package Controlador;


import Modelo.*;
import java.io.*;
import java.util.*;



/**
 *
 * @author vicho
 */

//FUNCION MAIN...
public class PruebaDatos {
    public static void main(String arg[])throws IOException{
        
        //Declaro las varaibles para acceder a colecciones y atributos de Ciudadanos
        CiudadanoPorRut pp = new CiudadanoPorRut();
        HashMap<String, Ciudadano> dato;
        Ciudadano ciudadano = new Ciudadano();
       
        //Obtengo mapa utilizado
        dato = pp.getMap();
        
        //Empiezo a poblar a los atributos de los 5 indices...
        ciudadano.setNombre("Mr Juan");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(true);
        dato.put("12345678-9", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Vladimir Putin");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(true);
        dato.put("6666666-6", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Barack Obama");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(true);
        dato.put("0000000-0", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Michelle Obama");
        ciudadano.setSexo(false);
        ciudadano.setHabilitado(true);
        dato.put("10000000-0", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Donald Trump");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(false);
        dato.put("9231456-k", ciudadano);
        
        ciudadano = new Ciudadano();
        
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int eleccion = 1;
        ConsultaPorID cc = new ConsultaPorID();
        
        //MENU DESDE LA CONSOLA...
        
        //Se ingresa a este ciclo
        while(eleccion != 0)
        {
            //Se imprime el menu y sus funciones...
            System.out.println("\n1 - Ingresar un nuevo ciudadano desde teclado");
            System.out.println("2 - Ingresar un nuevo ciudadano desde archivo.csv");
            System.out.println("3 - Mostrar los ciudadanos");
            System.out.println("4 - Ingresar nueva consulta desde teclado");
            System.out.println("5 - Mostrar todas las consultas");
            System.out.println("6 - Cargar votos de una consulta");
            System.out.println("0 - Salir del programa");

            System.out.println("Ingrese el numero de la operacion que desea realizar...");
            
            //Se debe ingresar un numero en el campo de la consola
            eleccion = Integer.parseInt(read.readLine());
            System.out.println();
            switch(eleccion){
                
                case 0: break;
                
                case 1: pp.agregarCiudadanos(pp, ciudadano); break;
                
                case 2: pp.agregarCiudadanos(); break;
                
                case 3: pp.mostrarCiudadanos(); break;
                
                case 4: cc.insertaConsulta(); break;
                
                case 5: cc.mostrarConsultas(); break;
                
                case 6:
                {
                    System.out.println("Ingrese la clave de la consulta para cargar sus votos...");
                    cc.cargarVotos( Integer.parseInt( read.readLine() ) );
                    break;
                }
                
                default: System.out.println("El numero que ingreso no esta dentro de las operaciones..."); break;
                
            }
        }        
    }
}