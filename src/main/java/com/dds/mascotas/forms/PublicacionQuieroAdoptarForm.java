package com.dds.mascotas.forms;

import com.dds.mascotas.model.*;

import java.io.Serializable;
import java.util.List;

public class PublicacionQuieroAdoptarForm extends CommonForm implements Serializable {

    private PublicacionQuieroAdoptar publicacion;
    private String ubicacion;
    private List<PreguntaQuieroAdoptar> preguntas;
    private List<TipoMascota> tipoMascotas;
    private Boolean estaEditando;

    public PublicacionQuieroAdoptar getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionQuieroAdoptar publicacion) {
        this.publicacion = publicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<PreguntaQuieroAdoptar> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaQuieroAdoptar> preguntas) {
        this.preguntas = preguntas;
    }

    public List<TipoMascota> getTipoMascotas() {
        return tipoMascotas;
    }

    public void setTipoMascotas(List<TipoMascota> tipoMascotas) {
        this.tipoMascotas = tipoMascotas;
    }

    public Boolean getEstaEditando() {
        return estaEditando;
    }

    public void setEstaEditando(Boolean estaEditando) {
        this.estaEditando = estaEditando;
    }

}