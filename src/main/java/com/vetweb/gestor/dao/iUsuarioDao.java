package com.vetweb.gestor.dao;
import org.springframework.data.repository.CrudRepository;
import com.vetweb.gestor.entity.Usuario;

public interface iUsuarioDao extends CrudRepository<Usuario, Long> {
    
}
