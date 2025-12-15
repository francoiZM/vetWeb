package com.vetweb.gestor.service.impl;

import com.vetweb.gestor.dao.iCitaDao;
import com.vetweb.gestor.entity.Cita;
import com.vetweb.gestor.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private iCitaDao citaDao;

    @Override
    @Transactional
    public Cita save(Cita cita) {
        return citaDao.save(cita);
    }

    @Override
    @Transactional
    public Cita update(Cita cita) {
        return citaDao.save(cita);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        citaDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> findAll() {
        return (List<Cita>) citaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cita findById(Long id) {
        return citaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> findByTutorId(Long tutorId) {
        return citaDao.findByTutorId(tutorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> findByVeterinarioId(Long veterinarioId) {
        return citaDao.findByVeterinarioId(veterinarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> findByVeterinarioIdAndFechaHoraBetween(Long veterinarioId, LocalDateTime inicio, LocalDateTime fin) {
        return citaDao.findByVeterinarioIdAndFechaHoraBetween(veterinarioId, inicio, fin);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isHorarioDisponible(Long veterinarioId, LocalDateTime fechaHora) {
        
        DayOfWeek diaSemana = fechaHora.getDayOfWeek();
        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            return false;
        }

        
        LocalTime hora = fechaHora.toLocalTime();
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(17, 0);
        LocalTime horaColacion = LocalTime.of(13, 0);

        if (hora.isBefore(horaInicio) || hora.isAfter(horaFin) || hora.equals(horaColacion)) {
            return false;
        }

        
        List<Cita> citasExistentes = citaDao.findByVeterinarioIdAndFechaHora(veterinarioId, fechaHora);
        return citasExistentes.isEmpty() || citasExistentes.stream()
                .allMatch(c -> "CANCELADA".equals(c.getEstado()));
    }
}
