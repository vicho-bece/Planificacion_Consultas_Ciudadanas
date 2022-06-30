package Ventanas;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Codigo.*;
import java.io.*;
/**
 *
 * @author vicho
 */
public class main {
    
    public static void main(String arg[]) throws IOException{
        
        //Declaro las varaibles para acceder a colecciones y atributos de Ciudadanos
        CiudadanoPorRut pp = new CiudadanoPorRut();
        
        pp.datosINICIALES();
       
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader sub = new BufferedReader(new InputStreamReader(System.in));
        
        String eleccionMenu = "1";
        String eleccionSubmenu;
        
        ConsultaPorID cc = new ConsultaPorID();
        cc.datosINICIAL();
        
        ArchivoCiudadano csv1 = new ArchivoCiudadano();
        ArchivoConsulta csv2 = new ArchivoConsulta();
        
        //DATOS PARA EL CIUDADANO...
        String fecha, nombre, rut, opcion, enunciado;
        Boolean sexo, habilitado;
        int clave, keyNum;
        
        
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
                
                            case "1":
                            {
                                System.out.println("\nIngrese nombre y apellido");
                                nombre = sub.readLine();
        
                                System.out.println("\nIndique el sexo\ntrue = hombre, false = mujer.\n"
                                    + "Si ingresa otro valor diferente a esos 2, se tomara como mujer");
                                sexo = Boolean.parseBoolean(sub.readLine());
        
                                System.out.println("Ingrese la fecha de nacimiento (formato dd/mm/yyyy)");
                                fecha = sub.readLine();
                                
                                System.out.println("\nIndique el permiso de sufragar\ntrue = habilitado, false = no habilitado.\n"
                                    + "Si ingresa otro valor diferente a esos 2, se tomara como No habilitado");
                                habilitado = Boolean.parseBoolean(sub.readLine());
                                
                                System.out.println("\nIngrese un rut unico para identificar");
                                rut = sub.readLine();
        
                                /*
                                while(mapaCiudadano.containsKey(nuevoRut))
                                {
                                    System.out.println("\nEl Rut " + nuevoRut + " esta OCUPADO.Favor de ingresar otro");
                                    nuevoRut = sub.readLine();
                                }*/
                                
                                System.out.println(pp.agregarCiudadanos(nombre, sexo, habilitado, fecha, rut));
                                break;
                            } 
                
                            case "2": System.out.println(pp.agregarCiudadanos()); break;
                
                            case "3": System.out.println(pp.mostrarCiudadanos()); break;
                
                            case "4": 
                            {
                                System.out.println("\nIngrese el rut del ciudadano...");
                                rut = sub.readLine();
                                
                                System.out.println(pp.eliminarCiudadano(rut));
                                break;
                            }
                
                            case "5": 
                            {
                                System.out.println("\nIngrese nombre y apellido");
                                nombre = sub.readLine();
        
                                System.out.println("\nIndique el sexo\ntrue = hombre, false = mujer.\n"
                                    + "Si ingresa otro valor diferente a esos 2, se tomara como mujer");
                                sexo = Boolean.parseBoolean(sub.readLine());
        
                                System.out.println("Ingrese la fecha de nacimiento (formato dd/mm/yyyy)");
                                fecha = sub.readLine();
                                
                                System.out.println("\nIndique el permiso de sufragar\ntrue = habilitado, false = no habilitado.\n"
                                    + "Si ingresa otro valor diferente a esos 2, se tomara como No habilitado");
                                habilitado = Boolean.parseBoolean(sub.readLine());
                                
                                System.out.println("\nIngrese un rut unico para identificar");
                                rut = sub.readLine();
                                
                                pp.modificarCiudadano(nombre, sexo, habilitado, fecha, rut);
                                break;
                            }
                            
                            case "6": 
                            {
                                csv1.generarArchivo(pp, 0);
                                break;
                            }
                            
                            case "7": System.out.println(pp.mostrarCiudadanoMasViejo()); break ;
                            
                            case "8": System.out.println(pp.mostrarHabilitados()); break;
                        
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
                            
                            case "1": 
                            {
                                System.out.println("1 = Consulta Binaria / 2 = Consulta Multiple");
                                opcion = sub.readLine();

                                //1 = Consulta Binaria
                                //2 = Consulta Multiple

                                //Verifico que la opcion sea valida
                                while(!"1".equals(opcion) && !"2".equals(opcion))
                                {
                                    System.out.println("1 = Consulta Binaria / 2 = Consulta Multiple");
                                    opcion = sub.readLine();
                                }
                                
                                //Datos de la consulta
                                System.out.println("INGRESE ENUNCIADO");
                                enunciado = sub.readLine();
                                System.out.println("INGRESE FECHA");
                                fecha = sub.readLine();

                                //Clave unica
                                System.out.println("INGRESE CLAVE UNICA");
                                clave = Integer.parseInt(sub.readLine());
                                System.out.println(cc.agregarConsulta(opcion, enunciado, fecha, clave));
                                break;
                            } 
                
                            case "2": System.out.println(cc.mostrarConsultas()); break;
                            
                            case "3":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para ver la lista");
                                clave = Integer.parseInt( sub.readLine() );
                                System.out.println(cc.imprimirVotos(clave));
                                break;
                            }
                            
                            case "4":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para cargar sus votos...");
        
                                System.out.println( cc.cargarVotos(Integer.parseInt( sub.readLine() ))  );
                                break;
                            }
                            case "5":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para modificarlo");
                                clave = Integer.parseInt( sub.readLine() );
                                
                                System.out.println("\nIngrese el enunciado que desea reemplazar");
                                enunciado = sub.readLine();
                                
                                System.out.println("\n Ingrese una nueva fecha");
                                fecha = sub.readLine();
                                
                                System.out.println("\nEn caso de que los votos ya estan"
                                        + " contados, indique la resolucion de la consulta");
                                
                                
                                System.out.println(cc.modificarConsulta(clave, enunciado, fecha, sub.readLine()));
                                break;
                            }
                            case "6":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para eliminarlo");
                                clave = Integer.parseInt( sub.readLine() );
                                System.out.println(cc.eliminarConsulta(clave));
                                break;
                            }
                            
                            case "7":
                            {
                                csv2.generarArchivo(cc, 0);
                                break;
                            }
                
                            case "8":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para revisar sus votos");
                                clave = Integer.parseInt(sub.readLine());
                                if( cc.eliminarVoto(clave, pp.getMap()) )
                                    System.out.println(cc.contarVOTOS(clave));
                            
                                break;
                            }
                            
                            case "9":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " para cambiar el voto");
                                clave = Integer.parseInt(sub.readLine());
                                System.out.println("El rut del votante");
                                rut = sub.readLine();
                                System.out.println("Si la consulta corresponde a Multiple"
                                        + ", elige la opcion del 1 al 5. Si corresponde"
                                        + " a Binario, siga la misma instruccion");
                                
                                keyNum = Integer.parseInt(sub.readLine());
                                
                                while( keyNum < 1 || keyNum > 5  )
                                {
                                    System.out.println("La opcion que eligio no corresponde al dominio."
                                            + "Favor de seguir la instruccion");
                                    keyNum = Integer.parseInt(sub.readLine());
                                }
                                
                                System.out.println(cc.modificarVotos(clave, rut, keyNum));
                                
                                break;
                            }
                            
                            case "10":
                            {
                                System.out.println("\nIngrese la clave de la consulta"
                                        + " que busca");
                                clave = Integer.parseInt(sub.readLine());
                                System.out.println(cc.buscarConsulta(clave));
                                break;
                            }
                            
                            case "11":
                            {
                                System.out.println("\nIngrese la fecha para buscar"
                                        + " la o las consultas asociadas a tal fecha");
                                System.out.println(cc.buscarConsulta( sub.readLine() ));
                                break;
                            }
                            
                            case "12": System.out.println(cc.consultaMenosVotos()); break;
                            
                            case "13": System.out.println(cc.opcionesMasVotadas()); break;
                            
                            default: System.out.println("\nEl numero que ingreso"
                                    + " no esta dentro de las operaciones..."); break;//REVISAR
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
