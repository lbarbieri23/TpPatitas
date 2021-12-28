package com.dds.mascotas.model.notifications;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.Notificacion;
import com.dds.mascotas.model.notifications.adapters.EmailAdapter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value="email")
public class Email extends FormaNotificacion {

    @Override
    public void notificar(Notificacion notificacion) throws NotificacionException {
        EmailAdapter adapter = new EmailAdapter();
        adapter.enviarNotificacion(notificacion);
        notificacion.marcarComoEnviada();
    }

}
