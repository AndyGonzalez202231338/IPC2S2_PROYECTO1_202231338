/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Participacion;

/**
 *
 * @author andy
 */
public class ParticipanteDTO {

    private Integer idUsuario;
    private String nombreCompleto;
    private String correo;
    private String tipoParticipacion;

    public ParticipanteDTO(Integer idUsuario, String nombreCompleto, String correo, String tipoParticipacion) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.tipoParticipacion = tipoParticipacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipoParticipacion() {
        return tipoParticipacion;
    }

}
