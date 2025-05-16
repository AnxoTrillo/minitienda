package minitienda.helpers;

import minitienda.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelperDeleteCD {

    public HelperDeleteCD(){};

    public String eliminarCD(HttpServletRequest request){
        String descripcionCD = request.getParameter("descripcionCD");
        if (descripcionCD != null) {
            HttpSession session = request.getSession(true);
            Carrito carrito = (Carrito) session.getAttribute("carrito");
            if (carrito != null && !carrito.getListaCD().isEmpty()){
                carrito.deleteCD(descripcionCD);
            }
        }
        return ("/Vista/VistaCarro.jsp"); 
    }   
}
