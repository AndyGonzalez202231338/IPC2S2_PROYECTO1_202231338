/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Salones;

import Exceptions.EntityNotFoundException;
import connection.SalonesDB;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author andy
 */
public class ConsultarSalon {
    public List<SalonModel> obtenerTodosLosSalones(){
        System.out.println("* se entro a consultarCongreso");
        SalonesDB salonDb = new SalonesDB();
        
        return salonDb.obtenerTodosLosCongresos();
    }
    
    public SalonModel obtenerSalonPorCongresoYNombre(Long idCongreso, String codigo) throws EntityNotFoundException {
        SalonesDB salonDb = new SalonesDB();
        Optional<SalonModel> congresoOpt = salonDb.obtenerSalonPorCongresoYNombre(idCongreso,codigo);
        if(congresoOpt.isEmpty()){
            throw new EntityNotFoundException(
                    String.format("El congreso con codigo %s no existe", codigo)
            );
        }
         return congresoOpt.get();
    }
}
