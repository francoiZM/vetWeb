package com.vetweb.gestor.dao;

import com.vetweb.gestor.entity.Cita;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface iCitaDao extends CrudRepository<Cita, Long> {
    
   
    List<Cita> findByTutorId(Long tutorId);
    
   
    List<Cita> findByVeterinarioId(Long veterinarioId);
    
    
    List<Cita> findByVeterinarioIdAndFechaHoraBetween(Long veterinarioId, LocalDateTime inicio, LocalDateTime fin);
    
   
    List<Cita> findByVeterinarioIdAndFechaHora(Long veterinarioId, LocalDateTime fechaHora);
    
  
    List<Cita> findByEstado(String estado);
}
