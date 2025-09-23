/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Usuarios;

import Exceptions.EntityNotFoundException;
import Exceptions.UserDataInvalidException;
import connection.UsuariosDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 *
 * @author andy
 */
public class ActualizadorUsuario {

    public UsuarioModel actualizar(HttpServletRequest request) throws UserDataInvalidException, EntityNotFoundException, IOException, ServletException{
        UsuariosDB usuariosDB = new UsuariosDB();

        UsuarioModel usuario = extraer(request);
        String contrasena = request.getParameter("contrasena");

// Si no viene en el form, recupera la actual
        if (contrasena == null || contrasena.isEmpty()) {
            UsuarioModel usuarioExistente = usuariosDB.buscarPorCorreo(request.getParameter("correo"));
            contrasena = usuarioExistente.getContrasena();
        }

        usuario.setContrasena(contrasena);

        if (usuariosDB.buscarPorCorreo(usuario.getCorreo()) == null) {
            throw new EntityNotFoundException(
                    String.format("El Usuario con correo %s no existe", usuario.getCorreo()));
        }

        usuariosDB.actualizarUsuarioPorCorreo(usuario);

        return usuario;
    }

    private UsuarioModel extraer(HttpServletRequest request) throws UserDataInvalidException, IOException, ServletException {
        try {
            // Obtener la fecha/hora como string
            System.out.println("        extraer");
            String fechaStr = request.getParameter("fechaRegistro");
            LocalDateTime fechaRegistro = null;

            if (fechaStr != null && !fechaStr.isEmpty()) {
                // Convierte el string a LocalDateTime (formato ISO 8601: yyyy-MM-ddTHH:mm)
                fechaRegistro = LocalDateTime.parse(fechaStr);
            }

            UsuarioModel usuario = new UsuarioModel(
                    request.getParameter("idUsuario") != null && !request.getParameter("idUsuario").isEmpty()
                    ? Integer.valueOf(request.getParameter("idUsuario"))
                    : null,
                    request.getParameter("nombreCompleto"),
                    request.getParameter("correo"),
                    request.getParameter("telefono"),
                    request.getParameter("numeroIdentificacion"),
                    request.getParameter("contrasena"),
                    fechaRegistro, // <-- ahora es LocalDateTime
                    request.getParameter("tipoCuenta"),
                    Long.valueOf(request.getParameter("organizacion"))
            );
            // 📌 Leer foto del request
            Part filePart = request.getPart("foto");
            if (filePart != null && filePart.getSize() > 0) {
                InputStream inputStream = filePart.getInputStream();
                usuario.setFoto(inputStream.readAllBytes()); // Guardamos bytes en el modelo
            }
            return usuario;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UserDataInvalidException("Error en los datos enviados del usuario");
        }
    }
}
