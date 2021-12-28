package com.dds.mascotas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_publicacion")
public class TipoPublicacion {


    public static final TipoPublicacion MASCOTA_PERDIDA = new TipoPublicacion(1, "Mascota Perdida");
    public static final TipoPublicacion MASCOTA_ENCONTRADA = new TipoPublicacion(2, "Mascota Encontrada");
    public static final TipoPublicacion MASCOTA_EN_ADOPCION = new TipoPublicacion(3, "Mascota en Adopcion");
    public static final TipoPublicacion QUIERO_ADOPTAR = new TipoPublicacion(4, "Quiero adoptar");


    @Id
    private Integer id;
    private String descripcion;

    public TipoPublicacion() {

    }

    public TipoPublicacion(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TipoPublicacion) ) {
            return  false;
        }
        return this.id.equals(((TipoPublicacion)obj).getId());
    }
}
