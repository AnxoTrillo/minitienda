package minitienda;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class anhadir_cd extends HttpServlet {

    private int sumaInstanciaServlet = 0;

  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

      HttpSession session = request.getSession(true);

      String descripcionCD = request.getParameter("CD");
      Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));

      HashMap<String, Integer> carrito = (HashMap<String, Integer>)session.getAttribute("carrito");
      if (carrito == null )
	  {
	      System.out.println("Carrito es null");
	      carrito = new HashMap<>();
	      session.setAttribute("carrito", carrito);
	  }

      
      if(carrito.containsKey(descripcionCD)){
        Integer temp = carrito.get(descripcionCD);
        carrito.put(descripcionCD, temp+cantidad);
      }else{
        carrito.put(descripcionCD, cantidad);
      }

      session.setAttribute("carrito", carrito);

      response.sendRedirect("index.html");
  }
}
