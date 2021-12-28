package com.dds.mascotas.model;

import com.dds.helpers.IUbicable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Ubicacion implements IUbicable {

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

    public Ubicacion(){}

    public Ubicacion(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Double getLatitud() { return latitud; }

    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
