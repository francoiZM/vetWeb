package com.vetweb.gestor.service;

import com.vetweb.gestor.entity.Cita;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {
    Cita save(Cita cita);
    Cita update(Cita cita);
    void delete(Long id);
    List<Cita> findAll();
    Cita findById(Long id);
    List<Cita> findByTutorId(Long tutorId);
    List<Cita> findByVeterinarioId(Long veterinarioId);
    List<Cita> findByVeterinarioIdAndFechaHoraBetween(Long veterinarioId, LocalDateTime inicio, LocalDateTime fin);
    boolean isHorarioDisponible(Long veterinarioId, LocalDateTime fechaHora);
}
