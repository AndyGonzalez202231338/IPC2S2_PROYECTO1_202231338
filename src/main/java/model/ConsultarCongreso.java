/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Exceptions.EntityNotFoundException;
import connection.CongresosDB;
import java.util.List;
import java.util.Optional;

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
    
    public CongresoModel obtenerCongresoPorCodigo(String codigo) throws EntityNotFoundException {
        CongresosDB congresoDb = new CongresosDB();
        Optional<CongresoModel> congresoOpt = congresoDb.obtenerCongresoPorCodigo(codigo);
        if(congresoOpt.isEmpty()){
            throw new EntityNotFoundException(
                    String.format("El congreso con codigo %s no existe", codigo)
            );
        }
         return congresoOpt.get();
    }
    
}
