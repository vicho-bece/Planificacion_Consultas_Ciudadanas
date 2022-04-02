package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vicho
 */
public class Consulta {
    
        //ATRIBUTOS...
    private String enunciado; //Enunciado de la consulta propuesta
    private String fecha; //Fecha estimada a realizar
    private int contSi; //Contador de votos con respuesta "si"
    private int contNo; //Contador de votos con respuesta "no"
    private ListaVotantes list;//Clase de la lista de votos registrados
    
    //Constructor por defecto
    public Consulta(){        
    }
    
    //Constructor para instanciar una variable
    public Consulta(Consulta consulta){
        this.enunciado = consulta.enunciado;
        this.fecha = consulta.fecha;
        this.list = consulta.list;
    }
    
    //Constructor de consulta
    public Consulta(String enunciado, String fecha, ListaVotantes list){
        
        //Con parametros
        this.enunciado = enunciado;
        this.fecha = fecha;
        this.list = list;
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
    
    //Getter de la Lista de votantes participados
    public ListaVotantes getList() {
        return list;
    }

    //Setter de la lista...
    public void setList(ListaVotantes list) {
        this.list = list;
    }
}
