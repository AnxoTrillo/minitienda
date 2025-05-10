package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import minitienda.Carrito;


public class ver_carro extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        gotoPage("/VistaCarro.jsp", request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descripcionCD = request.getParameter("descripcionCD");
        System.out.println(descripcionCD);
        if (descripcionCD != null) {
            HttpSession session = request.getSession(true);
            Carrito carrito = (Carrito) session.getAttribute("carrito");
            if (carrito != null && carrito.getListaCD().get(descripcionCD) != null) {
                carrito.deleteCD(descripcionCD);
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