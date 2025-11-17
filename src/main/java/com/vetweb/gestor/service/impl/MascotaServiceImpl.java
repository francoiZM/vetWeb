package com.vetweb.gestor.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vetweb.gestor.service.MascotaService;
import com.vetweb.gestor.dao.iMascotaDao;
import com.vetweb.gestor.entity.Mascota;
import jakarta.transaction.Transactional;
import java.util.List;


@Service
public class MascotaServiceImpl implements MascotaService {
    @Autowired 
    private iMascotaDao mascotaDao;

    @Transactional
    public Mascota save(Mascota mascota) {
        return mascotaDao.save(mascota);
    }

    @Transactional
    public Mascota update(Mascota mascota) {
        return mascotaDao.save(mascota);
    }

    @Transactional
    public void delete(Long id) {
        mascotaDao.deleteById(id);
    }
    @Transactional
    public List<Mascota> findAll() {
        return (List<Mascota>) mascotaDao.findAll();
    }
    @Transactional
    public Mascota findById(Long id) {
        return mascotaDao.findById(id).orElse(null);
    }
    @Transactional
    public List<Mascota> findByUsuarioId(Long usuarioId) {
        return mascotaDao.findByUsuarioId(usuarioId);
    }



}
