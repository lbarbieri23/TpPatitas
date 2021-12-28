package com.dds.mascotas.model.adapters.hogares;

import com.dds.helpers.IUbicable;
import com.dds.helpers.UbicacionHelper;
import com.dds.mascotas.model.Hogar;
import com.dds.mascotas.model.Ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CriteriosBusqueda {

    private Ubicacion ubicacion;
    private Double radioUbicacion;
    private Boolean aceptaGatos;
    private Boolean aceptaPerros;
    private Boolean conPatio;
    private List<String> caracteristicas;


    private CriteriosBusqueda(Ubicacion ubicacion, Double radioUbicacion, Boolean aceptaGatos, Boolean aceptaPerros, Boolean conPatio, List<String> caracteristicas) {
        this.ubicacion = ubicacion;
        this.radioUbicacion = radioUbicacion;
        this.aceptaGatos = aceptaGatos;
        this.aceptaPerros = aceptaPerros;
        this.conPatio = conPatio;
        this.caracteristicas = caracteristicas;
    }

    public List<Hogar> filter(List<Hogar> hogares) {
        List<Hogar> results = hogares;

        if (aceptaGatos != null) {
            results = results.stream()
                    .filter(x -> x.getAceptaGatos() == aceptaGatos).collect(Collectors.toList());
        }

        if (aceptaPerros != null) {
            results = results.stream()
                    .filter(x -> x.getAceptaPerros() == aceptaPerros).collect(Collectors.toList());
        }

        if (conPatio != null) {
            results = results.stream()
                    .filter(x -> x.getPoseePatio() == conPatio).collect(Collectors.toList());
        }

        if (caracteristicas != null) {
            for (String c : caracteristicas) {
                results = results.stream()
                        .filter(x -> x.getCaracteristicas().contains(c)).collect(Collectors.toList());
            }
        }

        if (ubicacion != null && radioUbicacion > 0) {
            List<IUbicable> filteredResults = UbicacionHelper.getInstance().obtenerUbicacionEnRadio(results.stream().map(x -> (IUbicable)x).collect(Collectors.toList()), ubicacion, radioUbicacion);
            results = filteredResults.stream().map(x -> (Hogar)x).collect(Collectors.toList());
        }

        return results;
    }

    public static final class CriterioBuilder implements ICriterioBuilder {
        private Ubicacion ubicacion;
        private Double radioUbicacion;
        private Boolean aceptaGatos;
        private Boolean aceptaPerros;
        private Boolean conPatio;
        private List<String> caracteristicas = new ArrayList<>();

        @Override
        public CriterioBuilder setUbicacion(Ubicacion ubicacion, Double radio) {
            this.ubicacion = ubicacion;
            this.radioUbicacion = radio;
            return this;
        }

        @Override
        public CriterioBuilder setAceptaGatos(Boolean value) {
            this.aceptaGatos = value;
            return this;
        }

        @Override
        public CriterioBuilder setAceptaPerros(Boolean value) {
            this.aceptaPerros = value;
            return this;
        }

        @Override
        public CriterioBuilder setConPatio(Boolean value) {
            this.conPatio = value;
            return this;
        }

        @Override
        public CriterioBuilder addFiltroCaracteristica(String caracteristica) {
            caracteristicas.add(caracteristica);
            return this;
        }

        public CriteriosBusqueda build() {
            return new CriteriosBusqueda(ubicacion, radioUbicacion, aceptaGatos, aceptaPerros, conPatio, caracteristicas);
        }
    }


}
