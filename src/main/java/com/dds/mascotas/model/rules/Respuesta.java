package com.dds.mascotas.model.rules;

public interface Respuesta {

    boolean esIgual(Respuesta respuesta);
    Pregunta getPregunta();
    Opcion getRespuesta();

}
