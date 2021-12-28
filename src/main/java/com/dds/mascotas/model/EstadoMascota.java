package com.dds.mascotas.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados_mascota")
public class EstadoMascota {

    public static final EstadoMascota EN_CASA = new EstadoMascota(1, "EN CASA");
    public static final EstadoMascota PERDIDA = new EstadoMascota(2, "PERDIDA");
    public static final EstadoMascota RESCATADA = new EstadoMascota(3, "RESCATADA");
    public static final EstadoMascota EN_ADOPCION = new EstadoMascota(4, "EN ADOPCION");

    @Id
    private Integer id;
    private String descripcion;

    public EstadoMascota() {
    }

    public EstadoMascota(Integer id, String descripcion) {
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EstadoMascota) ) {
            return  false;
        }
        return this.id.equals(((EstadoMascota)obj).getId());
    }
}
