/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UsuarioModel;
import persistencia.UsuarioDAO;

/**
 *
 * @author andy
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {
    UsuarioDAO UsuarioDAO= new UsuarioDAO();
    

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

    String correo = request.getParameter("correo");
    String password = request.getParameter("contrasena");
    System.out.println("correo: " + correo);
    System.out.println("contraseña: " + password);

    UsuarioModel usuario;
    
    try {
    usuario = UsuarioDAO.findById(correo);
    if (usuario != null 
            && usuario.getContrasena().equals(password) 
            && usuario.getCorreo().equals(correo)) {
        
        // Mensaje de éxito (puedes guardarlo en sesión si quieres mostrar en home)
        request.getSession().setAttribute("usuario", usuario);
        request.setAttribute("mensajeExito", "¡Inicio de sesión exitoso!");
        if(usuario.getTipoCuenta().equals("ADMIN")){
            request.getRequestDispatcher("/Home/home-admin.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/Home/home-admin.jsp").forward(request, response);
        }
        
        
    } else {
        // Mensaje de error
        request.setAttribute("mensajeError", "Correo o contraseña incorrectos.");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
} catch (SQLException ex) {
    Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
    throw new ServletException("Error en la base de datos", ex);
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

}
