package com.vetweb.gestor.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/default")
    public String defaultRedirect(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities() != null) {
            // Verifica los roles del usuario autenticado
            if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_vet"))) {
                return "redirect:/veterinario/agenda"; // Veterinaria va a su agenda
            }
            if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_tutor"))) {
                return "redirect:/tutor/mascotas"; // Dueño va a su lista de mascotas
            }
        }
        // Redirección por defecto si no se identifica el rol (o un error)
        return "redirect:/"; 
    }
}