package com.dds.mascotas.model;


import javax.persistence.*;


@Entity
@Table(name = "mascotas_caracteristicas")
public class CaracteristicaMascota extends CaracteristicaValor {

    @ManyToOne
    @JoinColumn(name = "id_mascota")
    private Mascota mascota;


    public CaracteristicaMascota() {
        super();
    }

    public CaracteristicaMascota(Caracteristica caracteristica, OpcionCaracteristica valor) {
        super(caracteristica, valor);
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}