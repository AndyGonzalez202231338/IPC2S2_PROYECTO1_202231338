/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import java.time.LocalDateTime;

public class ActividadModel {
    private Long idActividad;
    private String codigo;
    private Long idCongreso;
    private Long idSalon;
    private String nombreActividad;
    private String descripcion;
    private String tipoActividad; // PONENCIA o TALLER
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Long creadoPor; // id del admin que creó la actividad
    private LocalDateTime fechaCreacion;

    // Constructor vacío
    public ActividadModel() {}

    // Constructor con todos los campos
    public ActividadModel(Long idActividad, String codigo,Long idCongreso, Long idSalon, String nombreActividad,
                          String descripcion, String tipoActividad, LocalDateTime horaInicio,
                          LocalDateTime horaFin, Long creadoPor, LocalDateTime fechaCreacion) {
        this.idActividad = idActividad;
        this.codigo = codigo;
        this.idCongreso = idCongreso;
        this.idSalon = idSalon;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.tipoActividad = tipoActividad;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Long getIdCongreso() {
        return idCongreso;
    }

    public void setIdCongreso(Long idCongreso) {
        this.idCongreso = idCongreso;
    }

    public Long getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(Long idSalon) {
        this.idSalon = idSalon;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
}
