package com.dds.mascotas.model;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;


@Entity
@Table(name = "quiero_adoptar_caracteristicas")
public class CaracteristicaQuieroAdoptar {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiero_adoptar_caracteristicas_id_seq")
    @SequenceGenerator(name = "quiero_adoptar_caracteristicas_id_seq", sequenceName = "quiero_adoptar_caracteristicas_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_publicacion")
    private PublicacionQuieroAdoptar publicacion;

    @ManyToOne
    @JoinColumn(name = "id_caracteristica")
    private Caracteristica caracteristica;

    @ManyToOne
    @JoinColumn(name = "id_opcion")
    private OpcionCaracteristica opcion;

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public OpcionCaracteristica getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionCaracteristica opcion) {
        this.opcion = opcion;
    }

    public PublicacionQuieroAdoptar getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionQuieroAdoptar publicacion) {
        this.publicacion = publicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}