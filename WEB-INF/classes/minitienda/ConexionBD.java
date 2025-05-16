package minitienda;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            Usuario user = new Usuario();
            user.setCorreo(rs.getString("correo"));
            user.setPassword(rs.getString("password"));
            user.setTipo_tarjeta(rs.getString("tipo_tarjeta"));
            user.setNumero_tarjeta(rs.getString("numero_tarjeta"));

            request.getSession().setAttribute("usuario", user);
            return true;
        } else {
            request.setAttribute("error", "Correo o contraseña incorrectos.");
            return false;
        }
    }

    public boolean registrarUsuario(Connection con, Usuario user, HttpServletRequest request) throws SQLException {
        String comprobar = "SELECT correo FROM usuarios WHERE correo = ?";
        PreparedStatement check = con.prepareStatement(comprobar);
        check.setString(1, user.getCorreo());
        ResultSet rs = check.executeQuery();

        if (rs.next()) {
            request.setAttribute("error", "El correo ya está registrado.");
            return false;
        } else {
            String insertar = "INSERT INTO usuarios (correo, password, tipo_tarjeta, numero_tarjeta) VALUES (?, ?, ?, ?)";
            PreparedStatement insert = con.prepareStatement(insertar);
            insert.setString(1, user.getCorreo());
            insert.setString(2, user.getPassword());
            insert.setString(3, user.getTipo_tarjeta());
            insert.setString(4, user.getNumero_tarjeta());
            insert.executeUpdate();

            request.getSession().setAttribute("usuario", user);
            return true;
        }
    }

    public void guardarPedido(Connection con, Usuario user, float importe) throws SQLException {
        String insertar = "INSERT INTO pedidos (usuario, importe) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(insertar);
        ps.setString(1, user.getCorreo());
        ps.setFloat(2, importe);
        ps.executeUpdate();
    }

    public Pedido obtenerUltimoPedido(Connection con, Usuario user) {
        Pedido pedido = new Pedido();
        //HashMap<String, Object> pedido = new HashMap<>();
        try {
            String query = "SELECT * FROM pedidos WHERE usuario = ? ORDER BY id DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getCorreo());
            ResultSet rs = ps.executeQuery();
            escribirEnArchivo("query");
            escribirEnArchivo(ps.toString());
            if (rs.next()) {
                pedido.setId(rs.getInt("id"));
                pedido.setTotal(rs.getFloat("importe"));
                pedido.setUser(rs.getString("usuario"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }


    public static void escribirEnArchivo(String texto) {
        String nombreArchivo = "test_mostaza.txt";
        File archivo = new File(nombreArchivo);

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("Archivo creado: " + nombreArchivo);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
                writer.write(texto);
                writer.newLine();
                System.out.println("Texto escrito en el archivo " + nombreArchivo);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
}
