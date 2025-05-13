package minitienda;

import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;


public class ConexionBD {
    public ConexionBD(){};

    protected void testDriver() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Encontrado el driver de PostgreSQL");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver no encontrado ... ");
            throw e;
        }
    }

    public static Connection obtenerConexion(String host, String database) throws Exception {
        String url = "jdbc:postgresql://" + host + ":5432/" + database;

        try {
            Connection con = DriverManager.getConnection(url);
            System.out.println("Conexión establecida con " + url + "...");
            return con;
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión a la base de datos: " + e);
            throw e;
        }
    }

    protected void crearTablas(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        String usuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "correo VARCHAR(100) PRIMARY KEY, "
                + "password VARCHAR(100) NOT NULL, "
                + "tipo_tarjeta VARCHAR(100), "
                + "numero_tarjeta VARCHAR(100)"
                + ");";

        String pedidos = "CREATE TABLE IF NOT EXISTS pedidos ("
                + "id SERIAL PRIMARY KEY, "
                + "usuario VARCHAR(100) REFERENCES usuarios(correo), "
                + "importe NUMERIC(10,2))";

        stmt.execute(usuarios);
        stmt.execute(pedidos);
        stmt.close();
    }

    public boolean iniciarSesion(Connection con, String correo, String password, HttpServletRequest request) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, correo);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            request.getSession().setAttribute("usuario", correo);
            return true;
        } else {
            request.setAttribute("error", "Correo o contraseña incorrectos.");
            return false;
        }
    }

    public boolean registrarUsuario(Connection con, String correo, String password, String tipo, String numero, HttpServletRequest request) throws SQLException {
        String comprobar = "SELECT correo FROM usuarios WHERE correo = ?";
        PreparedStatement check = con.prepareStatement(comprobar);
        check.setString(1, correo);
        ResultSet rs = check.executeQuery();

        if (rs.next()) {
            request.setAttribute("error", "El correo ya está registrado.");
            return false;
        } else {
            String insertar = "INSERT INTO usuarios (correo, password, tipo_tarjeta, numero_tarjeta) VALUES (?, ?, ?, ?)";
            PreparedStatement insert = con.prepareStatement(insertar);
            insert.setString(1, correo);
            insert.setString(2, password);
            insert.setString(3, tipo);
            insert.setString(4, numero);
            insert.executeUpdate();

            request.getSession().setAttribute("usuario", correo);
            return true;
        }
    }

    public void guardarPedido(Connection con, String correo, float importe) throws SQLException {
        String insertar = "INSERT INTO pedidos (usuario, importe) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(insertar);
        ps.setString(1, correo);
        ps.setFloat(2, importe);
        ps.executeUpdate();
    }

    public HashMap<String, Object> obtenerUltimoPedido(Connection con, String correo) {
        HashMap<String, Object> pedido = new HashMap<>();
        try {
            String query = "SELECT * FROM pedidos WHERE correo = ? ORDER BY id DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pedido.put("id", rs.getInt("id"));
                pedido.put("usuario", rs.getString("usuario"));
                pedido.put("total", rs.getFloat("importe"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }
}
