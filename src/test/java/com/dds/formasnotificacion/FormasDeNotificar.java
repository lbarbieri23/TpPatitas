package com.dds.formasnotificacion;

import com.dds.mascotas.model.Contacto;
import com.dds.mascotas.model.Notificacion;
import com.dds.mascotas.model.Ubicacion;
import com.dds.mascotas.model.notifications.Email;
import com.dds.mascotas.model.notifications.FormaNotificacion;
import com.dds.mascotas.model.notifications.Whatsapp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



public class FormasDeNotificar {

    @Test
    public void notificarPorMail() throws Exception {
        Contacto contacto = new Contacto();
        contacto.setEmail("boyediego@hotmail.com");

        FormaNotificacion notificacionEmail = new Email();
        Notificacion notificacion = new Notificacion("Titulo", "Descripcion", null, new Ubicacion(0d, 0d));
        notificacion.setPara(contacto);
        notificacionEmail.notificar(notificacion);
        assertTrue(notificacion.getEnviada());
    }

    @Test
    public void notificarPorWhatsapp() throws Exception {
        Contacto contacto = new Contacto();
        contacto.setTelefono("+541159973765");

        FormaNotificacion notificacionWhatapp = new Whatsapp();
        Notificacion notificacion = new Notificacion("Titulo", "Descripcion", null, new Ubicacion(0d, 0d));
        notificacion.setPara(contacto);
        notificacionWhatapp.notificar(notificacion);
        assertTrue(notificacion.getEnviada());
    }


}
