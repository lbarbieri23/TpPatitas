package com.dds.helpers;

import com.dds.mascotas.model.PublicacionQuieroAdoptar;
import com.dds.mascotas.model.rules.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicacionReglasHelper {

    private static PublicacionReglasHelper instance;

    public static PublicacionReglasHelper getInstance() {
        if (instance == null) {
            instance = new PublicacionReglasHelper();
        }

        return instance;
    }

    private PublicacionReglasHelper() {

    }

    public List<Regla> getReglasFor(PublicacionQuieroAdoptar quieroAdoptar, List<ReglaConCondicionTipoOr> customRules) {
        List<Regla> reglas = new ArrayList<>();
        reglas.add(new ReglaRespuestaIgualA(quieroAdoptar.getSexo(), 20));

        if (quieroAdoptar.getCaracteristicasDeMascotaBuscada() != null && quieroAdoptar.getCaracteristicasDeMascotaBuscada().size() > 0) {
            reglas.add(new ReglaPuntosPorRespuestaCorrecta(
                    quieroAdoptar.getCaracteristicasDeMascotaBuscada().stream().map(x -> (Respuesta) x).collect(Collectors.toList()), 0));
        }

        if (customRules != null && customRules.size() > 0) {
            for (ReglaConCondicionTipoOr r : customRules) {
                r.setRespuestasContestadas(quieroAdoptar.getRespuestasContestadas());
            }

            reglas.addAll(customRules);
        }
        return reglas;
    }

}
