package com.dds.mascotas.model.adapters.hogares;

import com.dds.mascotas.model.Hogar;
import com.dds.mascotas.model.Ubicacion;
import com.dds.services.hogares.HogarService;

import java.util.ArrayList;
import java.util.List;

public class DDSHogarAdapter implements IHogarAdapter {

    private List<Hogar> convert(List<com.dds.services.hogares.model.Hogar> hogares) {
        List<Hogar> results = new ArrayList<>();
        for (com.dds.services.hogares.model.Hogar h : hogares) {
            Hogar item = new Hogar();
            item.setNombre(h.getNombre());
            item.setUbicacion(new Ubicacion(h.getUbicacion().getLat(), h.getUbicacion().getLon()));
            item.setDireccion(h.getUbicacion().getDireccion());
            item.setTelefono(h.getTelefono());
            item.setApiId(h.getId());
            if(h.getLugaresDisponibles() == null) {
                h.setLugaresDisponibles(0);
            }
            item.setCompleto(h.getLugaresDisponibles() == 0);
            item.setPoseePatio(h.getPatio());
            item.setAceptaGatos(h.getAdmisiones().getGatos());
            item.setAceptaPerros(h.getAdmisiones().getPerros());
            if (h.getCaracteristicas() != null) {
                item.agregarCaracteristicas(h.getCaracteristicas());
            }
            results.add(item);
        }
        return results;
    }

    @Override
    public List<Hogar> buscarHogares(CriteriosBusqueda criterios) throws Exception {
        List<com.dds.services.hogares.model.Hogar> hogares =new HogarService("4Xrkv0yBaeI9VykNbkh3ATWwfPqsZopLVx4V7alkbcsI2vHTRytWorA9hcYq").getHogares();
        List<Hogar> results = convert(hogares);
        return criterios.filter(results);
    }


    /*
    public static void main(String[] args) throws Exception {
        CriteriosBusqueda criterios =
                    new CriteriosBusqueda.CriterioBuilder()
                            .setAceptaPerros(true)
                            .setAceptaGatos(false)
                            .setConPatio(false)
                            .build();
    }*/





}