package minitienda;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.Serializable;

public class Carrito implements Serializable {
    private HashMap<Disco, Integer> listaCD;
    private float totalAmount;

    public Carrito() {
        this.listaCD = new HashMap<Disco, Integer>();
        this.totalAmount = 0f;
    }


    //Getters
    public HashMap<Disco, Integer> getListaCD() {

        return this.listaCD;
    }

    public float getTotalAmount() {
        return this.totalAmount;
    }


    //Setters
    public void setlistaCD(HashMap<Disco, Integer> lista) {
        this.listaCD = lista;
        this.totalAmount = 0;
        for (Disco cd : lista.keySet()) {
            this.totalAmount += cd.getPrecio() * lista.get(cd);
        }
    }

    public void setTotalAmount(float amount) {
        this.totalAmount = amount;
    }


    //Otros métodos
    public void addCD(Disco CD, Integer amount) {
        Integer prevAmount = this.listaCD.get(CD);
        if (prevAmount != null) {
            this.listaCD.remove(CD);
            this.listaCD.put(CD, prevAmount + amount);

        } else
            this.listaCD.put(CD, amount);
        totalAmount += (CD.getPrecio() * amount);
    }

    public void deleteCD(String descripcionCD) {
        for (Disco cd : this.listaCD.keySet()) {
            if (cd.getDescripcion().equals(descripcionCD)) {
                Integer amount = this.listaCD.get(cd);
                if (amount > 1) {
                    this.listaCD.put(cd, amount - 1);
                } else {
                    this.listaCD.remove(cd);
                }
                this.totalAmount -= cd.getPrecio();
            }
        }
    }

    public void clearList() {
        this.listaCD = new HashMap<Disco, Integer>();
        this.totalAmount = 0f;
    }
}
