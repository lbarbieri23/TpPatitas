package com.dds.mascotas.dao;

import com.dds.mascotas.model.EstadoMascota;
import com.dds.mascotas.model.Mascota;
import com.dds.mascotas.model.Persona;
import com.dds.mascotas.model.TipoMascota;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class TipoMascotaDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<TipoMascota> findTiposDeMascota() {
        System.out.println("comenzando query");

        List<TipoMascota> arrayResult=null;
        try {
            arrayResult = (List<TipoMascota>) entityManager.
                    createQuery( "from TipoMascota")
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
        System.out.println("resultado"+ arrayResult );

        return arrayResult;
    }
}