/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import Exceptions.CongresoDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import Exceptions.ParticipacionDataInvalidException;
import Exceptions.UserDataInvalidException;
import connection.InscripcionesTalleresDB;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 *
 * @author andy
 */
public class CreadorInscipcionTaller {

    public InscripcionTallerModel crearParticipacion(HttpServletRequest request) throws ParticipacionDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException, UserDataInvalidException, CongresoDataInvalidException {
        InscripcionesTalleresDB inscripcionesTalleresDB = new InscripcionesTalleresDB();
        InscripcionTallerModel inscripcionTaller = extraer(request);
        System.out.println("salio de extraer");

// si pasa validaciones entonces guardar

        inscripcionesTalleresDB.crearInscripcionTaller(inscripcionTaller);
        return inscripcionTaller;
    }
    
    public void CrearAsistencia(HttpServletRequest request) throws ParticipacionDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException, UserDataInvalidException, CongresoDataInvalidException {
        InscripcionesTalleresDB inscripcionesTalleresDB = new InscripcionesTalleresDB();
        InscripcionTallerModel inscripcionTaller = extraerAsistencia(request);
        System.out.println("salio de extraer");

// si pasa validaciones entonces guardar

        inscripcionesTalleresDB.confirmarAsistencia(inscripcionTaller.getIdusuario(), inscripcionTaller.getIdActividad());
    }

    private InscripcionTallerModel extraer(HttpServletRequest request) throws ParticipacionDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException, UserDataInvalidException, CongresoDataInvalidException {
        System.out.println("--entro a extraer");

        try {

            // Construcción del modelo Congreso directamente desde el request
            InscripcionTallerModel inscripcionTaller = new InscripcionTallerModel(
                    request.getParameter("idInscripcion") != null && !request.getParameter("idInscripcion").isEmpty()
                    ? Long.parseLong(request.getParameter("idInscripcion"))
                    : null,
                    Long.valueOf(request.getParameter("idUsuario")),
                    Long.valueOf(request.getParameter("idActividad")),
                    LocalDateTime.now(), // fechaCreacion (desde Java, no del request)
                    0
            );

        Long idActividad = Long.valueOf(request.getParameter("idActividad"));
        Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));

        InscripcionesTalleresDB inscripcionesDB = new InscripcionesTalleresDB();

        int cupoMaximo = inscripcionesDB.obtenerCupoMaximo(idActividad);
        int inscritos = inscripcionesDB.contarInscritos(idActividad);

        if (inscritos >= cupoMaximo) {
            throw new ParticipacionDataInvalidException("El taller ya alcanzó el cupo máximo de " + cupoMaximo + " participantes.");
        }

// además, evitar duplicados
        if (inscripcionesDB.encontrarInscripcionTallerPorIdUsuarioIdActividad(idUsuario, idActividad) != null) {
            throw new EntityAlreadyExistsException("Ya estás inscrito en este taller.");
        }

            return inscripcionTaller;

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ParticipacionDataInvalidException("Error en los datos enviados de la actividad");
        }
    }

    private InscripcionTallerModel extraerAsistencia(HttpServletRequest request) throws ParticipacionDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException, UserDataInvalidException, CongresoDataInvalidException{
        try {

            // Construcción del modelo Congreso directamente desde el request
            InscripcionTallerModel inscripcionTaller = new InscripcionTallerModel(
                    request.getParameter("idInscripcion") != null && !request.getParameter("idInscripcion").isEmpty()
                    ? Long.parseLong(request.getParameter("idInscripcion"))
                    : null,
                    Long.valueOf(request.getParameter("idUsuario")),
                    Long.valueOf(request.getParameter("idActividad"))
            );

            return inscripcionTaller;

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ParticipacionDataInvalidException("Error en los datos enviados de la actividad");
        }
    }
}
