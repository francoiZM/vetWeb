package com.vetweb.gestor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MascotaController {

    @GetMapping("/")
    public String listarMascotas(Model model) {
 
        return "index"; 
    }

 
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
       
        return "form-crear"; 
    }

   
    @PostMapping("/guardar")
    public String guardarMascota() {
       
        return "redirect:/"; 
    }

   
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
       
        return "form-editar"; 
    }

   
    @PostMapping("/actualizar/{id}")
    public String actualizarMascota(@PathVariable("id") Long id) {
      
        return "redirect:/"; 
    }

   
    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable("id") Long id) {
        
        return "redirect:/"; 
    }
}