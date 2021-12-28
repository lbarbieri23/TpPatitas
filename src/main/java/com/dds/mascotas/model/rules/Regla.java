package com.dds.mascotas.model.rules;

import java.util.List;

public abstract class Regla {

    protected Integer puntos;

    public abstract Integer calcularPuntos(List<Respuesta> respuestasContestadas);

}
