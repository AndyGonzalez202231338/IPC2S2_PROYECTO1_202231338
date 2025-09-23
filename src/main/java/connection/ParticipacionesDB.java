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
import model.Participacion.ParticipacionModel;

/**
 *
 * @author andy
 */
public class ParticipacionesDB {
    private static final String CREAR_PARTICIPACION_QUERY
            = "INSERT INTO Participacion (idUsuario, idCongreso, tipoParticipacion, fechaRegistro) VALUES (?,?,?,?)";
    
    private static final String ENCONTRAR_PARTICIPACION_POR_IDUSUARIOIDCONGRESO_QUERY
            = "SELECT * FROM Participacion WHERE idUsuario = ? AND idCongreso = ?";
    
    private static final String PARTICIPANTES_POR_IDCONGRESO_QUERY
            = "SELECT * FROM Participacion WHERE idCongreso = ?";
    /**
     * insert una participacion
     * @param participacion user
     */
    public void crearParticipacion(ParticipacionModel participacion) {
        System.out.println("*** Creando participante en la base de datos");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_PARTICIPACION_QUERY)) {
            insert.setLong(1, participacion.getIdUsuario());
            insert.setLong(2, participacion.getIdCongreso());                  // codigo Congreso
            insert.setString(3, participacion.getTipoParticipacion());                    // idSalon
            insert.setTimestamp(4, Timestamp.valueOf(participacion.getFechaRegistro()!= null ? participacion.getFechaRegistro() : LocalDateTime.now()));
            insert.executeUpdate();
            System.out.println("*** Participacion creada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * encotrar una particpacion en DB
     * @param idUsuario particpante
     * @param idCongreso isnrito
     * @return 
     */
    public ParticipacionModel encontrarParticipacionPorIdUsuarioIdCongreso(Long idUsuario, Long idCongreso) {
        System.out.println("+se busca el congreso por codigo");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_PARTICIPACION_POR_IDUSUARIOIDCONGRESO_QUERY)) {
            query.setLong(1, idUsuario);
            query.setLong(2, idCongreso);
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                System.out.println("lo encuentra y lo busca");
                //return mapResultSetToCongreso(rs);
                return new ParticipacionModel(
                        rs.getLong("idParticipacion"),
                        rs.getLong("idUsuario"),
                        rs.getLong("idCongreso"),
                        rs.getString("tipoParticipacion"),
                        rs.getTimestamp("fechaRegistro").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no existe el congreso");
        return null;
    }
    /**
     * todos los particpante a un
     * @param idCongreso congreso
     * @return list
     */
    public List<ParticipacionModel> obtenerParticipacionPorCongreso(Long idCongreso){
        List<ParticipacionModel> participantes = new ArrayList<>();
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(PARTICIPANTES_POR_IDCONGRESO_QUERY)) {
            query.setLong(1, idCongreso);  // OJO: si idCongreso puede venir null, hay que validarlo antes
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                System.out.println("lo encuentra y lo devuelve");
                ParticipacionModel paticipante = new ParticipacionModel(
                        rs.getLong("idParticipacion"),
                        rs.getLong("idUsuario"),
                        rs.getLong("idCongreso"),
                        rs.getString("tipoParticipacion"),
                        rs.getTimestamp("fechaRegistro").toLocalDateTime()
                );
                
                participantes.add(paticipante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no existe el salon");
        return participantes;
    }
}
