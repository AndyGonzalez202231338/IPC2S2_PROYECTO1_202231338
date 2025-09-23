/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Usuario;

import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import Exceptions.UserDataInvalidException;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import model.Organizacion.ConsultarOrganizacion;
import model.Organizacion.OrganizacionModel;
import org.apache.commons.lang3.StringUtils;
import model.Usuarios.ConsultarUsuario;
import model.Usuarios.CreadorUsuarios;
import model.Usuarios.UsuarioModel;

/**
 *
 * @author andy
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {


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
        ConsultarUsuario consultaUsuarios = new ConsultarUsuario();
        if(obtenerTodos(request)){
            //List<UsuarioModel> lista = consultaUsuarios.obtenerTodosLosUsuarios();
            request.setAttribute("usuarios", consultaUsuarios.obtenerTodosLosUsuarios());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Usuario/lista-usuarios.jsp");
            dispatcher.forward(request, response);
        } else{
            try{
                UsuarioModel usuario = consultaUsuarios.obtenerCongresoPorCodigo(request.getParameter("correo"));
                request.setAttribute("usuario", usuario);
                ConsultarOrganizacion consultarOrganizacion = new ConsultarOrganizacion();
                List<OrganizacionModel> organizaciones = consultarOrganizacion.obtenerTodasLasOrganizaciones();
                request.setAttribute("organizaciones", organizaciones);
            } catch (EntityNotFoundException e){
                request.setAttribute("error", e.getMessage());
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Usuario/usuario-actualizar.jsp");
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
        System.out.println("        DoPost Usuario");
        CreadorUsuarios creadorUsuario = new CreadorUsuarios();
        
        try{
            UsuarioModel usaurioCreado = creadorUsuario.crearUsuario(request);
            request.setAttribute("usuarioCreado", usaurioCreado);
            
        } catch (UserDataInvalidException | EntityAlreadyExistsException e){
            request.setAttribute("error", e.getMessage());
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Usuario/crear-usuario.jsp");
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
    
    private boolean obtenerTodos(HttpServletRequest request){
        return StringUtils.isBlank(request.getParameter("correo"));
    }

}
