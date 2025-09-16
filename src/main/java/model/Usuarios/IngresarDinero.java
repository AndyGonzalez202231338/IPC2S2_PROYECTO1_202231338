package model.Usuarios;

import Exceptions.EntityNotFoundException;
import Exceptions.UserDataInvalidException;
import connection.UsuariosDB;
import jakarta.servlet.http.HttpServletRequest;

public class IngresarDinero {

    public UsuarioModel ingresarDineroUsuario(HttpServletRequest request) 
            throws UserDataInvalidException, EntityNotFoundException {

        String correo = request.getParameter("correo");
        String montoStr = request.getParameter("monto");

        if (correo == null || correo.isEmpty() || montoStr == null || montoStr.isEmpty()) {
            throw new UserDataInvalidException("Correo o monto inválido.");
        }

        double monto;
        try {
            monto = Double.parseDouble(montoStr);
        } catch (NumberFormatException e) {
            throw new UserDataInvalidException("Monto inválido.");
        }

        UsuariosDB db = new UsuariosDB();
        UsuarioModel usuario = db.buscarPorCorreo(correo);

        if (usuario == null) {
            throw new EntityNotFoundException("No se encontró el usuario con correo: " + correo);
        }

        // Agregar dinero
        if (usuario.getCartera() == null) usuario.setCartera(0.0);
        usuario.setCartera(usuario.getCartera() + monto);

        // Guardar en DB
        db.actualizarCartera(usuario);

        return usuario;
    }
}

