package com.dds.mascotas.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ConfiguracionFoto {

    @Column(name = "conf_foto_alto")
    private Integer alto;

    @Column(name = "conf_foto_ancho")
    private Integer ancho;

    @Column(name = "conf_foto_calidad")
    private String calidad;


    public Integer getAlto() {
        return alto;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }
}
