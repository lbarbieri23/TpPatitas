package com.dds.mascotas.forms;

import com.dds.mascotas.model.Mascota;
import com.dds.mascotas.model.Publicacion;

import java.io.Serializable;
import java.util.List;

public class MisPublicacionesForm extends PublicacionForm implements Serializable {

    private List<Publicacion> publicaciones;
    private List<Publicacion> publicacionesPendienteAccion;

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Publicacion> getPublicacionesPendienteAccion() {
        return publicacionesPendienteAccion;
    }

    public void setPublicacionesPendienteAccion(List<Publicacion> publicacionesPendienteAccion) {
        this.publicacionesPendienteAccion = publicacionesPendienteAccion;
    }
}
