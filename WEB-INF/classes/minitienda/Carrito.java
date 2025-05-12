package minitienda;

import java.util.*;
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
    public void addCD(String descripcionCD, Integer amount) {
        boolean encontrado = false;

        for (Disco cd : this.listaCD.keySet()) {
            if (cd.getDescripcion().equals(descripcionCD)) {
                int prevAmount = this.listaCD.get(cd);
                this.listaCD.put(cd, prevAmount + amount);
                this.totalAmount += cd.getPrecio() * amount;
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            Disco nuevoCD = new Disco(descripcionCD);
            this.listaCD.put(nuevoCD, amount);
            this.totalAmount += nuevoCD.getPrecio() * amount;
        }
    }


    public void deleteCD(String descripcionCD) {
        Iterator<Map.Entry<Disco, Integer>> iterator = listaCD.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Disco, Integer> entry = iterator.next();
            Disco cd = entry.getKey();
            int cantidad = entry.getValue();

            if (cd.getDescripcion().equals(descripcionCD)) {
                if (cantidad > 1)
                    listaCD.put(cd, cantidad - 1);
                else
                    iterator.remove();
                totalAmount -= cd.getPrecio();
                break;
            }
        }
    }



    public void clearList() {
        this.listaCD = new HashMap<Disco, Integer>();
        this.totalAmount = 0f;
    }
}
