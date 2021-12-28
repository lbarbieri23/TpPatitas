package com.dds.persona;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.dds.mascotas.model.TipoMascota;
import com.dds.mascotas.model.Ubicacion;
import com.dds.mascotas.model.security.Rol;
import com.dds.mascotas.model.security.Usuario;
import com.dds.mascotas.model.Contacto;
import com.dds.mascotas.model.Mascota;
import com.dds.mascotas.model.Organizacion;
import com.dds.mascotas.model.Persona;
import com.dds.mascotas.model.Foto;
import com.dds.mascotas.model.PublicacionMascotaEncontrada;

import org.junit.Test;

public class VoluntarioTest {

    @Test
    public void crearVoluntario(){
        Organizacion organizacion = new Organizacion(1, "organizacion", new Ubicacion(0d,0d));

        Rol rol = new Rol(3, "Voluntario");
        Usuario usuario = new Usuario("Jean", rol);
        Contacto contacto = new Contacto();
        Persona persona = new Persona(usuario, contacto, organizacion);
        persona.setNombre("Jean Piere");

        assertEquals(persona.getNombre(), "Jean Piere");

    }

    @Test
    public void esVoluntario(){
        Organizacion organizacion = new Organizacion(1, "organizacion", new Ubicacion(0d,0d));

        Rol rol = new Rol(2, "Voluntario");
        Usuario usuario = new Usuario("Jean", rol);
        Contacto contacto = new Contacto();
        Persona persona = new Persona(usuario, contacto, organizacion);
        assertNotEquals(usuario.esVoluntario(), Boolean.TRUE);
    }

    @Test
    public void aprobarPublicacion(){
        Ubicacion ubicacionOrganicacion = new Ubicacion(40.2,42.3);
        Organizacion organizacion = new Organizacion(1, "Animal Planet",ubicacionOrganicacion);
        PublicacionMascotaEncontrada publicacion;
        Rol rol = new Rol(3, "Voluntario");
        Usuario usuario = new Usuario("Jean", rol);
        Contacto contacto = new Contacto();
        Persona persona = new Persona(usuario, contacto, organizacion);

        TipoMascota tipoMascota = new TipoMascota(1, "perro");
        Mascota mascota = new Mascota(tipoMascota);
        byte[] byteValue = new byte[100];
        mascota.agregarFoto(new Foto("foto 1",byteValue));

        Ubicacion ubicacionMascotaEncontrada = new Ubicacion(40.2,42.4);


        publicacion = new PublicacionMascotaEncontrada(persona, mascota.getFotos(), "se encontraba dentro de un contenedor", ubicacionMascotaEncontrada, mascota);

        publicacion.aprobar(usuario);

        assertEquals(publicacion.getEstado().getDescripcion(), "PUBLICADA");
    }

}

