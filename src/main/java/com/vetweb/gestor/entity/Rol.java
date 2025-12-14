package com.vetweb.gestor.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

/**
 * Roles definidos por el momento:
 * 
 * * ROLE_ADMIN: Acceso total al sistema.
 * * * ROLE_VETERINARIO: Acceso a la gestión de mascotas, citas y expedientes médicos.
 * * * ROLE_TUTOR: Acceso limitado a la gestión de sus propias mascotas y toma de citas.
 * 
 * 
 */

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios = new HashSet<>();

    // constructor
    public Rol() {

    }

    // constructor con nombre de rol
    public Rol(String nombre) {
        this.nombre = nombre;
    }

    //constructor completo
    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // todos los getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    //metodos override
    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rol)) return false;
        Rol rol = (Rol) o;
        return nombre != null && nombre.equals(rol.nombre);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    

}
