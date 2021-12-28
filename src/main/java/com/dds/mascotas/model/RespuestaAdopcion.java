package com.dds.mascotas.model;


import com.dds.mascotas.model.rules.Opcion;
import com.dds.mascotas.model.rules.Respuesta;

import javax.persistence.*;

@Entity
@Table(name = "respuestas_adopcion")
    public class RespuestaAdopcion implements Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "respuestas_adopcion_id_seq")
    @SequenceGenerator(name = "respuestas_adopcion_id_seq", sequenceName = "respuestas_adopcion_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private PreguntaAdopcion pregunta;

    @ManyToOne
    @JoinColumn(name = "id_opcion")
    private OpcionPreguntaAdopcion respuesta;

    @Column(name = "cumple_condicion")
    public Boolean cumpleCondicion;


    @ManyToOne
    @JoinColumn(name = "id_publicacion")
    private PublicacionMascotaEnAdopcion publicacion;


    public RespuestaAdopcion(){}

    public RespuestaAdopcion(PreguntaAdopcion pregunta, OpcionPreguntaAdopcion respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PreguntaAdopcion getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaAdopcion pregunta) {
        this.pregunta = pregunta;
    }

    public OpcionPreguntaAdopcion getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(OpcionPreguntaAdopcion respuesta) {
        this.respuesta = respuesta;
    }

    public PublicacionMascotaEnAdopcion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionMascotaEnAdopcion publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RespuestaAdopcion)) {
            return  false;
        }

        RespuestaAdopcion r = (RespuestaAdopcion) obj;

        return  r.getRespuesta().equals(this.getRespuesta());
    }

    @Override
    public boolean esIgual(Respuesta respuesta) {
        if (!(respuesta instanceof RespuestaAdopcion)) {
            return  false;
        }

        return ((RespuestaAdopcion)respuesta).equals((Object)this);
    }
}
