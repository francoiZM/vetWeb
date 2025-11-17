package com.vetweb.gestor.service;
import com.vetweb.gestor.entity.Mascota;
import java.util.List;


public interface MascotaService {

    public List<Mascota> findAll();

    public Mascota save(Mascota mascota);

    public Mascota findById(Long id);

    public void delete(Long id);

    public Mascota update(Mascota mascota);

    public List<Mascota> findByUsuarioId(Long usuarioId);



    
}
