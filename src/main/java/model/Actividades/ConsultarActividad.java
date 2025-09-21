/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import Exceptions.EntityNotFoundException;
import connection.ActividadesDB;
import java.util.List;

/**
 *
 * @author andy
 */
public class ConsultarActividad {
    public List<ActividadModel> obtenerTodasLasActividades(Long idCongreso) throws EntityNotFoundException {
        System.out.println("* se entro a Consultar actvidad por id congreso");
        ActividadesDB actividadDb = new ActividadesDB();
        return actividadDb.obtenerTodosLasActividadesPorCongreso(idCongreso);
    }
}
