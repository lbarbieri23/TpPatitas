package com.dds.mascotas.model.rules;

import java.util.List;

public class ReglaRespuestaIgualA extends Regla {

    private Respuesta respuestaCorrecta;

    public ReglaRespuestaIgualA(Respuesta respuestaCorrecta,  Integer puntosPorCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
        super.puntos = puntosPorCorrecta;
    }

    @Override
    public Integer calcularPuntos(List<Respuesta> respuestasContestadas) {
        return respuestasContestadas.stream().filter(x-> x.esIgual(respuestaCorrecta)).findAny().isPresent() ? puntos : 0;
    }
}
