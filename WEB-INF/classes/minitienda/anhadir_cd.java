package minitienda;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

/** Servlet para almacenar CDs seleccionados por el usuario */


public class anhadir_cd extends HttpServlet {

    // Variables privadas a la instancia del Servlet
    private int sumaInstanciaServlet = 0;

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      
      // Generamos un objeto para el contexto de la aplicacion
      ServletContext session = request.getSession(true);

      // se mete la cadena seleccionada en una variable
      String descripcionCD = request.getParameter("CD");
      String cantidad = Integer.parseInt(request.getParameter("cantidad"));

      Map<String, Integer> carrito= (Map<String, Integer>)session.getAttribute("carrito");
      if ( carrito == null )
	  {
	      System.out.println("Carrito es null");
	  
	      // Inicializamos el atributo sumaSesion
	      context.setAttribute("carrito", new HashMap<>() );
	  }

      
      if(carrito.containsKey(descripcionCD)){
        Integer temp=carrito.get(descripcionCD);
        carrito.put(descripcionCD, temp+cantidad);
      }else{
        carrito.put(descripcionCD, cantidad);
      }

      // Almacenamos la suma en el contexto
      // COMPLETAR...   
      session.setAttribute("carrito", new Map<String, Integer>(carrito));

  }
}
