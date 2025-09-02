/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *Clase que se encarga de crear un conexion con mysql a la base de datos de SistemaEventosWeb
 * @author andy
 */
public class DBConnection {
    /*private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String USER_NAME = "andyAdmin";
    private static final String SCHEMA = "SistemaEventosWeb";
    private static final String PASSWORD = "andy";*/
    
    //el enlace o ruta para acceder a la database
    /*private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;
    
    private Connection connection;*/
    /**
     * Comprobacion de la conexion en la base de datos
     */
    /*public void connect(){
        System.out.println("url conexion "+URL);
        try{
            //connection.get
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Esquema: " + connection.getSchema());
            System.out.println("Catalogo: " + connection.getCatalog());
        }catch (SQLException e){
            System.out.println("Error al conectarse");
            e.printStackTrace();
        }
    }/*
    
    /**
     * funcion estatica que se encarga de enviar la conexion a otras clases sin necesidad de ser construido el pbjeto DBConnection
     * @return la funcion de conexion
     * @throws SQLException de conexion no encontrada o base de datos no existente
     */
    /*public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }*/
    
    private static final String URL = "jdbc:mysql://localhost:3306/SistemaEventosWeb";
    private static final String USER = "andyAdmin";
    private static final String PASSWORD = "andy";
    
    
    private static Connection connection;
    
    static {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver JDBC ", e);
        }
    }

    
    private DBConnection() {}
    /**
     * Este metodo estatico se mantiene de esta manera para eviat la creacion de una nueva conneccion a ala base de datos que se inecesaria
     * evita la mala practica de llamar mas de una vez la conexion.
     * @return
     * @throws SQLException 
     */
        public static Connection getConnection() throws SQLException {
            if (connection == null || connection.isClosed()) {
                synchronized (DBConnection.class) {
                    if (connection == null || connection.isClosed()) {
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    }
                }
            }
            return connection;
        }
}
