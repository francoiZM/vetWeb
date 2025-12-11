package com.vetweb.gestor.service.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vetweb.gestor.dao.iUsuarioDao;
import com.vetweb.gestor.entity.Usuario;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final iUsuarioDao usuarioDao;

    public CustomUserDetailsService(iUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // In our app, username is the email
        Optional<Usuario> opt = usuarioDao.findByEmail(username);
        Usuario usuario = opt.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Map domain role (e.g., "vet" or "tutor") to Spring Security authority with ROLE_ prefix
        String role = usuario.getRol() == null ? "tutor" : usuario.getRol();
        String authority = "ROLE_" + role;

        return User
            .withUsername(usuario.getEmail())
            .password(usuario.getPassword())
            .authorities(Collections.singletonList(new SimpleGrantedAuthority(authority)))
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();
    }
}
