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
import model.Actividades.InscripcionTallerModel;

/**
 *
 * @author andy
 */
public class InscripcionesTalleresDB {
    private static final String CREAR_INSCRIPCION_QUERY
            = "INSERT INTO InscripcionTaller (idUsuario, idActividad, fechaRegistro) VALUES (?,?,?)";
    
    private static final String ENCONTRAR_INSCRIPCIONTALLER_POR_IDUSUARIOIDACTIVIDAD_QUERY
            = "SELECT * FROM InscripcionTaller WHERE idUsuario = ? AND idActividad = ?";
    
    private static final String ENCONTRAR_LAS_INSCRIPCIONTALLER_POR_IDACTIVIDAD_QUERY
            = "SELECT * FROM InscripcionTaller WHERE idActividad = ?";
    
    private static final String Asistencia = "UPDATE InscripcionTaller SET asistencia = 1 " +
             "WHERE idUsuario = ? AND idActividad = ?";
    /**
     * crear o insert
     * @param inscripcionTaller inscripcion actividad
     */
    public void crearInscripcionTaller(InscripcionTallerModel inscripcionTaller) {
        System.out.println("*** Creando participante en la base de datos");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_INSCRIPCION_QUERY)) {
            insert.setLong(1, inscripcionTaller.getIdusuario());
            insert.setLong(2, inscripcionTaller.getIdActividad());
            insert.setTimestamp(3, Timestamp.valueOf(inscripcionTaller.getFechaRegistro()!= null ? inscripcionTaller.getFechaRegistro() : LocalDateTime.now()));
            insert.executeUpdate();
            System.out.println("*** insripcion a taller creada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Taller por id usario y actividad
     * @param idUsuario user
     * @param idActividad a la que se iscribe
     * @return inscripcion valida
     */
    public InscripcionTallerModel encontrarInscripcionTallerPorIdUsuarioIdActividad(Long idUsuario, Long idActividad) {
        System.out.println("+se busca el congreso por codigo");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_INSCRIPCIONTALLER_POR_IDUSUARIOIDACTIVIDAD_QUERY)) {
            query.setLong(1, idUsuario);
            query.setLong(2, idActividad);
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                System.out.println("lo encuentra y lo busca");
                //return mapResultSetToCongreso(rs);
                return new InscripcionTallerModel(
                        rs.getLong("idInscripcion"),
                        rs.getLong("idUsuario"),
                        rs.getLong("idActividad"),
                        rs.getTimestamp("fechaRegistro").toLocalDateTime(),
                        rs.getInt("asistencia")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no existe el congreso");
        return null;
    }
    /**
     * Todas las insripciones a una catividad
     * @param idActividad filtra
     * @return lsita
     */
    public List<InscripcionTallerModel> obtenerTodosLasInscripcionesTallerPorIdActividad(Long idActividad) {
        System.out.println("+ Buscando actividades del congreso id = " + idActividad);
    List<InscripcionTallerModel> inscripcionesTaller = new ArrayList<>();

    if (idActividad == null) {
        System.out.println(" idActividad es null, no se ejecuta la consulta");
        return inscripcionesTaller; // devolver lista vacía
    }
    
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_LAS_INSCRIPCIONTALLER_POR_IDACTIVIDAD_QUERY)) {
            query.setLong(1, idActividad);  //si idCongreso puede venir null, hay que validarlo antes
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                InscripcionTallerModel inscripcionTaller = new InscripcionTallerModel(
                        rs.getLong("idInscripcion"),
                        rs.getLong("idUsuario"),
                        rs.getLong("idActividad"),
                        rs.getTimestamp("fechaRegistro").toLocalDateTime(),
                        rs.getInt("asistencia")
                );
                inscripcionesTaller.add(inscripcionTaller);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripcionesTaller;
    }
    /**
     * buscar su cupo
     * @param idActividad taller
     * @return cantidad
     */
    public Integer obtenerCupoMaximo(Long idActividad) {
    String sql = "SELECT cupoMaximo FROM Taller WHERE idActividad = ?";
    Connection connection = DBConnectionSingleton.getInstance().getConnection();
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setLong(1, idActividad);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("cupoMaximo");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // o lanzar excepción si no existe
}

    /**
     * buscar cantidad de isncritos
     * @param idActividad taller
     * @return cantidad
     */
    public Integer contarInscritos(Long idActividad) {
    String sql = "SELECT COUNT(*) AS total FROM InscripcionTaller WHERE idActividad = ?";
    Connection connection = DBConnectionSingleton.getInstance().getConnection();
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setLong(1, idActividad);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}
    /**
     * registrar una asisatencia
     * @param idUsuario participante
     * @param idActividad taller
     */
    public void confirmarAsistencia(Long idUsuario, Long idActividad) {
    Connection connection = DBConnectionSingleton.getInstance().getConnection();
    try (PreparedStatement query = connection.prepareStatement(Asistencia)) {
        query.setLong(1, idUsuario);
        query.setLong(2, idActividad);

        int filasActualizadas = query.executeUpdate();

        if (filasActualizadas > 0) {
            System.out.println("Asistencia confirmada para usuario " + idUsuario + " en actividad " + idActividad);
        } else {
            System.out.println("No se encontró inscripción para usuario " + idUsuario + " en actividad " + idActividad);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
}
