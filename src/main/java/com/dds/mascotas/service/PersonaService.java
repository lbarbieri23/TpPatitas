package com.dds.mascotas.service;

import com.dds.mascotas.dao.PersonaDao;
import com.dds.mascotas.model.Persona;
import com.dds.mascotas.model.security.Usuario;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class PersonaService {

    private PersonaDao personaDao;

    @Autowired
    public PersonaService(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    @Transactional
    public Persona findByLoggedUser(Usuario loggedUser) {
        if(loggedUser!= null){
            return personaDao.findBy(loggedUser);
        } else {
            return null;
        }
    }

    @Transactional
    public void actualizarPerfil(Persona persona) {
        Persona toUpdate = personaDao.findBy(persona.getId());

        toUpdate.setNombre(persona.getNombre());
        toUpdate.setApellido(persona.getApellido());
        toUpdate.setDireccion(persona.getDireccion());
        toUpdate.setTipoDocumento(persona.getTipoDocumento());
        toUpdate.setDocumento(persona.getDocumento());
        toUpdate.setFechaNacimiento(persona.getFechaNacimiento());

        personaDao.update(toUpdate);
    }

    @Transactional
    public Persona findPersonaConOrganizacion(Usuario loggedUser) {
        Persona persona = personaDao.findBy(loggedUser);
        persona.getOrganizacion().getTiposDeMascota().size();
        persona.getOrganizacion().getPreguntasAdopcion().size();
        persona.getOrganizacion().getPreguntasAdopcion().forEach(x-> x.getOpciones().size());
        persona.getOrganizacion().getPreguntasQuieroAdoptar().size();
        persona.getOrganizacion().getPreguntasQuieroAdoptar().forEach(x-> x.getOpciones().size());
        return persona;
    }
}