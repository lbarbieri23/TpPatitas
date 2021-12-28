package com.dds.mascotas.dao;

import com.dds.mascotas.model.Caracteristica;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CommonDao {


    @PersistenceContext
    protected EntityManager entityManager;

    public <T> T findBy(Class<T> clazz, Integer id) {
        return entityManager.find(clazz, id);
    }
}
