package com.dds.mascotas.forms;

import com.dds.mascotas.model.*;
import java.io.Serializable;
import java.util.List;

public class CorridaRecomendacionesForm extends CommonForm implements Serializable {

    private PublicacionMascotaPerdida publicacion;
    private String ubicacion;
    private List<PreguntaQuieroAdoptar> preguntas;
    private List<TipoMascota> tipoMascotas;


    public PublicacionMascotaPerdida getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionMascotaPerdida publicacion) {
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
}