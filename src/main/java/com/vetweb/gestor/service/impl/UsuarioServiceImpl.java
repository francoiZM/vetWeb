package com.vetweb.gestor.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetweb.gestor.dao.iUsuarioDao;
import com.vetweb.gestor.entity.Rol;
import com.vetweb.gestor.entity.Usuario;
import com.vetweb.gestor.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private iUsuarioDao usuarioDao;

    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        return usuarioDao.save(usuario);
    }
    @Transactional
    public void delete(Long id) {
        usuarioDao.deleteById(id);
    }

    @Transactional
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    
    }
    @Transactional
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    //metodos override

    @Override
    @Transactional (readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioDao.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        if (!usuario.isActivo()) {
            throw new UsernameNotFoundException("Usuario inactivo: " + username);

        }

        Set<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toSet());


        if (authorities.isEmpty()) {
            throw new UsernameNotFoundException("El usuario " + username + " no tiene roles asignados.");
        }

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!usuario.isActivo())
                .build();



    }

    public boolean existeUsuario(String email) {
        return usuarioDao.existsByEmail(email);
    }

    



}
