package com.vetweb.gestor.entity;
import java.util.Date;

public class Usuario {
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;
    private Date createdAt;
    private Date updatedAt;

    public Usuario
        (String nombre, String apellido, String email, String password, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rol = rol;

    }

    // Getters and Setters

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setRut(String rut) {
        this.rut = rut;
        this.updatedAt = new Date();
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.updatedAt = new Date();
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
        this.updatedAt = new Date();
    }
    public void setPassword(String password) {
        this.password = password;
        this.updatedAt = new Date();
    }
    public void setEmail(String email) {
        this.email = email;
        this.updatedAt = new Date();
    }
    public void setRol(String rol) {
        this.rol = rol;
        this.updatedAt = new Date();
    }
    
}
