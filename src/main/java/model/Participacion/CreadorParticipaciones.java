/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Participacion;

import Exceptions.CongresoDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import Exceptions.ParticipacionDataInvalidException;
import Exceptions.UserDataInvalidException;
import connection.ParticipacionesDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import model.Congresos.CongresoModel;
import model.Congresos.ConsultarCongreso;
import model.Congresos.RecaudarDinero;
import model.Usuarios.ActualizadorUsuario;
import model.Usuarios.ConsultarUsuario;
import model.Usuarios.IngresarDinero;
import model.Usuarios.UsuarioModel;

/**
 *
 * @author andy
 */
public class CreadorParticipaciones {
    public ParticipacionModel crearParticipacion(HttpServletRequest request) throws ParticipacionDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException, UserDataInvalidException, CongresoDataInvalidException{
        ParticipacionesDB participacionesDB = new ParticipacionesDB();
        ParticipacionModel participacion = extraer(request);

        if (participacionesDB.encontrarParticipacionPorIdUsuarioIdCongreso(participacion.getIdUsuario(), participacion.getIdCongreso()) != null) {
            throw new EntityAlreadyExistsException(
                    String.format("La estas inscrito a este congreso con este usuario"));
        }

        participacionesDB.crearParticipacion(participacion);
        return participacion;
    }
    
    private ParticipacionModel extraer(HttpServletRequest request) throws ParticipacionDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException, UserDataInvalidException, CongresoDataInvalidException{
        System.out.println("--entro a extraer");

        try {

            // Construcción del modelo Congreso directamente desde el request
            ParticipacionModel participacion = new ParticipacionModel(
                    request.getParameter("idParticipacion") != null && !request.getParameter("idParticipacion").isEmpty()
                    ? Long.parseLong(request.getParameter("idParticipacion"))
                    : null,
                    Long.valueOf(request.getParameter("idUsuario")),
                    Long.valueOf(request.getParameter("idCongreso")),
                    request.getParameter("tipoParticipacion"),
                    LocalDateTime.now() // fechaCreacion (desde Java, no del request)
            );
            
            if (!participacion.esValido()) {
                throw new ParticipacionDataInvalidException("Error en los datos enviados");
            }
            
            System.out.println("*******participacion es valida");
            IngresarDinero descontarDinero = new IngresarDinero();
            descontarDinero.descontarDineroUsuario(request);
            
            RecaudarDinero recaudarDinero = new RecaudarDinero();
            recaudarDinero.recaudarDineroParticipancion(request);
            //DescontarDineroUsario(request);
            
            return participacion;

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ParticipacionDataInvalidException("Error en los datos enviados de la actividad");
        }
    }
    
    private void DescontarDineroUsario(HttpServletRequest request) throws EntityNotFoundException, ParticipacionDataInvalidException, UserDataInvalidException, IOException, ServletException{
        ConsultarUsuario usarioConsultar = new ConsultarUsuario();
        UsuarioModel usuario = usarioConsultar.obtenerUsuarioPorIdUsuario(Long.valueOf(request.getParameter("idUsuario")));
        
        ConsultarCongreso congresoConsultar = new ConsultarCongreso();
        CongresoModel congreso = congresoConsultar.obtenerCongresoPorIdCongreso(Long.valueOf(request.getParameter("idCongreso")));
        
        Double precioCongreso = congreso.getPrecio();
        
        Double carteraActual = usuario.getCartera();
        
         if(carteraActual < precioCongreso){
             throw new ParticipacionDataInvalidException("No cuentas con el dinero suficiente para inscribirte al Congreso");
         }
        
        Double carteraNueva = carteraActual-precioCongreso;
        System.out.println("Se descuenta el pago");
        
        ActualizadorUsuario usuarioActualizador = new ActualizadorUsuario();
        UsuarioModel usuarioActualizado = usuarioActualizador.actualizar(request);
        
        usuario.setCartera(carteraNueva);
        
        
    }
}
