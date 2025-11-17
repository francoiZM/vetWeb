package com.vetweb.gestor.entity;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.OneToMany;



@Entity
@Table(name = "usuarios")
public class Usuario {
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Usuario() {}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;
    
    @Column(name = "created_at")
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date updatedAt;

    @OneToMany(mappedBy = "usuario", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    List<Mascota> mascotas = new ArrayList<>();

    public List<Mascota> getMascotas() { return mascotas; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
    @PreDestroy
    protected void onDestroy() {
       
    }   

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
