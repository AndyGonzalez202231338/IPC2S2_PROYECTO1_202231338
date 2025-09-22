/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Participacion;

import java.time.LocalDateTime;

/**
 *
 * @author andy
 */
public class ParticipacionModel {
    private Long idParticipacion;
    private Long idUsuario;
    private Long idCongreso;
    private String tipoParticipacion;
    private LocalDateTime fechaRegistro;

    public ParticipacionModel(Long idParticipacion, Long idUsuario, Long idCongreso, String tipoParticipacion, LocalDateTime fechaRegistro) {
        this.idParticipacion = idParticipacion;
        this.idUsuario = idUsuario;
        this.idCongreso = idCongreso;
        this.tipoParticipacion = tipoParticipacion;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdParticipacion() {
        return idParticipacion;
    }

    public void setIdParticipacion(Long idParticipacion) {
        this.idParticipacion = idParticipacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdCongreso() {
        return idCongreso;
    }

    public void setIdCongreso(Long idCongreso) {
        this.idCongreso = idCongreso;
    }

    public String getTipoParticipacion() {
        return tipoParticipacion;
    }

    public void setTipoParticipacion(String tipoParticipacion) {
        this.tipoParticipacion = tipoParticipacion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "ModelParticipacion{" + "idParticipacion=" + idParticipacion + ", idUsuario=" + idUsuario + ", idCongreso=" + idCongreso + ", tipoParticipacion=" + tipoParticipacion + ", fechaRegistro=" + fechaRegistro + '}';
    }
    
    public boolean esValido() {
        return tipoParticipacion != null && !tipoParticipacion.isBlank();
                
    }
}
