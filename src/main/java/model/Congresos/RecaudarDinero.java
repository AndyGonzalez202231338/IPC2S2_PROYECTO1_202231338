/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Congresos;

import Exceptions.CongresoDataInvalidException;
import Exceptions.EntityNotFoundException;
import connection.CongresosDB;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author andy
 */
public class RecaudarDinero {
    /**
     * Aumenta el recaudo de un congreso cuando se iscriben
     * @param request de inscripcion jsp
     * @return un congres cambiado
     * @throws CongresoDataInvalidException x
     * @throws EntityNotFoundException x
     */
    public CongresoModel recaudarDineroParticipancion(HttpServletRequest request)throws CongresoDataInvalidException, EntityNotFoundException {
        System.out.println("-----------recaudar dienro");
        ConsultarCongreso congresoConsultar = new ConsultarCongreso();
        CongresoModel congreso = congresoConsultar.obtenerCongresoPorIdCongreso(Long.valueOf(request.getParameter("idCongreso")));
        
        if (congreso.getRecaudado()== null) congreso.setRecaudado(0.0);
        
        Double pago = congreso.getPrecio();
        
        congreso.setRecaudado(pago);
        
        CongresosDB congresoDB = new CongresosDB();
        congresoDB.actualizarCartera(congreso);
        return congreso;
    }
}
