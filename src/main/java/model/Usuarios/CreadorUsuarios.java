/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Usuarios;

import Exceptions.EntityAlreadyExistsException;
import Exceptions.UserDataInvalidException;
import connection.UsuariosDB;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

/**
 *
 * @author andy
 */
public class CreadorUsuarios {

    public UsuarioModel crearUsuario(HttpServletRequest request) throws UserDataInvalidException, EntityAlreadyExistsException {
        System.out.println("        Creador usuario");
        UsuariosDB usuariosDB = new UsuariosDB();

        UsuarioModel usuario = extraer(request);

        if (usuariosDB.buscarPorCorreo(usuario.getCorreo()) != null) {
            throw new EntityAlreadyExistsException(
                    String.format("El Usuario con correo %s ya existe", usuario.getCorreo()));
        }
        System.out.println("" + usuario.toString());
        usuariosDB.crearUsuario(usuario);
        return usuario;
    }

    private UsuarioModel extraer(HttpServletRequest request) throws UserDataInvalidException {
        try {
            System.out.println("        extraer");
            String fechaStr = request.getParameter("fechaRegistro");
            LocalDateTime fechaRegistro = null;

            if (fechaStr != null && !fechaStr.isEmpty()) {
                fechaRegistro = LocalDateTime.parse(fechaStr);
            }

            // 🔹 Codificar contraseña en Base64
            String contrasenaOriginal = request.getParameter("contrasena");
            String contrasenaCodificada = Base64.getEncoder().encodeToString(contrasenaOriginal.getBytes());

            UsuarioModel usuario = new UsuarioModel(
                    request.getParameter("idUsuario") != null && !request.getParameter("idUsuario").isEmpty()
                    ? Integer.valueOf(request.getParameter("idUsuario"))
                    : null,
                    request.getParameter("nombreCompleto"),
                    request.getParameter("correo"),
                    request.getParameter("telefono"),
                    request.getParameter("numeroIdentificacion"),
                    contrasenaCodificada, // 🔹 Guardamos la contraseña codificada
                    fechaRegistro,
                    request.getParameter("tipoCuenta"),
                    Long.valueOf(request.getParameter("organizacion"))
            );

            return usuario;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UserDataInvalidException("Error en los datos enviados del usuario");
        }
    }

}
