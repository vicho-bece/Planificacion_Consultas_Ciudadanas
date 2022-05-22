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
public class ConsultaPorID {
    
    /**
     * treemap: Coleccion de Consultas multiples y binarias
     */
    private TreeMap<Integer, Consulta> treemap;
    
    /**
     * Constructor sin parametros
     */
    public ConsultaPorID(){
        this.treemap = new TreeMap();
    }

    /**
     * Getter de treemap
     * 
     * @return retorna la coleccion de consultas
     */
    public TreeMap<Integer, Consulta> getTreemap() {
        return treemap;
    }
    
    /**
     * Setter de treemap
     * 
     * @param consulta una consulta tipo binaria/multiple
     * @param key la clave de la consulta
     */
    public void agregarConsultas(Consulta consulta, Integer key){
        treemap.put(key, consulta);
    }
    
    //METODOS PARA ELEMENTOS DEL TREEMAP
    
    /**
     * Metodo para agregar una consulta
     * 
     * @throws IOException excepciones de input/output
     */
    public void agregarConsulta() throws IOException{
        
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        int clave;
        Consulta cc;
        
        System.out.println("1 = Consulta Binaria / 2 = Consulta Multiple");
        String opcion = campo.readLine();
        
        //1 = Consulta Binaria
        //2 = Consulta Multiple
        
        //Verifico que la opcion sea valida
        while(!"1".equals(opcion) && !"2".equals(opcion))
        {
            System.out.println("1 = Consulta Binaria / 2 = Consulta Multiple");
            opcion = campo.readLine();
        }
        
        //Segun el caso, doy referencia a la consulta correspondiente
        if(opcion.equals("1"))
            cc = new ConsultaBinaria();
        else
            cc = new ConsultaMultiple();
        
        //Datos de la consulta
        System.out.println("INGRESE ENUNCIADO");
        cc.setEnunciado(campo.readLine());
        System.out.println("INGRESE FECHA");
        cc.setFecha(campo.readLine());
        
        //Clave unica
        System.out.println("INGRESE CLAVE UNICA");
        clave = Integer.parseInt(campo.readLine());
        
        //Asegura que no se repita para agregar a la coleccion
        while(treemap.containsKey(clave))
        {
            clave = Integer.parseInt(campo.readLine());
        }
        
        agregarConsultas(cc, clave);   
    }
    
    /**
     * Metodo para mostrar todas las consulta de la coleccion
     * 
     * @throws IOException excepciones de input/output
     */
    public void mostrarConsultas() throws IOException{
        
        
        Object aux = null; //No necesito una consulta en especifico
        
        //Paso por esta sentencia para evitar errores
        if( !evitarCaidas(aux) )return;
        
        Consulta consultas;
        
        //Muestro todas las consultas segun la estrucutra de ella
        for(Map.Entry<Integer, Consulta> consulta : treemap.entrySet())
        {
            System.out.println("\nCLAVE: " + consulta.getKey());
            consultas = consulta.getValue();
            consultas.mostrarConsulta(false); 
        }
    }
    
    /**
     * Metodo para modificar una consulta de la coleccion
     * 
     * @param key la clave a una consulta asociada
     * @throws IOException excepciones de input/output
     */
    public void modificarConsulta(int key) throws IOException{
        
        //Prevenir errores y preguntas por la consulta dada key
        if( !evitarCaidas(key) )return;
        
        Consulta consulta = treemap.get(key);
        BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
        
        
        //Pregunto por el tipo de consulta
        if(consulta instanceof ConsultaMultiple)
        {
            ConsultaMultiple mm = (ConsultaMultiple)consulta;
            
            //En caso de que los votos fueron contados
            if( mm.getCont1() != 0 || mm.getCont2() != 0 || mm.getCont3() != 0 || mm.getCont4() != 0 || mm.getCont5() != 0)
            {
                //Agrego un texto en enunciado
                System.out.println("Ingrese la resolucion de esta consulta...");
                String aux = mm.getEnunciado() +"\nRESOLUCION: "+ campo.readLine();
                consulta.setEnunciado(aux);
                return;
            }
        }
        else
        {
            ConsultaBinaria bb = (ConsultaBinaria)consulta;
            
            //En caso de que los votos fueron contados
            if( bb.getContSi() != 0 || bb.getContNo() != 0)
            {
                //Agrego un texto en enunciado
                System.out.println("Ingrese la resolucion de esta consulta...");
                String aux = bb.getEnunciado() +"\nRESOLUCION: "+ campo.readLine();
                consulta.setEnunciado(aux);
                return;
            }
        }
            
            
        //En caso de no contar votos
        System.out.println("Ingrese un nuevo enunciado...");
        consulta.setEnunciado(campo.readLine());
        System.out.println("Ingrese una nueva fecha...");
        consulta.setFecha(campo.readLine());
    }
   
    /**
     * Elimino una consulta dada key
     * 
     * @param key la clave de la consulta
     * @throws IOException excepciones de input/output
     */
    public void eliminarConsulta(int key) throws IOException{
        
        //Evitar caidas
        if( !evitarCaidas(key) ) return;
        
        //Muestra la consulta
        System.out.println("La sgte. consulta sera ELIMINADA:");
        System.out.println("CLAVE: " + key);
        treemap.remove(key).mostrarConsulta(true);
    }
    
    /**
     * Busco y muestro una consulta a partir de una key
     * 
     * @param key la clave de la consulta
     * @throws IOException excepciones de input/output
     */
    public void buscarConsulta(int key) throws IOException{
        
        if( !evitarCaidas(key) ) return;
        
        Consulta consulta = treemap.get(key);
        consulta.mostrarConsulta(false);
    }
    
    /**
     * Busco y musetro las consultas dada fecha
     * 
     * @param date la fecha que se realizo la(s) consulta(s)
     * @throws IOException excepciones de input/output
     */
    public void buscarConsulta(String date) throws IOException{
        
        
        Object aux = null;
        if( !evitarCaidas(aux) ) return;
        
        Consulta consultas;
        
        //Reviso cada consulta de la coleccion, si hay coincidencia se imprime sus datos
        for(Map.Entry<Integer, Consulta> consulta : treemap.entrySet())
        {
            consultas = consulta.getValue();
            
            if(consultas.getFecha().equals(date))
            {
                System.out.println("CLAVE: " + consulta.getKey());
                consultas.mostrarConsulta(false);
            }
        }
    } 
    
    /**
     * Metodo para generar archivo de consultas multiples
     * 
     * @throws IOException excepciones de input/output
     */
    public void generarArchivoMultiple() throws IOException{
        
        try
        {
            String ruta = "./Salida_CSV/Consulta_Multiple/Multi.csv";
            File nuevoArchivo = new File(ruta);
            
            //Verifico que no exista
            if(!nuevoArchivo.exists())
                nuevoArchivo.createNewFile();//Crea uno nuevo
            
            //Archivo de escritura y buffer de escritura en el archivo
            FileWriter archivoEscritura = new FileWriter(nuevoArchivo);
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            
            int clave;
            Consulta consulta;
            
            //Recorro la coleccion
            for(Map.Entry<Integer, Consulta> mm: treemap.entrySet())
            {                   
                clave = mm.getKey();
                consulta = mm.getValue();
                //Asegura el tipo de consulta
                if(consulta instanceof ConsultaMultiple)
                {
                    //Escribo en el archivo
                    escribirDatos.write(clave + ";" + consulta.getEnunciado() + ";"
                    + consulta.getFecha() + ";" + ((ConsultaMultiple)consulta).getCont1()
                    + ";" + ((ConsultaMultiple)consulta).getCont2() + ";" + 
                    ((ConsultaMultiple)consulta).getCont3() + ";" + ((ConsultaMultiple)consulta).getCont4()
                    + ";" + ((ConsultaMultiple)consulta).getCont5() + "\n");
                    
                    //Aprovecho generar un archivo con respecto a la lista de vontantes
                    ((ConsultaMultiple)consulta).generarArchivoVOTOSMultiple(clave);
                }
            }
            escribirDatos.close();//Cierro el archivo
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
    }
    
    /**
     * Metodo para generar un archivo consultas binarias
     * 
     * @throws IOException excepciones de input/output
     */
    public void generarArchivoBinario() throws IOException{
        
        try
        {
            String ruta = "./Salida_CSV/Consulta_Binaria/Binaria.csv";
            File nuevoArchivo = new File(ruta);
            
            //Verifico que no exista
            if(!nuevoArchivo.exists())
                nuevoArchivo.createNewFile();//Crea uno nuevo
            
            //Archivo de escritura y buffer de escritura en el archivo
            FileWriter archivoEscritura = new FileWriter(nuevoArchivo);
            BufferedWriter escribirDatos = new BufferedWriter(archivoEscritura);
            
            int clave;
            Consulta consulta;
            
            //Recorro la coleccion
            for(Map.Entry<Integer, Consulta> mm: treemap.entrySet())
            {                   
                clave = mm.getKey();
                consulta = mm.getValue();
                //Aseguro el tipo de consulta
                if(consulta instanceof ConsultaBinaria)
                {
                    //Escribo en el archivo
                    escribirDatos.write(clave + ";" + consulta.getEnunciado() + ";"
                    + consulta.getFecha() + ";" + ((ConsultaBinaria) consulta).getContSi()
                    + ((ConsultaBinaria) consulta).getContNo() + "\n");
                    
                    //Aprovecho generar un archivo con respecto a la lista de vontantes
                    ((ConsultaBinaria) consulta).generarArchivoVOTOSBinario(clave);
                }
            }
            escribirDatos.close();//Cierro el archivo
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
    }
    
    //METODOS PARA LOS ELEMENTOS DE LA LISTA
    
    /**
     * Metodo para cargar votos de una consulta multiple
     * 
     * @param key la clave de la consulta multiple
     * @throws IOException excepciones de input/output
     */
    public void cargarVotosMultiples(int key) throws IOException{
        
        //Evito las caidas
        if( !evitarCaidas(key) ) return;
        
        //Aseguro el tipo de consulta
        if( treemap.get(key) instanceof ConsultaBinaria)
        {
            System.out.println("La consulta es de tipo Binaria...");
            return;
        }
         //Directorio donde se encuentra los archivos de votos (idealmente)
        File dir = new File("./Datos_CSV/Votos_Multiples/");
        
        //Nombre del archivo
        String nombre [] = dir.list();
        
        //Archivo csv
        CSV fileVotos;
                   
        String linea;//Para leer cada linea del archivo
        
       //Busco el archivo.csv
        for(String open : nombre)
            //Pregunto si el nombre del archivo termina en ".csv"
            if(open.endsWith(".csv") == true)
            {
                //Obtengo el archivo y lo leo
                fileVotos = new CSV("./Datos_CSV/Votos_Multiples/", open);
                linea = fileVotos.firstLine();
                
                //Pregunto si son los votos de tal consulta...
                if(key == Integer.parseInt(fileVotos.get_csvField(linea, 0)) )  
                {  
                    //Paso a la sgte linea
                    linea = fileVotos.nextLine();
                                
                    FormatoMultiple mm;
                    //EOF?
                    while(linea != null)
                    {
                        //Recojo los datos del csv
                        mm = new FormatoMultiple();
                        mm.setRut(fileVotos.get_csvField(linea, 0));
                        mm.setVoto(Integer.parseInt(fileVotos.get_csvField(linea, 1)));
                        
                        //Aseguro que el voto no sea repetido dado RUT
                        if(eliminarVotosMultiples( mm.getRut(), ((ConsultaMultiple)treemap.get(key)).getListaVotantes()) )
                            ((ConsultaMultiple)treemap.get(key)).agregarVoto(mm); //Lo agrego a la lista
                            
                        linea = fileVotos.nextLine();
                    }
                    
                    fileVotos.close();
                    break;//Finaliza la busqueda de archivo
                }
                fileVotos.close();     
            }
    }
    
    /**
     * Metodo para vargar votos de una consulta binaria
     * 
     * @param key la clave de una consulta binaria
     * @throws IOException excepciones de input/output
     */
    public void cargarVotosBinarios(int key) throws IOException{
        
        //Evito caidas
        if( !evitarCaidas(key) ) return;
        if( treemap.get(key) instanceof ConsultaMultiple)
        {
            System.out.println("La consulta es de tipo Multiple...");
            return;
        }
         //Directorio donde se encuentra los archivos de votos (idealmente)
        File dir = new File("./Datos_CSV/Votos_Binarios/");
        
        //Nombre del archivo
        String nombre [] = dir.list();
        
        //Archivo csv
        CSV fileVotos;
                   
        String linea;//Para leer cada linea del archivo
        
       //Busco el archivo.csv
        for(String open : nombre)
            //Pregunto si el nombre del archivo termina en ".csv"
            if(open.endsWith(".csv") == true)
            {
                //Obtengo el archivo y lo leo
                fileVotos = new CSV("./Datos_CSV/Votos_Binarios/", open);
                linea = fileVotos.firstLine();
                
                //Pregunto si son los votos de tal consulta...
                if(key == Integer.parseInt(fileVotos.get_csvField(linea, 0)) )  
                {  
                    //Paso a la sgte linea
                    linea = fileVotos.nextLine();
                    
                    
                    FormatoBinario bb;
                    //EOF?
                    while(linea != null)
                    {
                        //Recojo los datos del csv
                        bb = new FormatoBinario();
                        bb.setRut(fileVotos.get_csvField(linea, 0));
                        bb.setVoto(Boolean.parseBoolean(fileVotos.get_csvField(linea, 1)));
                        
                        //Asegura que no se repita el voto dado RUT
                        if(eliminarVotosBinarios( bb.getRut(), ((ConsultaBinaria)treemap.get(key)).getListaVotantes()) )
                            ((ConsultaBinaria)treemap.get(key)).agregarVoto(bb); //Lo agrego a la lista
                            
                        linea = fileVotos.nextLine();
                    }
                    
                    
                    fileVotos.close();
                    break;//Finaliza la busqueda de archivo
                }
                fileVotos.close();     
            }
    }
    
    /**
     * Metodo para imprimir los datos y votos de una consulta
     * 
     * @param key la clave de una consulta
     * @throws IOException excepciones de input/output
     */
    public void imprimirVotos(int key) throws IOException{
        
        //Reviso si esta la consulta
        if ( !evitarCaidas(key) ) return;
        
        //Imprimo datos
        Consulta consulta = treemap.get(key);
        consulta.mostrarConsulta(true);
    }
    
    /**
     * Metodo para modificar un voto binario
     * 
     * @param key la clave de una consulta binaria
     * @param rut el rut de votante
     * @throws IOException excepciones de input/output
     */
    public void modificarVotoBinario(int key, String rut) throws IOException{
        
        //Evita caidas
        if( !evitarCaidas(key) ) return;
        
        if( !(treemap.get(key) instanceof ConsultaBinaria) ) return;
        
        ConsultaBinaria consulta = (ConsultaBinaria) treemap.get(key);
        FormatoBinario voto;
        
        //Busco el voto en la lista por el RUT
        for(int i = 0; i < consulta.getListaVotantes().size(); i++)
        {
            voto = consulta.getListaVotantes().get(i);
            
            //Al encontrar se invierte el voto
            if(voto.getRut().equals(rut))
            {
                System.out.println("Voto del rut" + rut + "fue cambiado...");
                boolean aux = !voto.isVoto();
                voto.setVoto(aux);
                return;
            }
        }
        
        System.out.println("No hay rut guardado en la lista o la lista se encuentra vacia...");
    }
    
    /**
     * Metodo para modificar un voto multiple
     * 
     * @param key la clave de una consulta multiple
     * @param rut el rut del votante
     * @throws IOException excepciones de input/output
     */
    public void modificarVotoMultiple(int key, String rut) throws IOException{
        
        if( !evitarCaidas(key) ) return;
        
        if( !(treemap.get(key) instanceof ConsultaMultiple) ) return;
        
        ConsultaMultiple consulta = (ConsultaMultiple) treemap.get(key);
        FormatoMultiple voto;
        
        //Busco el votante de la lista por el RUT
        for(int i = 0; i < consulta.getListaVotantes().size(); i++)
        {
            voto = consulta.getListaVotantes().get(i);
            
            //Si lo encuentro
            if(voto.getRut().equals(rut))
            {
                //Informo las opciones que pude eligir
                BufferedReader campo = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Favor ingrese alguna de estas opciones:");
                System.out.println("[1, 2, 3, 4 o 5]");
                int op = Integer.parseInt(campo.readLine());
                
                //Aseguro que este en el dominio
                while(op < 1 || op > 5)
                {
                    System.out.println("Favor de obedecer el campo mencionado");
                    op = Integer.parseInt(campo.readLine());
                }    
                
                //Hago el cambio
                System.out.println("Voto del rut" + rut + "fue cambiado a " + op);
                voto.setVoto(op);
                return;
            }
        }
        
        System.out.println("No hay rut guardado en la lista o la lista se encuentra vacia...");
    }
    
    /**
     * Metodo para verificar que el voto este repetido 
     * 
     * @param rut el rut del votante
     * @param list el arraylist de la consulta binaria
     * @return false repetido, true para agregar a la lista
     */
    public boolean eliminarVotosBinarios(String rut, ArrayList<FormatoBinario> list){
        
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).getRut().equals(rut))
            {
                System.out.println("Rut repetido: " + rut);
                return false;
            }
        
        return true;
    }
    
    /**
     * Metodo para verificar que el voto este repetido
     * 
     * @param rut el rut del votante
     * @param list el arraylist de la consulta multiple
     * @return false repetido, true para agregar a la lista
     */
    public boolean eliminarVotosMultiples(String rut, ArrayList<FormatoMultiple> list){
        
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).getRut().equals(rut))
            {
                System.out.println("Rut repetido: " + rut);
                return false;
            }
        
        return true;
    }

    /**
     * Metodo para verificar que el votantes este en la coleccion de ciudadanos y este
     * habilitado para sufragar. Al no cumplir con las condiciones se elimina de la lista
     * 
     * @param key la clave de una consulta binaria
     * @param ciudadanos la coleccionn de ciudadanos
     * @return false cuando puede haber errores y true termino de revisar
     * @throws IOException excepciones de input/output
     */
    public boolean eliminarVotosBinarios(int key, HashMap<String, Ciudadano> ciudadanos) throws IOException{
        
        if( !evitarCaidas(key) ) return false;
        if( !(treemap.get(key) instanceof ConsultaBinaria) ) return false;
        
        ConsultaBinaria consultaB =(ConsultaBinaria) treemap.get(key);
        FormatoBinario formatoB;
        
        //Recorro la lista
        for(int i = 0; i < consultaB.getListaVotantes().size(); i++)
        {
            formatoB = consultaB.getListaVotantes().get(i);
            //Pregunto si esta en la coleccion de ciudadanos
            if(ciudadanos.containsKey(formatoB.getRut()))
            {
                //Esta habilitado para sufragar
                if( !ciudadanos.get(formatoB.getRut()).isHabilitado() )
                {
                    System.out.println("Voto eliminado, ciudadano no habilitado: " + formatoB.getRut());
                    consultaB.getListaVotantes().remove(i);
                    i--;
                }
            }
            else
            {
                System.out.println("Voto eliminado, ciudadano no registrado: " + formatoB.getRut());
                consultaB.getListaVotantes().remove(i);
                i--;
            }
        }
        return true;    
    }
 
    /**
     * Metodo para verificar que el votantes este en la coleccion de ciudadanos y este
     * habilitado para sufragar. Al no cumplir con las condiciones se elimina de la lista
     * 
     * @param key la clave de una consulta multiple
     * @param ciudadanos la coleccion de ciudadanos
     * @return false cuando puede haber errores y true termino de revisar
     * @throws IOException excepciones de input/output
     */
    public boolean eliminarVotosMultiples(int key, HashMap<String, Ciudadano> ciudadanos) throws IOException{
        
        if( !evitarCaidas(key) ) return false;
        
        if( !(treemap.get(key) instanceof ConsultaMultiple) ) return false;
        
        ConsultaMultiple consultaB =(ConsultaMultiple) treemap.get(key);
        FormatoMultiple formatoB;
        
        //Recorro la lista
        for(int i = 0; i < consultaB.getListaVotantes().size(); i++)
        {
            formatoB = consultaB.getListaVotantes().get(i);
            //Pregunto si esta en la coleccion de ciudadanos
            if(ciudadanos.containsKey(formatoB.getRut()))
            {
                //Habilitado para sufragar
                if( !ciudadanos.get(formatoB.getRut()).isHabilitado() )
                {
                    System.out.println("Voto eliminado, ciudadano no habilitado: " + formatoB.getRut());
                    consultaB.getListaVotantes().remove(i);
                    i--;
                }
            }
            else
            {
                System.out.println("Voto eliminado, ciudadano no registrado: " + formatoB.getRut());
                consultaB.getListaVotantes().remove(i);
                i--;
            }
        }
        return true;    
    }
    
    //METODOS DEL NEGOCIO
    
    /**
     * Metodo para mostrar la consulta con menos votos
     * @throws IOException excepciones de input/output
     */
    public void consultaMenosVotos() throws IOException{
        
        if( !evitarCaidas(null) ) return;
        
        //Variables de tipo aux
        int key = 0, menosVotos = -1, numVotos;
       
        //Reviso cada consulta
        for(Map.Entry<Integer, Consulta> consultas : treemap.entrySet())
        {
            
            numVotos = consultas.getValue().sumaVotos();
            //Exception del primera consulta
            if(menosVotos == -1)
            {
                //Reemplazo los datos
                key = consultas.getKey();
                menosVotos = numVotos;
            }
            else
                if(numVotos < menosVotos) //Compara con el numero de votos
                {
                    //Reemplazo los datos 
                    key = consultas.getKey();
                    menosVotos = numVotos;
                }
        }
        
        treemap.get(key).mostrarConsulta(false);
    }
    
    /**
     * Metodo para mostrar las consultas con su opcion mas votada
     * @throws IOException excepciones de input/output
     */
    public void opcionesMasVotadas() throws IOException{
        
        if ( !evitarCaidas(null) ) return;
        
        Consulta consulta;
        
        //Reviso cada consulta
        for(Map.Entry<Integer, Consulta> consultas : treemap.entrySet())
        {
            consulta = consultas.getValue();
            System.out.println("\nCLAVE: " + consultas.getKey() + "\n" + 
                    consulta.getEnunciado() + "\nOPCION MAS VOTADA: "
                    + consulta.opcionMasVotada());
        }
    }
    
    //METODOS ADICIONALES
    
    /**
     * Metodo para contar votos (para acceder al metodo )
     * @param key la clave de una consulta
     * @throws IOException excepciones de input/output
     */
    public void contarVOTOS(int key) throws IOException{
        
        treemap.get(key).contarVotos();
        System.out.println("Los votos fueron contados exitosamente...");
    }
    
    /**
     * Metodo para evitar que se caiga el programa
     * 
     * @param key opcional cuando se requiera verificar una consulta
     * @return False para prevenir, True que es seguro
     */
    public boolean evitarCaidas(Object key){
        
        //Si la coleccion esta vacia
        if( treemap.isEmpty() )
        {
            System.out.println("La coleccion esta vacia...");
            return false;
        }
        
        //En caso que necesite la key
        if(key != null)
            //Pregunto si esta en la coleccion
            if( !treemap.containsKey((int)key) )
            {
                System.out.println("No hay consulta con clave: " + key);
                return false;
            }
        
        return true;
    }
}
