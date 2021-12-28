package com.dds.mascotas.forms;

import com.dds.mascotas.model.PreguntaAdopcion;
import com.dds.mascotas.model.PublicacionMascotaEnAdopcion;

import java.io.Serializable;
import java.util.List;

public class PublicacionMascotaEnAdopcionForm extends CommonForm implements Serializable {

    private PublicacionMascotaEnAdopcion publicacion;
    private String ubicacion;
    private List<PreguntaAdopcion> preguntasAdopcion;

    public PublicacionMascotaEnAdopcion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionMascotaEnAdopcion publicacion) {
        this.publicacion = publicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<PreguntaAdopcion> getPreguntasAdopcion() {
        return preguntasAdopcion;
    }

    public void setPreguntasAdopcion(List<PreguntaAdopcion> preguntasAdopcion) {
        this.preguntasAdopcion = preguntasAdopcion;
    }
}