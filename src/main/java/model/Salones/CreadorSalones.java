/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Salones;

import Exceptions.EntityAlreadyExistsException;
import Exceptions.SalonDataInvalidException;
import connection.SalonesDB;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author andy
 */
public class CreadorSalones {
    /**
     * Crar salones en DB
     * @param request crear-salon
     * @return objetos salon
     * @throws SalonDataInvalidException
     * @throws EntityAlreadyExistsException 
     */
    public SalonModel crearSalon(HttpServletRequest request) throws SalonDataInvalidException, EntityAlreadyExistsException {
        SalonesDB salonesDB = new SalonesDB();

        SalonModel salon = extraer(request);

        if (salonesDB.encontrarSalonPorCongresoYNombre(salon.getIdCongreso(), salon.getNombreSalon()) != null) {
            throw new EntityAlreadyExistsException(
                    String.format("El Congreso con codigo %s, y nombre %s ya existe", salon.getIdCongreso(), salon.getNombreSalon()));
        }

        salonesDB.crearSalon(salon);
        return salon;
    }

    private SalonModel extraer(HttpServletRequest request) throws SalonDataInvalidException {
        System.out.println("--entro a extraer");
        try {
            // Construcción del modelo Congreso directamente desde el request
            SalonModel salon = new SalonModel(
                    request.getParameter("idSalon") != null && !request.getParameter("idSalon").isEmpty()
                    ? Long.parseLong(request.getParameter("idSalon"))
                    : null, // idCongreso (puede ser null porque es autoincremental)
                    Long.valueOf(request.getParameter("idCongreso")),
                    request.getParameter("nombreSalon"),
                    request.getParameter("ubicacion"),
                    Integer.valueOf(request.getParameter("capacidad")),
                    request.getParameter("recursos")
            );
            System.out.println("se contruyo el objeto congreso");
            System.out.println("" + salon.toString());

            if (!salon.esValidoParaCrear()) {
                throw new SalonDataInvalidException("Error en los datos enviados");
            }

            System.out.println("--logra salir de extraer");
            return salon;

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new SalonDataInvalidException("Error en los datos enviados del Salon");
        }
    }
}
