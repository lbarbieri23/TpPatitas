package com.dds.hogar;

import com.dds.mascotas.model.Caracteristica;
import com.dds.mascotas.model.Hogar;
import com.dds.mascotas.model.Ubicacion;
import com.dds.mascotas.model.adapters.hogares.CriteriosBusqueda;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HogarTest {

    List<Hogar> hogares = null;

    @Before
    public void generarEscenario() {
        hogares = new ArrayList<>();

        List<String> caracteristicas = new ArrayList<>();

        caracteristicas.add("MANSO");
        caracteristicas.add("PELO CORTO");

        //CON PATIO
        //PERROS Y GATOS
        hogares.add(crearHogar(true, true, true, false, new Ubicacion(0d,0d), new ArrayList<>()));
        //GATOS
        hogares.add(crearHogar(true, false, true, false, new Ubicacion(0d,0d), new ArrayList<>()));
        //PERROS
        hogares.add(crearHogar(false, true, true, false, new Ubicacion(0d,0d), caracteristicas));
        hogares.add(crearHogar(false, true, true, false, new Ubicacion(0d,0d), caracteristicas));


        //SIN PATIO
        //GATOS
        hogares.add(crearHogar(true, false, false, false, new Ubicacion(0d,0d), new ArrayList<>()));
        hogares.add(crearHogar(true, false, false, false, new Ubicacion(0d,0d), new ArrayList<>()));
        //PERROS
        hogares.add(crearHogar(false, true, false, false, new Ubicacion(0d,0d), new ArrayList<>()));


    }

    private Hogar crearHogar(Boolean aceptaGatos, Boolean aceptaPerros, Boolean tienePatio, Boolean completo, Ubicacion ubicacion, List<String> caracteristicas) {
        Hogar h = new Hogar();
        h.setAceptaGatos(aceptaGatos);
        h.setAceptaPerros(aceptaPerros);
        h.setPoseePatio(tienePatio);
        h.setCompleto(completo);
        h.setUbicacion(ubicacion);
        h.agregarCaracteristicas(caracteristicas);
        return h;
    }

    @Test
    public void filtarGatosNoPerroYPatio() {
        CriteriosBusqueda criterios =
                new CriteriosBusqueda.CriterioBuilder()
                        .setAceptaPerros(false)
                        .setAceptaGatos(true)
                        .setConPatio(true)
                        .build();

        List<Hogar> result = criterios.filter(hogares);
        assertEquals(result.size(), 1);
    }

    @Test
    public void filtarGatosYPatio() {
        CriteriosBusqueda criterios =
                new CriteriosBusqueda.CriterioBuilder()
                        .setAceptaGatos(true)
                        .setConPatio(true)
                        .build();

        List<Hogar> result = criterios.filter(hogares);
        assertEquals(result.size(), 2);
    }

    @Test
    public void filtarGatosYPerrosSinImportarPatio() {
        CriteriosBusqueda criterios =
                new CriteriosBusqueda.CriterioBuilder()
                        .setAceptaGatos(true)
                        .setAceptaPerros(true)
                        .setConPatio(true)
                        .build();

        List<Hogar> result = criterios.filter(hogares);
        assertEquals(result.size(), 1);
    }

    @Test
    public void filtarGatosOPerrosConPatio() {
        CriteriosBusqueda criterios =
                new CriteriosBusqueda.CriterioBuilder()
                        .setConPatio(true)
                        .build();

        List<Hogar> result = criterios.filter(hogares);
        assertEquals(result.size(), 4);
    }


    @Test
    public void filtarPerrosConCaracteristica() {
        CriteriosBusqueda criterios =
                new CriteriosBusqueda.CriterioBuilder()
                        .setAceptaPerros(true)
                        .setAceptaGatos(false)
                        .addFiltroCaracteristica("MANSO")
                        .build();

        List<Hogar> result = criterios.filter(hogares);
        assertEquals(result.size(), 2);
    }


    @Test
    public void filtarPerrosCon2Caracteristica() {
        CriteriosBusqueda criterios =
                new CriteriosBusqueda.CriterioBuilder()
                        .setAceptaPerros(true)
                        .setAceptaGatos(false)
                        .addFiltroCaracteristica("MANSO")
                        .addFiltroCaracteristica("PEQUEÑO")
                        .build();

        List<Hogar> result = criterios.filter(hogares);
        assertEquals(result.size(), 0);
    }


}
