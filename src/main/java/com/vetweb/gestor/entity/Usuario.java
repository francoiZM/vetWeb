package com.vetweb.gestor.entity;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;  
import java.util.Set;

import jakarta.persistence.*;
import jakarta.annotation.PreDestroy;
import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name = "usuarios")
public class Usuario {

    //constructor vacio
    public Usuario() {
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 12)
    private String rut;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String apellido;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    @Column(nullable = false)
    private Boolean activo = true;


    
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
        (String nombre, String apellido, String email, String password, Set<Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.activo = true;
     

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

    public void setRol (String rol) {
       
        this.updatedAt = new Date();
    }

    //getters y setters faltantes
    public Set<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
        this.updatedAt = new Date();
    }
    public Boolean isActivo() {
        return activo;
        
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
        this.updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.updatedAt = new Date();
    }

    public void addRol(Rol rol) {
        this.roles.add(rol);
        rol.getUsuarios().add(this);
    }

    public void removeRol(Rol rol) {
        this.roles.remove(rol);
        rol.getUsuarios().remove(this);
        this.updatedAt = new Date();
    }

    public String getUsername() {
        return this.email;
    }

    //metodos override
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", activo=" + activo +
                ", roles=" + roles.size() +
                '}';

    }









    
}
