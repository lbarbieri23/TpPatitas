package com.dds.persona;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.notifications.FormaNotificacion;
import com.dds.mascotas.model.security.Rol;
import com.dds.mascotas.model.security.Usuario;

import org.junit.Before;
import org.junit.Test;

public class PersonaTest {

    private Usuario usuario;

    @Before
    public void crearUsuario() {
        Rol rol = new Rol(1, "Usuario");
        this.usuario = new Usuario("pier", rol);
    }

    @Test
    public void crearPersona(){
        Organizacion organizacion = new Organizacion(1, "organizacion", new Ubicacion(0d,0d));
        Contacto contacto = new Contacto();
        Persona persona = new Persona(this.usuario, contacto, organizacion);
        persona.setNombre("Jean Pierre");
        assertEquals(persona.getNombre(), "Jean Pierre");
    }

    @Test
    public void notificar() {
        Contacto contacto = new Contacto();
        contacto.setNombre("Diego");
        contacto.setApellido("Perez");
        contacto.setEmail("diegoperez@algo.com");
        contacto.agregarFormaNotificacion(new MockNotificacionEnviada());

        Notificacion notificacion = new Notificacion("Titulo", "Descripcion", null, new Ubicacion(0d, 0d));
        Organizacion organizacion = new Organizacion(1, "organizacion", new Ubicacion(0d,0d));
        Persona persona = new Persona(this.usuario, contacto, organizacion);
        persona.notificarTodos(notificacion);
        assertTrue(notificacion.getEnviada());
    }


    private class MockNotificacionEnviada extends FormaNotificacion {

        @Override
        public void notificar(Notificacion notificacion) throws NotificacionException {
            notificacion.marcarComoEnviada();
        }
    }
}
