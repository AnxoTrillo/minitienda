package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.SQLException;

import minitienda.ConexionBD;

public class Controlador extends HttpServlet {
    ConexionBD conexion = new ConexionBD();
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
                gotoPage("/Vista/VistaLogin.jsp", request, response);
                break;

            case "login":
                String correo = request.getParameter("correo");
                String password = request.getParameter("password");

                try {
                    conexion.testDriver();
                    Connection con = conexion.obtenerConexion("localhost", "minitienda");
                    conexion.iniciarSesion(con, correo, password, request, response);
                }
                catch (Exception e){
                    throw new ServletException("Error al procesar el login", e);
                }
                break;

            case "toSignin":
                gotoPage("/Vista/VistaRegistro.jsp", request, response);
                break;

            case "signin":
                String correo2 = request.getParameter("correo");
                String password2 = request.getParameter("password");
                String tipo = request.getParameter("tipo_tarjeta");
                String numero = request.getParameter("numero_tarjeta");

                try {
                    conexion.testDriver();
                    Connection con = conexion.obtenerConexion("localhost", "minitienda");
                    conexion.registrarUsuario(con, correo2, password2, tipo, numero, request, response);
                }
                catch (Exception e){
                    throw new ServletException("Error al registrar el usuario", e);
                }
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