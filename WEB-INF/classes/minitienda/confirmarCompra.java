package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import minitienda.*;

public class confirmarCompra extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(true);
            Carrito carrito = (Carrito)session.getAttribute("carrito");
            if (carrito != null)
                carrito.clearList();
            else
                carrito = new Carrito();

            response.sendRedirect("index.html");
    }
}
