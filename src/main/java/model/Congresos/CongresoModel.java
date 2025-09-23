/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Congresos;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author andy
 */
public class CongresoModel {
    private Long idCongreso;
    private String codigo;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String lugar;
    private Double precio;
    private Double porcentajeGanancia;
    private Double recaudado;
    private LocalDateTime fechaCreacion;
    private Double cartera;
    /**
     * vacio
     */
    public CongresoModel() {
    }
    
    /**
     * Congreso creado 
     * @param idCongreso auto
     * @param codigo cod
     * @param nombre congreso
     * @param descripcion del congreso
     * @param fechaInicio dais
     * @param fechaFin dias
     * @param lugar donde se realiza
     * @param precio de inscripcion
     * @param fechaCreacion de congreso
     * @param porcentajeGanancia dad en su cosntruccion
     */
    public CongresoModel(Long idCongreso, String codigo, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, String lugar, Double precio, LocalDateTime fechaCreacion,Double porcentajeGanancia) {
        this.idCongreso = idCongreso;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.lugar = lugar;
        this.precio = precio;
        this.fechaCreacion = fechaCreacion;
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public Long getIdCongreso() {
        return idCongreso;
    }

    public void setIdCongreso(Long idCongreso) {
        this.idCongreso = idCongreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(Double porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public Double getRecaudado() {
        return recaudado;
    }

    public void setRecaudado(Double recaudado) {
        this.recaudado = recaudado;
    }

    public Double getCartera() {
        return cartera;
    }

    public void setCartera(Double cartera) {
        this.cartera = cartera;
    }
    
    public boolean esValido() {
    return codigo != null && !codigo.isBlank()
            && nombre != null && !nombre.isBlank()
            && descripcion != null && !descripcion.isBlank()
            && fechaInicio != null
            && fechaFin != null
            && !fechaFin.isBefore(fechaInicio) // La fecha de fin no puede ser antes de inicio
            && lugar != null && !lugar.isBlank()
            && precio != null && precio >= 35.0 // precio mínimo según tu constraint
            && porcentajeGanancia != null;
}

    @Override
    public String toString() {
        return "CongresoModel{" + "idCongreso=" + idCongreso + ", codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", lugar=" + lugar + ", precio=" + precio + ", porcentajeGanancia=" + porcentajeGanancia + ", recaudado=" + recaudado + ", fechaCreacion=" + fechaCreacion + '}';
    }
    
}
