/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import connection.CongresosDB;
import java.util.List;

/**
 *
 * @author andy
 */
public class ConsultarCongreso {
    
    public List<CongresoModel> obtenerTodosLosCongresos(){
        System.out.println("* se entro a consultarCongreso");
        CongresosDB congresoDb = new CongresosDB();
        
        return congresoDb.obtenerTodosLosCongresos();
    }
}
