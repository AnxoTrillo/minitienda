package minitienda;

import java.util.StringTokenizer;
import java.io.Serializable;

public class Disco implements Serializable{
    private String titulo;
    private String autor;
    private String lugar;
    private Float precio;
    private String descripcion;

    //constructor
    public Disco(){
        this.titulo=new String();
        this.autor=new String();
        this.lugar=new String();
        this.descripcion=new String();
        this.precio=0f;
    }

    //constructor con argumento de entrada
    public Disco(String entrada){
        this.descripcion=entrada;
        StringTokenizer t = new StringTokenizer(entrada,"|");
        this.titulo=t.nextToken();
        this.autor=t.nextToken();
        this.lugar=t.nextToken();
        this.precio=Float.parseFloat(t.nextToken().replace('$',' ').trim());
    }

    //getters
    public String getTitulo(){
        return this.titulo;
    }
    public String getAutor(){
        return this.autor;
    }
    public String getLugar(){
        return this.lugar;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public Float getPrecio(){
        return this.precio;
    }

    //setters
    public void setTitulo(String entrada){
        this.titulo=entrada;
    }
    public void setAutor(String entrada){
        this.autor=entrada;
    }
    public void setLugar(String entrada){
        this.lugar=entrada;
    }
    public void setDescripcion(String entrada){
        this.descripcion=entrada;
        StringTokenizer t = new StringTokenizer(entrada,"|");
        this.titulo=t.nextToken();
        this.autor=t.nextToken();
        this.lugar=t.nextToken();
        this.precio=Float.parseFloat(t.nextToken().replace('$',' ').trim());
    }
    public void setPrecio(Float entrada){
        this.precio=entrada;
    }

}
