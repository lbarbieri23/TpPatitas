package com.dds.mascotas.model.rules;

import com.dds.mascotas.model.Publicacion;
import com.dds.mascotas.model.PublicacionQuieroAdoptar;

public class Puntaje<T> {

    private T entity; // Publicacion Mascota en adopcion
    private Integer puntaje;
    private PublicacionQuieroAdoptar publicacionOrigen;

    public Puntaje(T entity, Integer puntaje) {
        this.entity = entity;
        this.puntaje = puntaje;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public PublicacionQuieroAdoptar getPublicacionOrigen() {
        return publicacionOrigen;
    }

    public void setPublicacionOrigen(PublicacionQuieroAdoptar publicacionOrigen) {
        this.publicacionOrigen = publicacionOrigen;
    }
}
