package com.vetweb.gestor.controller;

import com.vetweb.gestor.entity.Cita;
import com.vetweb.gestor.entity.Mascota;
import com.vetweb.gestor.entity.Usuario;
import com.vetweb.gestor.service.impl.CitaServiceImpl;
import com.vetweb.gestor.service.impl.MascotaServiceImpl;
import com.vetweb.gestor.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaServiceImpl citaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private MascotaServiceImpl mascotaService;

    
    @GetMapping("/mis-citas")
    public String misCitas(Model model, Authentication authentication) {
        String email = authentication.getName();
        Usuario tutor = usuarioService.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (tutor != null) {
            List<Cita> citas = citaService.findByTutorId(tutor.getId());
            model.addAttribute("citas", citas);
        }

        return "cita/mis-citas";
    }

    // seleccionar veterinario
    @GetMapping("/agendar")
    public String agendarCitaPaso1(Model model) {
        
        List<Usuario> veterinarios = usuarioService.findAll().stream()
                .filter(u -> u.getRoles().stream()
                        .anyMatch(r -> r.getNombre().equals("ROLE_VETERINARIO")))
                .collect(Collectors.toList());
        model.addAttribute("veterinarios", veterinarios);
        return "cita/agendar";
    }

    // ver calendario con horarios disponibles
    @GetMapping("/agendar/calendario")
    public String agendarCitaPaso2(@RequestParam("veterinarioId") Long veterinarioId, Model model) {
        Usuario veterinario = usuarioService.findById(veterinarioId);
        model.addAttribute("veterinario", veterinario);
        model.addAttribute("veterinarioId", veterinarioId);
        
        // generar horarios
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime inicioSemana;
        
       
        if (ahora.getDayOfWeek() == java.time.DayOfWeek.SATURDAY || 
            ahora.getDayOfWeek() == java.time.DayOfWeek.SUNDAY) {
            inicioSemana = ahora.with(java.time.temporal.TemporalAdjusters.next(java.time.DayOfWeek.MONDAY))
                    .withHour(0).withMinute(0).withSecond(0);
        } else {
           
            inicioSemana = ahora.withHour(0).withMinute(0).withSecond(0);
        }
        
        model.addAttribute("inicioSemana", inicioSemana);
        model.addAttribute("ahora", ahora);
        
        // generar lista de 5 días laborables desde inicioSemana
        java.util.List<LocalDateTime> diasLaborables = new java.util.ArrayList<>();
        java.util.Map<String, Boolean> disponibilidad = new java.util.HashMap<>();
        LocalDateTime diaIterador = inicioSemana;
        
        while (diasLaborables.size() < 5) {
           
            if (diaIterador.getDayOfWeek() != java.time.DayOfWeek.SATURDAY && 
                diaIterador.getDayOfWeek() != java.time.DayOfWeek.SUNDAY) {
                
                diasLaborables.add(diaIterador);
                
                for (int hora = 8; hora <= 17; hora++) {
                    if (hora != 13) { 
                        LocalDateTime fechaHora = diaIterador.withHour(hora).withMinute(0).withSecond(0);
                        String key = fechaHora.toString();
                        
                        boolean esPasado = fechaHora.isBefore(ahora);
                        boolean disponiblePorVeterinario = citaService.isHorarioDisponible(veterinarioId, fechaHora);
                        disponibilidad.put(key, !esPasado && disponiblePorVeterinario);
                    }
                }
            }
            diaIterador = diaIterador.plusDays(1);
        }
        model.addAttribute("diasLaborables", diasLaborables);
        model.addAttribute("disponibilidad", disponibilidad);
        
        return "cita/calendario-seleccion";
    }

    // seleccionar mascota y motivo
    @GetMapping("/agendar/confirmar")
    public String agendarCitaPaso3(@RequestParam("veterinarioId") Long veterinarioId,
                                    @RequestParam("fechaHora") String fechaHora,
                                    Model model,
                                    Authentication authentication) {
        String email = authentication.getName();
        Usuario tutor = usuarioService.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (tutor != null) {
            List<Mascota> mascotas = mascotaService.findByUsuarioId(tutor.getId());
            model.addAttribute("mascotas", mascotas);
        }

        Usuario veterinario = usuarioService.findById(veterinarioId);
        model.addAttribute("veterinario", veterinario);
        model.addAttribute("veterinarioId", veterinarioId);
        model.addAttribute("fechaHora", fechaHora);
        
        LocalDateTime fechaHoraObj = LocalDateTime.parse(fechaHora);
        model.addAttribute("fechaHoraFormateada", fechaHoraObj.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        return "cita/confirmar";
    }

    // guardar cita agendada
    @PostMapping("/guardar")
    public String guardarCita(@RequestParam("mascotaId") Long mascotaId,
                              @RequestParam("veterinarioId") Long veterinarioId,
                              @RequestParam("fechaHora") String fechaHora,
                              @RequestParam("motivo") String motivo,
                              Authentication authentication) {
        
        String email = authentication.getName();
        Usuario tutor = usuarioService.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (tutor == null) {
            return "redirect:/citas/mis-citas";
        }

        LocalDateTime fechaHoraObj = LocalDateTime.parse(fechaHora);
        
        
        if (!citaService.isHorarioDisponible(veterinarioId, fechaHoraObj)) {
            return "redirect:/citas/agendar?error=horario-no-disponible";
        }

        Mascota mascota = mascotaService.findById(mascotaId);
        Usuario veterinario = usuarioService.findById(veterinarioId);

        Cita cita = new Cita();
        cita.setTutor(tutor);
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        cita.setFechaHora(fechaHoraObj);
        cita.setMotivo(motivo);
        cita.setEstado("PENDIENTE");

        citaService.save(cita);
        return "redirect:/citas/mis-citas";
    }

    // cancelar cita
    @GetMapping("/cancelar/{id}")
    public String cancelarCita(@PathVariable Long id, Authentication authentication) {
        Cita cita = citaService.findById(id);
        
        if (cita == null) {
            return "redirect:/citas/mis-citas";
        }

        
        String email = authentication.getName();
        Usuario tutor = usuarioService.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (tutor == null || !cita.getTutor().getId().equals(tutor.getId())) {
            return "redirect:/citas/mis-citas";
        }

       
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime horaLimite = cita.getFechaHora().minusHours(1);

        if (ahora.isAfter(horaLimite)) {
            return "redirect:/citas/mis-citas?error=tiempo-limite";
        }

        cita.setEstado("CANCELADA");
        citaService.update(cita);

        return "redirect:/citas/mis-citas";
    }

   
    @GetMapping("/calendario")
    public String calendario(Model model, Authentication authentication) {
        String email = authentication.getName();
        
       
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            List<Cita> citas = citaService.findAll();
            model.addAttribute("citas", citas);
        } else {
           
            Usuario veterinario = usuarioService.findAll().stream()
                    .filter(u -> u.getEmail().equals(email))
                    .findFirst()
                    .orElse(null);

            if (veterinario != null) {
                List<Cita> citas = citaService.findByVeterinarioId(veterinario.getId());
                model.addAttribute("citas", citas);
            }
        }

        return "cita/calendario";
    }
}
