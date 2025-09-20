/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Salones;

import Exceptions.EntityNotFoundException;
import Exceptions.SalonDataInvalidException;
import connection.SalonesDB;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author andy
 */
public class ActualizadorSalon {
    public SalonModel actualizar(HttpServletRequest request) throws SalonDataInvalidException, EntityNotFoundException{
        SalonesDB salonesDb = new SalonesDB();
        String idsalon = request.getParameter("idCongreso");
        System.out.println("->: "+idsalon);
        SalonModel salon = extraer(request);
        System.out.println("ssssssssss"+salon.getNombreSalon()+"f"+salon.getIdCongreso());
        
        if (salonesDb.encontrarSalonPorCongresoYNombre(salon.getIdCongreso(), salon.getNombreSalon()) == null) {
            throw new EntityNotFoundException(
                    String.format("El Salon con nombre %s no existe en este congreso", salon.getNombreSalon()));
        }
        
        salonesDb.actualizarSalon(salon);
        
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
            
            salon.setNuevoNombreSalon(request.getParameter("nuevoNombreSalon"));
            
            System.out.println("se contruyo el objeto congreso");
            System.out.println("" + salon.toString());
            System.out.println("se agrego nuevvo nombre");
            System.out.println("***"+salon.getNuevoNombreSalon());
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
