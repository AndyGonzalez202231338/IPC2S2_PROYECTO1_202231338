/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Actividades;

import Exceptions.EntityNotFoundException;
import Exceptions.UserDataInvalidException;
import model.Congresos.CongresoModel;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Actividades.ActividadModel;
import model.Actividades.ConsultarActividad;
import model.Congresos.ActualizadorCongreso;
import model.Congresos.ConsultarCongreso;
import model.Salones.ConsultarSalon;
import model.Salones.SalonModel;
import model.Usuarios.ConsultarUsuario;
import model.Usuarios.UsuarioModel;

/**
 *
 * @author andy
 */
@WebServlet(name = "VerActividadServlet", urlPatterns = {"/VerActividadServlet"})
public class VerActividadServlet extends HttpServlet {

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
        String codigo = request.getParameter("codigo");
        ConsultarCongreso consultar = new ConsultarCongreso();
        try {

            CongresoModel congreso = consultar.obtenerCongresoPorCodigo(codigo);
            request.setAttribute("congreso", congreso);
            ConsultarActividad consultarActividades = new ConsultarActividad();
            List<ActividadModel> actividades = consultarActividades.obtenerTodasLasActividades(congreso.getIdCongreso());
            ConsultarSalon consultarSalones = new ConsultarSalon();
            ConsultarUsuario consultarUsuarios = new ConsultarUsuario();
            for (ActividadModel act : actividades) {
                SalonModel salon = consultarSalones.obtenerSalonPorIdSalon(act.getIdSalon());
                act.setSalon(salon);
                UsuarioModel usuario = consultarUsuarios.obtenerUsuarioPorIdUsuario(act.getCreadoPor());
                act.setPonente(usuario);
            }

            request.setAttribute("actividades", actividades);
            request.getRequestDispatcher("/Actividad/crear-actividad.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("no se ecnontro");
            request.setAttribute("error", "No se encontró el congreso con código " + codigo);
            request.getRequestDispatcher("/Congreso/lista-congresos.jsp").forward(request, response);
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
