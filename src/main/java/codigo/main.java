package codigo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
/**
 *
 * @author vicho
 */
public class main {
    
    public static void main(String arg[]) throws IOException{
        
        //Declaro las varaibles para acceder a colecciones y atributos de Ciudadanos
        CiudadanoPorRut pp = new CiudadanoPorRut();
        Ciudadano ciudadano = new Ciudadano();
       
        //Obtengo mapa utilizado
        
        
        //Empiezo a poblar a los atributos de los 5 indices...
        ciudadano.setNombre("Mr Juan");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(true);
        pp.getMap().put("12345678-9", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Vladimir Putin");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(true);
        pp.getMap().put("6666666-6", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Barack Obama");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(true);
        pp.getMap().put("0000000-0", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Michelle Obama");
        ciudadano.setSexo(false);
        ciudadano.setHabilitado(true);
        pp.getMap().put("10000000-0", ciudadano);
        
        ciudadano = new Ciudadano();
        ciudadano.setNombre("Donald Trump");
        ciudadano.setSexo(true);
        ciudadano.setHabilitado(false);
        pp.getMap().put("9231456-k", ciudadano);
        
        Ciudadano persona = null;
        
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader sub = new BufferedReader(new InputStreamReader(System.in));
        
        String eleccionMenu = "1";
        String eleccionSubmenu;
        
        ConsultaPorID cc = new ConsultaPorID();
        Consulta consulta = new ConsultaMultiple();
        consulta.setEnunciado("Estas de acuerdo con el nuevo gobierno?");
        consulta.setFecha("02/05/2022");
        
        cc.getTreemap().put(1, consulta);
        
        consulta = new ConsultaBinaria();
        consulta.setEnunciado("aaa");
        consulta.setFecha("02/05/2022");
        cc.getTreemap().put(2, consulta);
        
        //MENU DESDE LA CONSOLA...
        
        //Se ingresa a este ciclo
        while(!eleccionMenu.equals("0"))
        {
            //Se imprime el menu y sus funciones...
            System.out.println("\n ------------------------------------");
            System.out.println("| MENU DE PLANIFICACION DE CONSULTAS |");
            System.out.println(" ------------------------------------");
            System.out.println("\n0 - Salir del programa");
            System.out.println("1 - Coleccion de Ciudadanos");
            System.out.println("2 - Coleccion de Consulta y Votos");
            System.out.println("Ingrese el numero de la operacion que desea realizar...");
            
            //Se debe ingresar un numero en el campo de la consola
            //System.out.print("Eleccion:" );
            
            eleccionMenu = read.readLine();
            eleccionSubmenu = "1";
            int keyNum;
            
            switch(eleccionMenu){
                
                case "0": break;
                
                case "1":
                {
                    System.out.println("\n --------------------");
                    System.out.println("| MENU DE CIUDADANOS |");
                    System.out.println(" --------------------");
                    while(!eleccionSubmenu.equals("0"))
                    {
                        System.out.println("\n0 - Salir del Submenu");
                        System.out.println("1 - Ingresar un nuevo ciudadano desde teclado");
                        System.out.println("2 - Ingresar un nuevo ciudadano desde archivo.csv");
                        System.out.println("3 - Mostrar los ciudadanos");
                        System.out.println("4 - Eliminar un ciudadano ");
                        System.out.println("5 - Modificar datos de un ciudadano ");
                        System.out.println("6 - Generar archivo de Ciudadano");
                        System.out.println("7 - Mostrar rut del ciudadano mas viejo");
                        System.out.println("8 - Mostrar los ciudadanos habilitados");
                        System.out.println("Ingrese el numero de la operacion que desea realizar...");
                        eleccionSubmenu = sub.readLine();
                        
                        switch(eleccionSubmenu){
                
                            case "0": break;
                
                            case "1": pp.agregarCiudadanos(persona); break;
                
                            case "2": pp.agregarCiudadanos(); break;
                
                            case "3": pp.mostrarCiudadanos(); break;
                
                            case "4": 
                            {
                                System.out.println("\nIngrese el rut del ciudadano...");
                                pp.eliminarCiudadano(sub.readLine());
                                break;
                            }
                
                            case "5": 
                            {
                                System.out.println("\nIngrese el rut del ciudadano...");
                                pp.modificarCiudadano(sub.readLine());
                                break;
                            }
                            
                            case "6": 
                            {
                                pp.archivoCiudadano();
                                break;
                            }
                            
                            case "7": pp.mostrarCiudadanoMasViejo(); break ;
                            
                            case "8": pp.mostrarHabilitados(); break;
                        
                            default: System.out.println("\nEl numero que ingreso"
                                    + " no esta dentro de las operaciones..."); break;
                        }
                    }
                    break;
                }
                
                case "2":
                {
                    System.out.println("\n -------------------");
                    System.out.println("| MENU DE CONSULTAS |");
                    System.out.println(" -------------------");
                    while(!eleccionSubmenu.equals("0"))
                    {
                        System.out.println("\n0 - Salir del Submenu");
                        System.out.println("1 - Ingresar nueva consulta desde teclado");
                        System.out.println("2 - Mostrar todas las consultas");
                        System.out.println("3 - Mostrar la lista de vontantes de una consulta");
                        System.out.println("4 - Cargar votos de una consulta");
                        System.out.println("5 - Modificar datos de una consulta");
                        System.out.println("6 - Eliminar una Consulta");
                        System.out.println("7 - Generar archivo de Consultas");
                        System.out.println("8 - Verificar los votos y contar");
                        System.out.println("9 - Cambiar un voto de una consulta");
                        System.out.println("10 - Buscar una consulta asociada a una clave");
                        System.out.println("11 - Buscar una consulta asociada a una fecha");
                        System.out.println("12 - Mostrar la consulta con menos votos");
                        System.out.println("13 - Mostrar la opcion mas votada de cada consulta");
                        
                        eleccionSubmenu = sub.readLine();
                        
                        switch(eleccionSubmenu){
                            
                            case "0": break;
                            
                            case "1": cc.agregarConsulta(); break;
                
                            case "2": cc.mostrarConsultas(); break;
                            
                            
                            case "3":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para ver la lista");
                                cc.imprimirVotos(Integer.parseInt( sub.readLine() ));
                                break;
                            }
                            
                            case "4":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para cargar sus votos...");
                                cc.cargarVotosBinarios(Integer.parseInt( sub.readLine() ) );
                                cc.cargarVotosMultiples(Integer.parseInt( sub.readLine() ) );
                                break;
                            }
                            case "5":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para modificarlo");
                                cc.modificarConsulta(Integer.parseInt( sub.readLine() ));
                                break;
                            }
                            case "6":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para eliminarlo");
                                cc.eliminarConsulta(Integer.parseInt( sub.readLine() ));
                                break;
                            }
                            
                            case "7":
                            {
                                cc.generarArchivoMultiple();
                                cc.generarArchivoBinario();
                                break;
                            }
                
                            case "8":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para revisar sus votos");
                                keyNum = Integer.parseInt(sub.readLine());
                                if( cc.eliminarVotosBinarios(keyNum, pp.getMap()) || cc.eliminarVotosMultiples(keyNum, pp.getMap()))
                                    cc.contarVOTOS(keyNum);
                            
                                break;
                            }
                            
                            case "9":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para cambiar el voto");
                                keyNum = Integer.parseInt(sub.readLine());
                                System.out.println("Y el rut del votante");
                                cc.modificarVotoBinario(keyNum, sub.readLine());
                                cc.modificarVotoMultiple(keyNum, sub.readLine());
                                break;
                            }
                            
                            case "10":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " que busca");
                                keyNum = Integer.parseInt(sub.readLine());
                                cc.buscarConsulta(keyNum);
                                break;
                            }
                            
                            case "11":
                            {
                                System.out.println("\nIngrese la fecha para buscar"
                                        + " la o las consultas asociadas a tal fecha");
                                cc.buscarConsulta( sub.readLine() );
                                break;
                            }
                            
                            case "12": cc.consultaMenosVotos(); break;
                            
                            case "13": cc.opcionesMasVotadas(); break;
                            
                            default: System.out.println("\nEl numero que ingreso"
                                    + " no esta dentro de las operaciones..."); break;
                        }
                    }
                    break;
                }
                
                default: System.out.println("\nEl numero que ingreso no esta dentro"
                        + " de las operaciones..."); break;
            }
        }        
    }
}
