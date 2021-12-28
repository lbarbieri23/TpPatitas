package com.dds.mascotas.forms;

import com.dds.mascotas.model.Mascota;
import com.dds.mascotas.model.Persona;
import com.dds.mascotas.model.TipoDocumento;

import java.io.Serializable;
import java.util.List;

public class PerfilForm extends CommonForm implements Serializable {

    private Persona persona;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
