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
public class Resultado {
    
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
