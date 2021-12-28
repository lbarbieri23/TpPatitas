package com.dds.mascotas.dao;

import com.dds.mascotas.model.Persona;
import com.dds.mascotas.model.security.Usuario;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class PersonaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Persona findBy(Integer id) {
        return entityManager.find(Persona.class, id);
    }

    public Persona findBy(Usuario usuario) {
        Persona singleResult=null;
        try {
            singleResult = (Persona) entityManager.
                    createQuery(
                            "from Persona where usuario.id = :id")
                    .setParameter("id",usuario.getId())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }

    public void update(Persona persona) {
        Session session =  entityManager.unwrap(Session.class);
        session.update(persona);
        entityManager.persist(persona);
    }
}
