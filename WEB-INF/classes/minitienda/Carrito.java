package minitienda;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.Serializable;

public class Carrito implements Serializable{
    private HashMap<String,Integer> listaCD; //guarda el nombre del disco y la cantidad de ese mismo disco
    private float totalAmount; //guarda el precio total del carro

    //constructor
    public Carrito(){
        this.listaCD=new HashMap<String,Integer>();
        this.totalAmount=0f;
    }

    //añade amount CDs y acctualiza el precio total
    public void addCD(String CD, Integer amount){
        Integer prevamount=this.listaCD.get(CD);
        if (prevamount != null) {
            this.listaCD.put(CD, prevamount+amount);
        } else
            this.listaCD.put(CD, amount);
        totalAmount+=(getPrecio(CD)*amount);
    }

    //getter de la lista
    public HashMap<String,Integer> getListaCD(){
        return this.listaCD;
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
            this.listaCD.put(CD, amount-1);
        }else{
            this.listaCD.remove(CD);
        }
        //LUEGO SE REDUCE EL PRECIO TOTAL
        this.totalAmount-=getPrecio(CD);
    }

    //setter de la lista, tb actualiza la amount
    public void setlistaCD(HashMap<String,Integer> listacd){
        this.listaCD = listacd;
        float auxamount  = 0;
        for (String nombre : this.listaCD.keySet()) {
            auxamount+=(this.listaCD.get(nombre)*getPrecio(nombre));
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
        this.listaCD=new HashMap<String, Integer>();
        this.totalAmount=0f;
    }

}
