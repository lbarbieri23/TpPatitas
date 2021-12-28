package com.dds.mascotas.forms;

import com.dds.mascotas.model.Foto;
import com.dds.mascotas.model.Mascota;

import java.io.Serializable;
import java.util.List;

public class MascotaForm  extends CommonForm implements Serializable {

    private Mascota mascota;

    public Mascota getMascota() {
        return mascota;
    }
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

}
