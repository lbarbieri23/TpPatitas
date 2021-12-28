package com.dds.mascotas.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipos_mascota")
public class TipoMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caracteristicas_id_seq")
    @SequenceGenerator(name = "caracteristicas_id_seq", sequenceName = "caracteristicas_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "caracteristicas_tipos_mascota",
            joinColumns = {@JoinColumn(name = "id_tipo_mascota")},
            inverseJoinColumns = {@JoinColumn(name = "id_caracteristica")}
    )
    private List<Caracteristica> caracteristicas = new ArrayList<>();

    public TipoMascota(){}

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

    public void agregarCaracteristica(Caracteristica caracteristica) {
        this.caracteristicas.add(caracteristica);
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public TipoMascota(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }


}
