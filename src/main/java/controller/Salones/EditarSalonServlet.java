/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Salones;

import Exceptions.EntityNotFoundException;
import Exceptions.SalonDataInvalidException;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Congresos.CongresoModel;
import model.Congresos.ConsultarCongreso;
import model.Salones.ActualizadorSalon;
import model.Salones.SalonModel;

/**
 *
 * @author andy
 */
@WebServlet(name = "EditarSalonServlet", urlPatterns = {"/EditarSalonServlet"})
public class EditarSalonServlet extends HttpServlet {

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
        System.out.println("Salon de post actualizar");
        ActualizadorSalon actualizadorSalones = new ActualizadorSalon();
        ConsultarCongreso consultarCongresos = new ConsultarCongreso();
        try {
           String codigo = request.getParameter("codigo");
            CongresoModel congreso = consultarCongresos.obtenerCongresoPorCodigo(codigo);
            
            request.setAttribute("congreso", congreso);
            SalonModel salonActualizado = actualizadorSalones.actualizar(request);
            request.setAttribute("mensajeExito", "Salon actualizado correctamente.");
            request.setAttribute("salon", salonActualizado);
        } catch (SalonDataInvalidException | EntityNotFoundException e) {
            request.setAttribute("error", e.getMessage());

            String congresoParam = request.getParameter("idCongreso");
            Long idCongreso = null;
            if (congresoParam != null && !congresoParam.isEmpty()) {
                idCongreso = Long.valueOf(congresoParam); // ✅ convierte el parámetro a Long
            }

            SalonModel salonConDatos = null;
            try {
                salonConDatos = new SalonModel(
                        null,
                        idCongreso,
                        request.getParameter("nuevoNombreSalon"),
                        request.getParameter("ubicacion"),
                        Integer.valueOf(request.getParameter("capacidad")),
                        request.getParameter("recursos")
                );
            } catch (Exception ignored) {
            }
            request.setAttribute("salon", salonConDatos);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Salon/salon-actualizar.jsp");
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
