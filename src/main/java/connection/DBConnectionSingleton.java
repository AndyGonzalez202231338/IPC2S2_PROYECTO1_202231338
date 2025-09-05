package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Singleton para gestionar la conexión a la base de datos
 * SistemaEventosWeb
 * @author andy
 */
public class DBConnectionSingleton {
    
    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "SistemaEventosWeb";
    private static final String USER = "andyAdmin";
    private static final String PASSWORD = "andy";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;

    private static DBConnectionSingleton instance;
    private Connection connection;

    // Constructor privado → evita que se creen instancias externas
    private DBConnectionSingleton() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // cargar el driver
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Error al conectarse a la base de datos");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver JDBC no encontrado");
            e.printStackTrace();
        }
    }

    /**
     * Devuelve siempre la misma instancia de la conexión.
     */
    public static DBConnectionSingleton getInstance() {
        if (instance == null) {
            synchronized (DBConnectionSingleton.class) {
                if (instance == null) {
                    instance = new DBConnectionSingleton();
                }
            }
        }
        return instance;
    }

    /**
     * Retorna la conexión activa a la base de datos.
     */
    public Connection getConnection() {
        return connection;
    }
}
