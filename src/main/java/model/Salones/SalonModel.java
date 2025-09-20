/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Salones;

/**
 *
 * @author andy
 */
public class SalonModel {
    private Long idSalon;
    private Long idCongreso;
    private String nombreSalon;
    private String ubicacion;
    private Integer capacidad;
    private String recursos;
    private String nuevoNombreSalon;

    public SalonModel(Long idSalon, Long idCongreso, String nombreSalon, String ubicacion, Integer capacidad, String recursos) {
        this.idSalon = idSalon;
        this.idCongreso = idCongreso;
        this.nombreSalon = nombreSalon;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.recursos = recursos;
    }

    public Long getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(Long idSalon) {
        this.idSalon = idSalon;
    }

    public Long getIdCongreso() {
        return idCongreso;
    }

    public void setIdCongreso(Long idCongreso) {
        this.idCongreso = idCongreso;
    }

    public String getNombreSalon() {
        return nombreSalon;
    }

    public void setNombreSalon(String nombreSalon) {
        this.nombreSalon = nombreSalon;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }
    
    public boolean esValidoParaCrear() {
        return idCongreso != null && idCongreso > 0
                && nombreSalon != null && !nombreSalon.isBlank()
                && ubicacion != null && !ubicacion.isBlank()
                && capacidad != null && capacidad >= 10
                && recursos != null && !recursos.isBlank();
    }

    public boolean esValidoParaActualizar() {
        return idSalon != null && idSalon > 0 && esValidoParaCrear();
    }

    public String getNuevoNombreSalon() {
        return nuevoNombreSalon;
    }

    public void setNuevoNombreSalon(String nuevoNombreSalon) {
        this.nuevoNombreSalon = nuevoNombreSalon;
    }

    @Override
    public String toString() {
        return "SalonModel{" + "idSalon=" + idSalon + ", idCongreso=" + idCongreso + ", nombreSalon=" + nombreSalon + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + ", recursos=" + recursos + '}';
    }
    
    
}
