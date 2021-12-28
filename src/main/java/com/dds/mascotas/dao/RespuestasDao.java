package com.dds.mascotas.dao;

import com.dds.mascotas.model.RespuestaAdopcion;
import com.dds.mascotas.model.RespuestaQuieroAdoptar;
import com.dds.mascotas.model.security.Usuario;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RespuestasDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RespuestaQuieroAdoptar> findRespuestasPublicacionQuieroAdoptar(Integer publicacionId) {
        return entityManager.createQuery("from RespuestaQuieroAdoptar where publicacion.id=:publicacionId")
                .setParameter("publicacionId", publicacionId)
                .getResultList();
    }

    public List<RespuestaAdopcion> findRespuestasPublicacionAdopcion(Integer publicacionId) {
        return entityManager.createQuery("from RespuestaAdopcion where publicacion.id=:publicacionId")
                .setParameter("publicacionId", publicacionId)
                .getResultList();
    }

}
