
import java.io.*;

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
        
        //Declaro un arreglo tipo clase Ciudadano
        Ciudadano ciudadano[] = new Ciudadano[5];
        
        //En cada indice del arreglo instacia con la estructura de la clase
        int i = 0;
        for(;i < ciudadano.length; i++)
            ciudadano[i] = new Ciudadano();
        
        //Empiezo a poblar a los atributos de los 5 indices...
        ciudadano[0].setNombre("Mr Juan");
        ciudadano[0].setRut("12345678-9");
        ciudadano[0].setSexo(true);
        ciudadano[0].setHabilitado(true);
        
        ciudadano[1].setNombre("Vladimir Putin");
        ciudadano[1].setRut("11111111-1");
        ciudadano[1].setSexo(true);
        ciudadano[1].setHabilitado(true);
        
        ciudadano[2].setNombre("Barack Obama");
        ciudadano[2].setRut("0000000-0");
        ciudadano[2].setSexo(true);
        ciudadano[2].setHabilitado(true);
        
        ciudadano[3].setNombre("Michelle Obama");
        ciudadano[3].setRut("20000000-k");
        ciudadano[3].setSexo(false);
        ciudadano[3].setHabilitado(true);
        
        ciudadano[4].setNombre("Donald Trump");
        ciudadano[4].setRut("9231456-k");
        ciudadano[4].setSexo(true);
        ciudadano[4].setHabilitado(false);
        
        //Imprimo toda la informacion de cada indice
        for(i = 0;i < ciudadano.length; i++){
            System.out.println(ciudadano[i].getNombre() + " " + ciudadano[i].getRut());
            
            //Verificacion de Sexo y Habilitacion
            if(ciudadano[i].isSexo())
                System.out.println("HOMBRE");
            else
                System.out.println("MUJER");
            
            if(ciudadano[i].isHabilitado())
                System.out.println("Habilitado para votar\n");
            else
                System.out.println("No apto para votar\n");
        }
        
        //Crea una variable tipo Consulta y se completa los datos tipo cadena
        Consulta consulta = new Consulta();
        consulta.setEnunciado("Esta de acuerdo con el aborto?");
        consulta.setFecha("29/02/2024");
        
        //Equivale a incremento de una unidad...
        consulta.setContNo();
        consulta.setContSi();
        
        //Se imprime toda la informacion de la consulta
        System.out.println(consulta.getFecha());
        System.out.println(consulta.getEnunciado());
        System.out.println("Si = " + consulta.getContSi());
        System.out.println("No = " +consulta.getContNo() + "\n");
        
        //Se crea una variable tipo Resultado y completo los datos...
        Resultado resultado = new Resultado();
        resultado.setResolucion("Resultado = Empate");
        resultado.setSi(consulta.getContSi());
        resultado.setNo(consulta.getContNo());
        
        //Se imprime los resultados...
        System.out.println(resultado.getResolucion());
        System.out.println("SI = " + resultado.getSi() + " VS NO = " + resultado.getNo());
        
        //AQUI FINALIAZA LA FUNCION MAIN...
    }
}

class Ciudadano {
    
    //ATRIBUTOS...
    private String rut; //rut del ciudadano, dato tipo unico
    private String nombre; //identidad del ciudadano
    private boolean sexo; //True = Hombre, False = Mujer
    private boolean habilitado; //habilitado para sufragar
    
    //Constructor por defecto
    public Ciudadano(){
        
    }
    
    //Constructor para instanciar una variable
    public Ciudadano(Ciudadano ciudadano)
    {
        this.rut = ciudadano.rut;
        this.nombre = ciudadano.nombre;
        this.sexo = ciudadano.sexo;
        this.habilitado = ciudadano.habilitado;
    }
    
    //Constructor ciudadano con parametros
    public Ciudadano(String id, String name, boolean sex, boolean permitido){
        this.rut = id;
        this.nombre = name;
        this.sexo = sex; 
        this.habilitado = permitido;
    }
    
    //Getter de rut
    public String getRut() {
        return rut;
    }
    
    //Setter de rut
    public void setRut(String rut) {
        this.rut = rut;
    }
    
    //Getter de nombre
    public String getNombre() {
        return nombre;
    }
    
    //Setter de nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Getter de sexo
    public boolean isSexo() {
        return sexo;
    }
    
    //Setter de sexo
    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
    
    //Getter de habilitado
    public boolean isHabilitado() {
        return habilitado;
    }
    
    //Setter de habilitado
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
}

class Consulta {
    
    //ATRIBUTOS...
    private String enunciado; //Enunciado de la consulta propuesta
    private String fecha; //Fecha estimada a realizar
    private int contSi; //Contador de votos con respuesta "si"
    private int contNo; //Contador de votos con respuesta "no"
    
    //Constructor por defecto
    public Consulta(){
        
    }
    
    //Constructor para instanciar una variable
    public Consulta(Consulta consulta){
        this.enunciado = consulta.enunciado;
        this.fecha = consulta.fecha;
    }
    
    //Constructor de consulta
    public Consulta(String pregunta, String date){
        
        //Con parametros
        this.enunciado = pregunta;
        this.fecha = date;
        
        //Atributos iniciados en 0s y sin parametros
        contSi = 0;
        contNo = 0;
    }
    
    //Getter del enunciado
    public String getEnunciado() {
        return enunciado;
    }
    
    //Setter del enunciado
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    
    //Getter de fecha
    public String getFecha() {
        return fecha;
    }
    
    //Setter de fecha
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    //Getter de respuesta SI
    public int getContSi() {
        return contSi;
    }
    
    //Setter de respuesta SI, metodo contador
    public void setContSi() {
        this.contSi++;
    }
    
    //Getter de respuesta NO
    public int getContNo() {
        return contNo;
    }
    
    //Setter de respuesta NO, metodo contador
    public void setContNo() {
        this.contNo++;
    }
    
    
}

class Resultado {
    
    //ATRIBUTOS
    private String resolucion; //Imprimir un mensaje acerca del resultado
    private int si; //Total de votos con respuesta "si"
    private int no; //Total de votos con respuesta "no"

    //Constructor por defecto
    public Resultado(){
        
    }
    
    //Constructor para instanciar una variable
    public Resultado(Resultado resultado){
        this.resolucion = resultado.resolucion;
        this.si = resultado.si;
        this.no = resultado.no;
    }
    
    //Constructor de resultado con parametros
    public Resultado(String resolucion, int si, int no) {
        this.resolucion = resolucion;
        this.si = si;
        this.no = no;
    }
    
    //Getter de resolucion
    public String getResolucion() {
        return resolucion;
    }
    
    //Setter de resolucion
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
    
    //Getter de respuestas con Si
    public int getSi() {
        return si;
    }

    //Setter de respuestas con Si
    public void setSi(int si) {
        this.si = si;
    }

    //Getter de respuestas con No
    public int getNo() {
        return no;
    }

    //Setter de respuestas con No
    public void setNo(int no) {
        this.no = no;
    }   
}