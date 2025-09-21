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

    private static final String CREAR_SALON_QUERY
            = "INSERT INTO Salon (idCongreso, nombreSalon, ubicacion, capacidad, recursos) VALUES (?,?,?,?,?)";

    private static final String ENCONTRAR_SALON_POR_ID_QUERY
            = "SELECT * FROM Salon WHERE idCongreso = ? AND nombreSalon = ?";
    
    private static final String ENCONTRAR_SALON_POR_IDSALON_QUERY
            = "SELECT * FROM Salon WHERE idSalon = ?";

    private static final String OBTENER_TODOS_SALONES_QUERY
            = "SELECT * FROM Salon";

    private static final String ACTUALIZAR_SALON_QUERY = "UPDATE Salon SET nombreSalon = ?, ubicacion = ?, capacidad = ?, recursos = ? WHERE idCongreso = ? AND nombreSalon = ?";
    
    private static final String OBTENER_TODOS_SALONES_IDCONGRESO_QUERY
            = "SELECT * FROM Salon WHERE idCongreso = ?";
    
    public void crearSalon(SalonModel salon) {
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

    public List<SalonModel> obtenerTodosLosSalones() {
        System.out.println("nuevo modelo de obtener todos los Salones");
        List<SalonModel> salones = new ArrayList<>();
        Connection connection = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement query = connection.prepareStatement(OBTENER_TODOS_SALONES_QUERY); ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                SalonModel salon = new SalonModel(
                        rs.getLong("idSalon"),
                        rs.getLong("idCongreso"),
                        rs.getString("nombreSalon"),
                        rs.getString("ubicacion"),
                        rs.getInt("capacidad"),
                        rs.getString("recursos")
                );
                salones.add(salon);
            }
            System.out.println("Total congresos cargados: " + salones.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salones;
    }

    public void actualizarSalon(SalonModel salon) {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement update = connection.prepareStatement(ACTUALIZAR_SALON_QUERY)) {
            update.setString(1, salon.getNuevoNombreSalon());  // nuevo nombre
            update.setString(2, salon.getUbicacion());
            update.setInt(3, salon.getCapacidad());
            update.setString(4, salon.getRecursos());

            update.setLong(5, salon.getIdCongreso());           // WHERE idCongreso = ?
            update.setString(6, salon.getNombreSalon());        // WHERE nombreSalon = ?

            int filasAfectadas = update.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Salón actualizado correctamente: " + salon.getNuevoNombreSalon());
            } else {
                System.out.println("No se encontró el salón con nombre: " + salon.getNombreSalon());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SalonModel> obtenerTodosLosSalonesPorCongreso(Long idCongreso) {
        List<SalonModel> salones = new ArrayList<>();
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(OBTENER_TODOS_SALONES_IDCONGRESO_QUERY)) {
            query.setLong(1, idCongreso);  // OJO: si idCongreso puede venir null, hay que validarlo antes
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                SalonModel salon = new SalonModel(
                        rs.getLong("idSalon"),
                        rs.getLong("idCongreso"),
                        rs.getString("nombreSalon"),
                        rs.getString("ubicacion"),
                        rs.getInt("capacidad"),
                        rs.getString("recursos")
                );
                salones.add(salon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salones;
    }
    
    public SalonModel obtenerSalonPorCongreso(Long idSalon){
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_SALON_POR_IDSALON_QUERY)) {
            query.setLong(1, idSalon);  // OJO: si idCongreso puede venir null, hay que validarlo antes
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
}
