package com.dds.mascota;

import static org.junit.Assert.assertEquals;

import com.dds.mascotas.model.EstadoMascota;
import com.dds.mascotas.model.Foto;
import com.dds.mascotas.model.Mascota;
import com.dds.mascotas.model.TipoMascota;

import org.junit.Test;

public class MascotaTest {

    @Test
    public void crearMascota(){
        TipoMascota tipoMascota = new TipoMascota(1, "perro");
        Mascota mascota = new Mascota(tipoMascota);
        byte[] byteValue = new byte[100];

        mascota.setNombre("Firulais");
        mascota.agregarFoto(new Foto("foto 1",byteValue));
        mascota.agregarFoto(new Foto("foto 2",byteValue));

        assertEquals(mascota.getNombre(), "Firulais");
        assertEquals(mascota.getFotos().size(),2);
        assertEquals(mascota.getEstado(), EstadoMascota.EN_CASA);
    }
    
}
