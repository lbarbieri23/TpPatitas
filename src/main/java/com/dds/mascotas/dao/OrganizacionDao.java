package com.dds.mascotas.dao;

import com.dds.mascotas.model.EstadoMascota;
import com.dds.mascotas.model.Mascota;
import com.dds.mascotas.model.Organizacion;
import com.dds.mascotas.model.Persona;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class OrganizacionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Organizacion findBy(Integer id) {
        return  entityManager.find(Organizacion.class, id);
    }


    }
