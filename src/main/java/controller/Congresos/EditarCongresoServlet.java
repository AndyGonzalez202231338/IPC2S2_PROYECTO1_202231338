/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Congresos;

import Exceptions.CongresoDataInvalidException;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import model.Congresos.ActualizadorCongreso;

/**
 *
 * @author andy
 */
@WebServlet(name = "EditarCongresoServlet", urlPatterns = {"/EditarCongresoServlet"})
public class EditarCongresoServlet extends HttpServlet {

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
    ActualizadorCongreso actualizadorCongresos = new ActualizadorCongreso();

    try {
        CongresoModel congresoActualizado = actualizadorCongresos.actualizar(request);
        request.setAttribute("mensajeExito", "Congreso actualizado correctamente.");
        request.setAttribute("congreso", congresoActualizado);
    } catch (CongresoDataInvalidException | EntityNotFoundException e) {
        request.setAttribute("error", e.getMessage());

        // También reenviamos el congreso con los datos ya cargados
        // para que no se borren del formulario al mostrar el error
        CongresoModel congresoConDatos = null;
        try {
            congresoConDatos = new CongresoModel(
                null,
                request.getParameter("codigo"),
                request.getParameter("nombre"),
                request.getParameter("descripcion"),
                LocalDate.parse(request.getParameter("fechaInicio")),
                LocalDate.parse(request.getParameter("fechaFin")),
                request.getParameter("lugar"),
                Double.valueOf(request.getParameter("precio")),
                LocalDateTime.now()
            );
        } catch (Exception ignored) {}
        request.setAttribute("congreso", congresoConDatos);
    }

    // 🔹 Aquí usamos forward, no redirect
    RequestDispatcher dispatcher = request.getRequestDispatcher("/Congreso/congreso-actualizar.jsp");
    dispatcher.forward(request, response);
}

}
