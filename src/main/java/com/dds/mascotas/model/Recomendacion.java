package com.dds.mascotas.model;

import com.dds.helpers.PublicacionReglasHelper;
import com.dds.mascotas.model.rules.*;
import com.dds.mascotas.model.security.Rol;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "recomendaciones")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recomendaciones_id_recomendacion_seq")
    @SequenceGenerator(name = "recomendaciones_id_recomendacion_seq", sequenceName = "recomendaciones_id_recomendacion_seq", allocationSize = 1)
    @Column(name = "id_recomendacion")
    private Integer idRecomendacion;

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "id_publicacion")
    private Integer idPublicacion;

    @Column(name = "id_publicacion_generadora")
    private Integer idPublicacionGeneradora;

    public Integer getId() {
        return idRecomendacion;
    }
    public Integer getIdPublicacionGeneradora() {
        return idPublicacionGeneradora;
    }

    public void setIdPublicacionGeneradora(Integer idPublicacionGeneradora) {
        this.idPublicacionGeneradora = idPublicacionGeneradora;
    }
    public void setId(Integer idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    public Integer getIdPersona() {
        return this.idPersona;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
    public Integer getIdPublicacion() {
        return this.idPublicacion;
    }
}
