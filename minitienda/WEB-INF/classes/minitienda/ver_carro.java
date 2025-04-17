package supercalculadora;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

/** Servlet para almacenar CDs seleccionados por el usuario */

public class SumaNumeros extends HttpServlet {

    // Variables privadas a la instancia del Servlet
    private int sumaInstanciaServlet = 0;

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      // Generamos un objeto sesion
      HttpSession session = request.getSession(true);

      // Generamos un objeto para el contexto de la aplicacion
      ServletContext context = getServletContext();

      // Almacenamos los parametros de entrada en variables temporales
      String numeroString = request.getParameter("numero");      
      // Convertimos "numero" a entero
      int numeroActual = Integer.parseInt(numeroString);
	  
      // Recuperamos sumaSesion de la sesion y si no existe, creamos este atributo
      Integer sumaSesion = (Integer)session.getAttribute("sumaSesion");
      if ( sumaSesion == null )
	  {
	      System.out.println("Sesion es null");
	  
	      // Inicializamos el atributo sumaSesion
	      session.setAttribute("sumaSesion", new Integer(0) );
	      sumaSesion = (Integer)session.getAttribute("sumaSesion");
	  }

      // Recuperamos contadorContext del Contexto y si no existe, creamos este atributo
      // contadorContext cuenta el numero de operaciones realizadas en la supercalculadora
      // COMPLETAR...
      Integer sumaContext = (Integer)context.getAttribute("sumaContext");
      if ( sumaContext == null )
	  {
	      System.out.println("Context es null");
	  
	      // Inicializamos el atributo sumaSesion
	      context.setAttribute("sumaContext", new Integer(0) );
	      sumaContext = (Integer)context.getAttribute("sumaContext");
	  }

      // Incrementamos la variable global del servlet
      // COMPLETAR...   
      sumaInstanciaServlet+=numeroActual;


      // Incrementamos la variable de la sesion
      // COMPLETAR...   
      sumaSesion+=numeroActual;

      // Incrementamos en uno el contador del contexto
      // COMPLETAR...   
      sumaContext+=1;


      // Almacenamos la suma en la sesion
      session.setAttribute("sumaSesion", new Integer( sumaSesion ));

      // Almacenamos la suma en el contexto
      // COMPLETAR...   
      context.setAttribute("sumaContext", new Integer( sumaContext ));


      // Generamos pagina de salida
      // COMPLETAR PRESENTACION DE RESULTADOS...
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String docType =
	  "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	  "Transitional//EN\">\n";
      out.println(docType +
		  "<HTML>\n" +
		  "<HEAD><TITLE>Suma Numeros: La calculadora definitiva</TITLE></HEAD>\n" +
		  "<BODY BGCOLOR=\"#FDF5E6\">\n" +
		  "<center> <H1>Suma Numeros: La calculadora definitiva </H1>\n" +
		  "\n\n" +
		  "<table border=\"0\" cellpadding=\"0\" width=\"75%\" bgcolor=\"#FFFFFF\">" +
		  "<tr>" +
		  "<td><b>Numero introducido</b></td>" +
		  "<td><b>sumaInstanciaServlet</b></td>" +
		  "<td><b>sumaSesion</b></td>" +
		  "<td><b>Contador operaciones en Context</b></td>" +
		  "</tr>" +
		  "<tr>" +
		  "<td><b>" + numeroActual + "</b></td>" +
		  "<td><b>" + sumaInstanciaServlet + "</b></td>" +
		  "<td><b>" + sumaSesion + "</b></td>" +
		  "<td><b>" + sumaContext + "</b></td>" +
		  "</tr>" +
		  "</table>" +
		  "<p>" +
		  "<a HREF=\"/supercalculadora/index.html\">Introducir mas numeros</a>" +
		  "</center>" +
		  "</BODY></HTML>");
  }
}
