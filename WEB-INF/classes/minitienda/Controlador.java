package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Controlador extends HttpServlet {
    ConexionBD con = new ConexionBD();

    public void init() throws ServletException {
        super.init();
        try{
            con.testDriver();
            Connection connect = con.obtenerConexion("localhost", "minitienda");
            con.crearTablas(connect);
        }
        catch(Exception e){
            System.out.println("Error al obtener la conexion a la base de datos: " + e);
        }

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        String accion = request.getParameter("accion");

        switch (accion) {
            case "addCD":
                String descripcionCD = request.getParameter("CD");
                Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
                carrito.addCD(descripcionCD, cantidad);
                session.setAttribute("carrito", carrito);
                gotoPage("/index.html", request, response);
                break;

            case "deleteCD":
                String descripcionCD1 = request.getParameter("descripcionCD");
                carrito.deleteCD(descripcionCD1);
                gotoPage("/Vista/VistaCarro.jsp", request, response);
                break;

            case "cart":
                gotoPage("/Vista/VistaCarro.jsp", request, response);
                break;

            case "checkout":
                gotoPage("/Vista/VistaCaja.jsp", request, response);
                break;

            case "pay":
                if (carrito != null)
                    carrito.clearList();
                else
                    carrito = new Carrito();
                gotoPage("/index.html", request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
        }
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}