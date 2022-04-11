/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author vicho
 */
public class ListaVotantes {
    
    private ArrayList<FormatoBinario> lista;//Coleccion tipo lista
    
    //Constructores de la clase
    public ListaVotantes(){
        lista = new ArrayList();
    }
    
    public ListaVotantes(ArrayList lista){
        lista = new ArrayList();
        lista.addAll(lista);
    }
    
    //Getter de la lista
    public ArrayList<FormatoBinario> getLista() {
        return lista;
    }
    
    //Setter para ingresar una lista dentro de la coleccion
    public void agregarVoto(FormatoBinario formato){
        lista.add(formato);
    }
}
