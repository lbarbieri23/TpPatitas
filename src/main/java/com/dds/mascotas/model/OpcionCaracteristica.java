package com.dds.mascotas.model;


import com.dds.mascotas.model.rules.Opcion;

import javax.persistence.*;

@Entity
@Table(name = "opciones_caracteristicas")
public class OpcionCaracteristica implements Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opciones_caracteristicas_id_seq")
    @SequenceGenerator(name = "opciones_caracteristicas_id_seq", sequenceName = "opciones_caracteristicas_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    public OpcionCaracteristica() {

    }

    public OpcionCaracteristica(Integer id, String descripcion) {
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
    public boolean equals(Object obj) {
        if (!(obj instanceof OpcionCaracteristica)) {
            return false;
        }
        return ((OpcionCaracteristica)obj).getId().equals(this.getId());
    }
}
