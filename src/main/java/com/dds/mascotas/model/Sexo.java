package com.dds.mascotas.model;

import com.dds.mascotas.model.rules.Opcion;
import com.dds.mascotas.model.rules.Pregunta;
import com.dds.mascotas.model.rules.Respuesta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sexos")
public class Sexo implements Respuesta,  Opcion {

    public static final Sexo MASCULINO = new Sexo(1, "MASCULINO");
    public static final Sexo FEMENINO = new Sexo(2, "FEMENINO");

    @Id
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    public Sexo() {

    }

    public Sexo(Integer id, String descripcion) {
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
    public boolean equals(Object another) {
        if (!(another instanceof  Sexo)) {
            return false;
        }

        return this.id.equals(((Sexo)another).getId());
    }


    @Override
    public boolean esIgual(Respuesta respuesta) {
        return respuesta.equals(this);
    }

    @Override
    public Pregunta getPregunta() {
        return new Pregunta() {
            @Override
            public String getPregunta() {
                return "¿Cual es el sexo?";
            }
        };
    }

    @Override
    public Opcion getRespuesta() {
        return this;
    }
}
