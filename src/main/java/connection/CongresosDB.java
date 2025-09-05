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
import java.util.ArrayList;
import java.util.List;
import model.CongresoModel;

/**
 *
 * @author andy
 */
public class CongresosDB {
    private static final String CREAR_CONGRESO_QUERY =
    "INSERT INTO Congreso (codigo, nombre, descripcion, fechaInicio, fechaFin, lugar, precio) VALUES (?,?,?,?,?,?,?)";
    
    private static final String ENCONTRAR_CONGRESO_POR_ID_QUERY = 
        "SELECT * FROM Congreso WHERE codigo = ?";
    
    private static final String OBTENER_TODOS_CONGRESOS_QUERY = 
        "SELECT * FROM Congreso";
    
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
                    rs.getTimestamp("fechaCreacion").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no existe el congreso");
        return null;
    }
    
    // Listar todos
    public List<CongresoModel> obtenerTodosLosCongresos() {
        List<CongresoModel> congresos = new ArrayList<>();
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        
        try (PreparedStatement query = connection.prepareStatement(OBTENER_TODOS_CONGRESOS_QUERY)) {
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                congresos.add(mapResultSetToCongreso(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return congresos;
    }
    
    // ==== Mapper auxiliar ====
    private CongresoModel mapResultSetToCongreso(ResultSet rs) throws SQLException {
        System.out.println("?se entro a map");
        CongresoModel congreso = new CongresoModel();
        congreso.setIdCongreso(rs.getLong("idCongreso"));
        congreso.setCodigo(rs.getString("codigo"));
        congreso.setNombre(rs.getString("nombre"));
        congreso.setDescripcion(rs.getString("descripcion"));
        congreso.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
        congreso.setFechaFin(rs.getDate("fechaFin").toLocalDate());
        congreso.setLugar(rs.getString("lugar"));
        congreso.setPrecio(rs.getDouble("precio"));
        
        Timestamp fechaCreacion = rs.getTimestamp("fechaCreacion");
        if (fechaCreacion != null) {
            congreso.setFechaCreacion(fechaCreacion.toLocalDateTime());
        }
        return congreso;
    }
}
