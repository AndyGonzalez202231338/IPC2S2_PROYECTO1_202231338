/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.Congresos.ConsultarCongreso;
import model.Usuarios.ConsultarUsuario;
import model.Usuarios.UsuarioModel;

/**
 *
 * @author andy
 */
@WebServlet(name = "VerUsuarioServlet", urlPatterns = {"/VerUsuarioServlet"})
public class VerUsuarioServlet extends HttpServlet {

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
        System.out.println("Ver congreso");
        String correo = request.getParameter("correo");
        ConsultarUsuario consultar = new ConsultarUsuario();
        try {
            UsuarioModel usuario = consultar.obtenerCongresoPorCodigo(correo);
            request.setAttribute("usuario", usuario);
            System.out.println("el usuario se mando al jsp");
            request.getRequestDispatcher("/Usuario/detalle-usuario.jsp").forward(request, response);
        } catch (Exception e){
            System.out.println("no se ecnontro");
            request.setAttribute("error", "No se encontró el Usuario con Correo " + correo);
            request.getRequestDispatcher("/Usuario/lista-usuarios.jsp").forward(request, response);
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
