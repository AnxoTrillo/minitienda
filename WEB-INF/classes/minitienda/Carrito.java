package minitienda;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.Serializable;

public class Carrito implements Serializable{
    private HashMap<String,Integer> ListaCD; //guarda el nombre del disco y la cantidad de ese mismo disco
    private float totalAmount; //guarda el precio total del carro

    //constructor
    public Carrito(){
        this.ListaCD=new HashMap<String,Integer>();
        this.totalAmount=0f;
    }

    //añade amount CDs y acctualiza el precio total
    public void addCD(String CD, Integer amount){
        Integer prevamount=this.ListaCD.get(CD);
        this.ListaCD.put(CD, prevamount+amount);
        totalAmount+=(getPrecio(CD)*amount);
    }

    //getter de la lista
    public HashMap<String,Integer> getListaCD(){
        return this.ListaCD;
    }

    //recoge el precio dado un titulo de cd
    public float getPrecio(String CD){
        StringTokenizer t = new StringTokenizer(CD,"|");
        t.nextToken();
        t.nextToken();
        t.nextToken();
        String precioString = t.nextToken();
        precioString = precioString.replace('$',' ').trim();
        float precio = Float.parseFloat(precioString);
        return precio;
    }

    //borra de uno en unno un cd de la lista y actualiza el precio
    public void deleteCD(String CD){
        //PRIMERO SE REDUCE EN UNO LA CANTIDAD DE CDS QUE SE TIENE DE UN TIPO
        Integer amount=getListaCD().get(CD);
        if(amount>1){
            this.ListaCD.put(CD, amount-1);
        }else{
            this.ListaCD.remove(CD);
        }
        //LUEGO SE REDUCE EL PRECIO TOTAL
        this.totalAmount-=getPrecio(CD);
    }

    //setter de la lista, tb actualiza la amount
    public void setListaCD(HashMap<String,Integer> listacd){
        this.ListaCD = listacd;
        float auxamount  = 0;
        for (String nombre : this.ListaCD.keySet()) {
            auxamount+=(this.ListaCD.get(nombre)*getPrecio(nombre));
        }
        setTotalAmount(auxamount);
    }

    //setter del precio del carro
    public void setTotalAmount(float amount){
        this.totalAmount=amount;
    }

    //getter del precio del carro
    public float getTotalAmount(){
        return this.totalAmount;
    }

    public void clearList(){
        this.ListaCD=new HashMap<String, Integer>();
        this.totalAmount=0f;
    }

}
