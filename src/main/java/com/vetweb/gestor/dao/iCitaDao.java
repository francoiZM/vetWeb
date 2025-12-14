package com.vetweb.gestor.dao;

import com.vetweb.gestor.entity.Cita;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface iCitaDao extends CrudRepository<Cita, Long> {
    
    // Buscar citas por tutor
    List<Cita> findByTutorId(Long tutorId);
    
    // Buscar citas por veterinario
    List<Cita> findByVeterinarioId(Long veterinarioId);
    
    // Buscar citas por veterinario en un rango de fechas
    List<Cita> findByVeterinarioIdAndFechaHoraBetween(Long veterinarioId, LocalDateTime inicio, LocalDateTime fin);
    
    // Buscar citas por veterinario y fecha/hora específica
    List<Cita> findByVeterinarioIdAndFechaHora(Long veterinarioId, LocalDateTime fechaHora);
    
    // Buscar citas por estado
    List<Cita> findByEstado(String estado);
}
