/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import Exceptions.EntityNotFoundException;
import connection.ActividadesDB;
import connection.TalleresDB;
import java.util.List;

/**
 *
 * @author andy
 */
public class ConsultarTaller {
    public List<TallerModel> obtenerTodasLosTallers(Long idActividad) throws EntityNotFoundException {
        System.out.println("* se entro a Consultar actvidad por id congreso");
        TalleresDB tallerDb = new TalleresDB();
        return tallerDb.obtenerTodosLasActividadesPorCongreso(idActividad);
    }
    
    public TallerModel obtenerTallerPorIdActividad(Long idActividad) throws EntityNotFoundException{
        
        TalleresDB tallerDB = new TalleresDB();
        TallerModel usuario = tallerDB.obtenerTallerPorIdActividad(idActividad);
        if(usuario == null){
            throw new EntityNotFoundException(
                    String.format("El Taller con id %s no existe", idActividad)
            );
        }
        return usuario;
    }
}
