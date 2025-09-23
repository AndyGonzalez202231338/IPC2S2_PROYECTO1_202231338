/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Organizacion;

import Exceptions.EntityNotFoundException;
import Exceptions.UserDataInvalidException;
import connection.OrganizacionesDB;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author andy
 */
public class ActualizadorOrganizacion {
    public OrganizacionModel actualizar(HttpServletRequest request) throws UserDataInvalidException, EntityNotFoundException{
        OrganizacionesDB organizacionesDB = new OrganizacionesDB();

        OrganizacionModel organizacion = extraer(request);

        organizacionesDB.actualizarOrganizacion(organizacion);

        return organizacion;
    }

    private OrganizacionModel extraer(HttpServletRequest request) throws UserDataInvalidException {
    try {
        OrganizacionModel organizacion = new OrganizacionModel(
            request.getParameter("idOrganizacion") != null && !request.getParameter("idOrganizacion").isEmpty()
                ? Long.valueOf(request.getParameter("idOrganizacion"))
                : null,
            request.getParameter("nombre")
        );
        
        if (!organizacion.esValidoParaCrear()) {
                throw new UserDataInvalidException("Error en los datos enviados para la Organizacion");
            }
        
        return organizacion;
    } catch (IllegalArgumentException | NullPointerException e) {
        throw new UserDataInvalidException("Error en los datos enviados del usuario");
    }
}
}
