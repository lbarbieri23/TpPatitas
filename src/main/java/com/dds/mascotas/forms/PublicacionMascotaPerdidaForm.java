package com.dds.mascotas.forms;

import com.dds.mascotas.model.PublicacionMascotaPerdida;

import java.io.Serializable;

public class PublicacionMascotaPerdidaForm extends CommonForm implements Serializable {

    private PublicacionMascotaPerdida publicacion;
    private String ubicacion;

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
}