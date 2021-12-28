package com.dds.mascotas.model.notifications.adapters;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.Foto;
import com.dds.mascotas.model.Notificacion;
import com.dds.services.whatsapp.ChatApiImpl.ChatApiWhatsappService;
import com.dds.services.whatsapp.IWhatsAppService;
import com.dds.services.whatsapp.WhatsappException;
import com.dds.services.whatsapp.WhatsappMessage;

public class WhatsappAdapter {


    public void enviarNotificacion(Notificacion notificacion) throws NotificacionException {
        try {
            IWhatsAppService service = new ChatApiWhatsappService("", "");

            WhatsappMessage message = new WhatsappMessage();
            message.setTo(notificacion.getPara().getTelefono());
            message.setText(notificacion.getTitulo() + " - " + notificacion.getDescripcion());
            Boolean mensajeEnviado = service.enviarMensaje(message);

            if (!mensajeEnviado) {
                throw new NotificacionException("No se pudo enviar el mensaje a " + message.getTo());
            }

            //FIXME VER QUE PASA SI ENVIA EL MSG A LA MITAD
            for (Foto f : notificacion.getFotos()) {
                message = new WhatsappMessage();
                message.setTo(notificacion.getPara().getTelefono());
                message.setImage(f.getBase64());
                mensajeEnviado = service.enviarMensaje(message);

                if (!mensajeEnviado) {
                    throw new NotificacionException("No se pudo enviar el mensaje a " + message.getTo());
                }

            }
        } catch (
                WhatsappException ex) {
            throw new NotificacionException(ex);
        }
    }

}
