package com.dds.mascotas.model.adapters.hogares;

import com.dds.mascotas.model.Caracteristica;
import com.dds.mascotas.model.Ubicacion;

import java.util.List;

public interface ICriterioBuilder {
    ICriterioBuilder setUbicacion(Ubicacion ubicacion, Double radio);
    ICriterioBuilder setAceptaGatos(Boolean value);
    ICriterioBuilder setAceptaPerros(Boolean value);
    ICriterioBuilder setConPatio(Boolean value);
    ICriterioBuilder addFiltroCaracteristica(String caracteristica);
}

