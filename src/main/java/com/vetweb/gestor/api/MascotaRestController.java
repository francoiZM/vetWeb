package com.vetweb.gestor.api;

import com.vetweb.gestor.entity.Mascota;
import com.vetweb.gestor.entity.Usuario;
import com.vetweb.gestor.service.impl.MascotaServiceImpl;
import com.vetweb.gestor.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaRestController {

    @Autowired
    private MascotaServiceImpl mascotaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    // 🔹 1. TODAS las mascotas (admin / vet)
    @GetMapping
    public List<Mascota> obtenerTodas() {
        return mascotaService.findAll();
    }

    // 🔹 2. Mascotas por usuarioId (caso ideal para la app móvil)
    @GetMapping("/usuario/{usuarioId}")
    public List<Mascota> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return mascotaService.findByUsuarioId(usuarioId);
    }

    // 🔹 3. Mascota por ID (opcional)
    @GetMapping("/{id}")
    public Mascota obtenerPorId(@PathVariable Long id) {
        return mascotaService.findById(id);
    }
}
