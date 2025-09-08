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

/**
 *
 * @author andy
 */
public class CreadorUsuarios {
    public UsuarioModel crearUsuario(HttpServletRequest request) throws UserDataInvalidException, EntityAlreadyExistsException{
        System.out.println("        Creador usuario");
        UsuariosDB usuariosDB = new UsuariosDB();
        
        UsuarioModel usuario = extraer(request);
        
        if(usuariosDB.buscarPorCorreo(usuario.getCorreo()) != null){
            throw new EntityAlreadyExistsException(
                    String.format("El Usuario con correo %s ya existe", usuario.getCorreo()));
        }
        System.out.println(""+usuario.toString());
        usuariosDB.crearUsuario(usuario);
        return usuario;
    }
    
    private UsuarioModel extraer(HttpServletRequest request) throws UserDataInvalidException {
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
            request.getParameter("organizacion"),
            request.getParameter("numeroIdentificacion"),
            request.getParameter("foto"),
            request.getParameter("contrasena"),
            fechaRegistro,   // <-- ahora es LocalDateTime
            request.getParameter("tipoCuenta")
        );
        
        return usuario;
    } catch (IllegalArgumentException | NullPointerException e) {
        throw new UserDataInvalidException("Error en los datos enviados del usuario");
    }
}
}
