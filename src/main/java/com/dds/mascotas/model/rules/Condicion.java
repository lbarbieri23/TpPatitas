package com.dds.mascotas.model.rules;

import com.dds.mascotas.model.RespuestaAdopcion;

import java.util.List;

public class Condicion {

    private Respuesta respuesta;

    public Condicion(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public  boolean cumple(List<Respuesta> respuestas) {
        return respuestas.stream().filter(x -> x.equals(this.respuesta)).findAny().isPresent();
    }
}
