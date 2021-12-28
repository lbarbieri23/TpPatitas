package com.dds.mascotas.model;


import com.dds.mascotas.model.rules.Opcion;
import com.dds.mascotas.model.rules.Pregunta;
import com.dds.mascotas.model.rules.Respuesta;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CaracteristicaValor implements Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mascotas_caracteristicas_id_seq")
    @SequenceGenerator(name = "mascotas_caracteristicas_id_seq", sequenceName = "mascotas_caracteristicas_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_caracteristica")
    private Caracteristica caracteristica;

    @ManyToOne
    @JoinColumn(name = "id_opcion")
    private OpcionCaracteristica valor;


    public CaracteristicaValor() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public OpcionCaracteristica getValor() {
        return valor;
    }

    public void setValor(OpcionCaracteristica valor) {
        this.valor = valor;
    }

    public CaracteristicaValor(Caracteristica caracteristica, OpcionCaracteristica valor) {
        this.caracteristica = caracteristica;
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaracteristicaValor)) {
            return false;
        }
        return ((CaracteristicaValor)obj).getValor().equals(this.getValor());
    }

    @Override
    public boolean esIgual(Respuesta respuesta) {
        if (!(respuesta instanceof CaracteristicaValor)) {
            return false;
        }

        CaracteristicaValor caracteristicaMascota = (CaracteristicaValor) respuesta;
        return caracteristicaMascota.equals((Object)this);
    }



    @Override
    public Pregunta getPregunta() {
        return caracteristica;
    }

    @Override
    public Opcion getRespuesta() {
        return valor;
    }
}