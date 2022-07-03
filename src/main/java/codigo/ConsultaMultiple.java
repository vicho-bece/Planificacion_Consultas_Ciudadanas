package Codigo;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
/**
 *
 * @author vicho
 */

//Clase hijo de Consulta
public class ConsultaMultiple extends Consulta{
    
    /**
     * listaVotantes: lista de registro de ciudadanos que votaron
     * admite el formato de seleccion multiple
     * 
     * cont1: contador de opcion 1
     * cont2: contador de opcion 2
     * cont3: contador de opcion 3
     * cont4: contador de opcion 4
     * cont5: contador de opcion 5
     */
    private ArrayList<FormatoMultiple> listaVotantes = new ArrayList(); 
    private int cont1, cont2, cont3, cont4, cont5; 
    
    /**
     * Constructor de la clase
     */
    public ConsultaMultiple(){}
    
    
    /**
     * Getter de listaVotantes
     * 
     * @return retorna la lista de votantes de una consulta 
     */
    @Override
    public ArrayList<FormatoMultiple> getListaVotantes() {
        return listaVotantes;
    }
    
    /**
     * Setter de listaVotantes
     * 
     * @param formato corresponde al voto de seleccion multiple, con Rut y voto
     */
    public void agregarVoto(FormatoMultiple formato){
        listaVotantes.add(formato);
    }
    
    /**
     * Getter de cont1
     * 
     * @return el numero de votos de cont1 
     */
    public int getCont1() {
        return cont1;
    }
    
    /**
     * Setter de cont1
     * Incrementa una unidad a este atributo
     */
    public void setCont1() {
        this.cont1++;
    }
    
    /**
     * Getter de cont2
     * @return el numero de votos de cont2 
     */
    public int getCont2() {
        return cont2;
    }
    
    /**
     * Setter de cont2
     * Incrementa una unidad a este atributo
     */
    public void setCont2() {
        this.cont2++;
    }
    
    /**
     * Getter de cont3
     * @return el numero de votos de cont3
     */
    public int getCont3() {
        return cont3;
    }
    
    /**
     * Setter de cont3
     * Incrementa una unidad a este atributo
     */
    public void setCont3() {
        this.cont3++;
    }
    
    /**
     * Getter de cont4
     * @return el numero de votos de cont4
     */
    public int getCont4() {
        return cont4;
    }
    
    /**
     * Setter de cont4
     * Incrementa una unidad a este atributo
     */
    public void setCont4() {
        this.cont4++;
    }
    
    /**
     * Getter de cont5
     * @return el numero de votos de cont5
     */
    public int getCont5() {
        return cont5;
    }
    
    /**
     * Setter de cont5
     * Incrementa una unidad a este atributo
     */
    public void setCont5() {
        this.cont5++;
    }
    
    /**
     * Metodo para mostrar los datos de una Consulta Multiple c/s la lista de Votantes
     * @param lista Opcion de mostrar la lista
     * @return Un String con los datos de una consulta
     */
    @Override
    public String mostrarConsulta(boolean lista) {
        
        String mostrar = "";
        
        mostrar += ("\nENUNCIADO: " + getEnunciado() + "\nFECHA: " + getFecha());
        mostrar += ("\nOPCION 1: " + getCont1() + "\nOPCION 2: " + getCont2());
        mostrar += ("\nOPCION 3: " + getCont3() + "\nOPCION 4: " + getCont4());
        mostrar += ("\nOPCION 5: " + getCont5());

        if(lista)
        {
            //Verifica si la lista esta vacia
            if(listaVotantes.isEmpty())
            {
                mostrar += "\nLa lista de votantes esta vacia...";
                return mostrar;
            }
            
            //Se imprime todos los votantes que participaron. Rut y voto
            mostrar += "\nLISTA DE PARTICIPANTES:\n";
            FormatoMultiple mm;
            
            for(int i = 0; i < listaVotantes.size(); i++)
            {
                mm = listaVotantes.get(i);
                mostrar += "\n" + (i+1) + ")" + mm.getRut() + " " + mm.getVoto();
            }
        }
        
        return mostrar;
    }
    
    /**
     * Metodo implementado para contar los votos de una consulta multiple e indicar
     * que se conto los votos de la consulta
     */
    @Override
    public void contarVotos(){
        
        //Reviso cada votante
        FormatoMultiple voto;
        for(int i = 0; i < listaVotantes.size(); i++)
        {
            voto = listaVotantes.get(i);
            
            //Incrementa en una unidad segun la opcion del voto
            switch(voto.getVoto())
            {
                case 1: setCont1(); break;
                case 2: setCont2(); break;
                case 3: setCont3(); break;
                case 4: setCont4(); break;
                case 5: setCont5(); break;
                
                default: break;
            }
        }
        
        setCheck(true);
    }
    
    /**
     * Metodo para retornar la suma de votos de las 5 contadores
     * @return retorna la suma total entre los 5 contadores
     * 
     */
    @Override
    public int sumaVotos() {
        return (getCont1() + getCont2() + getCont3() + getCont4() + getCont5());
    }
    
    /**
     * Metodo para retornar la opcion mas votada, se compara los 5 contadores partiendo
     * desde el primero. Se asume que un contador no es el mas votado cuando no
     * cumple con la sentencia
     * 
     * @return la opcion mas votada en String
     * 
     */
    @Override
    public String opcionMasVotada() {
        
        
        if( getCont1() > getCont2() )
            if( getCont1() > getCont3())
                if( getCont1() > getCont4() )
                    if( getCont1() > getCont5())
                        return "OPCION 1: " + getCont1();
        
        if( getCont2() > getCont3() )
            if( getCont2() > getCont4() )
                if( getCont2() > getCont5() )
                    return "OPCION 2: " + getCont2();
        
        if( getCont3() > getCont4() )
            if( getCont3() > getCont5() )
                return "OPCION 3: " + getCont3();
                
        if( getCont4() > getCont5() )
            return "OPCION 4: " + getCont4();
            
        return "OPCION 5: " + getCont5();
    }
    
    /**
     * Metodo para modificar un Voto multiple de un Votante con el rut
     * @param opcion Respuesta que opto del 1 al 5
     * @param rut Rut del Votante
     * @return Un String que indica el resultado de la operacion
     */
    @Override
    public String modificarVoto(int opcion, String rut){
        
        FormatoMultiple voto;
        
        for(int i = 0; i < listaVotantes.size(); i++)
        {
            voto = listaVotantes.get(i);
            
            //Si lo encuentro
            if(voto.getRut().equals(rut))
            {
                voto.setVoto(opcion);
                return "El votante del rut: " + rut + " a cambiado su voto a " + opcion;
            }
        }
        
        return "No hay votante con el rut: " + rut;
    }
    
    /**
     * Metodo para prevenir que repita votos con el uso de la
     * @param rut Rut del votante para investigar
     * @param voto Contiene el rut y respuesta del Votante
     * @return Indica si se repite el voto o se agrega el voto 
     */
    @Override
    public boolean eliminarVotos(String rut, Object voto){
        
        for(int i = 0; i < listaVotantes.size(); i++){
            if(listaVotantes.get(i).getRut().equals(rut))
                return false;
        }
        
        agregarVoto( (FormatoMultiple)voto );
        return true;
    }
    
    /**
     * Metodo para revisar que los Votantes de la lista esten en la Coleccion de Ciudadanos
     * y que se encuentra Habilitados para sufragar. Si no cumple ambas condiciones se elimina 
     * de la lista
     * 
     * @param ciudadanos Coleccion de Ciudadanos
     * @return Un String que contiene los Votantes que se eliminaron
     */
    @Override
    public String eliminarVotos(HashMap<String, Ciudadano> ciudadanos){
        
        FormatoMultiple formatoB;
        String mostrar = "";
        //Recorro la lista
        for(int i = 0; i < listaVotantes.size(); i++)
        {
            formatoB = listaVotantes.get(i);
            //Pregunto si esta en la coleccion de ciudadanos
            if(ciudadanos.containsKey(formatoB.getRut()))
            {
                //Habilitado para sufragar
                if( !ciudadanos.get(formatoB.getRut()).isHabilitado() )
                {
                    mostrar += ("\nVoto eliminado, ciudadano no habilitado: " + formatoB.getRut());
                    listaVotantes.remove(i);
                    i--;
                }
            }
            else
            {
                mostrar += ("\nVoto eliminado, ciudadano no registrado: " + formatoB.getRut());
                listaVotantes.remove(i);
                i--;
            }
        }
        
        return mostrar;
    }
    
    /**
     * Metodo para almacenar los datos de una Consulta Multiple, no incluye lista
     * @return Un String de la informacion de una Consulta Multiple
     */
    @Override
    public String formatoCSV(){
        return (getEnunciado() + ";" + getFecha() + ";" + isCheck() + ";" +
                getCont1() + ";" + getCont2() + ";" + getCont3() + ";" + getCont4()
                + ";" + getCont5() + "\n");
    }
    
    /**
     * Metodo para buscar un Votante de la lista con el rut
     * @param rut Rut del Votante a buscar
     * @return Indica que resultado de la busqueda
     */
    public String buscarVotante(String rut){
        
        FormatoMultiple voto;
        
        for(int i = 0; i < listaVotantes.size(); i++)
        {
            voto = listaVotantes.get(i);
            
            //Si lo encuentro
            if(voto.getRut().equals(rut))
                return "\nEl rut: " + rut + " participo en este consulta";
        }
        
        return "\nEl rut: " + rut + " no se encontro en la lista de votantes"; 
    }
    
    /**
     * Metodo para eliminar un Votante de la lista con el rut
     * @param rut Rut del Votante a eliminar
     * @return Indica el resultado de la eliminacion
     */
    @Override
    public String eliminarVotante(String rut){
        
         FormatoMultiple voto;
        
        for(int i = 0; i < listaVotantes.size(); i++)
        {
            voto = listaVotantes.get(i);
            
            //Si lo encuentro
            if(voto.getRut().equals(rut))
            {
                listaVotantes.remove(i);
                return "Se elimino el votante del rut:" + rut;
            }
                
        }
        
        return "\nEl rut: " + rut + " no se encontro en la lista de votantes"; 
    }
    
    /**
     * Metodo para agregar un Votante a la lista de Votantes.
     * @param rut Rut del votante
     * @param voto Respuesta que opto
     * @return Indica que resultado de la agregacion
     */
    @Override
    public String agregarVotante(String rut, int voto){
        
        FormatoMultiple votante = new FormatoMultiple(rut, voto);
        if(voto > 0 && voto < 6)
        {
            listaVotantes.add(votante);
            return "Se agrego el votante exitosamente";
        }
        
        return "La opcion que ingreso no esta dentro del dominio";
    }
}
