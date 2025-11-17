package com.vetweb.gestor.dao;

import org.springframework.data.repository.CrudRepository;
import com.vetweb.gestor.entity.Mascota;
import java.util.List;


public interface iMascotaDao extends CrudRepository<Mascota, Long> {
    List<Mascota> findByUsuarioId(Long usuarioId);
}
