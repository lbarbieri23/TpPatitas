package com.dds.mascota;

import static org.junit.Assert.assertEquals;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.notifications.FormaNotificacion;
import com.dds.mascotas.model.security.Rol;
import com.dds.mascotas.model.security.Usuario;
import org.junit.Before;
import org.junit.Test;

public class MascotaEncontradaConQrTest {

    private Persona duenio;
    private Persona rescatista;


    @Before
    public void generarEscenario() {
        Organizacion organizacion = new Organizacion(1, "organizacion", new Ubicacion(0d,0d));

        Rol rol = new Rol(3, "Usuario");
        Usuario userDuenio = new Usuario("duenio", rol);
        Usuario userRescatista = new Usuario("rescatista", rol);

        Contacto contacto = new Contacto();
        contacto.setNombre("Diego");
        contacto.agregarFormaNotificacion(new MockNotificacionEnviada());

        TipoMascota tipoMascota = new TipoMascota(1, "GATO");
        Mascota bolaDeNieve = new Mascota(tipoMascota);

        duenio = new Persona(userDuenio, contacto, organizacion);
        duenio.agregarMascota(bolaDeNieve);

        contacto = new Contacto();
        contacto.setNombre("Pedro");
        contacto.agregarFormaNotificacion(new MockNotificacionEnviada());
        rescatista = new Persona(userRescatista, contacto, organizacion);
    }

    @Test
    public void rescatarMascota()  {
        Mascota bolaDeNievePerdido = duenio.getMisMascotas().get(0);
        bolaDeNievePerdido.pasarAPerdida();
        assertEquals(bolaDeNievePerdido.getEstado(),EstadoMascota.PERDIDA);

        MascotaEncontrada mascotaEncontrada = new MascotaEncontrada(bolaDeNievePerdido, null, new Ubicacion(0d,0d), "Descripcion fisica");
        mascotaEncontrada.rescatar(rescatista);

        assertEquals(bolaDeNievePerdido.getEstado(), EstadoMascota.RESCATADA);
        assertEquals(mascotaEncontrada.getEncontradaPor(), rescatista);
    }

    @Test
    public void recuperarMascota()  {
        //GENERO MASCOTA PERDIDA
        Mascota bolaDeNievePerdido = duenio.getMisMascotas().get(0);
        bolaDeNievePerdido.pasarAPerdida();
        assertEquals(bolaDeNievePerdido.getEstado(),EstadoMascota.PERDIDA);

        //LA RESCATO A LA MASCOTA
        MascotaEncontrada mascotaEncontrada = new MascotaEncontrada(bolaDeNievePerdido, null, new Ubicacion(0d,0d), "Descripcion fisica");
        mascotaEncontrada.rescatar(rescatista);
        assertEquals(bolaDeNievePerdido.getEstado(), EstadoMascota.RESCATADA);
        assertEquals(mascotaEncontrada.getEncontradaPor(), rescatista);

        mascotaEncontrada.recuperar();
        assertEquals(bolaDeNievePerdido.getEstado(), EstadoMascota.EN_CASA);
    }


    private class MockNotificacionEnviada extends FormaNotificacion {

        @Override
        public void notificar(Notificacion notificacion) throws NotificacionException {
            notificacion.marcarComoEnviada();
        }
    }

}
