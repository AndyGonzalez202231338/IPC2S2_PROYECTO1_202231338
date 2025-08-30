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
    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String USER_NAME = "andyAdmin";
    private static final String SCHEMA = "SistemaEventosWeb";
    private static final String PASSWORD = "andy";
    
    //el enlace o ruta para acceder a la database
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;
    
    private Connection connection;
    /**
     * Comprobacion de la conexion en la base de datos
     */
    public void connect(){
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
    }
    
    /**
     * funcion estatica que se encarga de enviar la conexion a otras clases sin necesidad de ser construido el pbjeto DBConnection
     * @return la funcion de conexion
     * @throws SQLException de conexion no encontrada o base de datos no existente
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
