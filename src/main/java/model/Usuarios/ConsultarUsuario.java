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
    public List<UsuarioModel> obtenerTodosLosUsuarios(){
        UsuariosDB usuarioDB = new UsuariosDB();
        
        return usuarioDB.obtenerTodosLosUsuarios();
    }
    
    public UsuarioModel obtenerCongresoPorCodigo(String correo) throws EntityNotFoundException{
        UsuariosDB usuarioDB = new UsuariosDB();
        Optional<UsuarioModel> usuarioOpt = usuarioDB.obtenerUsuarioPorCodigo(correo);
        if(usuarioOpt.isEmpty()){
            throw new EntityNotFoundException(
                    String.format("El Usuario con correo %s no existe", correo)
            );
        }
        return usuarioOpt.get();
    }
    
}
