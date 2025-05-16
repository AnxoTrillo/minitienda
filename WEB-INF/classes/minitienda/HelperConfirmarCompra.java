package minitienda.helpers;

import minitienda.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

public class HelperConfirmarCompra {

    public HelperConfirmarCompra(){};

    public String confirmarCompra(HttpServletRequest request, ConexionBD conexionBD, Connection con, Usuario user){
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String tipo = request.getParameter("tipo_tarjeta");
        String numero = request.getParameter("numero_tarjeta");

        boolean loginCorrecto = false;

        try{
            if (tipo != null && numero != null) {
                user.setCorreo(correo);
                user.setNumero_tarjeta(numero);
                user.setPassword(password);
                user.setTipo_tarjeta(tipo);
                loginCorrecto = conexionBD.registrarUsuario(con, user, request);
            }
            else {
                loginCorrecto = conexionBD.iniciarSesion(con, correo, password, request);
            }

            if (loginCorrecto) {
                HttpSession session = request.getSession(true);
                user = (Usuario) session.getAttribute("usuario");
                Carrito carrito = (Carrito) session.getAttribute("carrito");

                if (user != null && carrito != null && !carrito.getListaCD().isEmpty()) {
                    float total = carrito.getTotalAmount();
                    conexionBD.guardarPedido(con, user, total);
                    carrito.clearList();
                    session.setAttribute("carrito", carrito);

                    Pedido ultimoPedido = conexionBD.obtenerUltimoPedido(con, user);
                    session.setAttribute("pedido", ultimoPedido);

                    return ("/Vista/VistaPedido.jsp");
                }
                else{
                    return ("/index.html");
                }
            }
            else{
                return ("/Vista/VistaError.jsp");
            }
        }
        catch (SQLException e){
            request.setAttribute("error", "Error de base de datos: " + e.getMessage());
            return "/Vista/VistaError.jsp";
        }
    }   
}
