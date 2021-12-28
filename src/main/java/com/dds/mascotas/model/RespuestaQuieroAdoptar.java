package com.dds.mascotas.model;

import com.dds.mascotas.model.rules.Respuesta;

import javax.persistence.*;

@Entity
@Table(name = "respuestas_quiero_adoptar")
public class RespuestaQuieroAdoptar implements Respuesta  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "respuestas_quiero_adoptar_id_seq")
    @SequenceGenerator(name = "respuestas_quiero_adoptar_id_seq", sequenceName = "respuestas_quiero_adoptar_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private PreguntaQuieroAdoptar pregunta;

    @ManyToOne
    @JoinColumn(name = "id_opcion")
    private OpcionPreguntaQuieroAdoptar respuesta;

    @Column(name = "cumple_condicion")
    public Boolean cumpleCondicion;

    @ManyToOne
    @JoinColumn(name = "id_publicacion")
    private PublicacionQuieroAdoptar publicacion;

    public RespuestaQuieroAdoptar(){}

    public RespuestaQuieroAdoptar(PreguntaQuieroAdoptar pregunta, OpcionPreguntaQuieroAdoptar respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PreguntaQuieroAdoptar getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaQuieroAdoptar pregunta) {
        this.pregunta = pregunta;
    }

    public OpcionPreguntaQuieroAdoptar getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(OpcionPreguntaQuieroAdoptar respuesta) {
        this.respuesta = respuesta;
    }

    public PublicacionQuieroAdoptar getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionQuieroAdoptar publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RespuestaQuieroAdoptar)) {
            return false;
        }
        return ((RespuestaQuieroAdoptar)obj).getRespuesta().equals(this.getRespuesta());
    }

    @Override
    public boolean esIgual(Respuesta respuesta) {
        return this.equals(respuesta);
    }
}
