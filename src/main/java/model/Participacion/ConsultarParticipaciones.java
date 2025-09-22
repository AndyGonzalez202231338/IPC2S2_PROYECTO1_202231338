/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Participacion;

import Exceptions.EntityNotFoundException;
import connection.ParticipacionesDB;
import java.util.List;

/**
 *
 * @author andy
 */
public class ConsultarParticipaciones {
    public List<ParticipacionModel> obtenerParticipantesPorIdCongreso(Long idCongreso) throws EntityNotFoundException {
        ParticipacionesDB participacionDb = new ParticipacionesDB();
        List<ParticipacionModel> participacion = participacionDb.obtenerParticipacionPorCongreso(idCongreso);
        if(participacion == null){
            throw new EntityNotFoundException(
                    String.format("La Participacion con congreso id %s no existe", idCongreso)
            );
        }
         return participacion;
    }
}
