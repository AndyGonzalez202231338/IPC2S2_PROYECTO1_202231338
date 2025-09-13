/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Congresos.CongresoModel;

/**
 *
 * @author andy
 */
public class CongresosDB {
    private static final String CREAR_CONGRESO_QUERY =
    "INSERT INTO Congreso (codigo, nombre, descripcion, fechaInicio, fechaFin, lugar, precio, porcentajeGanancia) VALUES (?,?,?,?,?,?,?,?)";
    
    private static final String ENCONTRAR_CONGRESO_POR_ID_QUERY = 
        "SELECT * FROM Congreso WHERE codigo = ?";
    
    private static final String OBTENER_TODOS_CONGRESOS_QUERY = 
        "SELECT * FROM Congreso";
    
    private static final String ACTUALIZAR_CONGRESO_QUERY = "UPDATE Congreso SET nombre = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, lugar = ?, precio = ?, porcentajeGanancia = ? WHERE codigo = ?";
    
    // Crear congreso
    public void crearCongreso(CongresoModel congreso) {
        System.out.println("*** entro a crear modelo y comunicarse con la base de datos");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_CONGRESO_QUERY)) {
            insert.setString(1, congreso.getCodigo());
            insert.setString(2, congreso.getNombre());
            insert.setString(3, congreso.getDescripcion());
            insert.setDate(4, Date.valueOf(congreso.getFechaInicio()));
            insert.setDate(5, Date.valueOf(congreso.getFechaFin()));
            insert.setString(6, congreso.getLugar());
            insert.setDouble(7, congreso.getPrecio());
            insert.setDouble(8, congreso.getPorcentajeGanancia());
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Buscar por ID
    public CongresoModel encontrarCongresoPorId(String codigo) {
        System.out.println("+se busca el congreso por codigo");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_CONGRESO_POR_ID_QUERY)) {
            query.setString(1, codigo);
            ResultSet rs = query.executeQuery();
            
            if (rs.next()) {
                System.out.println("lo encuentra y lo busca");
                //return mapResultSetToCongreso(rs);
                return new CongresoModel(
                    rs.getLong("idCongreso"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDate("fechaInicio").toLocalDate(),
                    rs.getDate("fechaFin").toLocalDate(),
                    rs.getString("lugar"),
                    rs.getDouble("precio"),
                    rs.getTimestamp("fechaCreacion").toLocalDateTime(),
                    rs.getDouble("porcentajeGanancia")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no existe el congreso");
        return null;
    }
    
    public Optional<CongresoModel> obtenerCongresoPorCodigo(String codigo) {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_CONGRESO_POR_ID_QUERY);) {
            query.setString(1, codigo);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                CongresoModel congreso = new CongresoModel(
                        rs.getLong("idCongreso"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDate("fechaInicio").toLocalDate(),
                        rs.getDate("fechaFin").toLocalDate(),
                        rs.getString("lugar"),
                        rs.getDouble("precio"),
                        rs.getTimestamp("fechaCreacion").toLocalDateTime(),
                        rs.getDouble("porcentajeGanancia")
                );

                return Optional.of(congreso);
            }
        } catch (SQLException e) {
            // manejar o propagar la exception
            e.printStackTrace();
        }

        return Optional.empty();
    }
    
    public List<CongresoModel> obtenerTodosLosCongresos() {
    System.out.println("nuevo modelo de obtener todos");
    List<CongresoModel> congresos = new ArrayList<>();
    Connection connection = DBConnectionSingleton.getInstance().getConnection();

    try (PreparedStatement query = connection.prepareStatement(OBTENER_TODOS_CONGRESOS_QUERY);
         ResultSet rs = query.executeQuery()) {
        
        while (rs.next()) {
            System.out.println("→ Leyendo registro con idCongreso=" + rs.getLong("idCongreso"));
            
            Timestamp ts = rs.getTimestamp("fechaCreacion");
            LocalDateTime fechaCreacion = ts != null ? ts.toLocalDateTime() : null;

            CongresoModel congreso = new CongresoModel(
                rs.getLong("idCongreso"),
                rs.getString("codigo"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDate("fechaInicio").toLocalDate(),
                rs.getDate("fechaFin").toLocalDate(),
                rs.getString("lugar"),
                rs.getDouble("precio"),
                fechaCreacion,
                rs.getDouble("porcentajeGanancia")
            );
            congresos.add(congreso);
        }
        System.out.println("Total congresos cargados: " + congresos.size());
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return congresos;
}
    
    // Actualizar congreso
public void actualizarCongreso(CongresoModel congreso) {
    System.out.println("*** entro a actualizar congreso en la base de datos");
    Connection connection = DBConnectionSingleton.getInstance().getConnection();
   

    try (PreparedStatement update = connection.prepareStatement(ACTUALIZAR_CONGRESO_QUERY)) {
        update.setString(1, congreso.getNombre());
        update.setString(2, congreso.getDescripcion());
        update.setDate(3, Date.valueOf(congreso.getFechaInicio()));
        update.setDate(4, Date.valueOf(congreso.getFechaFin()));
        update.setString(5, congreso.getLugar());
        update.setDouble(6, congreso.getPrecio());
        update.setDouble(7, congreso.getPorcentajeGanancia());
        update.setString(8, congreso.getCodigo());

        int filasAfectadas = update.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Congreso actualizado correctamente: " + congreso.getCodigo());
        } else {
            System.out.println("No se encontró el congreso con código: " + congreso.getCodigo());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



}
