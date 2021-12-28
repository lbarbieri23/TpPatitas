package com.dds.mascotas.dao;

import com.dds.mascotas.model.*;
import com.dds.mascotas.model.security.Usuario;
import com.dds.mascotas.service.PersonaService;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class PublicacionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Publicacion> findBy(TipoPublicacion tipo,  String searchCriteria, Integer page, Integer itemsPerPage) {
        StringBuffer buff = new StringBuffer();
        buff.append("from Publicacion where estado.id=:estado ");
        if (tipo != null) {
            buff.append(" and tipoPublicacion.id = :tipo");
        }

        Query query =  entityManager.createQuery(buff.toString())
                .setParameter("estado", EstadoPublicacion.PUBLICADA.getId());
        //.setFirstResult((page-1) * itemsPerPage)
        //.setMaxResults(itemsPerPage);

        if (tipo != null) {
            query.setParameter("tipo", tipo.getId());
        }

        return query.getResultList();
    }

    public Long getCantidadAprobada(String tipoPublicacion,  String searchCriteria) {
        return (Long) entityManager.createQuery("select count(id) from Publicacion where estado.id=:estado ")
                .setParameter("estado", EstadoPublicacion.PUBLICADA.getId())
                .getSingleResult();
    }

    public Publicacion findBy(Integer id) {
        return  entityManager.find(Publicacion.class, id);
    }

    public List<PublicacionMascotaEnAdopcion> findEnAdopcion() {
        return entityManager.createQuery("from PublicacionMascotaEnAdopcion where estado = 2")
                .getResultList();
    }


    public PublicacionMascotaEncontrada findPublicacionMascotaEncontradaBy(Integer id) {
        return entityManager.find(PublicacionMascotaEncontrada.class, id);
    }

    public PublicacionMascotaPerdida findPublicacionMascotaPerdidaBy(Integer id) {
        return entityManager.find(PublicacionMascotaPerdida.class, id);
    }

    public PublicacionMascotaEnAdopcion findPublicacionAdopcionBy(Integer id) {
        return entityManager.find(PublicacionMascotaEnAdopcion.class, id);
    }
    public List<PublicacionQuieroAdoptar> findPublicacionQuieroAdoptarByIdPersona(Integer idPersona) {
        return entityManager.
                createQuery(
                        "from Publicacion where publicadaPor.id = :id and estado.id = :estado and tipoPublicacion.id = :tipoPublicacion")
                .setParameter("id",idPersona)
                .setParameter("estado", 2)
                .setParameter("tipoPublicacion", 4)
                .getResultList();
    }

    public PublicacionQuieroAdoptar findPublicacionQuieroAdoptarBy(Integer id) {
        return entityManager.find(PublicacionQuieroAdoptar.class, id);
    }

    public Publicacion findBy(Integer id, EstadoPublicacion estado) {
        Publicacion singleResult=null;
        try {
            singleResult = (Publicacion) entityManager.
                    createQuery(
                            "from Publicacion where id = :id and estado.id = :estado")
                    .setParameter("id",id)
                    .setParameter("estado", estado.getId())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }

    public Publicacion findBy(Integer id, Persona persona) {
        Publicacion singleResult=null;
        try {
            singleResult = (Publicacion) entityManager.
                    createQuery(
                            "from Publicacion where id = :id and publicadaPor.id = :persona")
                    .setParameter("id",id)
                    .setParameter("persona", persona.getId())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }

    @SuppressWarnings("unchecked")
    public List<Publicacion> findBy(Persona persona) {
        return entityManager.createQuery("from Publicacion where publicadaPor.id=:id order by fecha desc")
                .setParameter("id", persona.getId())
                .getResultList();
    }


    public void save(PublicacionMascotaEncontrada publicacion)  throws Exception {
        Session session =  entityManager.unwrap(Session.class);
        session.save(publicacion);
        session.save(publicacion.getMascota());
        for (CaracteristicaValor cv: publicacion.getMascota().getCaracteristicas()) {
            session.save(cv);
        }

        entityManager.persist(publicacion);
    }

    public void save(Publicacion publicacion)  throws Exception {
        Publicacion pubaux = publicacion;
        Session session =  entityManager.unwrap(Session.class);
        session.save(publicacion);
        entityManager.persist(publicacion);
    }

    public void update(Publicacion publicacion)  throws Exception {
        Session session =  entityManager.unwrap(Session.class);
        session.update(publicacion);
        entityManager.persist(publicacion);
    }

    public void update(PublicacionMascotaEncontrada publicacion)  throws Exception {
        Session session =  entityManager.unwrap(Session.class);
        session.update(publicacion);
        session.update(publicacion.getMascota());
        for (CaracteristicaMascota cm: publicacion.getMascota().getCaracteristicas()) {
            session.update(cm);
        }

        entityManager.persist(publicacion);
    }

    public List<PublicacionMascotaEncontrada> findMascotaEncontradaBy(Persona persona) {
        return entityManager.createQuery("from PublicacionMascotaEncontrada where reclamadaPor.id=:persona order by fecha desc")
                .setParameter("persona", persona.getId())
                .getResultList();
    }

    public List<PublicacionQuieroAdoptar> findQuieroAdoptarBy(Persona persona) {
        return entityManager.createQuery("from PublicacionQuieroAdoptar where adoptadaA.id=:persona order by fecha desc")
                .setParameter("persona", persona.getId())
                .getResultList();
    }
}