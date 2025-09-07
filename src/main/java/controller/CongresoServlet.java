/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Exceptions.CongresoDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import model.CongresoModel;
import model.ConsultarCongreso;
import model.CreadorCongresos;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author andy
 */
@WebServlet(name = "CongresoServlet", urlPatterns = {"/CongresoServlet"})
public class CongresoServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Se entro a doGet");
        ConsultarCongreso consultaCongresos = new ConsultarCongreso();
        if (obtenerTodos(request)) {
            List<CongresoModel> lista = consultaCongresos.obtenerTodosLosCongresos();
            System.out.println("Cantidad de congresos encontrados: " + lista.size());
//request.setAttribute("congresos", lista);

            request.setAttribute("congresos", consultaCongresos.obtenerTodosLosCongresos());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Congreso/lista-congresos.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.println("se entro por ");
            try {
                CongresoModel congreso = consultaCongresos.obtenerCongresoPorCodigo(request.getParameter("codigo"));
                request.setAttribute("congreso", congreso);
            } catch (EntityNotFoundException e) {
                request.setAttribute("error", e.getMessage());
            }
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/Congreso/congreso-actualizar.jsp");
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

        System.out.println("entro a crear el congreso a dopost");
        CreadorCongresos creadorCongresos = new CreadorCongresos();

        try {
            CongresoModel congresoCreado = creadorCongresos.crearEvento(request);

            request.setAttribute("congresoCreado", congresoCreado);
            System.out.println("devolvio un congreso creado");
        } catch (CongresoDataInvalidException | EntityAlreadyExistsException e) {
            request.setAttribute("error", e.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Congreso/congreso.jsp");
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
        System.out.println("entro a obtenerTodos los congresos");
        return StringUtils.isBlank(request.getParameter("codigo"));
    }
}
