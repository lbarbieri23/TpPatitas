package com.dds.mascotas.model.notifications;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.Notificacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="sms")
public  class Sms extends FormaNotificacion {

    @Override
    public void notificar(Notificacion notificacion) throws NotificacionException {

    }

}
