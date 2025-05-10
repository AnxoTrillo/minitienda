package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import minitienda.*;


public class anhadir_cd extends HttpServlet {

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        String descripcionCD = request.getParameter("CD");
        Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito == null) {
            System.out.println("Carrito es null");
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        carrito.addCD(descripcionCD, cantidad);

        session.setAttribute("carrito", carrito);

        response.sendRedirect("index.html");
    }
}
