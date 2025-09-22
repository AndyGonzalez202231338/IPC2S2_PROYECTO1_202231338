/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Participacion;

import Exceptions.CongresoDataInvalidException;
import Exceptions.EntityAlreadyExistsException;
import Exceptions.EntityNotFoundException;
import Exceptions.ParticipacionDataInvalidException;
import Exceptions.UserDataInvalidException;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Congresos.CongresoModel;
import model.Congresos.ConsultarCongreso;
import model.Participacion.ConsultarParticipaciones;
import model.Participacion.CreadorParticipaciones;
import model.Participacion.ParticipacionModel;
import model.Participacion.ParticipanteDTO;
import model.Usuarios.ConsultarUsuario;
import model.Usuarios.UsuarioModel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author andy
 */
@WebServlet(name = "VerParticipanteServlet", urlPatterns = {"/VerParticipanteServlet"})
public class VerParticipanteServlet extends HttpServlet {

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
        ConsultarParticipaciones consultarParticipantes = new ConsultarParticipaciones();
        ConsultarUsuario consultarUsuarios = new ConsultarUsuario();
        ConsultarCongreso consultarCongreso = new ConsultarCongreso();
        try {
            CongresoModel congreso = consultarCongreso.obtenerCongresoPorIdCongreso(Long.valueOf(request.getParameter("idCongreso")));
            List<ParticipacionModel> participaciones = consultarParticipantes.obtenerParticipantesPorIdCongreso(Long.valueOf(request.getParameter("idCongreso")));

            List<ParticipanteDTO> participantesDTO = new ArrayList<>();

            for (ParticipacionModel p : participaciones) {
                UsuarioModel usuario = consultarUsuarios.obtenerUsuarioPorIdUsuario(p.getIdUsuario());
                participantesDTO.add(new ParticipanteDTO(
                        usuario.getId(),
                        usuario.getNombreCompleto(),
                        usuario.getCorreo(),
                        p.getTipoParticipacion() // viene directo de la tabla Participacion
                ));
            }

            request.setAttribute("participantes", participantesDTO);
            request.setAttribute("congreso", congreso);
            request.getRequestDispatcher("/Participacion/lista-participantes.jsp").forward(request, response);
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(VerParticipanteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
