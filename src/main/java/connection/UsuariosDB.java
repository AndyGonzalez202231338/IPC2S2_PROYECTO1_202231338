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
import model.UsuarioModel;

/**
 *
 * @author andy
 */
public class UsuariosDB {
    
    private static final String CREAR_USUARIO_QUERY = 
        "INSERT INTO Usuario (nombre_completo, correo, telefono, organizacion, numero_identificacion, foto, contrasena, fecha_registro, tipo_cuenta) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String ENCONTRAR_USUARIO_POR_CORREO_QUERY = 
        "SELECT * FROM Usuario WHERE correo = ?";
    
    private static final String OBTENER_TODOS_QUERY = 
        "SELECT * FROM Usuario";

    /**
     * Inserta un nuevo usuario en la base de datos
     * @param usuario
     */
    public void crearUsuario(UsuarioModel usuario) {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_USUARIO_QUERY)) {
            insert.setString(1, usuario.getNombreCompleto());
            insert.setString(2, usuario.getCorreo());
            insert.setString(3, usuario.getTelefono());
            insert.setString(4, usuario.getOrganizacion());
            insert.setString(5, usuario.getNumeroIdentificacion());
            insert.setString(6, usuario.getFoto());
            insert.setString(7, usuario.getContrasena());
            insert.setTimestamp(8, Timestamp.valueOf(
                usuario.getFechaRegistro() != null ? usuario.getFechaRegistro() : LocalDateTime.now()
            ));
            insert.setString(9, usuario.getTipoCuenta());
            
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // aquí podrías propagar la excepción personalizada
        }
    }

    /**
     * Verifica si un usuario existe por su correo
     * @param correo
     * @return 
     */
    public boolean existeUsuario(String correo) {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_CORREO_QUERY)) {
            query.setString(1, correo);
            try (ResultSet rs = query.executeQuery()) {
                return rs.next(); // true si encontró al menos un usuario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtiene todos los usuarios en la base de datos
     * @return 
     */
    public List<UsuarioModel> obtenerTodosLosUsuarios() {
        List<UsuarioModel> usuarios = new ArrayList<>();
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(OBTENER_TODOS_QUERY);
             ResultSet rs = query.executeQuery()) {
            
            while (rs.next()) {
                UsuarioModel usuario = new UsuarioModel(
                    rs.getString("nombreCompleto"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("organizacion"),
                    rs.getString("numeroIdentificacion"),
                    rs.getString("foto"),
                    rs.getString("contrasena"),
                    rs.getString("tipoCuenta")
                );
                usuario.setId(rs.getInt("id"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    /**
 * Busca un usuario por su correo y devuelve el objeto UsuarioModel
 * @param correo correo a buscar
 * @return UsuarioModel si existe, o null si no se encontró
 */
public UsuarioModel buscarPorCorreo(String correo) {
    Connection connection = DBConnectionSingleton.getInstance().getConnection();
    try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_CORREO_QUERY)) {
        query.setString(1, correo);
        try (ResultSet rs = query.executeQuery()) {
            if (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setNombreCompleto(rs.getString("nombreCompleto")); // columna exacta
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
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}

