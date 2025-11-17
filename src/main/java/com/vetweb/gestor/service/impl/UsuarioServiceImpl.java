package com.vetweb.gestor.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vetweb.gestor.service.UsuarioService;
import jakarta.transaction.Transactional;
import com.vetweb.gestor.dao.iUsuarioDao;
import com.vetweb.gestor.entity.Usuario;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

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
}
