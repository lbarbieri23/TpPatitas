package com.dds.mascotas.model;

import java.util.List;

public class MascotaEncontrada {

    private Mascota mascota;
    private Persona encontradaPor;
    private List<Foto> fotos;
    private Ubicacion ubicacion;
    private String descripcionEstado;

    public MascotaEncontrada(Mascota mascota, List<Foto> fotos, Ubicacion ubicacion, String descripcionEstado) {
        this.mascota = mascota;
        this.fotos = fotos;
        this.ubicacion = ubicacion;
        this.descripcionEstado = descripcionEstado;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Persona getEncontradaPor() {
        return encontradaPor;
    }

    public void setEncontradaPor(Persona encontradaPor) {
        this.encontradaPor = encontradaPor;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public Boolean tieneDuenio() {
        return this.mascota.tieneDuenio();
    }

    public void recuperar()  {
        this.mascota.pasarAEnCasa();
    }

    public void rescatar(Persona rescatista) {
        this.setEncontradaPor(rescatista);
        this.mascota.pasarARescatada();
        this.notificarAlDuenio();
    }

    public Notificacion notificarAlDuenio() {
        Notificacion notificacion = nuevaNotificacionMascotaEncontrada(this);
        this.mascota.getDuenio().notificarTodos(notificacion);
        return notificacion;
    }

    private Notificacion nuevaNotificacionMascotaEncontrada(MascotaEncontrada m) {
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo("Alguien encontro tu mascota");
        notificacion.setDescripcion(m.getDescripcionEstado());
        notificacion.setUbicacion(m.getUbicacion());
        notificacion.setFotos(m.getFotos());
        return notificacion;
    }
}
