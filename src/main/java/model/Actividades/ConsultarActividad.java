/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import connection.ActividadesDB;
import java.util.List;

/**
 *
 * @author andy
 */
public class ConsultarActividad {
    public List<ActividadModel> obtenerTodasLasActividades(){
        System.out.println("* se entro a consultarCongreso");
        ActividadesDB actividadDb = new ActividadesDB();
        
        return actividadDb.obtenerTodasLasActividades();
    }
    
    
}
