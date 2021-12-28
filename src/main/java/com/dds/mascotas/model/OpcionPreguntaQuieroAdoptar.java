package com.dds.mascotas.model;

import com.dds.mascotas.model.rules.Opcion;

import javax.persistence.*;

@Entity
@Table(name = "opciones_preguntas_quiero_adoptar")
public class OpcionPreguntaQuieroAdoptar implements Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opciones_preguntas_quiero_adoptar_id_seq")
    @SequenceGenerator(name = "opciones_preguntas_quiero_adoptar_id_seq", sequenceName = "opciones_preguntas_quiero_adoptar_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "opcion")
    private String descripcion;

    public OpcionPreguntaQuieroAdoptar() {

    }

    public OpcionPreguntaQuieroAdoptar(Integer id, String descripcion) {
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
        if (!(obj instanceof OpcionPreguntaQuieroAdoptar)) {
            return false;
        }
        return ((OpcionPreguntaQuieroAdoptar)obj).getId().equals(this.getId());
    }
}
