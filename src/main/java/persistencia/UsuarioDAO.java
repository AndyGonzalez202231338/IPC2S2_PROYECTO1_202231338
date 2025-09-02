/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.UsuarioModel;

/**
 * Se encarga de todo lo que tenga que ver con la entidad Usuario en cuanto
 * insetar, eliminar o cambios
 *
 * @author andy
 */
public class UsuarioDAO extends CrudDAO<UsuarioModel> {

    @Override
    public UsuarioModel insert(UsuarioModel entity) throws SQLException {
        String sql = "INSERT INTO Usuario (nombreCompleto, correo, telefono, organizacion, numeroIdentificacion, foto, contrasena, tipoCuenta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entity.getNombreCompleto());
            stmt.setString(2, entity.getCorreo());
            stmt.setString(3, entity.getTelefono());
            stmt.setString(4, entity.getOrganizacion());
            stmt.setString(5, entity.getNumeroIdentificacion());
            stmt.setString(6, entity.getFoto());
            stmt.setString(7, entity.getContrasena());
            stmt.setString(8, entity.getTipoCuenta()); // "NORMAL" o "ADMIN"

            stmt.executeUpdate();

            // Recuperar el id autogenerado
            /*try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setIdUsuario(rs.getLong(1));
                }
            }*/
        }

        return entity;
    }

    @Override
    public List<UsuarioModel> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(UsuarioModel entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public UsuarioModel findById(String correo) throws SQLException {
        System.out.println("llega: "+correo);
    String sql = "SELECT * FROM Usuario WHERE correo = ?";
    
    try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        stmt.setString(1, correo); // El 1 es la posición del parámetro "?" en la query
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setNombreCompleto(rs.getString("nombreCompleto"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setOrganizacion(rs.getString("organizacion"));
                usuario.setNumeroIdentificacion(rs.getString("numeroIdentificacion"));
                usuario.setFoto(rs.getString("foto"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setFechaRegistro(rs.getTimestamp("fechaRegistro").toLocalDateTime());
                usuario.setTipoCuenta(rs.getString("tipoCuenta"));
                return usuario;
            }
        }
    }
    return null; // Si no lo encuentra
}



}
