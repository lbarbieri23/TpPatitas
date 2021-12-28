package com.dds.mascotas.model;


import com.dds.mascotas.model.rules.Pregunta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "preguntas_adopcion")
public class PreguntaAdopcion implements Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preguntas_adopcion_id_seq")
    @SequenceGenerator(name = "preguntas_adopcion_id_seq", sequenceName = "preguntas_adopcion_id_seq", allocationSize = 1)
    private Integer id;
    private String pregunta;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta")
    private List<OpcionPreguntaAdopcion> opciones;

    private Boolean obligatoria;

    public PreguntaAdopcion() {}

    public PreguntaAdopcion(Integer id, String pregunta, List<OpcionPreguntaAdopcion> opciones) {
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

    public List<OpcionPreguntaAdopcion> getOpciones() {
        return opciones;
    }

    public void agregarOpcion(OpcionPreguntaAdopcion opcion) {
        if (opciones == null) {
            opciones = new ArrayList<>();
        }

        opciones.add(opcion);
    }

    public void setOpciones(List<OpcionPreguntaAdopcion> opciones) {
        this.opciones = opciones;
    }

    public Boolean getObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(Boolean obligatoria) {
        this.obligatoria = obligatoria;
    }
}
