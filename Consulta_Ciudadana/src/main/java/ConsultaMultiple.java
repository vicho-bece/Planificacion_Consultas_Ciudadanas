/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author vicho
 */

//Clase hijo de Consulta
public class ConsultaMultiple extends Consulta{
    
    private ArrayList<FormatoMultiple> listaVotantes = new ArrayList(); //registro
    //de ciudadanos que votaron
    
    
    private int cont1, cont2, cont3, cont4, cont5; //Contadores para las 5 opciones
    
    //Constructor
    public ConsultaMultiple(){}

    //Metodos de Getter Y Setter
    public ArrayList<FormatoMultiple> getListaVotantes() {
        return listaVotantes;
    }
    
    public void agregarVoto(FormatoMultiple formato){
        listaVotantes.add(formato);
    }

    public int getCont1() {
        return cont1;
    }

    public void setCont1() {
        this.cont1++;
    }

    public int getCont2() {
        return cont2;
    }

    public void setCont2() {
        this.cont2++;
    }

    public int getCont3() {
        return cont3;
    }

    public void setCont3() {
        this.cont3++;
    }

    public int getCont4() {
        return cont4;
    }

    public void setCont4() {
        this.cont4++;
    }

    public int getCont5() {
        return cont5;
    }

    public void setCont5() {
        this.cont5++;
    }

    
    @Override
    //Metodo implementado para mostrar los datos de una consulta
    //y la lista segun el caso (lo implementado en ConsultaPorID)
    public void mostrarConsulta(boolean lista) throws IOException{
        
        System.out.println("ENUNCIADO: " + getEnunciado() + "\nFECHA: " + getFecha());
        System.out.println("OPCION 1: " + getCont1() + "\nOPCION 2: " + getCont2());
        System.out.println("OPCION 3: " + getCont3() + "\nOPCION 4: " + getCont4());
        System.out.println("OPCION 5: " + getCont5());

        if(lista)
        {
            //Verifica si la lista esta vacia
            if(listaVotantes.isEmpty())
            {
                System.out.println("La lista de votantes esta vacia...");
                return;
            }
            
            //Se imprime todos los votantes que participaron. Rut y voto
            System.out.println("\nLISTA DE PARTICIPANTES:\n");
            FormatoMultiple mm;
            
            for(int i = 0; i < listaVotantes.size(); i++)
            {
                mm = listaVotantes.get(i);
                System.out.println((i+1) + ")" + mm.getRut() + " " + mm.getVoto());
            }
        }  
    }
    
    @Override
    //Metodo implementado para contar los votos de una consulta multiple
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
    }
    
    @Override
    //Metodo para retornar la suma de votos de las 5 contadores
    public int sumaVotos() throws IOException{
        return (getCont1() + getCont2() + getCont3() + getCont4() + getCont5());
    }
    
    
    @Override
    //Metodo para retornar la opcion mas votada, se compara los 5 contadores partiendo
    //desde el primero. Se asume que un contador no es el mas votado cuando no
    //cumple con la sentencia
    public String opcionMasVotada() throws IOException{
        
        
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
     * 
     * @param key
     * @throws IOException 
     */
    //Metodo para generar un archivo de los votos de una consulta multiple
    public void generarArchivoVOTOSMultiple(Object key) throws IOException{
        
        //Si no hay datos en la lista finalizo la operacion
        if(listaVotantes.isEmpty()) return;
        
        try
        {
            //Directorio para los votos y variable tipo archivo
            String ruta = ("./Salida_CSV/Votos_Multiple/VotoMulti" +(int)key+ ".csv");
            File newfile = new File(ruta);
            
            //Pregunto si no existe un archivo
            if(!newfile.exists())
                newfile.createNewFile();//Se crea uno nuevo
            
            //Archivo de escritura y buffer de escritura en el archivo
            FileWriter archivoEscritura = new FileWriter(newfile);
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            FormatoMultiple voto;
            
            //Escribo la key en la primera, en referencia a los archivos de lectura
            escribirDatos.write((int)key + "\n");
            
            //Recorro la lista de votantes
            for(int i = 0; i < listaVotantes.size(); i++)
            {
                voto = listaVotantes.get(i);
                escribirDatos.write(voto.getRut() + ";" + voto.getVoto() + "\n");
            }
       
            escribirDatos.close();//Cierro el archivo
                    
        }
        catch(Exception e)//Encuentra el error
        {
            e.printStackTrace();//Imprime dicho error
        }
    }
}
