/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Actividades;

/**
 *
 * @author andy
 */
public class TallerModel {
    private Long idActividad;
    private Integer cupoMaximo;
    /**
     * Taller
     * @param idActividad a la que perteace
     * @param cupoMaximo por taller
     */
    public TallerModel(Long idActividad, Integer cupoMaximo) {
        this.idActividad = idActividad;
        this.cupoMaximo = cupoMaximo;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    @Override
    public String toString() {
        return "TallerModel{" + "idActividad=" + idActividad + ", cupoMaximo=" + cupoMaximo + '}';
    }
    
    
}
