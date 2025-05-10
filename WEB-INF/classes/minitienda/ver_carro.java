package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ver_carro extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        gotoPage("/VistaCarro.jsp", request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descripcionCD = request.getParameter("descripcionCD");
        if (descripcionCD != null) {
            HttpSession session = request.getSession(true);
            HashMap<String, Integer> carrito = (HashMap<String, Integer>) session.getAttribute("carrito");
            if (carrito != null && carrito.get(descripcionCD) != null) {
                int cantidad = carrito.get(descripcionCD);

                if (cantidad <= 1) {
                    carrito.remove(descripcionCD);
                } else {
                    cantidad = cantidad - 1;
                    carrito.put(descripcionCD, cantidad);
                }
                session.setAttribute("carrito", carrito);
            }


            gotoPage("/VistaCarro.jsp", request, response);
        }
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}