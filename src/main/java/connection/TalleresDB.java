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
import model.Actividades.TallerModel;

/**
 *
 * @author andy
 */
public class TalleresDB {
    
    private static final String CREAR_TALLER_QUERY
            = "INSERT INTO Actividad (idActividad, cupoMaximo ) VALUES (?,?)";
    
    private static final String ENCONTRAR_ACTIVIDAD_POR_ID_QUERY
            = "SELECT * FROM Actividad WHERE idActividad = ?";
    
    private static final String ENCONTRAR_TALLERES_POR_IDACTIVIDAD_QUERY
            = "SELECT idActividad, cupoMaximo FROM Taller WHERE idActividad = ?";
    /**
     * insert de taller
     * @param taller activdad
     */
    public void crearTaller(TallerModel taller) {
        System.out.println("*** Creando actividad en la base de datos");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_TALLER_QUERY)) {
            insert.setLong(1, taller.getIdActividad());                  // codigo Congreso                   // idSalon
            insert.setInt(2, taller.getCupoMaximo());         // nombreActividad

            insert.executeUpdate();
            System.out.println("*** Taller creada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Ecnotrar por idActivdad
     * @param idActividad Taller
     * @return taller
     */
    public TallerModel encontrarActividadTallerPorId(Long idActividad) {
        System.out.println("+se busca el congreso por codigo");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_ACTIVIDAD_POR_ID_QUERY)) {
            query.setLong(1, idActividad);
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                System.out.println("lo encuentra y lo busca");
                //return mapResultSetToCongreso(rs);
                return new TallerModel(
                        rs.getLong("idActividad"),
                        rs.getInt("cupoMaximo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no existe el congreso");
        return null;
    }
    /**
     * Tofos los salones por una
     * @param idActividad activdidad
     * @return list
     */
    public List<TallerModel> obtenerTodosLasActividadesPorCongreso(Long idActividad) {
        System.out.println("+ Buscando actividades del congreso id = " + idActividad);
    List<TallerModel> talleres = new ArrayList<>();

    if (idActividad == null) {
        System.out.println(" idActividad es null, no se ejecuta la consulta");
        return talleres; // devolver lista vacía
    }
    
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_TALLERES_POR_IDACTIVIDAD_QUERY)) {
            query.setLong(1, idActividad);  //si idCongreso puede venir null, hay que validarlo antes
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                TallerModel taller = new TallerModel(
                        rs.getLong("idActividad"),
                        rs.getInt("cupoMaximo")
                );
                talleres.add(taller);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return talleres;
    }
    
    /**
     * un solo taller por su activdad
     * @param idActividad activdida
     * @return taller
     */
    public TallerModel obtenerTallerPorIdActividad(Long idActividad){
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_TALLERES_POR_IDACTIVIDAD_QUERY)) {
            query.setLong(1, idActividad);  // OJO: si idCongreso puede venir null, hay que validarlo antes
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                System.out.println("lo encuentra y lo devuelve");
                TallerModel taller = new TallerModel(
                        rs.getLong("idActividad"),
                        rs.getInt("cupoMaximo")
                );
                return taller;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no existe el salon");
        return null;
    }
    
}
