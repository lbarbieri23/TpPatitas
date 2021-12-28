package com.dds.mascotas.forms;

import com.dds.mascotas.model.*;
import com.dds.mascotas.model.rules.Respuesta;

import java.io.Serializable;
import java.util.List;

public class PublicacionForm extends CommonForm implements Serializable {

    private Publicacion publicacion;
    private List<Mascota> misMascotas;
    private Mascota mascota;
    private String successActionMessage;
    private Boolean permiteAcciones = true;

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public String getSuccessActionMessage() {
        return successActionMessage;
    }

    public void setSuccessActionMessage(String successActionMessage) {
        this.successActionMessage = successActionMessage;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public List<Mascota> getMisMascotas() {
        return misMascotas;
    }

    public void setMisMascotas(List<Mascota> misMascotas) {
        this.misMascotas = misMascotas;
    }

    public Boolean getPermiteAcciones() {
        return permiteAcciones;
    }

    public void setPermiteAcciones(Boolean permiteAcciones) {
        this.permiteAcciones = permiteAcciones;
    }
}