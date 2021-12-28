package com.dds.mascotas.model;


import com.dds.helpers.IUbicable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizaciones")
public class Organizacion implements IUbicable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizaciones_id_seq")
    @SequenceGenerator(name = "organizaciones_id_seq", sequenceName = "organizaciones_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "organizaciones_tipo_mascota",
            joinColumns = { @JoinColumn(name = "id_organizacion") },
            inverseJoinColumns = { @JoinColumn(name = "id_tipo_mascota") }
    )

    private List<TipoMascota> tiposDeMascota;

    @Embedded
    private ConfiguracionFoto configuracionFotos;

    @Embedded
    private Ubicacion ubicacion;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizacion")
    private List<PreguntaAdopcion> preguntasAdopcion;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizacion")
    private List<PreguntaQuieroAdoptar> preguntasQuieroAdoptar;

    public Organizacion() {

    }

    public Organizacion(Integer id, String descripcion, Ubicacion ubicacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
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

    public List<TipoMascota> getTiposDeMascota() {
        return tiposDeMascota;
    }

    public void setTiposDeMascota(List<TipoMascota> tiposDeMascota) {
        this.tiposDeMascota = tiposDeMascota;
    }

    public ConfiguracionFoto getConfiguracionFotos() {
        return configuracionFotos;
    }

    public void setConfiguracionFotos(ConfiguracionFoto configuracionFotos) {
        this.configuracionFotos = configuracionFotos;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<PreguntaAdopcion> getPreguntasAdopcion() {
        return preguntasAdopcion;
    }

    public void setPreguntasAdopcion(List<PreguntaAdopcion> preguntasAdopcion) {
        this.preguntasAdopcion = preguntasAdopcion;
    }

    public List<PreguntaQuieroAdoptar> getPreguntasQuieroAdoptar() {
        return preguntasQuieroAdoptar;
    }

    public void setPreguntasQuieroAdoptar(List<PreguntaQuieroAdoptar> preguntasQuieroAdoptar) {
        this.preguntasQuieroAdoptar = preguntasQuieroAdoptar;
    }

    public void agregarPreguntaAdopcion(PreguntaAdopcion pregunta) {
        if (preguntasAdopcion == null) {
            preguntasAdopcion = new ArrayList<>();
        }
        preguntasAdopcion.add(pregunta);
    }

    @Override
    public Double getLatitud() {
        return this.ubicacion.getLatitud();
    }

    @Override
    public Double getLongitud() {
        return this.ubicacion.getLongitud();
    }
}
