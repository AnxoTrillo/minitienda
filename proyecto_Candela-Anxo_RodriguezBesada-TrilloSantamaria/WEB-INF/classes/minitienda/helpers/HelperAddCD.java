package minitienda.helpers;

import minitienda.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelperAddCD {
    public HelperAddCD(){};

    public String anhadirProducto(HttpServletRequest request) {
        String descripcionCD = request.getParameter("CD");
        Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
        if (descripcionCD != null && cantidad != null){
            HttpSession session = request.getSession(true);
            Carrito carrito = (Carrito) session.getAttribute("carrito");
            if (carrito != null) {
                carrito.addCD(descripcionCD, cantidad);
                session.setAttribute("carrito", carrito);
            }
        }
        return ("/index.html");
    } 
}
