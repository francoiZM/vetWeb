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
import java.util.Arrays;
import java.util.List;

@Controller
public class MascotaController {

    @GetMapping("/")
    public String listarMascotas(Model model) {
        List<Mascota> mascotasSimuladas = Arrays.asList(
            // Mascota 1
            new Mascota() {{
                setId(1L);
                setNombre("Fido");
                setEspecie("Perro");
                setRaza("Labrador");
                setFechaNacimiento(LocalDate.of(2018, 5, 20));
                setPeso(30.5);
            }},
            // Mascota 2
            new Mascota() {{
                setId(2L);
                setNombre("Mittens");
                setEspecie("Gato");
                setRaza("Siames");
                setFechaNacimiento(LocalDate.of(2020, 8, 15));
                setPeso(10.2);
            }},
            // Mascota 3
            new Mascota() {{
                setId(3L);
                setNombre("Piolín");
                setEspecie("Ave");
                setRaza("Canario");
                setFechaNacimiento(LocalDate.of(2023, 3, 15));
                setPeso(0.05);
            }}
        );
        
        // Pasamos la lista simulada a la vista (¡CRUCIAL!)
        model.addAttribute("mascotas", mascotasSimuladas);
        
        return "index"; 
    }

    

 
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("mascota", new Mascota());

        return "form-crear"; 
    }

   
    @PostMapping("/guardar")
    public String guardarMascota( @ModelAttribute Mascota mascota, RedirectAttributes redirectAttributes) {
       redirectAttributes.addFlashAttribute("mensaje", "Paciente registrado");
        return "redirect:/"; 
    }

   
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Mascota mascotaSimulada = new Mascota();
        model.addAttribute("mascota", mascotaSimulada);
       
        return "form-editar"; 
    }

    //detalle mascota

    @GetMapping("/detalle/{id}")
    public String verDetalleMascota(@PathVariable Long id, Model model) {
        Mascota mascotaSimulada = new Mascota();
        model.addAttribute("mascota", mascotaSimulada);
            
            return "detalle"; 
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMascota(@PathVariable("id") Long id, @ModelAttribute Mascota mascota, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensaje", "Paciente actualizado");
        return "redirect:/"; 
    }

    

   
    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("mensaje", "Paciente eliminado");
        return "redirect:/";
    }
}