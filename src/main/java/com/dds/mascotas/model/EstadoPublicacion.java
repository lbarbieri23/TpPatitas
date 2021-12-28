package com.dds.mascotas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados_publicaciones")
public class EstadoPublicacion {

    public static final EstadoPublicacion PENDIENTE = new EstadoPublicacion(1, "PENDIENTE");
    public static final EstadoPublicacion PUBLICADA = new EstadoPublicacion(2, "PUBLICADA");
    public static final EstadoPublicacion EN_PROCESO_RECLAMO = new EstadoPublicacion(3, "PUBLICADA");
    public static final EstadoPublicacion ENTREGADA_AL_DUENIO = new EstadoPublicacion(4, "ENTREGADA AL DUEÑO");
    public static final EstadoPublicacion FINALIZADA = new EstadoPublicacion(5, "FINALIZADA");
    public static final EstadoPublicacion RECHAZADA = new EstadoPublicacion(6, "RECHAZADA");
    public static final EstadoPublicacion CANCELADA = new EstadoPublicacion(7, "CANCELADA");

    @Id
    private Integer id;
    private String descripcion;

    public EstadoPublicacion() {

    }

    public EstadoPublicacion(Integer id, String descripcion) {
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
        if (!(obj instanceof EstadoPublicacion) ) {
            return  false;
        }
        return this.id.equals(((EstadoPublicacion)obj).getId());
    }
}
