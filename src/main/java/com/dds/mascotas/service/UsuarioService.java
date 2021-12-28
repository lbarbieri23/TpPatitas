package com.dds.mascotas.service;

import com.dds.mascotas.dao.UsuarioDao;
import com.dds.mascotas.model.security.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class UsuarioService {


    private UsuarioDao usuarioDao;

    @Autowired
    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Transactional
    public Usuario login(String username, String password) {
        return usuarioDao.findBy(username, password);
    }

    @Transactional
    public void registrarUsuarioNuevo( Usuario usuario) { this.usuarioDao.registrarUsuario(usuario); }

}
