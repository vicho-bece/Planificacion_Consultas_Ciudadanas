/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
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
     * Metodo implementado para mostrar los datos de una consulta 
     * y la lista de los votantes segun la situacion
     * 
     * @param lista para acceder a los datos de la lista de votantes 
     * @throws IOException excepciones de input/output
     */
    @Override
    public void mostrarConsulta(boolean lista) throws IOException{
        System.out.println("ENUNCIADO: " + getEnunciado() + "\nFECHA: " + getFecha());
        System.out.println("SI: " + getContSi() + "\nNO: " + getContNo());
        
        
        if(lista)
        {
            //Verifica si la lista esta vacia
            if(listaVotantes.isEmpty())
            {
                System.out.println("La lista de votantes esta vacia...");
                return;
            }    
            
            //Imprime los votantes que participaron. Rut y voto.
            System.out.println("\nLISTA DE VOTANTES:\n");
            FormatoBinario bb;
            
            for(int i = 0; i < listaVotantes.size(); i++)
            {
                bb = listaVotantes.get(i);
                System.out.println((i+1) + ")" + bb.getRut() + " " + bb.isVoto());
            }
        }  
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
    }
    
    /**
     * Metodo implementado para retornar la sumar los 2 contadores
     * @return la suma de votos de contSi y contNo
     * @throws IOException excepciones de input/output
     */
    @Override
    public int sumaVotos() throws IOException{
        return (getContSi() + getContNo());
    }
    
    /**
     * Metodo implementado para retornar la opcion mas votada
     * @return un String para imprimir que indica la opcion mas votada
     * @throws IOException excepciones de input/output
     */
    @Override
    public String opcionMasVotada() throws IOException{
        
        if( getContSi() > getContNo() )
            return("SI: " + getContSi());
        else
            return("NO: " + getContNo());
    }
    
    /**
     * Metodo de la clase para generar un archivo de los votos de una consulta binaria
     * @param key la clave de una consulta
     * @throws IOException excepciones de input/output
     */
    public void generarArchivoVOTOSBinario(Object key) throws IOException{
        
        //Si no hay datos en la lista finalizo la operacion
        if(listaVotantes.isEmpty()) return;
        
        try
        {
            //Directorio para los votos y variable tipo archivo
            String ruta = ("./Salida_CSV/Votos_Binario/VotoBinario" +(int)key+ ".csv");
            File newfile = new File(ruta);
            
            //Pregunto si no existe un archivo
            if(!newfile.exists())
                newfile.createNewFile();//Se crea uno nuevo
            
            //Archivo de escritura y buffer de escritura en el archivo
            FileWriter archivoEscritura = new FileWriter(newfile);
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            FormatoBinario voto;
            
            //Escribo la key en la primera, en referencia a los archivos de lectura
            escribirDatos.write((int)key + "\n");
            
            //Recorro la lista de votantes
            for(int i = 0; i < listaVotantes.size(); i++)
            {
                voto = listaVotantes.get(i);
                escribirDatos.write(voto.getRut() + ";" + voto.isVoto() + "\n");
            }
       
            escribirDatos.close();//Cierro el archivo
                    
        }
        catch(Exception e)//Encuentra el error
        {
            e.printStackTrace();//Imprime dicho error
        }
    }
}
