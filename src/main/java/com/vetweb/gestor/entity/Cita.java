package com.vetweb.gestor.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Usuario veterinario;

    @ManyToOne
    @JoinColumn(name = "tutor_id", nullable = false)
    private Usuario tutor;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(length = 20, nullable = false)
    private String estado; // PENDIENTE, CONFIRMADA, CANCELADA, COMPLETADA

    @Column(length = 500)
    private String motivo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructor vacío
    public Cita() {
    }

    // Constructor completo
    public Cita(Usuario veterinario, Usuario tutor, Mascota mascota, LocalDateTime fechaHora, String estado, String motivo) {
        this.veterinario = veterinario;
        this.tutor = tutor;
        this.mascota = mascota;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.motivo = motivo;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (estado == null) {
            estado = "PENDIENTE";
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Usuario veterinario) {
        this.veterinario = veterinario;
    }

    public Usuario getTutor() {
        return tutor;
    }

    public void setTutor(Usuario tutor) {
        this.tutor = tutor;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", veterinario=" + (veterinario != null ? veterinario.getNombre() : "null") +
                ", tutor=" + (tutor != null ? tutor.getNombre() : "null") +
                ", mascota=" + (mascota != null ? mascota.getNombre() : "null") +
                ", fechaHora=" + fechaHora +
                ", estado='" + estado + '\'' +
                '}';
    }
}
