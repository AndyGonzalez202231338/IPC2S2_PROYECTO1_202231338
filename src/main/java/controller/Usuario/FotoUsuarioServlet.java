/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Usuario;

import Exceptions.EntityNotFoundException;
import Exceptions.UserDataInvalidException;
import connection.UsuariosDB;
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

/**
 *
 * @author andy
 */
@WebServlet(name = "FotoUsuarioServlet", urlPatterns = {"/FotoUsuarioServlet"})
public class FotoUsuarioServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occuFotoUsuariors
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SE ENTRA A DOGAT FOTO ");
        String correo = request.getParameter("correo");
        UsuariosDB usuariosDB = new UsuariosDB();
        UsuarioModel usuario = usuariosDB.buscarPorCorreo(correo);

        if (usuario != null && usuario.getFoto() != null) {
            System.out.println("la foto si existe en la base de datos");
            response.setContentType("image/jpeg");
            response.getOutputStream().write(usuario.getFoto());
        } else {
            System.out.println("LA FOTO NO EXISTE");
            response.sendRedirect(request.getContextPath() + "/resources/default-user.png");
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
