package Ventanas;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Codigo.*;
import java.io.*;

/**
 * Clase main desde consola, encaragado de hacer los Input/Outputs del programa
 * @author vicho
 */
public class main {
    
    /**
     * Ejecucion del programa
     * @param arg Dato sin utilizar
     * @throws IOException [Input/Output]
     * @throws DatoNumerico_Exception [Inserta dato distinto a un Numerico] 
     * @throws Fecha_Exception [Formato fecha incorrecta]
     */
    public static void main(String arg[]) throws IOException, DatoNumerico_Exception, Fecha_Exception{
        
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
        String fecha, nombre, rut, opcion, enunciado, contenido;
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
        
                                System.out.println("Ingrese la fecha de nacimiento (formato dd/MM/yyyy)");
                                fecha = sub.readLine();
                                
                                if(pp.formatoFECHA(fecha))
                                    throw new Fecha_Exception();
                                
                                System.out.println("\nIndique el permiso de sufragar\ntrue = habilitado, false = no habilitado.\n"
                                    + "Si ingresa otro valor diferente a esos 2, se tomara como No habilitado");
                                habilitado = Boolean.parseBoolean(sub.readLine());
                                
                                System.out.println("\nIngrese un rut unico para identificar");
                                rut = sub.readLine();
        
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
        
                                System.out.println("Ingrese la fecha de nacimiento (formato dd/MM/yyyy)");
                                fecha = sub.readLine();
                                
                                if(pp.formatoFECHA(fecha))
                                    throw new Fecha_Exception();
                                
                                System.out.println("\nIndique el permiso de sufragar\ntrue = habilitado, false = no habilitado.\n"
                                    + "Si ingresa otro valor diferente a esos 2, se tomara como No habilitado");
                                habilitado = Boolean.parseBoolean(sub.readLine());
                                
                                System.out.println("\nIngrese un rut unico para identificar");
                                rut = sub.readLine();
                                
                                System.out.println(pp.modificarCiudadano(nombre, sexo, habilitado, fecha, rut));
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
                        System.out.println("14 - Buscar votante por rut en una consulta especifica");
                        System.out.println("15 - Eliminar un votante de la lista de una consulta");
                        System.out.println("16 - Agregar un votante a la lista de una consulta");
                        
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
                                System.out.println("INGRESE FECHA (dd/MM/yyyy)");
                                fecha = sub.readLine();
                                
                                if(pp.formatoFECHA(fecha))
                                    throw new Fecha_Exception();
                                //Clave unica
                                System.out.println("INGRESE CLAVE UNICA (un numero)");
                                
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                System.out.println(cc.agregarConsulta(opcion, enunciado, fecha, clave));
                                break;
                            } 
                
                            case "2": System.out.println(cc.mostrarConsultas()); break;
                            
                            case "3":
                            {
                                System.out.println("\nIngrese la clave numerico de la consulta"
                                        + " para ver la lista");
                                
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                
                                System.out.println(cc.imprimirVotos(clave));
                                break;
                            }
                            
                            case "4":
                            {
                                System.out.println("\nIngrese la clave numerico de la consulta"
                                        + " para cargar sus votos...");
                                
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                
                                System.out.println( cc.cargarVotos(clave ) );
                                break;
                            }
                            case "5":
                            {
                                System.out.println("\nIngrese la clave numerico de la consulta"
                                        + " para modificarlo");
                                
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                
                                System.out.println("\nIngrese el enunciado que desea reemplazar");
                                enunciado = sub.readLine();
                                
                                System.out.println("\n Ingrese una nueva fecha (dd/MM/yyyy)");
                                fecha = sub.readLine();
                                
                                if(pp.formatoFECHA(fecha))
                                    throw new Fecha_Exception();
                                
                                System.out.println("\nEn caso de que los votos ya estan"
                                        + " contados, indique la resolucion de la consulta");
                                
                                
                                System.out.println(cc.modificarConsulta(clave, enunciado, fecha, sub.readLine()));
                                break;
                            }
                            case "6":
                            {
                                System.out.println("\nIngrese la clave numerico de la consulta"
                                        + " para eliminarlo");
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
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
                                System.out.println("\nIngrese la clave numerico de la consulta"
                                        + " para revisar sus votos");
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                if( !cc.evitarCaidas(clave) )
                                {
                                    System.out.println(cc.eliminarVoto(clave, pp.getMap()));
                                    System.out.println(cc.contarVOTOS(clave));
                                }
                                else
                                    System.out.println("La consulta esta vacia o bien"
                                            + " no consulta con la clave asociada:" + clave);
                                break;
                            }
                            
                            case "9":
                            {
                                System.out.println("\nIngrese la clave numerico de la consulta"
                                        + " para cambiar el voto");
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                System.out.println("El rut del votante");
                                rut = sub.readLine();
                                System.out.println("Si la consulta corresponde a Multiple"
                                        + ", elige la opcion del 1 al 5. Si corresponde"
                                        + " a Binario, siga la misma instruccion");
                                
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                keyNum = Integer.parseInt(contenido);
                                
               
                                
                                while( keyNum < 1 || keyNum > 5  )
                                {
                                    System.out.println("La opcion que eligio no corresponde al dominio."
                                            + "Favor de seguir la instruccion");
                                    contenido = sub.readLine();
                                    if(cc.datoNUMERICO(contenido))
                                        throw new DatoNumerico_Exception();
                                    
                                    keyNum = Integer.parseInt(contenido);
                                }
                                
                                System.out.println(cc.modificarVotos(clave, rut, keyNum));
                                
                                break;
                            }
                            
                            case "10":
                            {
                                System.out.println("\nIngrese la clave numerico de la consulta"
                                        + " que busca");
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                System.out.println(cc.buscarConsulta(clave));
                                break;
                            }
                            
                            case "11":
                            {
                                System.out.println("\nIngrese la fecha para buscar"
                                        + " la o las consultas asociadas a tal fecha");
                                fecha = sub.readLine();
                                
                                if(pp.formatoFECHA(fecha))
                                    throw new Fecha_Exception();
                                System.out.println(cc.buscarConsulta( fecha ));
                                break;
                            }
                            
                            case "12": System.out.println(cc.consultaMenosVotos()); break;
                            
                            case "13": System.out.println(cc.opcionesMasVotadas()); break;
                            
                            case "14":
                            {
                                System.out.println("\nIngrese la clave numerico de la consulta");
                                
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                System.out.println("\nIngrese el rut del votante que desea buscar");
                                rut = sub.readLine();
                                
                                System.out.println(cc.infoVontanteEnConsulta(clave, rut));
                                break;
                            }
                            
                            case "15":
                            {
                                System.out.println("\nIngrese la clave numerico de la consulta");
                                
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                System.out.println("\nIngrese el rut del votante que desea buscar");
                                rut = sub.readLine();
                                
                                System.out.println(cc.eliminarVoto(clave, rut));
                                break;
                            }
                            
                            case "16":
                            {
                                
                                System.out.println("\nIngrese la clave numerico de la consulta");
                                
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                clave = Integer.parseInt(contenido);
                                System.out.println("\nIngrese el rut del votante que desea buscar");
                                rut = sub.readLine();
                                
                                System.out.println("Si la consulta corresponde a Multiple"
                                        + ", elige la opcion del 1 al 5.\nSi corresponde"
                                        + " a Binario, 1 = true y 2 = false");
                                
                                contenido = sub.readLine();
                                if(cc.datoNUMERICO(contenido))
                                    throw new DatoNumerico_Exception();
                                    
                                keyNum = Integer.parseInt(contenido);
                                
                                while( keyNum < 1 || keyNum > 5  )
                                {
                                    System.out.println("La opcion que eligio no corresponde al dominio."
                                            + "Favor de seguir la instruccion");
                                    contenido = sub.readLine();
                                    if(cc.datoNUMERICO(contenido))
                                        throw new DatoNumerico_Exception();
                                    
                                    keyNum = Integer.parseInt(contenido);
                                }
                                
                                System.out.println( cc.agregarVoto(clave, rut, keyNum) );
                                
                                break;
                            }
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
