package com.vetweb.gestor.controller;

import java.util.List;
import com.vetweb.gestor.entity.Mascota;
import com.vetweb.gestor.entity.Usuario;
import com.vetweb.gestor.service.impl.MascotaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.vetweb.gestor.service.impl.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@RequestMapping("/mascotas")
public class MascotaController {
 



    // Aquí se inyectará el servicio/DAO de Mascota en el futuro
    @Autowired
    private MascotaServiceImpl mascotaService;
    @Autowired
    private UsuarioServiceImpl usuarioService;


    @GetMapping("/listar")
    public String listar(Model model) {
        List<Mascota> mascotas = mascotaService.findAll();
        model.addAttribute("mascotas", mascotas);
        return "mascota/listar";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "mascota/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Mascota mascota) {
        mascotaService.save(mascota);
        return "redirect:/mascotas/listar";
    }

    //para eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        mascotaService.delete(id);
        return "redirect:/mascotas/listar";
    }

    //para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Mascota mascota = mascotaService.findById(id);
        model.addAttribute("mascota", mascota);
        model.addAttribute("usuarios", usuarioService.findAll());
        return "mascota/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Mascota mascota) {
        mascota.setId(id);
        mascotaService.update(mascota);
        return "redirect:/mascotas/listar";
    }

}