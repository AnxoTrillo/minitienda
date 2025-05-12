package minitienda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public ConexionBD(){
        ;
    }

    protected void testDriver() throws Exception {
        try {
            Class.forName ( "org.postgresql.Driver" );
            System.out.println ( "Encontrado el driver de PostgreSQL" );
        }catch (java.lang.ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver no encontrado ... ");
            throw (e);
        }
    }

    protected Connection obtenerConexion(String host, String database) throws Exception {
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

        String usuarios = """
            CREATE TABLE IF NOT EXISTS usuarios (
                correo VARCHAR(100) PRIMARY KEY,
                password VARCHAR(100) NOT NULL,
                tipo_tarjeta VARCHAR(100),
                numero_tarjeta VARCHAR(100)
            )
        """;

        String pedidos = """
            CREATE TABLE IF NOT EXISTS pedidos (
                id SERIAL PRIMARY KEY,
                usuario VARCHAR(100) REFERENCES usuarios(correo),
                importe NUMERIC(10,2)
            )
        """;

        stmt.execute(usuarios);
        stmt.execute(pedidos);

        System.out.println("Tablas creadas correctamente.");
        stmt.close();
    }


}
