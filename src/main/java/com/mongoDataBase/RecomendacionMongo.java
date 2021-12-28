package com.mongoDataBase;

import javax.persistence.*;


@Entity(name = "recomendacionesMongo")
public class RecomendacionMongo {


    @Id
    private Integer idRecomendacion;

    private Integer idPublicacion;
    private Integer idPersona;
    private Integer idPublicacionGeneradora;

    public RecomendacionMongo(Integer idPublicacion, Integer idPersona, Integer idPublicacionGeneradora){
        this.idPublicacion= idPublicacion;
        this.idPersona= idPersona;
        this.idPublicacionGeneradora= idPublicacionGeneradora;
    }

    public RecomendacionMongo(){}

    public Integer getIdPublicacionGeneradora() {
        return idPublicacionGeneradora;
    }

    public void setIdPublicacionGeneradora(Integer idPublicacionGeneradora) {
        this.idPublicacionGeneradora = idPublicacionGeneradora;
    }

    public Integer getId() {
        return idRecomendacion;
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
