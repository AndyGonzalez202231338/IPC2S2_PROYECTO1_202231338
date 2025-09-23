/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Usuarios;

import Exceptions.EntityNotFoundException;
import connection.UsuariosDB;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author andy
 */
public class ConsultarUsuario {
    /**
     * Lista de todos los usuarios sin filtro
     * @return una lsita
     */
    public List<UsuarioModel> obtenerTodosLosUsuarios(){
        UsuariosDB usuarioDB = new UsuariosDB();
        
        return usuarioDB.obtenerTodosLosUsuarios();
    }
    
    /**
     * Obtener un suario por 
     * @param correo personal
     * @return un usuario
     * @throws EntityNotFoundException no exsite
     */
    public UsuarioModel obtenerCongresoPorCodigo(String correo) throws EntityNotFoundException{
        System.out.println("    obtener el congreso");
        UsuariosDB usuarioDB = new UsuariosDB();
        Optional<UsuarioModel> usuarioOpt = usuarioDB.obtenerUsuarioPorCodigo(correo);
        if(usuarioOpt.isEmpty()){
            throw new EntityNotFoundException(
                    String.format("El Usuario con correo %s no existe", correo)
            );
        }
        return usuarioOpt.get();
    }
    /**
     * buscar por id
     * @param idUsuario id creado en mysal
     * @return objeto usairo
     * @throws EntityNotFoundException no existe
     */
    public UsuarioModel obtenerUsuarioPorIdUsuario(Long idUsuario) throws EntityNotFoundException{
        System.out.println("    obtener el El usaurio dsaf");
        UsuariosDB usuarioDB = new UsuariosDB();
        UsuarioModel usuario = usuarioDB.obtenerUsuarioPorIdUsuario(idUsuario);
        if(usuario == null){
            throw new EntityNotFoundException(
                    String.format("El Usuario con id %s no existe", idUsuario)
            );
        }
        return usuario;
    }
    
    
    
}
