package com.vetweb.gestor.dao;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.vetweb.gestor.entity.Usuario;

public interface iUsuarioDao extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
