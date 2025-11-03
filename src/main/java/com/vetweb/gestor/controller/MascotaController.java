package com.vetweb.gestor.controller;

import com.vetweb.gestor.entity.Mascota;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class MascotaController {

    private static final List<Mascota> mascotas = new ArrayList<>();
    private static final AtomicLong counter = new AtomicLong(0);

    // Bloque estático para inicializar datos de prueba solo una vez
    static {
        // Inicialización usando el counter y el add()
        mascotas.add(new Mascota() {{
            setId(counter.incrementAndGet()); 
            setNombre("Fido");
            setEspecie("Perro");
            setRaza("Labrador");
            setFechaNacimiento(LocalDate.of(2018, 5, 20));
            setPeso(30.5);
        }});
        mascotas.add(new Mascota() {{
            setId(counter.incrementAndGet()); 
            setNombre("Mittens");
            setEspecie("Gato");
            setRaza("Siames");
            setFechaNacimiento(LocalDate.of(2020, 8, 15));
            setPeso(10.2);
        }});
        mascotas.add(new Mascota() {{
            setId(counter.incrementAndGet()); 
            setNombre("Tweety");
            setEspecie("Pájaro");
            setRaza("Canario");
            setFechaNacimiento(LocalDate.of(2021, 3, 10));
            setPeso(0.5);
        }});
    }

    @GetMapping("/")
    public String listarMascotas(Model model) {
        model.addAttribute("mascotas", mascotas);
        
        return "index"; 
    }

    

 
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("mascota", new Mascota());

        return "form-crear"; 
    }

   
    @PostMapping("/guardar")
    public String guardarMascota( @ModelAttribute Mascota mascota, RedirectAttributes redirectAttributes) {
        mascota.setId(counter.incrementAndGet()); 
        mascotas.add(mascota);
       redirectAttributes.addFlashAttribute("mensaje", "Paciente registrado");
        return "redirect:/"; 
    }

   
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
         Optional<Mascota> mascotaOpt = mascotas.stream().filter(m -> m.getId().equals(id)).findFirst();
        if (mascotaOpt.isPresent()) {
            model.addAttribute("mascota", mascotaOpt.get());
            return "form-editar";
        } else {
            redirectAttributes.addFlashAttribute("error", "Mascota no encontrada");
            return "redirect:/";
        }
    }

    //detalle mascota

    @GetMapping("/detalle/{id}")
    public String verDetalleMascota(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Mascota> mascotaOpt = mascotas.stream().filter(m -> m.getId().equals(id)).findFirst();
        if (mascotaOpt.isPresent()) {
            model.addAttribute("mascota", mascotaOpt.get());
            return "detalle";
        } else {
            redirectAttributes.addFlashAttribute("error", "Mascota no encontrada");
            return "redirect:/";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMascota(@PathVariable("id") Long id, @ModelAttribute Mascota mascotaActualizada, RedirectAttributes redirectAttributes) {
        Optional<Mascota> mascotaOpt = mascotas.stream().filter(m -> m.getId().equals(id)).findFirst();

        if (mascotaOpt.isPresent()) {
            Mascota m = mascotaOpt.get();
            m.setNombre(mascotaActualizada.getNombre());
            m.setEspecie(mascotaActualizada.getEspecie());
            m.setRaza(mascotaActualizada.getRaza());
            m.setFechaNacimiento(mascotaActualizada.getFechaNacimiento());
            m.setPeso(mascotaActualizada.getPeso());

            redirectAttributes.addFlashAttribute("mensaje", "Paciente actualizado");
        } else {
            redirectAttributes.addFlashAttribute("error", "Mascota no encontrada");
        }
        return "redirect:/";
    }

    

   
    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (mascotas.removeIf(m -> m.getId().equals(id))) {
            redirectAttributes.addFlashAttribute("mensaje", "Paciente eliminado");
        } else {
            redirectAttributes.addFlashAttribute("error", "Mascota no encontrada");
        }

        return "redirect:/";
    }
}