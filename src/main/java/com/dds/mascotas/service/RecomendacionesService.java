package com.dds.mascotas.service;

import com.dds.mascotas.dao.*;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.rules.Puntaje;
import com.dds.mascotas.model.security.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacionesService {

    private RecomendacionDao recomendacionDao;

    @Autowired
    public RecomendacionesService(RecomendacionDao recomendacionDao ) {
        this.recomendacionDao = recomendacionDao;
    }

    @Transactional
    public List<Recomendacion> findRecomendacionesByPerson(Integer idPersona) {
        return  recomendacionDao.findRecomendacionesByPerson(idPersona);
    }


    @Transactional
    public void setRecomendaciones(List<Puntaje<PublicacionMascotaEnAdopcion>> ListaDePublicacionesRecomendadas, Integer idPersona) {
        recomendacionDao.setRecomendaciones(ListaDePublicacionesRecomendadas, idPersona);
    }
}