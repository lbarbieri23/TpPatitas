package com.dds.mascotas.service;

import com.dds.mascotas.service.RecomendacionesService;
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
public class MongoService {

    private RecomendacionDao recomendacionDao;

    @Autowired
    public MongoService(RecomendacionDao recomendacionDao ) {
        this.recomendacionDao = recomendacionDao;
    }

    public void guardarRecomendacionesMongo (List<Puntaje<PublicacionMascotaEnAdopcion>> ListaDePublicacionesRecomendadas, Integer idPersona) {
        recomendacionDao.setRecomendacionesMongo(ListaDePublicacionesRecomendadas, idPersona);
    }


}
