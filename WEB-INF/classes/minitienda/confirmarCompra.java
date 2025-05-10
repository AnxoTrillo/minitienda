package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class confirmarCompra extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(true);
            HashMap<String, Integer> carrito = new HashMap<>();
            session.invalidate();

            response.sendRedirect("index.html");
    }
}
