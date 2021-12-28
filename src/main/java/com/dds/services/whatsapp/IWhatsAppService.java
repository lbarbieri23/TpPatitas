package com.dds.services.whatsapp;

import com.dds.mascotas.model.notifications.Whatsapp;

public interface IWhatsAppService {

    Boolean enviarMensaje(WhatsappMessage message) throws WhatsappException;

}
