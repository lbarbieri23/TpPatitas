package com.dds.mascotas.model.adapters.hogares;

import com.dds.mascotas.model.Hogar;

import java.util.List;

public interface IHogarAdapter {

    List<Hogar> buscarHogares(CriteriosBusqueda criterios) throws Exception;

}
