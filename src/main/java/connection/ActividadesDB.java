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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
            = "SELECT * FROM Actividad WHERE codigo = ?";

// Obtener todas las actividades
    private static final String OBTENER_TODAS_ACTIVIDADES_QUERY
            = "SELECT * FROM Actividad";

// Actualizar una actividad
    private static final String ACTUALIZAR_ACTIVIDAD_QUERY
            = "UPDATE Actividad SET idCongreso = ?, idSalon = ?, nombreActividad = ?, descripcion = ?, tipoActividad = ?, horaInicio = ?, horaFin = ?, creado_por = ? WHERE idActividad = ?";

// Eliminar una actividad
    private static final String ELIMINAR_ACTIVIDAD_QUERY
            = "DELETE FROM Actividad WHERE idActividad = ?";
    
    private static final String ENCONTRAR_ACTIVIDADES_POR_IDCONGRESO_QUERY
            = "SELECT * FROM Actividad WHERE idCongreso = ?";

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
            insert.setTime(7, java.sql.Time.valueOf(actividad.getHoraInicio()));
            insert.setTime(8, java.sql.Time.valueOf(actividad.getHoraFin()));
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

        try (PreparedStatement query = connection.prepareStatement(OBTENER_TODAS_ACTIVIDADES_QUERY); ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                java.sql.Time tsI = rs.getTime("horaInicio");
                LocalTime horaInicio = tsI != null ? tsI.toLocalTime() : null;

                java.sql.Time tsF = rs.getTime("horaFin");
                LocalTime horaFin = tsF != null ? tsF.toLocalTime() : null;

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
                        rs.getDate("fechaCreacion").toLocalDate()
                );
                congresos.add(actividad);
            }
            System.out.println("Total congresos cargados: " + congresos.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return congresos;
    }

    public ActividadModel encontrarActividadPorId(String codigo) {
        System.out.println("+se busca el congreso por codigo");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_ACTIVIDAD_POR_ID_QUERY)) {
            query.setString(1, codigo);
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                System.out.println("lo encuentra y lo busca");
                //return mapResultSetToCongreso(rs);
                return new ActividadModel(
                        rs.getLong("idActividad"),
                        rs.getString("codigo"),
                        rs.getLong("idCongreso"),
                        rs.getLong("idSalon"),
                        rs.getString("nombreActividad"),
                        rs.getString("descripcion"),
                        rs.getString("tipoActividad"),
                        rs.getTime("horaInicio").toLocalTime(),
                        rs.getTime("horaFin").toLocalTime(),
                        rs.getLong("creado_por"),
                        rs.getDate("fechaCreacion").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no existe el congreso");
        return null;
    }
    
    public boolean salonDisponible(Long idSalon, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
    System.out.println("*** Verificando disponibilidad del salón " + idSalon);
    Connection connection = DBConnectionSingleton.getInstance().getConnection();

    // La lógica: dos rangos de tiempo se solapan si:
    // (horaInicioNueva < horaFinExistente) AND (horaFinNueva > horaInicioExistente)
    String query = "SELECT COUNT(*) FROM Actividad " +
                   "WHERE idSalon = ? " +
                   "AND DATE(fechaCreacion) = ? " +
                   "AND ( ? < horaFin AND ? > horaInicio )";

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setLong(1, idSalon);
        stmt.setDate(2, java.sql.Date.valueOf(fecha));
        stmt.setTime(3, java.sql.Time.valueOf(horaFin));     // nueva horaFin
        stmt.setTime(4, java.sql.Time.valueOf(horaInicio));  // nueva horaInicio

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("Actividades en conflicto: " + count);
            return count == 0; // true si NO hay conflictos → salón disponible
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Si hubo error, mejor devolver no disponible
}
    
    public List<ActividadModel> obtenerTodosLasActividadesPorCongreso(Long idCongreso) {
        System.out.println("+ Buscando actividades del congreso id = " + idCongreso);
    List<ActividadModel> actividades = new ArrayList<>();

    if (idCongreso == null) {
        System.out.println("⚠ idCongreso es null, no se ejecuta la consulta");
        return actividades; // devolver lista vacía
    }
    
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_ACTIVIDADES_POR_IDCONGRESO_QUERY)) {
            query.setLong(1, idCongreso);  //si idCongreso puede venir null, hay que validarlo antes
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                ActividadModel actividad = new ActividadModel(
                        rs.getLong("idActividad"),
                        rs.getString("codigo"),
                        rs.getLong("idCongreso"),
                        rs.getLong("idSalon"),
                        rs.getString("nombreActividad"),
                        rs.getString("descripcion"),
                        rs.getString("tipoActividad"),
                        rs.getTime("horaInicio").toLocalTime(),
                        rs.getTime("horaFin").toLocalTime(),
                        rs.getLong("creado_por"),
                        rs.getDate("fechaCreacion").toLocalDate()
                );
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actividades;
    }

}
