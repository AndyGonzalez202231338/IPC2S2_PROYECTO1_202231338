/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Organizacion;

/**
 *
 * @author andy
 */
public class OrganizacionModel {
    private Long idOrganizacion;
    private String nombre;
/**
 * instituciones
 * @param idOrganizacion atuo
 * @param nombre de la institucion
 */
    public OrganizacionModel(Long idOrganizacion, String nombre) {
        this.idOrganizacion = idOrganizacion;
        this.nombre = nombre;
    }

    public Long getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(Long idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean esValidoParaCrear() {
        return nombre != null && !nombre.isBlank();
                
    }

    @Override
    public String toString() {
        return "OrganizacionModel{" + "idOrganizacion=" + idOrganizacion + ", nombre=" + nombre + '}';
    }
    
    
}
