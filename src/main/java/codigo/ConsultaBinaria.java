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

/**
 * 
 * Clase hijo de Consulta
 */
public class ConsultaBinaria extends Consulta{
    
    /**
     * listaVotantes: lista de registro de ciudadanos que votaron 
     * admite el formato binario
     * 
     * contSi: contador de votos de Si
     * contNo: contador de votos de No
     */
    private ArrayList<FormatoBinario> listaVotantes = new ArrayList();
    private int contSi, contNo; 


    /**
     * Constructor de la clase
     */
    public ConsultaBinaria(){}
    
    /**
     * Getter de listaVotantes
     * 
     * @return la listaVotantes de una consulta 
     */
    @Override
    public ArrayList<FormatoBinario> getListaVotantes() {
        return listaVotantes;
    }
    
    /**
     * Setter del ArrayList
     * 
     * @param formato el voto binario, contiene el rut y voto 
     */
    public void agregarVoto(FormatoBinario formato){
        listaVotantes.add(formato);
    }

    /**
     * Getter de contNo
     * 
     * @return numero de votos de contSi
     */
    public int getContSi() {
        return contSi;
    }

    /**
     * Setter de contSi
     * 
     * Incrementa en una unidad a este atributo
     */
    public void setContSi() {
        this.contSi++;
    }

    /**
     * Getter de contNo
     * 
     * @return numero de votos de contNo 
     */
    public int getContNo() {
        return contNo;
    }

    /**
     * Setter de contNo
     * 
     * Incrementa en una unidad a este atributo
     */
    public void setContNo() {
        this.contNo++;
    }
    
    
    /**
     * Metodo para mostrar la informacion de una Consulta Binaria c/s la lista de votantes
     * @param lista La opcion de mostrar la lista en boolean
     * @return La informacion de una Consulta c/s la lista
     */
    @Override
    public String mostrarConsulta(boolean lista) {
        
        String mostrar = "";
        
        mostrar += "\nENUNCIADO: " + getEnunciado() + "\nFECHA: " + getFecha();
        mostrar += "\nSI: " + getContSi() + "\nNO: " + getContNo();
        
        if(lista)
        {
            //Verifica si la lista esta vacia
            if(listaVotantes.isEmpty())
            {
                mostrar += "\nLa lista de votantes esta vacia...";
                return mostrar;
            }    
            
            //Imprime los votantes que participaron. Rut y voto.
            mostrar += "\nLISTA DE VOTANTES:\n";
            FormatoBinario bb;
            
            for(int i = 0; i < listaVotantes.size(); i++)
            {
                bb = listaVotantes.get(i);
                mostrar += "\n" + ((i+1) + ")" + bb.getRut() + " " + bb.isVoto());
            }
        }
        
        return mostrar;
    }
    /**
     * Metodo implementado para contar los votos de una consulta binaria e indicar que
     * se contaron los votos
     */
    @Override
    public void contarVotos(){
        
        //Reviso cada votante
        FormatoBinario voto;
        for(int i = 0; i < listaVotantes.size(); i++)
        {
            voto = listaVotantes.get(i);
            //Aumenta en una unidad segun el voto
            if(voto.isVoto())
                setContSi();
            else
                setContNo();
        }
        
        setCheck(true);
    }
    
    /**
     * Metodo implementado para retornar la sumar los 2 contadores
     * @return la suma de votos de contSi y contNo
     * 
     */
    @Override
    public int sumaVotos() {
        return (getContSi() + getContNo());
    }
    
    /**
     * Metodo implementado para retornar la opcion mas votada
     * @return un String para imprimir que indica la opcion mas votada
     * 
     */
    @Override
    public String opcionMasVotada() {
        
        if( getContSi() > getContNo() )
            return("SI: " + getContSi());
        else
            return("NO: " + getContNo());
    }
    
    
    /**
     * Metodo para modificar el voto binario de un Votante con el Rut
     * @param opcion Respuesta que opto (En realidad, solo se invierte el voto con
     * respecto al anterior)
     * @param rut Rut del Votante
     * @return Un mensaje que indica el resultado de la operacion
     */
    @Override
    public String modificarVoto(int opcion, String rut){
        
        FormatoBinario voto;
        
        for(int i = 0; i < listaVotantes.size(); i++)
        {
            voto = listaVotantes.get(i);
            
            //Al encontrar se invierte el voto
            if(voto.getRut().equals(rut))
            {
                boolean aux = !voto.isVoto();
                voto.setVoto(aux);
                return "El votante del rut: " + rut + " a cambiado su voto a " + aux;
            }
        }
        
        return "No hay votante con el rut: " + rut;
    }
    
    /**
     * Metodo para eliminar los votos repetidos durante el ingreso de votos por CSV
     * @param rut Rut del votante, para ver si se repite
     * @param voto Contiene el rut y respuesta de un votante
     * @return Indica si esta repetido o no
     */
    @Override
    public boolean eliminarVotos(String rut, Object voto){
        
        for(int i = 0; i < listaVotantes.size(); i++){
            if(listaVotantes.get(i).getRut().equals(rut))
                return false;
        }
        
        agregarVoto( (FormatoBinario)voto );
        return true;
    }
    
    /**
     * Metodo para revisar que los Votantes de la lista esten en la Coleccion de Ciudadanos
     * y que se encuentra Habilitados para sufragar. Si no cumple ambas condiciones se elimina 
     * de la lista
     * 
     * @param ciudadanos La coleccion de Ciudadanos
     * @return Un String que indica los Votantes eliminados
     */
    @Override
    public String eliminarVotos(HashMap<String, Ciudadano> ciudadanos){
        
        FormatoBinario formatoB;
        String mostrar = "";
        //Recorro la lista
        for(int i = 0; i < listaVotantes.size(); i++)
        {
            formatoB = listaVotantes.get(i);
            //Pregunto si esta en la coleccion de ciudadanos
            if(ciudadanos.containsKey(formatoB.getRut()))
            {
                //Esta habilitado para sufragar
                if( !ciudadanos.get(formatoB.getRut()).isHabilitado() )
                {
                    mostrar +=("\nVoto eliminado, ciudadano no habilitado: " + formatoB.getRut());
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
     * Metodo para almacenar los datos de una Consulta Binaria, no incluye la lista
     * @return Un String con la informacion de una Consulta Binaria
     */
    @Override
    public String formatoCSV(){
        return (getEnunciado() + ";" + getFecha() + ";" + isCheck() + ";" +
                getContSi() + ";" + getContNo() + "\n");
    }
    
    /**
     * Metodo para buscar un Votante de la lista con el rut
     * @param rut Rut del Votante
     * @return Indica el resultado de la busqueda
     */
    @Override
    public String buscarVotante(String rut){
        
        FormatoBinario voto;
        
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
     * @param rut Rut del Votante
     * @return Indica el resultado de la eliminacion
     */
    @Override
    public String eliminarVotante(String rut){
        
         FormatoBinario voto;
        
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
     * Metodo para agregar un Votante de la lista de Votantes.
     * voto = 1 - true
     * voto = 2 - false
     * Ninguna de las anteriores - opcion no valido
     * @param rut Rut del Votante
     * @param voto Su respuesta binaria
     * @return Indica el resultado de la agregacion
     */
    @Override
    public String agregarVotante(String rut, int voto){
        
        FormatoBinario votante;
        
        for(int i = 0; i < listaVotantes.size(); i++)
        {
            votante = listaVotantes.get(i);
            
            //Si lo encuentro
            if(votante.getRut().equals(rut))
                return "\nEl rut: " + rut + " participo en este consulta";
        }
        
        
        
        if(voto == 1)
            votante = new FormatoBinario(rut, true);
        else
        {
            if(voto == 2)
                votante = new FormatoBinario(rut, false);
            else
                return "La opcion del voto no es valido";
        }
        
        listaVotantes.add(votante);
        return "Se agrego el votante exitosamente";
    }
}
