package com.dds.mascotas.dao;

import com.dds.mascotas.model.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MascotaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Mascota findBy(Integer id) {
        return  entityManager.find(Mascota.class, id);
    }


    public List<Mascota> findBy(Persona persona) {
        StringBuffer buff = new StringBuffer();
        buff.append("from Mascota where duenio.id=:id ");
        Query query =  entityManager.createQuery(buff.toString())
                .setParameter("id", persona.getId());

        return query.getResultList();
    }

    public List<Mascota> findBy(Persona persona, EstadoMascota estadoMascota) {
        StringBuffer buff = new StringBuffer();
        buff.append("from Mascota where duenio.id=:id and estado.id=:estado");
        Query query =  entityManager.createQuery(buff.toString())
                .setParameter("id", persona.getId())
                .setParameter("estado", estadoMascota.getId());

        return query.getResultList();
    }
}
