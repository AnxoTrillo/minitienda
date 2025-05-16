package minitienda;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String correo;
    private String password;
    private String tipo_tarjeta;
    private String numero_tarjeta;

    public Usuario(){
        
    }
    public Usuario(String correo,String password,String tipo_tarjeta,String numero_tarjeta){
        this.correo=correo;
        this.password=password;
        this.tipo_tarjeta=tipo_tarjeta;
        this.numero_tarjeta=numero_tarjeta;
    }

    //SETTERS
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setTipo_tarjeta(String tipo_tarjeta) {
        this.tipo_tarjeta = tipo_tarjeta;
    }

    //GETTERS
    public String getCorreo() {
        return correo;
    }
    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }
    public String getPassword() {
        return password;
    }
    public String getTipo_tarjeta() {
        return tipo_tarjeta;
    }

}
