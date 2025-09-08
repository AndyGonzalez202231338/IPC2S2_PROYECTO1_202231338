/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Congresos;

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

            request.setAttribute("congresoActualizado", congresoActualizado);
        } catch (UserDataInvalidException | EntityNotFoundException e){
            request.setAttribute("error", e.getMessage());
        }
            
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Congreso/congreso-actualizado.jsp");
        //dispatcher.forward(request, response);
        response.sendRedirect(request.getContextPath() + "/CongresoServlet");

    }
}
