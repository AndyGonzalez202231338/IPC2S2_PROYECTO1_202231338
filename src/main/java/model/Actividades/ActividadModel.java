/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.Salones.SalonModel;
import model.Usuarios.UsuarioModel;

public class ActividadModel {

    private Long idActividad;
    private String codigo;
    private Long idCongreso;
    private Long idSalon;
    private String nombreActividad;
    private String descripcion;
    private String tipoActividad; // PONENCIA o TALLER
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long creadoPor; // id del admin que creó la actividad
    private LocalDate fechaCreacion;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    private SalonModel salon;
    private UsuarioModel ponente;
    private TallerModel taller;

    // Constructor vacío
    public ActividadModel() {
    }

    // Constructor con todos los campos
    public ActividadModel(Long idActividad, String codigo, Long idCongreso, Long idSalon, String nombreActividad,
            String descripcion, String tipoActividad, LocalTime horaInicio,
            LocalTime horaFin, Long creadoPor, LocalDate fechaCreacion) {
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

    public ActividadModel(Long idActividad, String codigo, Long idCongreso, Long idSalon, String nombreActividad, String descripcion, String tipoActividad, LocalTime horaInicio, LocalTime horaFin, Long creadoPor, LocalDate fechaCreacion, LocalDate fechaInicio, LocalDate fechaFin, SalonModel salon, UsuarioModel ponente) {
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
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.salon = salon;
        this.ponente = ponente;
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public SalonModel getSalon() {
        return salon;
    }

    public void setSalon(SalonModel salon) {
        this.salon = salon;
    }

    public UsuarioModel getPonente() {
        return ponente;
    }

    public void setPonente(UsuarioModel ponente) {
        this.ponente = ponente;
    }

    public TallerModel getTaller() {
        return taller;
    }

    public void setTaller(TallerModel taller) {
        this.taller = taller;
    }
    
    
    public boolean esValido() {
        return codigo != null && !codigo.isBlank()
                && nombreActividad != null && !nombreActividad.isBlank()
                && descripcion != null && !descripcion.isBlank()
                && horaInicio != null
                && horaFin != null
                && !horaFin.isBefore(horaInicio) // La hora de fin no puede ser antes de inicio
                && tipoActividad != null && !tipoActividad.isBlank()
                && fechaCreacion != null
                && fechaInicio != null
                && fechaFin != null
                && ( (fechaCreacion.isEqual(fechaInicio) || fechaCreacion.isAfter(fechaInicio)) 
              && (fechaCreacion.isEqual(fechaFin) || fechaCreacion.isBefore(fechaFin)) );
    }

    @Override
    public String toString() {
        return "ActividadModel{" + "idActividad=" + idActividad + ", codigo=" + codigo + ", idCongreso=" + idCongreso + ", idSalon=" + idSalon + ", nombreActividad=" + nombreActividad + ", descripcion=" + descripcion + ", tipoActividad=" + tipoActividad + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", creadoPor=" + creadoPor + ", fechaCreacion=" + fechaCreacion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
    
    
}
