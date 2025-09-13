/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Congresos;

import model.Congresos.CongresoModel;
import Exceptions.CongresoDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import connection.CongresosDB;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author andy
 */
public class CreadorCongresos {

    public CongresoModel crearEvento(HttpServletRequest request) throws CongresoDataInvalidException, EntityAlreadyExistsException {
        System.out.println("* entro a crear un evento");

        CongresosDB congresosDb = new CongresosDB();

        CongresoModel congreso = extraer(request);

        if (congresosDb.encontrarCongresoPorId(congreso.getCodigo()) != null) {
            throw new EntityAlreadyExistsException(
                    String.format("El Congreso con codigo %s ya existe", congreso.getCodigo()));
        }

        System.out.println("***se creo el congreso con el modelo congreoModel");
        congresosDb.crearCongreso(congreso);
        System.out.println("* regreso un evento creado");
        return congreso;
    }

    private CongresoModel extraer(HttpServletRequest request) throws CongresoDataInvalidException {
        System.out.println("--entro a extraer");
        try {
            // Construcción del modelo Congreso directamente desde el request
            CongresoModel congreso = new CongresoModel(
                    request.getParameter("idCongreso") != null && !request.getParameter("idCongreso").isEmpty()
                    ? Long.parseLong(request.getParameter("idCongreso"))
                    : null, // idCongreso (puede ser null porque es autoincremental)

                    request.getParameter("codigo"), // codigo
                    request.getParameter("nombre"), // nombre
                    request.getParameter("descripcion"), // descripcion

                    LocalDate.parse(request.getParameter("fechaInicio")), // fechaInicio
                    LocalDate.parse(request.getParameter("fechaFin")), // fechaFin

                    request.getParameter("lugar"), // lugar
                    Double.valueOf(request.getParameter("precio")), // precioS

                    LocalDateTime.now(), // fechaCreacion (desde Java, no del request)
                    Double.valueOf(request.getParameter("porcentajeGanancia"))
            );
            System.out.println("se contruyo el objeto congreso");
            System.out.println("" + congreso.toString());

            if (congreso.getFechaFin().isBefore(congreso.getFechaInicio())) {
                throw new CongresoDataInvalidException("La fecha de fin no puede ser anterior a la fecha de inicio.");
            }

            if (!congreso.esValido()) {
                throw new CongresoDataInvalidException("Error en los datos enviados");
            }

            System.out.println("--logra salir de extraer");
            return congreso;

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CongresoDataInvalidException("Error en los datos enviados del congreso");
        }
    }

}
