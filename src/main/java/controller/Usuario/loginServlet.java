package controller.Usuario;

import connection.UsuariosDB;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Usuarios.UsuarioModel;


/**
 *
 * @author andy
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {
    
    private UsuariosDB usuariosDB = new UsuariosDB(); // usamos UsuariosDB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirigir al login si alguien entra por GET
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String password = request.getParameter("contrasena");

        System.out.println("correo: " + correo);
        System.out.println("contraseña: " + password);

        try {
            // Buscar usuario directamente en DB
            UsuarioModel usuario = usuariosDB.buscarPorCorreo(correo);

            if (usuario != null && usuario.getContrasena().equals(password)) {
                System.out.println("Usuario autenticado correctamente");

                if ("ADMIN".equalsIgnoreCase(usuario.getTipoCuenta())) {
                    request.getRequestDispatcher("/Home/home-admin.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/Home/home-participante.jsp").forward(request, response);
                }

            } else {
                // Usuario no encontrado o credenciales incorrectas
                request.setAttribute("mensajeError", "Correo o contraseña incorrectos.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException("Error al consultar usuario", ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet de login para validar credenciales de Usuario";
    }
}
