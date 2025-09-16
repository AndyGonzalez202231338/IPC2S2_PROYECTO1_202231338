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
import model.Usuarios.ConsultarUsuario;
import model.Usuarios.IngresarDinero;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author andy
 */
@WebServlet(name = "DineroUsuarioServlet", urlPatterns = {"/AgregarDineroServlet"})
public class AgregarDineroServlet extends HttpServlet {


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
        System.out.println("coreo"+request.getParameter("correo"));
        ConsultarUsuario consultaUsuarios = new ConsultarUsuario();
            try{
                UsuarioModel usuario = consultaUsuarios.obtenerCongresoPorCodigo(request.getParameter("correo"));
                request.setAttribute("usuario", usuario);
            } catch (EntityNotFoundException e){
                request.setAttribute("error", e.getMessage());
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Usuario/Cartera.jsp");
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
        
        // Supongamos que el correo del usuario viene de un input hidden o sesión
        String correo = request.getParameter("correo");
        if (correo == null || correo.isEmpty()) {
            request.setAttribute("error", "No se pudo identificar al usuario.");
            request.getRequestDispatcher("/Usuario/Cartera.jsp").forward(request, response);
            return;
        }

        try {
            // Se usa la clase que maneja la lógica
            IngresarDinero ingresar = new IngresarDinero();
            UsuarioModel usuarioActualizado = ingresar.ingresarDineroUsuario(request);

            request.setAttribute("usuario", usuarioActualizado);
            request.setAttribute("success", "Dinero agregado correctamente: Q" + request.getParameter("monto"));
        } catch (UserDataInvalidException | EntityNotFoundException e) {
            request.setAttribute("error", e.getMessage());
        }

        // Redirige al mismo JSP de cartera
        request.getRequestDispatcher("/Usuario/Cartera.jsp").forward(request, response);
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
    
    private boolean obtenerTodos(HttpServletRequest request){
        return StringUtils.isBlank(request.getParameter("correo"));
    }

}
