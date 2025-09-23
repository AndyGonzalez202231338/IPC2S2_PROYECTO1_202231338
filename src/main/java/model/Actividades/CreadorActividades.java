/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import Exceptions.ActividadDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import connection.ActividadesDB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.Congresos.CongresoModel;
import model.Congresos.ConsultarCongreso;
import model.Usuarios.UsuarioModel;

/**
 *
 * @author andy
 */
public class CreadorActividades {

    public ActividadModel crearEvento(HttpServletRequest request) throws ActividadDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException {
        ActividadesDB actividadesDB = new ActividadesDB();
        ActividadModel actividad = extraer(request);

        if (actividadesDB.encontrarActividadPorId(actividad.getCodigo()) != null) {
            throw new EntityAlreadyExistsException(
                    String.format("La actividad con codigo %s ya existe", actividad.getCodigo()));
        }

        actividadesDB.crearActividad(actividad);
        return actividad;
    }

    private ActividadModel extraer(HttpServletRequest request) throws ActividadDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException {
        System.out.println("--entro a extraer");

        try {
            System.out.println("entro en try");
            HttpSession session = request.getSession(false);
            UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuarioLogueado");

            if (usuario == null) {
                throw new ActividadDataInvalidException("ERROR: debes iniciar sesion para crear una activdad.");
            }

            Long idUsuario = (long) usuario.getId();
            System.out.println("id usuario" + idUsuario);

            // Construcción del modelo Congreso directamente desde el request
            ActividadModel actividad = new ActividadModel(
                    request.getParameter("idActividad") != null && !request.getParameter("idActividad").isEmpty()
                    ? Long.parseLong(request.getParameter("idActividad"))
                    : null,
                    request.getParameter("codigo"), // codigo
                    Long.valueOf(request.getParameter("idCongreso")),
                    Long.valueOf(request.getParameter("idSalon")),
                    request.getParameter("nombreActividad"), // nombre
                    request.getParameter("descripcion"), // descripcion
                    request.getParameter("tipoActividad"),
                    LocalTime.parse(request.getParameter("horaInicio")), // fechaInicio
                    LocalTime.parse(request.getParameter("horaFin")), // fechaFin
                    idUsuario,
                    //Long.valueOf(request.getParameter("idSalon")),
                    LocalDate.parse(request.getParameter("fechaCreacion"))
            );
            System.out.println("se contruyo el objeto");
            ConsultarCongreso consultarCongresos = new ConsultarCongreso();
            CongresoModel congreso = consultarCongresos.obtenerCongresoPorCodigo(request.getParameter("codigoCongreso"));
            actividad.setFechaInicio(congreso.getFechaInicio());
            actividad.setFechaFin(congreso.getFechaFin());

            System.out.println("***" + actividad.toString());

            ActividadesDB actividadesDB = new ActividadesDB();

            boolean disponible = actividadesDB.salonDisponible(
                    actividad.getIdSalon(),
                    actividad.getFechaCreacion(),
                    actividad.getHoraInicio(),
                    actividad.getHoraFin()
            );

            if (!disponible) {
                throw new ActividadDataInvalidException("El salón ya está ocupado en ese horario.");
            }
            
            if (actividad.getHoraFin().isBefore(actividad.getHoraInicio())) {
                throw new ActividadDataInvalidException("La hora de fin no puede ser anterior a la hora de inicio.");
            }

            if (actividad.getFechaCreacion().isBefore(actividad.getFechaInicio())
                    || actividad.getFechaCreacion().isAfter(actividad.getFechaFin())) {
                throw new ActividadDataInvalidException("La fecha de actividad debe estar dentro del rango de fechas de inicio y fin.");
            }

            if (!actividad.esValido()) {
                throw new ActividadDataInvalidException("Error en los datos enviados");
            }
            return actividad;

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ActividadDataInvalidException("Error en los datos enviados de la actividad");
        }
    }

}
