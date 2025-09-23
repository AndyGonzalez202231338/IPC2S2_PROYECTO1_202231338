/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Actividades.ActividadModel;
import model.Organizacion.OrganizacionModel;

/**
 *
 * @author andy
 */
public class OrganizacionesDB {
    
    private static final String CREAR_ORGANIZACION_QUERY
            = "INSERT INTO Organizacion (nombre) VALUES (?)";

    
    private static final String ENCONTRAR_ORGANIZACION_POR_NOMBRE_QUERY
            = "SELECT * FROM Organizacion WHERE nombre = ?";
    
    private static final String OBTENER_TODAS_ORGANIZACIONES_QUERY
            = "SELECT * FROM Organizacion";
    
    private static final String ACTUALIZAR_ORGANIZACION_QUERY
            = "UPDATE Organizacion SET nombre = ? WHERE idOrganizacion = ?";
    
    public void crearOrganizacion(OrganizacionModel organizacion) {
    System.out.println("se crea organ " + organizacion.getNombre());
    Connection connection = DBConnectionSingleton.getInstance().getConnection();
    try (PreparedStatement insert = connection.prepareStatement(CREAR_ORGANIZACION_QUERY)) {
        insert.setString(1, organizacion.getNombre());
        
        int filas = insert.executeUpdate(); // 👈 Ejecuta el INSERT
        if (filas > 0) {
            System.out.println("Organización creada exitosamente");
        } else {
            System.out.println("No se insertó ninguna fila");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    public OrganizacionModel encontrarOrganizacionPorNombre(String nombre) {
        System.out.println("nombre"+nombre);
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_ORGANIZACION_POR_NOMBRE_QUERY)) {
            query.setString(1, nombre);
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                //return mapResultSetToCongreso(rs);
                return new OrganizacionModel(
                        rs.getLong("idOrganizacion"),
                        rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();                
        }
        System.out.println("no existe el congreso");
        return null;
    }
    
    public List<OrganizacionModel> obtenerTodasLasOrganizaciones() {
        System.out.println("nuevo modelo de obtener todos");
        List<OrganizacionModel> orgniazaciones = new ArrayList<>();
        Connection connection = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement query = connection.prepareStatement(OBTENER_TODAS_ORGANIZACIONES_QUERY); ResultSet rs = query.executeQuery()) {

            while (rs.next()) {

                OrganizacionModel organizacion = new OrganizacionModel(
                        rs.getLong("idOrganizacion"),
                        rs.getString("nombre")
                );
                orgniazaciones.add(organizacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orgniazaciones;
    }
    
    public void actualizarOrganizacion(OrganizacionModel organizacion) {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement update = connection.prepareStatement(ACTUALIZAR_ORGANIZACION_QUERY)) {
            update.setString(1, organizacion.getNombre());
            update.setLong(2, organizacion.getIdOrganizacion());
            int filasAfectadas = update.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Congreso actualizado correctamente: " + organizacion.getNombre());
            } else {
                System.out.println("No se encontró el congreso con código: " + organizacion.getNombre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
