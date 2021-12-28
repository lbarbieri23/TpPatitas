package com.dds.mascotas.dao;

import com.dds.mascotas.model.OpcionCaracteristica;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class OpcionCaracteristicaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public OpcionCaracteristica findBy(Integer id) {
        return entityManager.find(OpcionCaracteristica.class, id);
    }


}
