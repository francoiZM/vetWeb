package com.vetweb.gestor.controller;

import com.vetweb.gestor.entity.Usuario;
import com.vetweb.gestor.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario, @RequestParam("confirm-password") String confirmPassword, Model model) {
        // Validaciones básicas
        String error = null;
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            error = "El nombre es obligatorio.";
        } else if (usuario.getApellido() == null || usuario.getApellido().trim().isEmpty()) {
            error = "El apellido es obligatorio.";
        } else if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            error = "El email es obligatorio.";
        } else if (!usuario.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            error = "El formato del email no es válido.";
        } else if (usuario.getPassword() == null || usuario.getPassword().length() < 8) {
            error = "La contraseña debe tener al menos 8 caracteres.";
        } else if (!usuario.getPassword().equals(confirmPassword)) {
            error = "Las contraseñas no coinciden.";
        }

        if (error != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("error", error);
            return "registro";
        }
        usuarioService.save(usuario);
        return "redirect:/usuarios/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                Model model) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Debes ingresar email y contraseña.");
            return "login";
        }
        // Aquí iría la lógica de autenticación real
        return "redirect:/mascotas/listar";
    }
}