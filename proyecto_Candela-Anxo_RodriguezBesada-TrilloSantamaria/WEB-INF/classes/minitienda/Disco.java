package minitienda;

import java.util.StringTokenizer;
import java.io.Serializable;

public class Disco implements Serializable{
    private String titulo;
    private String autor;
    private String pais;
    private float precio;
    private String descripcion;

    //Constructores
    public Disco(){
        this.titulo = new String();
        this.autor = new String();
        this.pais = new String();
        this.descripcion = new String();
        this.precio = 0f;
    }

    //constructor con argumento de entrada
    public Disco(String entrada){
        this.descripcion = entrada;
        StringTokenizer t = new StringTokenizer(entrada,"|");
        this.titulo = t.nextToken();
        this.autor = t.nextToken();
        this.pais = t.nextToken();
        this.precio = Float.parseFloat(t.nextToken().replace('$',' ').trim());
    }


    //Getters
    public String getTitulo(){
        return this.titulo;
    }

    public String getAutor(){
        return this.autor;
    }

    public String getPais(){
        return this.pais;
    }

    public Float getPrecio(){
        return this.precio;
    }

    public String getDescripcion(){
        return this.descripcion;
    }


    //Setters
    public void setTitulo(String entrada){
        this.titulo = entrada;
    }

    public void setAutor(String entrada){
        this.autor = entrada;
    }

    public void setPais(String entrada){
        this.pais = entrada;
    }

    public void setPrecio(float entrada){
        this.precio = entrada;
    }

    public void setDescripcion(String entrada){
        this.descripcion = entrada;
        StringTokenizer t = new StringTokenizer(entrada,"|");
        this.titulo = t.nextToken().trim();
        this.autor = t.nextToken().trim();
        this.pais = t.nextToken().trim();
        this.precio = Float.parseFloat(t.nextToken().replace('$',' ').trim());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disco other = (Disco) obj;
        return this.descripcion.equals(other.descripcion);
    }

    @Override
    public int hashCode() {
        return this.descripcion.hashCode();
    }


}
