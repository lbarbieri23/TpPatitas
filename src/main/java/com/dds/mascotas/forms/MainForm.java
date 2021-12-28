package com.dds.mascotas.forms;

import com.dds.mascotas.model.Publicacion;
import com.dds.mascotas.model.TipoMascota;
import com.dds.mascotas.model.TipoPublicacion;

import java.util.List;

public class MainForm extends CommonForm {

    private List<Publicacion> publicacionList;


    private String searchCriteria;
    private TipoPublicacion tipoPublicacion;


    public List<Publicacion> getPublicacionList() {
        return publicacionList;
    }

    public void setPublicacionList(List<Publicacion> publicacionList) {
        this.publicacionList = publicacionList;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }
}
