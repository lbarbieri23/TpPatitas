package com.dds.mascotas.model.notifications;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.Foto;
import com.dds.mascotas.model.Notificacion;
import com.dds.mascotas.model.notifications.adapters.WhatsappAdapter;
import com.dds.services.whatsapp.ChatApiImpl.ChatApiWhatsappService;
import com.dds.services.whatsapp.IWhatsAppService;
import com.dds.services.whatsapp.WhatsappException;
import com.dds.services.whatsapp.WhatsappMessage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="whatsapp")
public class Whatsapp extends FormaNotificacion {

    @Override
    public void notificar(Notificacion notificacion) throws NotificacionException {
        WhatsappAdapter adapter = new WhatsappAdapter();
        adapter.enviarNotificacion(notificacion);
        notificacion.marcarComoEnviada();
    }



}
