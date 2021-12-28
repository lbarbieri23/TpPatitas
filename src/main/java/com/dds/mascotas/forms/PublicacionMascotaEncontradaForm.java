package com.dds.mascotas.forms;

import com.dds.mascotas.model.Hogar;
import com.dds.mascotas.model.PublicacionMascotaEncontrada;
import com.dds.mascotas.model.TipoMascota;

import java.io.Serializable;
import java.util.List;

public class PublicacionMascotaEncontradaForm extends CommonForm implements Serializable {

    private PublicacionMascotaEncontrada publicacion;
    private List<TipoMascota> tipoMascotas;
    private String ubicacion;

    public List<Hogar> getHogares() {
        return hogares;
    }

    public void setHogares(List<Hogar> hogares) {
        this.hogares = hogares;
    }

    private List<Hogar> hogares;

    public Hogar getHogar() {
        return hogar;
    }

    public void setHogar(Hogar hogar) {
        this.hogar = hogar;
    }

    private Hogar hogar;

    public PublicacionMascotaEncontrada getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionMascotaEncontrada publicacion) {
        this.publicacion = publicacion;
    }

    public List<TipoMascota> getTipoMascotas() {
        return tipoMascotas;
    }

    public void setTipoMascotas(List<TipoMascota> tipoMascotas) {
        this.tipoMascotas = tipoMascotas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}