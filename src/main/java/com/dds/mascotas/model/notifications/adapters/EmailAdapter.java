package com.dds.mascotas.model.notifications.adapters;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.Foto;
import com.dds.mascotas.model.Notificacion;
import com.dds.services.email.EmailException;
import com.dds.services.email.IEmailService;
import com.dds.services.email.SmtpEmailService;
import com.dds.services.email.model.Attachment;
import com.dds.services.email.model.EmailMessage;
import com.dds.services.email.model.SmtpConfiguration;
import com.dds.services.whatsapp.ChatApiImpl.ChatApiWhatsappService;
import com.dds.services.whatsapp.IWhatsAppService;
import com.dds.services.whatsapp.WhatsappException;
import com.dds.services.whatsapp.WhatsappMessage;

public class EmailAdapter {

    private SmtpConfiguration getConfiguracion() {
        String protocol = "smtp";
        String host = "smtp.gmail.com";
        Boolean authentication = true;
        Integer port = 587;
        String mail = "patitasdds2021@gmail.com";
        String password = "qwerty$123";
        Boolean startTls = true;
        Boolean enableSSL = true;
        return new SmtpConfiguration(protocol, host, authentication, port, mail, password, startTls, enableSSL);
    }


    public void enviarNotificacion(Notificacion notificacion) throws NotificacionException {
        try {
            IEmailService service = new SmtpEmailService(getConfiguracion());

            EmailMessage message = new EmailMessage();
            message.agregarPara(notificacion.getPara().getEmail());
            message.setSubject(notificacion.getTitulo());
            message.setHtmlBody(notificacion.getDescripcion());
            if (notificacion.getFotos() != null && notificacion.getFotos().size() > 0) {
                for (Foto f: notificacion.getFotos()) {
                    message.agregarAdjunto(new Attachment(f.getName(), f.getBytes()));
                }
            }


            service.enviarMensaje(message);
        } catch (EmailException ex) {
            throw new NotificacionException(ex);
        }
    }



}
