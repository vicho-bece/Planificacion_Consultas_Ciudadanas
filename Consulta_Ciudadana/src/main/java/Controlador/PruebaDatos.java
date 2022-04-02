package Controlador;


import Modelo.CiudadanoPorRut;
import Modelo.*;
import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        
        pp.agregarCiudadanos();
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
        
        //Ingreso los datos a la clase CiudadanoPorRut que contiene el hashmap...
        pp.setMap(dato);
        ciudadano = new Ciudadano();
        //Ingreso por teclado
        pp.agregarCiudadanos(pp,ciudadano);
        dato = pp.getMap();
        
        ciudadano = dato.get("9231456-k");
        System.out.println(ciudadano.getNombre());
                
        
        //Variables para las Consultas
        ConsultaPorID cc = new ConsultaPorID();
        TreeMap<Integer, Consulta> mapa;
        
        Consulta consulta = new Consulta();
        mapa = cc.getTreemap();
        
        //Ingreso un ejemplo de consulta...
        consulta.setEnunciado("Prueba de uso el treemap de consultas...");
        consulta.setFecha("01042022");
        
        mapa.put(1, consulta);
        cc.setTreemap(mapa);
        
        //Se busca la consulta
        consulta = cc.buscarConsulta("01042022");
        System.out.println("FUNCIONA... " + consulta.getEnunciado());
                
    }
}