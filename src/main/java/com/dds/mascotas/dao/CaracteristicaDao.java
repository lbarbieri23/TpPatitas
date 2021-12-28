package com.dds.mascotas.dao;

import com.dds.mascotas.model.Caracteristica;
import com.dds.mascotas.model.Organizacion;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CaracteristicaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Caracteristica findBy(Integer id) {
        return entityManager.find(Caracteristica.class, id);
    }


}
