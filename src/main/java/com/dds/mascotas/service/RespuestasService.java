package com.dds.mascotas.service;

import com.dds.mascotas.dao.*;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.security.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RespuestasService {


    private RespuestasDao respuestasDao;


    @Autowired
    public RespuestasService(RespuestasDao respuestasDao) {
        this.respuestasDao = respuestasDao;

    }


    @Transactional
    public List<RespuestaQuieroAdoptar> findRespuestasPublicacionQuieroAdoptar(Integer publicacionId) {
        return respuestasDao.findRespuestasPublicacionQuieroAdoptar(publicacionId);
    }

    @Transactional
    public List<RespuestaAdopcion> findRespuestasPublicacionAdopcion(Integer publicacionId) {
        return respuestasDao.findRespuestasPublicacionAdopcion(publicacionId);
    }



}