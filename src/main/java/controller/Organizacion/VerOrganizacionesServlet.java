/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Organizacion;

import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import Exceptions.UserDataInvalidException;
import connection.OrganizacionesDB;
import model.Congresos.CongresoModel;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import model.Congresos.ActualizadorCongreso;
import model.Usuarios.ActualizadorUsuario;
import model.Usuarios.UsuarioModel;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.util.List;
import model.Organizacion.ActualizadorOrganizacion;
import model.Organizacion.ConsultarOrganizacion;
import model.Organizacion.CreadorOrganizaciones;
import model.Organizacion.OrganizacionModel;

/**
 *
 * @author andy
 */
@WebServlet(name = "VerOrganizacionesServlet", urlPatterns = {"/VerOrganizacionesServlet"})
public class VerOrganizacionesServlet extends HttpServlet {

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
        ConsultarOrganizacion consultarOrganizacion = new ConsultarOrganizacion();
        try{
                List<OrganizacionModel> organizaciones = consultarOrganizacion.obtenerTodasLasOrganizaciones();
                request.setAttribute("organizaciones", organizaciones);
            } catch (EntityNotFoundException e){
                request.setAttribute("error", e.getMessage());
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Usuario/crear-usuario.jsp");
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
