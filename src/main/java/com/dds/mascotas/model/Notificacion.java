package com.dds.mascotas.model;

import java.util.ArrayList;
import java.util.List;

public class Notificacion {

    private Integer id;
    private Contacto para;
    private String titulo;
    private String descripcion;
    private List<Foto> fotos = new ArrayList<>();
    private Ubicacion ubicacion;
    private Boolean enviada;

    public Notificacion() {
        this.enviada = false;
    }

    public Notificacion(String titulo, String descripcion, List<Foto> fotos, Ubicacion ubicacion) {
        this.enviada = false;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fotos = fotos;
        this.ubicacion = ubicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contacto getPara() {
        return para;
    }

    public void setPara(Contacto para) {
        this.para = para;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void agregarFoto(Foto foto) {
        this.fotos.add(foto);
    }

    public Boolean getEnviada() {
        return enviada;
    }

    public void marcarComoEnviada() {
        this.enviada = true;
    }
}
