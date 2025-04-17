package minitienda;


/*import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*; */

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Servlet para almacenar CDs seleccionados por el usuario */


public class anhadir_cd extends HttpServlet {

    // Variables privadas a la instancia del Servlet
    private int sumaInstanciaServlet = 0;

  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      
      // Generamos un objeto para la sesión de la aplicacion
      HttpSession session = request.getSession(true);

      // se mete la cadena seleccionada en una variable
      String descripcionCD = request.getParameter("CD");
      Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));

      HashMap<String, Integer> carrito = (HashMap<String, Integer>)session.getAttribute("carrito");
      if (carrito == null )
	  {
	      System.out.println("Carrito es null");
	      carrito = new HashMap<>();
	      // Inicializamos el atributo sumaSesion
	      session.setAttribute("carrito", carrito);
	  }

      
      if(carrito.containsKey(descripcionCD)){
        Integer temp = carrito.get(descripcionCD);
        carrito.put(descripcionCD, temp+cantidad);
      }else{
        carrito.put(descripcionCD, cantidad);
      }

      // Almacenamos la suma en el contexto
      // COMPLETAR...   
      session.setAttribute("carrito", carrito);

      response.sendRedirect("index.html");
  }
}
