/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import Exceptions.EntityNotFoundException;
import connection.InscripcionesTalleresDB;
import java.util.List;

/**
 *
 * @author andy
 */
public class ConsultarInscripcionTaller {
    public List<InscripcionTallerModel> obtenerTodosLosSalonesInscritosAlTaller(Long idActividad) throws EntityNotFoundException {
        InscripcionesTalleresDB inscripcionesTalleresDB = new InscripcionesTalleresDB();
        
        return inscripcionesTalleresDB.obtenerTodosLasInscripcionesTallerPorIdActividad(idActividad);
    }
    
}
