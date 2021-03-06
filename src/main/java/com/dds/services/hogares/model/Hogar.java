package com.dds.services.hogares.model;

import java.util.List;

public class Hogar {

    private String id;
    private String nombre;
    private Ubicacion ubicacion;
    private String telefono;
    private Admisiones admisiones;
    private Integer capacidad;
    private Integer lugaresDisponibles;
    private Boolean patio;
    private List<String> caracteristicas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Admisiones getAdmisiones() {
        return admisiones;
    }

    public void setAdmisiones(Admisiones admisiones) {
        this.admisiones = admisiones;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    public void setLugaresDisponibles(Integer lugaresDisponibles) {
        this.lugaresDisponibles = lugaresDisponibles;
    }

    public Boolean getPatio() {
        return patio;
    }

    public void setPatio(Boolean patio) {
        this.patio = patio;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
