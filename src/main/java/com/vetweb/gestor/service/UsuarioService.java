package com.vetweb.gestor.service;

import java.util.List;

import com.vetweb.gestor.entity.Usuario;

public interface UsuarioService {

    public List<Usuario> findAll();

    public Usuario save(Usuario usuario);

    public Usuario findById(Long id);

    public void delete(Long id);

    public Usuario update(Usuario usuario);


  

    
}
