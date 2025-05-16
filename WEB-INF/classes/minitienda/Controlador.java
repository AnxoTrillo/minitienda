package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Controlador extends HttpServlet {
    private ConexionBD conexionBD;
    private Connection conexion;

    @Override
    public void init() throws ServletException {
        conexionBD = new ConexionBD();
    }

    private Connection getConexion() throws Exception {
        if (conexion == null || conexion.isClosed()) {
            conexionBD.testDriver();
            conexion = conexionBD.obtenerConexion("localhost", "minitienda");
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

        try {
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
                case "toLogin":
                    gotoPage("/Vista/VistaLogin.jsp", request, response);
                    break;

                case "toSignin":
                    gotoPage("/Vista/VistaRegistro.jsp", request, response);
                    break;

                case "confirmarCompra":

                    String correo = request.getParameter("correo");
                    String password = request.getParameter("password");
                    String tipo = request.getParameter("tipo_tarjeta");
                    String numero = request.getParameter("numero_tarjeta");

                    Connection con = getConexion();
                    boolean loginCorrecto = false;
                    Usuario user=new Usuario();
                    if (tipo != null && numero != null) {
                        user=new Usuario(correo, password, tipo, numero);
                        loginCorrecto = conexionBD.registrarUsuario(con, user, request);
                    } else {
                        loginCorrecto = conexionBD.iniciarSesion(con, correo, password, request);
                    }

                    if (loginCorrecto) {
                        user    = (Usuario) session.getAttribute("usuario");
                        carrito = (Carrito) session.getAttribute("carrito");

                        if (carrito != null && !carrito.getListaCD().isEmpty()) {
                            float total = carrito.getTotalAmount();
                            conexionBD.guardarPedido(con, user, total);
                            carrito.clearList();
                            session.setAttribute("carrito", carrito);

                            // Obtener el último pedido
                            Pedido ultimoPedido = conexionBD.obtenerUltimoPedido(con, user);

                            session.setAttribute("pedido", ultimoPedido);

                            gotoPage("/Vista/VistaPedido.jsp", request, response);
                        } else {
                            response.sendRedirect("index.html");
                        }
                    } else {
                        request.getRequestDispatcher("/Vista/VistaLogin.jsp").forward(request, response);
                    }
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
