/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Usuarios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que modela un objeto usuario
 * @author andy
 */
public class UsuarioModel {
    private Integer id;
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String organizacion;
    private String numeroIdentificacion;
    private byte[] foto;
    private String contrasena;
    private LocalDateTime fechaRegistro;
    private String tipoCuenta;
    private Double cartera;

    /**
     * Crear un objeto modelo
     * @param nombreCompleto del usuario
     * @param correo del usuario
     * @param telefono numero
     * @param organizacion o institucion de donde pertenece
     * @param numeroIdentificacion puede ser extranjero
     * @param contrasena de usuario
     * @param tipoCuenta  Aministrador, participante
     */
    public UsuarioModel(String nombreCompleto, String correo, String telefono, String organizacion, String numeroIdentificacion, String contrasena, String tipoCuenta) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.organizacion = organizacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.contrasena = contrasena;
        this.tipoCuenta = tipoCuenta;
    }

    public UsuarioModel(Integer id, String nombreCompleto, String correo, String telefono, String organizacion, String numeroIdentificacion, String contrasena, LocalDateTime fechaRegistro, String tipoCuenta) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.organizacion = organizacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.tipoCuenta = tipoCuenta;
    }
    
    
    
    
    /**
     * constructor vacio para recribir datos
     */
    public UsuarioModel() {
}
    

    public Integer getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }


    public String getContrasena() {
        return contrasena;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Double getCartera() {
        return cartera;
    }

    public void setCartera(Double cartera) {
        this.cartera = cartera;
    }


    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    @Override
    public String toString() {
        return "UsuarioModel{" + "id=" + id + ", nombreCompleto=" + nombreCompleto + ", correo=" + correo + ", telefono=" + telefono + ", organizacion=" + organizacion + ", numeroIdentificacion=" + numeroIdentificacion + ", foto=" + foto + ", contrasena=" + contrasena + ", fechaRegistro=" + fechaRegistro + ", tipoCuenta=" + tipoCuenta + ", cartera=" + cartera + '}';
    }

    
    
    /**
     * Utiliza el LocalDateTime para converitrlo en texto legible con un orden
     * @return texto de la fecha y hora
     */
    public String getFechaRegistroFormatted() {
        if (fechaRegistro == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaRegistro.format(formatter);
    }
    
    
}
