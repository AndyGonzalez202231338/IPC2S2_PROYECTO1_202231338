/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Actividades;

import Exceptions.CongresoDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import Exceptions.ParticipacionDataInvalidException;
import Exceptions.UserDataInvalidException;
import connection.ActividadesDB;
import connection.UsuariosDB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Actividades.ConsultarInscripcionTaller;
import model.Actividades.CreadorInscipcionTaller;
import model.Actividades.InscripcionTallerModel;
import model.Congresos.CongresoModel;
import model.Congresos.ConsultarCongreso;
import model.Participacion.CreadorParticipaciones;
import model.Participacion.ParticipacionModel;
import model.Usuarios.UsuarioModel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author andy
 */
@WebServlet(name = "InscribirseTallerServlet", urlPatterns = {"/InscribirseTallerServlet"})
public class InscribirseTallerServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    System.out.println("SE PUDO ENTRAR A DOGET");
    CreadorInscipcionTaller creadorInscripcionTaller = new CreadorInscipcionTaller();
    ConsultarInscripcionTaller consultarInscripcion = new ConsultarInscripcionTaller();
    if(request.getParameter("listado")!=null){
        try {
            List<InscripcionTallerModel> inscritos = consultarInscripcion.obtenerTodosLosSalonesInscritosAlTaller(Long.valueOf(request.getParameter("idActividad")));
            UsuariosDB usuariosDB = new UsuariosDB();
            ActividadesDB actividadesDB = new ActividadesDB();
            for (InscripcionTallerModel inscrito : inscritos) {
                UsuarioModel usuario = usuariosDB.obtenerUsuarioPorIdUsuario(inscrito.getIdusuario());
                inscrito.setUsuario(usuario);
            }
            
            request.setAttribute("inscritosTaller", inscritos);
        } catch (EntityNotFoundException ex) {
            request.setAttribute("mensaje", "No se pudo encuentran inscripciones por la Actividad: " + ex.getMessage());
        }
    
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Actividad/inscritos.jsp");
        dispatcher.forward(request, response);
        
    } else{
        try {
        InscripcionTallerModel inscripcionTallerCreada = creadorInscripcionTaller.crearParticipacion(request);

        // mensaje de éxito
        request.setAttribute("mensaje", "Inscripción realizada con éxito en el taller.");
        request.setAttribute("participacion", inscripcionTallerCreada);

    } catch (ParticipacionDataInvalidException 
           | EntityAlreadyExistsException 
           | EntityNotFoundException 
           | UserDataInvalidException 
           | CongresoDataInvalidException e) {

        // mensaje de error
        request.setAttribute("mensaje", "No se pudo realizar la inscripción: " + e.getMessage());
    }

    // redirige al nuevo JSP para mostrar el resultado
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Actividad/inscripcion-resultado.jsp");
    dispatcher.forward(request, response);
    }
    
}


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SE PUEDO ENTRAR A POST");

        try {
            CreadorInscipcionTaller creadorInscripcionTaller = new CreadorInscipcionTaller();
            creadorInscripcionTaller.CrearAsistencia(request);
            
        } catch (ParticipacionDataInvalidException | EntityAlreadyExistsException | EntityNotFoundException | UserDataInvalidException | CongresoDataInvalidException e) {
            request.setAttribute("error", e.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Actividad/inscritos.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
