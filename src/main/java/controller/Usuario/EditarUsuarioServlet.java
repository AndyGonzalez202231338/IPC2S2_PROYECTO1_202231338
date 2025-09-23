/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Usuario;

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

@WebServlet(name = "EditarUsuarioServlet", urlPatterns = {"/EditarUsuarioServlet"})
@MultipartConfig(maxFileSize = 5000000) // ~5MB
public class EditarUsuarioServlet extends HttpServlet {

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
    ActualizadorUsuario actualizadorUsuarios = new ActualizadorUsuario();

    try {
        UsuarioModel usuarioActualizado = actualizadorUsuarios.actualizar(request);

        // Mensaje de éxito
        request.setAttribute("mensajeExito", "Usuario actualizado correctamente.");
        request.setAttribute("usuario", usuarioActualizado);

    } catch (UserDataInvalidException | EntityNotFoundException e) {
        // Mensaje de error
        request.setAttribute("error", e.getMessage());
        
        // Para no perder los datos ingresados si hubo error
        UsuarioModel usuarioConDatos = new UsuarioModel(
            request.getParameter("nombreCompleto"),
            request.getParameter("correo"),
            request.getParameter("telefono"),
            request.getParameter("numeroIdentificacion"),
            request.getParameter("fechaRegistro"),
            request.getParameter("tipoCuenta"),
            Long.valueOf(request.getParameter("organizacion"))
        );
        request.setAttribute("usuario", usuarioConDatos);
    }

    // 🔹 Forward en vez de Redirect
    RequestDispatcher dispatcher = request.getRequestDispatcher("/Usuario/usuario-actualizar.jsp");
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
