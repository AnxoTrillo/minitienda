package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class pagar extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(true);
            HashMap<String, Integer> carrito = (HashMap<String, Integer>) session.getAttribute("carrito");

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html><head><title>Caja</title></head><body bgcolor=\"#FDF5E6\">");

                float total = 0;
                String[] keys = carrito.keySet().toArray(new String[carrito.size()]);
                for (int i = 0; i < carrito.size(); i++) {
                    String item = keys[i];
                    int cantidad = carrito.get(item);
                    StringTokenizer t = new StringTokenizer(item,"|");
                    t.nextToken();
                    t.nextToken();
                    t.nextToken();
                    String precioString = t.nextToken();
                    precioString = precioString.replace('$',' ').trim();

                    float precio = Float.parseFloat(precioString);
                    float importe = precio * cantidad;
                    total += importe;
                }
                // Importe final
                out.println("<table align=\"center\" border=\"1\" style=\"background-color: #ffffff;\">");
                out.println("<table><tr><th>TOTAL A PAGAR</th></tr><tr><td>" + String.format("%.2f", total) + " euros</td><td></td></tr></table>");

                out.println("<form action=\"confirmarCompra\" method=\"POST\"><input type=\"image\" src=\"/minitienda/imagenes/musica.gif\" height=\"150px\"><figcaption>Pagar y volver a la tienda</figcaption></form>");

    }
}
