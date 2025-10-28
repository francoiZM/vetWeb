package com.vetweb.gestor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {


    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; 
    }


    @PostMapping("/login")
    public String procesarLogin() {
      
        return "redirect:/"; 
    }

    
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
       
        return "registro"; 
    }

    
    @PostMapping("/registro")
    public String procesarRegistro() {
        
        return "redirect:/login"; 
    }
}