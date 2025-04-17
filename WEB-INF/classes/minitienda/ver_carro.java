package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ver_carro extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        HashMap<String, Integer> carrito = (HashMap<String, Integer>) session.getAttribute("carrito");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Carrito de Compras</title></head><body bgcolor=\"#FDF5E6\">");

        // Si el carrito no está vacío, mostrar los productos
        if (carrito != null && carrito.size() > 0) {
            out.println("<h2 align=\"center\">Carrito de Compras</h2>");
            out.println("<table align=\"center\" border=\"1\" width=\"100%\" style=\"background-color: #ffffff;\">");
            out.println("<tr><th>Titulo del CD</th><th>Cantidad</th><th style=\"width: 100px;\">Importe</th><th style=\"width: 80px;\">Eliminar</th></tr>");

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

                out.println("<tr><td>" + item + "</th><td>" + cantidad + "</td><td style=\"width: 100px;\">" + String.format("%.2f", importe) + " euros</td><td style=\"width: 80px;\">");
                out.println("<form action=\"ver_carro\" method=\"POST\">");
                out.println("<input type=\"hidden\" name=\"descripcionCD\" value=\"" + item +"\">");
                out.println("<input type=\"submit\" value=\"Eliminar\"></form>");
                out.println("</th></tr>");
            }
            // Importe final
            out.println("<tr><td></td>");
            out.println("<td>IMPORTE TOTAL</td><td>" + String.format("%.2f", total) + " euros</td><td></td></tr></table>");


            // Imágenes con enlaces para seguir comprando (tienda) o pagar (caja)
            out.println("<div style=\"display: flex; justify-content: center;\">");
            out.println("<figure style=\"text-align: center; margin-right: 20px;\">");
            out.println("<a href=\"index.html\"><img src=\"/minitienda/imagenes/carrito_compra.gif\" alt=\"Seguir comprando\"></a><figcaption><a href=\"index.html\">Seguir comprando</a></figcaption></figure>");

            out.println("<figure style=\"text-align: center; margin-left: 20px;\">");
            out.println("<a href=\"ver_caja\"><img src=\"/minitienda/imagenes/pago_importe2.jpg\" alt=\"Me largo a pagar\"></a><figcaption><a href=\"ver_caja\">Me largo a pagar</a></figcaption></figure>");
        }

        else {
            out.println("<center>");
            out.println("<h2>Carrito de Compras Vacio</h2>");
            out.println("<p>No hay productos en el carrito de compras.</p>");
            out.println("<p>Para agregar productos, visite la <a href=\"index.html\">Tienda</a>.</p>");
            out.println("</center>");
        }




        out.println("</div></body></html>");
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descripcionCD = request.getParameter("descripcionCD");
        if (descripcionCD != null) {
            HttpSession session = request.getSession(true);
            HashMap<String, Integer> carrito = (HashMap<String, Integer>) session.getAttribute("carrito");
            int cantidad = carrito.get(descripcionCD);

            if (cantidad <= 1) {
                carrito.remove(descripcionCD);
            }

            else {
                cantidad = cantidad - 1;
                carrito.put(descripcionCD, cantidad);
            }

            session.setAttribute("carrito", carrito);

            response.sendRedirect("ver_carro");
            return;
        }
    }
}