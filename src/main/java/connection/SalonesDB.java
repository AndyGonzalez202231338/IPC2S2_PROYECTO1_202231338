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
import java.util.Optional;
import model.Salones.SalonModel;

/**
 *
 * @author andy
 */
public class SalonesDB {
    private static final String CREAR_SALON_QUERY =
    "INSERT INTO Salon (idCongreso, nombreSalon, ubicacion, capacidad, recursos) VALUES (?,?,?,?,?)";
    
    private static final String ENCONTRAR_SALON_POR_ID_QUERY = 
        "SELECT * FROM Salon WHERE idCongreso = ? AND nombreSalon = ?";
    
    private static final String OBTENER_TODOS_SALONES_QUERY = 
        "SELECT * FROM Congreso";
    
    private static final String ACTUALIZAR_SALON_QUERY = "UPDATE Congreso SET nombre = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, lugar = ?, precio = ?, porcentajeGanancia = ? WHERE codigo = ?";
    
    public void crearSalon(SalonModel salon){
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_SALON_QUERY)) {
            insert.setLong(1, salon.getIdCongreso());
            insert.setString(2, salon.getNombreSalon());
            insert.setString(3, salon.getUbicacion());
            insert.setInt(4, salon.getCapacidad());
            insert.setString(5, salon.getRecursos());
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Optional<SalonModel> obtenerSalonPorCongresoYNombre(Long idCongreso, String nombreSalon) {
    Connection connection = DBConnectionSingleton.getInstance().getConnection();
    try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_SALON_POR_ID_QUERY)) {
        query.setLong(1, idCongreso);
        query.setString(2, nombreSalon);

        try (ResultSet rs = query.executeQuery()) {
            if (rs.next()) {
                SalonModel salon = new SalonModel(
                        rs.getLong("idSalon"),
                        rs.getLong("idCongreso"),
                        rs.getString("nombreSalon"),
                        rs.getString("ubicacion"),
                        rs.getInt("capacidad"),
                        rs.getString("recursos")
                );
                return Optional.of(salon);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return Optional.empty();
}
    
    public SalonModel encontrarSalonPorCongresoYNombre(Long idCongreso, String nombreSalon) {
    System.out.println("+se busca el salon por congreso y nombre");
    Connection connection = DBConnectionSingleton.getInstance().getConnection();
    try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_SALON_POR_ID_QUERY)) {
        query.setLong(1, idCongreso);  // OJO: si idCongreso puede venir null, hay que validarlo antes
        query.setString(2, nombreSalon);
        ResultSet rs = query.executeQuery();

        if (rs.next()) {
            System.out.println("lo encuentra y lo devuelve");
            return new SalonModel(
                    rs.getLong("idSalon"),
                    rs.getLong("idCongreso"),
                    rs.getString("nombreSalon"),
                    rs.getString("ubicacion"),
                    rs.getInt("capacidad"),
                    rs.getString("recursos")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    System.out.println("no existe el salon");
    return null;
}
    
    public List<SalonModel> obtenerTodosLosCongresos() {
    System.out.println("nuevo modelo de obtener todos");
    List<SalonModel> congresos = new ArrayList<>();
    Connection connection = DBConnectionSingleton.getInstance().getConnection();

    try (PreparedStatement query = connection.prepareStatement(OBTENER_TODOS_SALONES_QUERY);
         ResultSet rs = query.executeQuery()) {
        
        while (rs.next()) {
            SalonModel salon = new SalonModel(
                rs.getLong("idSalon"),
                rs.getLong("idCongreso"),
                rs.getString("nombreSalon"),
                rs.getString("ubicacion"),
                rs.getInt("capacidad"),
                rs.getString("recursos")
            );
            congresos.add(salon);
        }
        System.out.println("Total congresos cargados: " + congresos.size());
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return congresos;
}

}
