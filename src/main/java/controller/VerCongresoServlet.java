/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Exceptions.EntityNotFoundException;
import Exceptions.UserDataInvalidException;
import model.CongresoModel;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ActualizadorCongreso;
import model.ConsultarCongreso;
/**
 *
 * @author andy
 */
@WebServlet(name = "VerCongresoServlet", urlPatterns = {"/VerCongresoServlet"})
public class VerCongresoServlet extends HttpServlet {


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
            System.out.println("se encontro");
            request.setAttribute("congreso", congreso);
            request.getRequestDispatcher("/Congreso/detalle-congreso.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("no se ecnontro");
            request.setAttribute("error", "No se encontró el congreso con código " + codigo);
            request.getRequestDispatcher("/Congreso/lista-congresos.jsp").forward(request, response);
        }
    }

}
