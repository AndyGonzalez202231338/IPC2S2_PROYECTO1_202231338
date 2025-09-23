package model.Usuarios;

import Exceptions.EntityNotFoundException;
import Exceptions.ParticipacionDataInvalidException;
import Exceptions.UserDataInvalidException;
import connection.UsuariosDB;
import jakarta.servlet.http.HttpServletRequest;
import model.Congresos.CongresoModel;
import model.Congresos.ConsultarCongreso;

public class IngresarDinero {
    /**
     * Meter dinero a cuenta usuario
     * @param request de jsp
     * @return el usuario con cambios
     * @throws UserDataInvalidException mala infroamcion
     * @throws EntityNotFoundException no existe
     */
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
    /**
     * Descontar por una inscripcion a congreso
     * @param request del jsp insricpcion
     * @return objeto usario con dscuento
     * @throws UserDataInvalidException mala inforamcion
     * @throws EntityNotFoundException no existe
     */
    public UsuarioModel descontarDineroUsuario(HttpServletRequest request) 
            throws UserDataInvalidException, EntityNotFoundException {
        System.out.println("///// dsecontar dienro usaurio");
        String id = request.getParameter("idUsuario");
        System.out.println("idUsario."+id);
        ConsultarUsuario usarioConsultar = new ConsultarUsuario();
        UsuarioModel usuario = usarioConsultar.obtenerUsuarioPorIdUsuario(Long.valueOf(request.getParameter("idUsuario")));
        
        System.out.println("usuario que se insribe"+usuario.toString());
        if (usuario.getCartera() == null) usuario.setCartera(0.0);
        
        ConsultarCongreso congresoConsultar = new ConsultarCongreso();
        CongresoModel congreso = congresoConsultar.obtenerCongresoPorIdCongreso(Long.valueOf(request.getParameter("idCongreso")));
        System.out.println("congreso al que se inscribe"+congreso.toString());
        Double precioCongreso = congreso.getPrecio();
        
        Double carteraActual = usuario.getCartera();
        
         if(carteraActual < precioCongreso){
             throw new UserDataInvalidException("No cuentas con el dinero suficiente para inscribirte al Congreso");
         }

        // Agregar dinero
        
        usuario.setCartera(usuario.getCartera() - congreso.getPrecio());

        // Guardar en DB
        UsuariosDB db = new UsuariosDB();
        db.actualizarCartera(usuario);

        return usuario;
    }
}

