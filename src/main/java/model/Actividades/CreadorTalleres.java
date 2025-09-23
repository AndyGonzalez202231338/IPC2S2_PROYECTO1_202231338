/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import Exceptions.ActividadDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import connection.TalleresDB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Usuarios.UsuarioModel;

/**
 *
 * @author andy
 */
public class CreadorTalleres {
    public TallerModel crearTaller(Long idActividad, Integer cupoMaximo) throws ActividadDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException {
        TalleresDB tallerDB = new TalleresDB();
        TallerModel taller = extraer(idActividad, cupoMaximo);

        if (tallerDB.encontrarActividadTallerPorId(taller.getIdActividad()) != null) {
            throw new EntityAlreadyExistsException(
                    String.format("El taller con codigo %s ya existe", taller.getIdActividad()));
        }

        tallerDB.crearTaller(taller);
        return taller;
    }
    
    private TallerModel extraer(Long idActividad, Integer cupoMaximo) throws ActividadDataInvalidException, EntityAlreadyExistsException, EntityNotFoundException {
        System.out.println("--entro a extraer");

        try {
            // Construcción del modelo Congreso directamente desde el request
            TallerModel taller = new TallerModel(
                idActividad,
                cupoMaximo
            );
            return taller;

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ActividadDataInvalidException("Error en los datos enviados de la actividad");
        }
    }
}
