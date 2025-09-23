/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Organizacion;

import Exceptions.EntityNotFoundException;
import connection.OrganizacionesDB;
import java.util.List;

/**
 *
 * @author andy
 */
public class ConsultarOrganizacion {
    public List<OrganizacionModel> obtenerTodasLasOrganizaciones() throws EntityNotFoundException {
        OrganizacionesDB organizacionesDB = new OrganizacionesDB();
        return organizacionesDB.obtenerTodasLasOrganizaciones();
    }
}
