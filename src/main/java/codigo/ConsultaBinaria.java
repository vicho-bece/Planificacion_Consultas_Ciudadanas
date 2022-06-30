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
     * Metodo implementado para contar los votos de una consulta binaria
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
    
    @Override
    public boolean eliminarVotos(String rut, Object lista){
        
        for(int i = 0; i < listaVotantes.size(); i++){
            if(listaVotantes.get(i).getRut().equals(rut))
                return false;
        }
        
        agregarVoto( (FormatoBinario)lista );
        return true;
    }
    
    @Override
    public boolean eliminarVotos(HashMap<String, Ciudadano> ciudadanos){
        
        FormatoBinario formatoB;
        
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
                    System.out.println("Voto eliminado, ciudadano no habilitado: " + formatoB.getRut());
                    listaVotantes.remove(i);
                    i--;
                }
            }
            else
            {
                System.out.println("Voto eliminado, ciudadano no registrado: " + formatoB.getRut());
                listaVotantes.remove(i);
                i--;
            }
        }
        return true;
    }
    
    @Override
    public String formatoCSV(){
        return (getEnunciado() + ";" + getFecha() + ";" + isCheck() + ";" +
                getContSi() + ";" + getContNo() + "\n");
    }
}
