/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Actividades.ActividadModel;

/**
 *
 * @author andy
 */
public class ActividadesDB {
    // Crear nueva actividad

    private static final String CREAR_ACTIVIDAD_QUERY
            = "INSERT INTO Actividad (codigo, idCongreso, idSalon, nombreActividad, descripcion, tipoActividad, horaInicio, horaFin, creado_por) VALUES (?,?,?,?,?,?,?,?,?)";

// Buscar actividad por ID
    private static final String ENCONTRAR_ACTIVIDAD_POR_ID_QUERY
            = "SELECT * FROM Actividad WHERE idActividad = ?";

// Obtener todas las actividades
    private static final String OBTENER_TODAS_ACTIVIDADES_QUERY
            = "SELECT * FROM Actividad";

// Actualizar una actividad
    private static final String ACTUALIZAR_ACTIVIDAD_QUERY
            = "UPDATE Actividad SET idCongreso = ?, idSalon = ?, nombreActividad = ?, descripcion = ?, tipoActividad = ?, horaInicio = ?, horaFin = ?, creado_por = ? WHERE idActividad = ?";

// Eliminar una actividad
    private static final String ELIMINAR_ACTIVIDAD_QUERY
            = "DELETE FROM Actividad WHERE idActividad = ?";
    
    public void crearActividad(ActividadModel actividad) {
    System.out.println("*** Creando actividad en la base de datos");
    Connection connection = DBConnectionSingleton.getInstance().getConnection();
    try (PreparedStatement insert = connection.prepareStatement(CREAR_ACTIVIDAD_QUERY)) {     
        insert.setString(1, actividad.getCodigo());
        insert.setLong(2, actividad.getIdCongreso());                  // codigo Congreso
        insert.setLong(3, actividad.getIdSalon());                    // idSalon
        insert.setString(4, actividad.getNombreActividad());         // nombreActividad
        insert.setString(5, actividad.getDescripcion());             // descripcion
        insert.setString(6, actividad.getTipoActividad());           // tipoActividad
        insert.setTimestamp(7, java.sql.Timestamp.valueOf(actividad.getHoraInicio())); // horaInicio
        insert.setTimestamp(8, java.sql.Timestamp.valueOf(actividad.getHoraFin()));    // horaFin
        insert.setLong(9, actividad.getCreadoPor());               // creado_por

        insert.executeUpdate();
        System.out.println("*** Actividad creada correctamente");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public List<ActividadModel> obtenerTodasLasActividades() {
    System.out.println("nuevo modelo de obtener todos");
    List<ActividadModel> congresos = new ArrayList<>();
    Connection connection = DBConnectionSingleton.getInstance().getConnection();

    try (PreparedStatement query = connection.prepareStatement(OBTENER_TODAS_ACTIVIDADES_QUERY);
         ResultSet rs = query.executeQuery()) {
        
        while (rs.next()) {
            
             Timestamp ts = rs.getTimestamp("fechaCreacion");
            LocalDateTime fechaCreacion = ts != null ? ts.toLocalDateTime() : null;
            
            Timestamp tsI = rs.getTimestamp("horaInicio");
            LocalDateTime horaInicio = tsI != null ? ts.toLocalDateTime() : null;
            Timestamp tsF = rs.getTimestamp("horaFin");
            LocalDateTime horaFin = tsF != null ? ts.toLocalDateTime() : null;

            ActividadModel actividad = new ActividadModel(
                rs.getLong("idActividad"),
                rs.getString("codigo"),
                rs.getLong("idCongreso"),
                rs.getLong("idSalon"),
                rs.getString("nombreActividad"),
                rs.getString("descripcion"),
                rs.getString("tipoActividad"),
                horaInicio,
                horaFin,
                rs.getLong("creado_por"),
                fechaCreacion
            );
            congresos.add(actividad);
        }
        System.out.println("Total congresos cargados: " + congresos.size());
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return congresos;
}

}
