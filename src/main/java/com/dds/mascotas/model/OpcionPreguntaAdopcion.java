package com.dds.mascotas.model;

import com.dds.mascotas.model.rules.Opcion;

import javax.persistence.*;

@Entity
@Table(name = "opciones_preguntas_adopcion")
public class OpcionPreguntaAdopcion implements Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preguntas_adopcion_id_seq")
    @SequenceGenerator(name = "preguntas_adopcion_id_seq", sequenceName = "preguntas_adopcion_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "opcion")
    private String descripcion;

    public OpcionPreguntaAdopcion() {

    }

    public OpcionPreguntaAdopcion(Integer id, String descripcion) {
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
        if (!(obj instanceof OpcionPreguntaAdopcion)) {
            return false;
        }
        return ((OpcionPreguntaAdopcion)obj).getId().equals(this.getId());
    }
}
