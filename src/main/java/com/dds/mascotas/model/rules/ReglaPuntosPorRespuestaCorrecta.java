package com.dds.mascotas.model.rules;

import java.util.List;

public class ReglaPuntosPorRespuestaCorrecta extends Regla {

    private List<Respuesta> respuestasCorrectas;


    public ReglaPuntosPorRespuestaCorrecta(List<Respuesta> respuestasCorrectas, Integer puntos) {
        this.respuestasCorrectas = respuestasCorrectas;
        super.puntos = puntos;
    }

    @Override
    public Integer calcularPuntos(List<Respuesta> respuestasContestadas) {
        Integer puntos = 0;

        if (respuestasCorrectas != null && respuestasContestadas != null) {
            for (Respuesta c : respuestasCorrectas) {
               if (respuestasContestadas.stream().filter(x -> x.esIgual(c)).findAny().isPresent()) {
                    puntos += super.puntos;
                }
            }
        }
        return puntos;
    }
}
