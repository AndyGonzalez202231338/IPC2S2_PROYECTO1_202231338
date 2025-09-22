/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Participacion;

import Exceptions.CongresoDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import Exceptions.ParticipacionDataInvalidException;
import Exceptions.UserDataInvalidException;
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
import model.Congresos.CongresoModel;
import model.Congresos.ConsultarCongreso;
import model.Participacion.CreadorParticipaciones;
import model.Participacion.ParticipacionModel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author andy
 */
@WebServlet(name = "ParticipacionServlet", urlPatterns = {"/ParticipacionServlet"})
public class ParticipacionServlet extends HttpServlet {

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
        ConsultarCongreso consultarCongresos = new ConsultarCongreso();
        String idCongresoStr = request.getParameter("idCongreso");
                Long idCongreso = Long.valueOf(idCongresoStr);
            try {
                
                CongresoModel congreso = consultarCongresos.obtenerCongresoPorIdCongreso(idCongreso);
                request.setAttribute("congreso", congreso);
            } catch (EntityNotFoundException e) {
                request.setAttribute("error", e.getMessage());
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Participacion/participacion.jsp");
            dispatcher.forward(request, response);
        

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
        CreadorParticipaciones creadorParticipacion = new CreadorParticipaciones();

        try {
            ParticipacionModel participacionCreada = creadorParticipacion.crearParticipacion(request);

            request.setAttribute("participacion", participacionCreada);
        } catch (ParticipacionDataInvalidException | EntityAlreadyExistsException | EntityNotFoundException | UserDataInvalidException | CongresoDataInvalidException e) {
            request.setAttribute("error", e.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Participacion/participacion.jsp");
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
    
    private boolean obtenerTodos(HttpServletRequest request) {
        return StringUtils.isBlank(request.getParameter("lista"));
    }

}
