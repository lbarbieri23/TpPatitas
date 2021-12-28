package com.dds.mascotas.model;


import com.dds.mascotas.model.rules.Pregunta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "preguntas_quiero_adoptar")
public class PreguntaQuieroAdoptar implements Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preguntas_quiero_adoptar_id_seq")
    @SequenceGenerator(name = "preguntas_quiero_adoptar_id_seq", sequenceName = "preguntas_quiero_adoptar_id_seq", allocationSize = 1)
    private Integer id;

    private String pregunta;

    @ManyToOne
    @JoinColumn(name = "id_organizacion")
    private Organizacion organizacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta")
    private List<OpcionPreguntaQuieroAdoptar> opciones;


    @Column(name = "es_excluyente")
    public Boolean esExcluyente;

    public PreguntaQuieroAdoptar(){}

    public PreguntaQuieroAdoptar(Integer id, String pregunta, List<OpcionPreguntaQuieroAdoptar> opciones) {
        this.id = id;
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<OpcionPreguntaQuieroAdoptar> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionPreguntaQuieroAdoptar> opciones) {
        this.opciones = opciones;
    }

    public void agregarOpcion(OpcionPreguntaQuieroAdoptar opcion) {
        if (opciones == null) {
            opciones = new ArrayList<>();
        }

        opciones.add(opcion);
    }

}
