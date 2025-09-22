/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Actividades;

import Exceptions.ActividadDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Actividades.ActividadModel;
import model.Actividades.ConsultarActividad;
import model.Actividades.CreadorActividades;


/**
 *
 * @author andy
 */
@WebServlet(name = "ActividadServlet", urlPatterns = {"/ActividadServlet"})
public class ActividadServlet extends HttpServlet {

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
        ConsultarActividad consultaActividades = new ConsultarActividad();
        if (obtenerTodos(request)) {
            //List<ActividadModel> lista = consultaActividades.obtenerTodasLasActividades();
            //System.out.println("Cantidad de congresos encontrados: " + lista.size());
//request.setAttribute("congresos", lista);

            //request.setAttribute("congresos", consultaActividades.obtenerTodasLasActividades());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Congreso/lista-congresos.jsp");
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
        CreadorActividades creadorActividades = new CreadorActividades();
        String codigo = request.getParameter("idCongreso");
        try {
            ActividadModel actividadCreada = creadorActividades.crearEvento(request);

            request.setAttribute("actividadCreada", actividadCreada);
        } catch (ActividadDataInvalidException | EntityNotFoundException | EntityAlreadyExistsException e) {
            request.setAttribute("error", e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/VerActividadServlet?codigo=" + codigo);
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
        System.out.println("entro a obtenerTodos los congresos");
        return StringUtils.isBlank(request.getParameter("codigo"));
    }
}
