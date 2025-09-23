/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import java.time.LocalDateTime;
import model.Usuarios.UsuarioModel;

/**
 *
 * @author andy
 */
public class InscripcionTallerModel {
    private Long idInscripcion;
    private Long idusuario;
    private Long idActividad;
    private LocalDateTime fechaRegistro;
    private Integer asistencia;
    
    private UsuarioModel usuario;
    private ActividadModel actividad;
    

    public InscripcionTallerModel(Long idInscripcion, Long idusuario, Long idActividad, LocalDateTime fechaRegistro, Integer asistencia) {
        this.idInscripcion = idInscripcion;
        this.idusuario = idusuario;
        this.idActividad = idActividad;
        this.fechaRegistro = fechaRegistro;
        this.asistencia = asistencia;
    }

    public InscripcionTallerModel(Long idInscripcion, Long idusuario, Long idActividad) {
        this.idInscripcion = idInscripcion;
        this.idusuario = idusuario;
        this.idActividad = idActividad;
    }

    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Long idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public ActividadModel getActividad() {
        return actividad;
    }

    public void setActividad(ActividadModel actividad) {
        this.actividad = actividad;
    }

    public Integer getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Integer asistencia) {
        this.asistencia = asistencia;
    }
    
    public boolean isAsistenciaRegistrada() {
    return asistencia == 1;
}

}
