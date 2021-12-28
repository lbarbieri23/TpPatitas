package com.dds.mascotas.model;

import com.dds.mascotas.model.rules.Pregunta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "caracteristicas")
public class Caracteristica implements Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caracteristicas_id_seq")
    @SequenceGenerator(name = "caracteristicas_id_seq", sequenceName = "caracteristicas_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_caracteristica")
    private List<OpcionCaracteristica> opciones;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_organizacion")
    private Organizacion organizacion;

    public Caracteristica() {

    }

    public Caracteristica(Organizacion organizacion, Integer id,  String nombre, List<OpcionCaracteristica> opciones) {
        this.id = id;
        this.organizacion = organizacion;
        this.nombre = nombre;
        this.opciones = opciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public List<OpcionCaracteristica> getOpciones() {
        return opciones;
    }

    public void agregarOpcion(OpcionCaracteristica opcion) {
        if (opciones == null) {
            opciones = new ArrayList<>();
        }

        opciones.add(opcion);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Caracteristica)) {
            return false;
        }
        return ((Caracteristica)obj).getId().equals(this.getId());
    }

    @Override
    public String getPregunta() {
        return this.nombre;
    }
}
