package com.dds.publicacion;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.notifications.FormaNotificacion;
import com.dds.mascotas.model.security.Rol;
import com.dds.mascotas.model.security.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PublicacionTest {

    private final TipoMascota tipoMascota = new TipoMascota(1, "PERRO");
    private Usuario voluntario;
    private Persona rescatista;
    private Persona duenio;
    private Mascota mascotaPerdida;
    private Organizacion organizacion;

    @Before
    public void generarEscenario() {
        generarOrganizacion();
        generarRescatista();
        generarDuenio();
        generarMascotaPerdida();
        generarVoluntario();
    }

    private void generarVoluntario() {
        Rol rol = new Rol(3, "Voluntario");
        voluntario = new Usuario("diego", rol);
    }

    private void generarOrganizacion() {
        Ubicacion ubicacionOrganicacion = new Ubicacion(38.50,39.50);
        organizacion = new Organizacion(1, "Asociación 1",ubicacionOrganicacion);
    }

    private void generarMascotaPerdida() {
        mascotaPerdida = new Mascota(tipoMascota);
        mascotaPerdida.pasarAPerdida();
    }

    private void generarRescatista() {
        Rol rol = new Rol(1, "Usuario");
        Usuario usuario = new Usuario("pier", rol);
        Contacto contacto = new Contacto();
        contacto.agregarFormaNotificacion(new MockNotificacionEnviada());
        rescatista = new Persona(usuario, contacto, organizacion);
        rescatista.setNombre("Juan");
    }

    private void generarDuenio() {
        Rol rol = new Rol(1, "Usuario");
        Usuario usuario = new Usuario("Pedro", rol);
        Contacto contacto = new Contacto();
        contacto.agregarFormaNotificacion(new MockNotificacionEnviada());
        duenio = new Persona(usuario, contacto, organizacion);
        duenio.setNombre("Pedro");

        Mascota ayudanteDeSanta = new Mascota(tipoMascota);
        ayudanteDeSanta.pasarAPerdida();
        duenio.agregarMascota(ayudanteDeSanta);
    }

    @Test
    public void generarNuevaPublicacion(){
        List<Foto> fotosMascotaEncontrada = new ArrayList<>();
        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(rescatista, fotosMascotaEncontrada, "La mascota se rescató en un parque", new Ubicacion(0d, 0d), mascotaPerdida);

        assertEquals(publicacion.getMascota(), mascotaPerdida);
        assertEquals(publicacion.getPublicadaPor(), rescatista);
        assertEquals(publicacion.getEstado(), EstadoPublicacion.PENDIENTE);
    }

    @Test
    public void aprobar(){
        List<Foto> fotosMascotaEncontrada = new ArrayList<>();
        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(rescatista, fotosMascotaEncontrada, "La mascota se rescató en un parque", new Ubicacion(0d, 0d), mascotaPerdida);
        publicacion.aprobar(voluntario);
        assertEquals(publicacion.getEstado(), EstadoPublicacion.PUBLICADA);
        assertEquals(publicacion.getAprobadaPor(), voluntario);
    }

    @Test
    public void rechazar(){
        List<Foto> fotosMascotaEncontrada = new ArrayList<>();
        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(rescatista, fotosMascotaEncontrada, "La mascota se rescató en un parque", new Ubicacion(0d, 0d), mascotaPerdida);
        publicacion.rechazar(voluntario);
        assertEquals(publicacion.getEstado(), EstadoPublicacion.RECHAZADA);
        assertEquals(publicacion.getRechazadaPor(), voluntario);
    }

    @Test
    public void cancelar() throws Exception {
        List<Foto> fotosMascotaEncontrada = new ArrayList<>();
        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(rescatista, fotosMascotaEncontrada, "La mascota se rescató en un parque", new Ubicacion(0d, 0d), mascotaPerdida);
        publicacion.setEstado(EstadoPublicacion.PUBLICADA);
        publicacion.aprobar(voluntario);
        publicacion.cancelar();
        assertEquals(publicacion.getEstado(), EstadoPublicacion.CANCELADA);
    }

    @Test
    public void reclamada() throws Exception{
        List<Foto> fotosMascotaEncontrada = new ArrayList<>();
        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(rescatista, fotosMascotaEncontrada, "La mascota se rescató en un parque", new Ubicacion(0d, 0d), mascotaPerdida);
        publicacion.aprobar(voluntario);
        publicacion.reclamar(duenio);
        assertEquals(publicacion.getEstado(), EstadoPublicacion.EN_PROCESO_RECLAMO);
        assertEquals(publicacion.getReclamadaPor(), duenio);
    }

    @Test
    public void rechazarReclamo()  throws Exception{
        List<Foto> fotosMascotaEncontrada = new ArrayList<>();
        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(rescatista, fotosMascotaEncontrada, "La mascota se rescató en un parque", new Ubicacion(0d, 0d), mascotaPerdida);
        publicacion.aprobar(voluntario);
        publicacion.reclamar(duenio);
        publicacion.rechazarSolicitud();
        assertEquals(publicacion.getEstado(), EstadoPublicacion.PUBLICADA);
        assertEquals(publicacion.getReclamadaPor(), null);
    }

    @Test
    public void entregarMascota()  throws Exception{
        List<Foto> fotosMascotaEncontrada = new ArrayList<>();
        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(rescatista, fotosMascotaEncontrada, "La mascota se rescató en un parque", new Ubicacion(0d, 0d), mascotaPerdida);
        publicacion.setEstado(EstadoPublicacion.EN_PROCESO_RECLAMO);
        publicacion.aprobar(voluntario);
        publicacion.reclamar(duenio);
        publicacion.entregar();
        assertEquals(publicacion.getEstado(), EstadoPublicacion.ENTREGADA_AL_DUENIO);
    }

    @Test
    public void recupearMascotaComoNueva()  throws Exception{
        List<Foto> fotosMascotaEncontrada = new ArrayList<>();
        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(rescatista, fotosMascotaEncontrada, "La mascota se rescató en un parque", new Ubicacion(0d, 0d), mascotaPerdida);
        publicacion.setEstado(EstadoPublicacion.EN_PROCESO_RECLAMO);
        publicacion.aprobar(voluntario);
        publicacion.reclamar(duenio);
        publicacion.entregar();

        Mascota nuevaMascota = new Mascota(tipoMascota);
        nuevaMascota.setNombre("Lassy");
        nuevaMascota.setApodo("xxx");
        publicacion.recuperarComoNueva(nuevaMascota);
        assertTrue(nuevaMascota.tieneDuenio());
        assertEquals(nuevaMascota.getDuenio(), publicacion.getReclamadaPor());
        assertEquals(nuevaMascota.getEstado(),EstadoMascota.EN_CASA);

        assertEquals(duenio.getMisMascotas().size(), 2);
        assertEquals(duenio.getMisMascotas().get(1), nuevaMascota);
        assertEquals(duenio.getMisMascotas().get(1), publicacion.getMascota());

        assertEquals(publicacion.getEstado(), EstadoPublicacion.FINALIZADA);
    }


    @Test
    public void recupearMascotaComoExistente()  throws Exception{
        List<Foto> fotosMascotaEncontrada = new ArrayList<>();
        PublicacionMascotaEncontrada publicacion = new PublicacionMascotaEncontrada(rescatista, fotosMascotaEncontrada, "La mascota se rescató en un parque", new Ubicacion(0d, 0d), mascotaPerdida);
        publicacion.setEstado(EstadoPublicacion.EN_PROCESO_RECLAMO);
        publicacion.aprobar(voluntario);
        publicacion.reclamar(duenio);
        publicacion.entregar();

        Mascota mascotaRecuperada = duenio.getMisMascotas().get(0);
        publicacion.recuperarComoExistente(mascotaRecuperada);

        assertEquals(mascotaRecuperada.getEstado(),EstadoMascota.EN_CASA);

        assertEquals(duenio.getMisMascotas().size(), 1);

        assertEquals(publicacion.getMascota(), mascotaRecuperada);
        assertEquals(publicacion.getEstado(), EstadoPublicacion.FINALIZADA);
    }



    private class MockNotificacionEnviada extends FormaNotificacion {

        @Override
        public void notificar(Notificacion notificacion) throws NotificacionException {
            notificacion.marcarComoEnviada();
        }
    }
}
