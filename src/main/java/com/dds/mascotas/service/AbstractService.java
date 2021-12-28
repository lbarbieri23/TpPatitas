package com.dds.mascotas.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public abstract class AbstractService<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    public void save(T entity) {
        entityManager.persist(entity);
    }


}
