package com.dds.mascotas.service;

import com.dds.mascotas.dao.MascotaDao;
import com.dds.mascotas.dao.OrganizacionDao;
import com.dds.mascotas.dao.PersonaDao;
import com.dds.mascotas.dao.TipoMascotaDao;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.security.Usuario;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    private PersonaDao personaDao;
    private MascotaDao mascotaDao;
    private TipoMascotaDao tipoMascotaDao;
    private OrganizacionDao organizacionDao;

    @Autowired
    public MascotaService(MascotaDao mascotaDao, PersonaDao personaDao, OrganizacionDao organizacionDao, TipoMascotaDao tipoMascotaDao ) {
        this.mascotaDao = mascotaDao;
        this.personaDao = personaDao;
        this.organizacionDao = organizacionDao;
        this.tipoMascotaDao = tipoMascotaDao;
    }

    @Transactional
    public Mascota findBy(Integer id) {
        Mascota mascota = mascotaDao.findBy(id);
        mascota.getFotos().size();
        return mascota;
    }


    @Transactional
    public List<Mascota> findMascotasBy(Usuario loggedUser) {
        Persona persona = personaDao.findBy(loggedUser);
        return mascotaDao.findBy(persona);
    }

    @Transactional
    public List<Mascota> findMascotasEnCasaBy(Usuario loggedUser) {
        Persona persona = personaDao.findBy(loggedUser);
        return mascotaDao.findBy(persona, EstadoMascota.EN_CASA);
    }

    @Transactional
    public List<Mascota> findMascotasBy(Persona persona) {
        return mascotaDao.findBy(persona);
    }

    @Transactional
    public List<Caracteristica> getCaracteristicas(Organizacion organizacion, Long idTipoMascota) {
        final Organizacion org = organizacionDao.findBy(organizacion.getId());
        TipoMascota tipoMascota = org.getTiposDeMascota()
                .stream()
                .filter(x -> x.getId().intValue() == idTipoMascota.intValue())
                .findAny()
                .get();

        List<Caracteristica> result = tipoMascota.getCaracteristicas().stream().filter(x -> x.getOrganizacion().getId().equals(org.getId())).collect(Collectors.toList());
        result.forEach(x -> x.getOpciones().size());
        return  result;
    }

    @Transactional
    public List<TipoMascota> findTiposDeMascotas() {
        return this.tipoMascotaDao.findTiposDeMascota();
    }
}