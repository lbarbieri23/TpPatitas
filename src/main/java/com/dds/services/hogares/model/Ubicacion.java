package com.dds.services.hogares.model;

import com.google.gson.annotations.SerializedName;

public class Ubicacion {

    private String direccion;
    private Double lat;

    @SerializedName(value ="long")
    private Double lon;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
