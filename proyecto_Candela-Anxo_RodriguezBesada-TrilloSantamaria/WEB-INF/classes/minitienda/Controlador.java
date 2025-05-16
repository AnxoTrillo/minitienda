package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.SQLException;
import minitienda.helpers.*;

public class Controlador extends HttpServlet {
    private ConexionBD conexionBD;
    private Connection conexion;


    private HelperAddCD h1 = new HelperAddCD();
    private HelperDeleteCD h2 = new HelperDeleteCD();
    private HelperConfirmarCompra h3 = new HelperConfirmarCompra();


    @Override
    public void init() throws ServletException {
        conexionBD = new ConexionBD();
    }

    private Connection getConexion() throws Exception {
        if (conexion == null || conexion.isClosed()) {
            conexionBD.testDriver();
            conexion = ConexionBD.obtenerConexion("localhost", "minitienda");
            conexionBD.crearTablas(conexion);
        }
        return conexion;
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
        String url = "";

        try {
            switch (accion) {
                case "addCD":
                    url = h1.anhadirProducto(request);
                    gotoPage(url, request, response);
                    break;

                case "deleteCD":
                    url = h2.eliminarCD(request);
                    gotoPage(url, request, response);
                    break;

                case "cart":
                    gotoPage("/Vista/VistaCarro.jsp", request, response);
                    break;

                case "checkout":
                    gotoPage("/Vista/VistaCaja.jsp", request, response);
                    break;

                case "pay":
                case "toLogin":
                    gotoPage("/Vista/VistaLogin.jsp", request, response);
                    break;

                case "toSignin":
                    gotoPage("/Vista/VistaRegistro.jsp", request, response);
                    break;

                case "confirmarCompra":
                    Connection con = getConexion();                    
                    url = h3.confirmarCompra(request, conexionBD, con);
                    gotoPage(url, request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new ServletException("Error en el controlador: " + e.getMessage(), e);
        }
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
