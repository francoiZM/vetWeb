package com.vetweb.gestor.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.vetweb.gestor.entity.Rol;

public interface iRolDao extends CrudRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
