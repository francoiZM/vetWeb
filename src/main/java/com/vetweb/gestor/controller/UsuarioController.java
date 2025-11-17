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
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuario/listar";
    }

    
    @GetMapping("/crear")
    public String Crear(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/crear";
    }

    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, Model model) {
        usuarioService.save(usuario);
        return "redirect:/usuarios/listar";
    }

    // editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return "redirect:/usuarios/listar";
        }
        model.addAttribute("usuario", usuario);
        return "usuario/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuario.setId(id);
        usuarioService.update(usuario);
        return "redirect:/usuarios/listar";
    }

    // eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null && usuario.getMascotas() != null && !usuario.getMascotas().isEmpty()) {
            model.addAttribute("usuarios", usuarioService.findAll());
            model.addAttribute("error", "No se puede eliminar el usuario porque tiene mascotas asociadas.");
            return "usuario/listar";
        }
        usuarioService.delete(id);
        return "redirect:/usuarios/listar";
    }

    // registro
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute Usuario usuario, @RequestParam("confirm-password") String confirmPassword, Model model) {
        // validaciones básicas
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

    // login
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
        
        return "redirect:/mascotas/listar";
    }
}