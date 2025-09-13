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
import jakarta.servlet.http.HttpSession;

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

        System.out.println("correo recibido: " + correo);
        System.out.println("contraseña recibida: " + password);

        try {
            // Buscar usuario en DB por correo
            UsuarioModel usuario = usuariosDB.buscarPorCorreo(correo);

            if (usuario == null) {
                // Caso 1: correo no existe
                System.out.println("No existe un usuario con ese correo");
                request.setAttribute("mensajeError", "El correo no está registrado.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);

            } else if (usuario.getContrasena() == null || !usuario.getContrasena().equals(password)) {
                // Caso 2: contraseña incorrecta o null
                System.out.println("Contraseña incorrecta para el usuario: " + correo);
                request.setAttribute("mensajeError", "La contraseña es incorrecta.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);

            } else {
                // Caso 3: autenticación correcta
                System.out.println("Usuario autenticado correctamente: " + correo);
                // Guardamos en sesión el usuario
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogueado", usuario);
                request.getRequestDispatcher("/Home/home-admin.jsp").forward(request, response);
                
            }

        } catch (Exception ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, "Error en login", ex);

            // Mensaje genérico para el usuario
            request.setAttribute("mensajeError", "Ha ocurrido un error al procesar el inicio de sesión. Intente más tarde.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet de login para validar credenciales de Usuario";
    }
}
