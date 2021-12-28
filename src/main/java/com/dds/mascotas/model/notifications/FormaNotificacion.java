package com.dds.mascotas.model.notifications;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.Notificacion;
import com.dds.services.whatsapp.WhatsappException;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "field", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("dummy")
public abstract class FormaNotificacion {

    @Id
    private Integer id;
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public abstract void notificar(Notificacion notificacion) throws NotificacionException;
}
