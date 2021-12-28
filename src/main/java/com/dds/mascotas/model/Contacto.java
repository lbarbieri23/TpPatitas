package com.dds.mascotas.model;

import com.dds.exceptions.NotificacionException;
import com.dds.mascotas.model.notifications.FormaNotificacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contactos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactos_id_seq")
    @SequenceGenerator(name = "contactos_id_seq", sequenceName = "contactos_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;


    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "contacto_formas_notificacion",
            joinColumns = { @JoinColumn(name = "id_contacto") },
            inverseJoinColumns = { @JoinColumn(name = "id_forma_notificacion") }
    )
    private List<FormaNotificacion> formasNotificacion = new ArrayList<>();


    public void agregarFormaNotificacion(FormaNotificacion formaNotificacion) {
        this.formasNotificacion.add(formaNotificacion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<FormaNotificacion> getFormasNotificacion() {
        return formasNotificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFormasNotificacion(List<FormaNotificacion> formasNotificacion) {
        this.formasNotificacion = formasNotificacion;
    }

    public void notificar(Notificacion notificacion) {
        for (FormaNotificacion fn : formasNotificacion) {
            try {
                notificacion.setPara(this);
                fn.notificar(notificacion);
                notificacion.marcarComoEnviada();
            } catch (NotificacionException ex) {
                //TODO VER QUE HACER SI NO PUEDE ENVIAR NOTIFICACION
            }
        }
    }


}
