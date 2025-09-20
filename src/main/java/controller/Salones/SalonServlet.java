/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Salones;

import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import Exceptions.SalonDataInvalidException;
import Exceptions.UserDataInvalidException;
import model.Congresos.CongresoModel;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Congresos.ActualizadorCongreso;
import model.Congresos.ConsultarCongreso;
import model.Salones.ConsultarSalon;
import model.Salones.CreadorSalones;
import model.Salones.SalonModel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author andy
 */
@WebServlet(name = "SalonServlet", urlPatterns = {"/SalonServlet"})
public class SalonServlet extends HttpServlet {

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
        ConsultarSalon consultarSalones = new ConsultarSalon();
        ConsultarCongreso consultarCongresos = new ConsultarCongreso();
        try {
            System.out.println("Parametros de edicion");
            String codigo = request.getParameter("codigo");
            String nombreSalon = request.getParameter("nombreSalon");
            String congresoParam = request.getParameter("idCongreso");
            Long idCongreso = null;
            if (congresoParam != null && !congresoParam.isEmpty()) {
                idCongreso = Long.valueOf(congresoParam); // ✅ convierte el parámetro a Long
            }
            SalonModel salon = consultarSalones.obtenerSalonPorCongresoYNombre(idCongreso, nombreSalon);
            CongresoModel congreso = consultarCongresos.obtenerCongresoPorCodigo(codigo);
            
            request.setAttribute("congreso", congreso);
            request.setAttribute("salon", salon);
        } catch (EntityNotFoundException e) {
            request.setAttribute("error", e.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Salon/salon-actualizar.jsp");
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
        CreadorSalones creadorSalones = new CreadorSalones();
        try {
            SalonModel salonCreado = creadorSalones.crearSalon(request);
            //Long codigoCongreso = salonCreado.getIdCongreso();
            //String codigo = String.valueOf(codigoCongreso);

            // ✅ obtener el código del congreso desde el formulario
            String codigoCongreso = request.getParameter("codigo");
            System.out.println("cdoigo" + codigoCongreso);
            // ✅ Redirigir a VerSalonServlet con el código como parámetro
            response.sendRedirect(request.getContextPath() + "/VerSalonServlet?codigo=" + codigoCongreso);

        } catch (SalonDataInvalidException | EntityAlreadyExistsException e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Salon/form-salon.jsp");
            dispatcher.forward(request, response);
        }
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
