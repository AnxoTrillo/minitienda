package minitienda;

import java.io.Serializable;

public class Pedido implements Serializable {
    
    private int id;
    private String user;
    private float total;

    public Pedido(){

    }

    public Pedido(int id, String user, float total){
        this.id=id;
        this.total=total;
        this.user=user;
    }

    //getters
    public int getId() {
        return id;
    }
    public float getTotal() {
        return total;
    }
    public String getUser() {
        return user;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public void setUser(String user) {
        this.user = user;
    }

}
