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
import java.util.Optional;
import model.Usuarios.UsuarioModel;

/**
 *
 * @author andy
 */
public class UsuariosDB {

    private static final String CREAR_USUARIO_QUERY
            = "INSERT INTO Usuario (nombreCompleto, correo, telefono, numeroIdentificacion, contrasena, fechaRegistro, tipoCuenta, idOrganizacion) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String ENCONTRAR_USUARIO_POR_CORREO_QUERY
            = "SELECT * FROM Usuario WHERE correo = ?";
    
    private static final String ENCONTRAR_USUARIO_POR_IDUSUARIO_QUERY
            = "SELECT * FROM Usuario WHERE idUsuario = ?";

    private static final String OBTENER_TODOS_QUERY
            = "SELECT * FROM Usuario";

    private static final String ACTUALIZAR_USUARIO_QUERY
            = "UPDATE Usuario SET nombreCompleto = ?, telefono = ?, "
            + "numeroIdentificacion = ?, contrasena = ?, tipoCuenta = ?, foto = ?, idOrganizacion = ?"
            + "WHERE correo = ?";

    /**
     * Inserta un nuevo usuario en la base de datos
     *
     * @param usuario
     */
    public void crearUsuario(UsuarioModel usuario) {
        System.out.println("        crear usuario insert");
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(CREAR_USUARIO_QUERY)) {
            insert.setString(1, usuario.getNombreCompleto());
            insert.setString(2, usuario.getCorreo());
            insert.setString(3, usuario.getTelefono());
            insert.setString(4, usuario.getNumeroIdentificacion());
            insert.setString(5, usuario.getContrasena());
            insert.setTimestamp(6, Timestamp.valueOf(
                    usuario.getFechaRegistro() != null ? usuario.getFechaRegistro() : LocalDateTime.now()
            ));
            insert.setString(7, usuario.getTipoCuenta());
            insert.setLong(8, usuario.getOrganizacion());
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // aquí podrías propagar la excepción personalizada
        }
    }

    /**
     * Verifica si un usuario existe por su correo
     *
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
     *
     * @return
     */
    public List<UsuarioModel> obtenerTodosLosUsuarios() {
        List<UsuarioModel> usuarios = new ArrayList<>();
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(OBTENER_TODOS_QUERY); ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                UsuarioModel usuario = new UsuarioModel(
                        rs.getString("nombreCompleto"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("numeroIdentificacion"),
                        rs.getString("contrasena"),
                        rs.getString("tipoCuenta"),
                        rs.getLong("idOrganizacion")
                );
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setFechaRegistro(rs.getTimestamp("fechaRegistro").toLocalDateTime());
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Busca un usuario por su correo y devuelve el objeto UsuarioModel
     *
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
                    usuario.setNumeroIdentificacion(rs.getString("numeroIdentificacion"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setFoto(rs.getBytes("foto"));
                    usuario.setFechaRegistro(rs.getTimestamp("fechaRegistro").toLocalDateTime());
                    usuario.setTipoCuenta(rs.getString("tipoCuenta"));
                    usuario.setCartera(rs.getDouble("cartera"));
                    usuario.setOrganizacion(rs.getLong("idOrganizacion"));

                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<UsuarioModel> obtenerUsuarioPorCodigo(String correo) {
        System.out.println("obtener por codigo optional");
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
                    usuario.setNumeroIdentificacion(rs.getString("numeroIdentificacion"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setFoto(rs.getBytes("foto"));  
                    usuario.setFechaRegistro(rs.getTimestamp("fechaRegistro").toLocalDateTime());
                    usuario.setTipoCuenta(rs.getString("tipoCuenta"));
                    usuario.setCartera(rs.getDouble("cartera"));
                    usuario.setOrganizacion(rs.getLong("idOrganizacion"));
                    return Optional.of(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void actualizarUsuarioPorCorreo(UsuarioModel usuario) {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement update = connection.prepareStatement(ACTUALIZAR_USUARIO_QUERY)) {
            update.setString(1, usuario.getNombreCompleto());
            update.setString(2, usuario.getTelefono());
            update.setString(3, usuario.getNumeroIdentificacion());
            update.setString(4, usuario.getContrasena());
            update.setString(5, usuario.getTipoCuenta());
            if (usuario.getFoto() != null) {
                update.setBytes(6, usuario.getFoto());
            } else {
                update.setNull(6, java.sql.Types.BLOB);
            }
            update.setString(7, usuario.getCorreo()); // condición WHERE
            update.setLong(8, usuario.getOrganizacion());
            int filasAfectadas = update.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Congreso actualizado correctamente: " + usuario.getCorreo());
            } else {
                System.out.println("No se encontró el congreso con código: " + usuario.getCorreo());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
 * Actualiza la cartera de un usuario en la base de datos usando su correo.
 * Si la cartera es null, la inicializa en 0.0 antes de guardar.
 *
 * @param usuario objeto UsuarioModel con correo y nueva cartera
 */
public void actualizarCartera(UsuarioModel usuario) {
    if (usuario == null || usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
        System.out.println("Usuario o correo inválido.");
        return;
    }

    // Asegurarse de que cartera no sea null
    if (usuario.getCartera() == null) {
        usuario.setCartera(0.0);
    }

    String sql = "UPDATE Usuario SET cartera = ? WHERE correo = ?";
    Connection connection = DBConnectionSingleton.getInstance().getConnection();

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setDouble(1, usuario.getCartera());
        stmt.setString(2, usuario.getCorreo());

        int filas = stmt.executeUpdate();
        if (filas > 0) {
            System.out.println("Cartera actualizada correctamente para: " + usuario.getCorreo());
        } else {
            System.out.println("No se encontró usuario con correo: " + usuario.getCorreo());
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
public UsuarioModel obtenerUsuarioPorIdUsuario(Long idUsuario){
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_IDUSUARIO_QUERY)) {
            query.setLong(1, idUsuario);  // OJO: si idCongreso puede venir null, hay que validarlo antes
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                System.out.println("lo encuentra y lo devuelve");
                UsuarioModel usuario = new UsuarioModel(
                        rs.getString("nombreCompleto"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("numeroIdentificacion"),
                        rs.getString("contrasena"),
                        rs.getString("tipoCuenta"),
                        rs.getLong("idOrganizacion")
                );
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setFechaRegistro(rs.getTimestamp("fechaRegistro").toLocalDateTime());
                usuario.setCartera(rs.getDouble("cartera"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("no existe el salon");
        return null;
    }

    
}
