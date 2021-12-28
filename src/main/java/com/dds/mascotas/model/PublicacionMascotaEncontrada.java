package com.dds.mascotas.model;

import com.dds.mascotas.model.rules.Respuesta;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "publicaciones_mascota_encontrada")
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public  class PublicacionMascotaEncontrada extends Publicacion {

    @ManyToOne
    @JoinColumn(name = "reclamada_por")
    private Persona reclamadaPor;



    public PublicacionMascotaEncontrada() {
        super();
        this.setTipoPublicacion(TipoPublicacion.MASCOTA_ENCONTRADA);
        this.setMascota(new Mascota());
    }

    public PublicacionMascotaEncontrada(Persona publicadaPor, List<Foto> fotos, String descripcion, Ubicacion ubicacion, Mascota mascota) {
        super(publicadaPor, fotos, descripcion, ubicacion, mascota);
        this.setTipoPublicacion(TipoPublicacion.MASCOTA_ENCONTRADA);
    }



    public Persona getReclamadaPor() {
        return reclamadaPor;
    }

    public void setReclamadaPor(Persona reclamadaPor) {
        this.reclamadaPor = reclamadaPor;
    }

    public void reclamar(Persona reclamadaPor) throws Exception {
        if (getPublicadaPor().equals(reclamadaPor)) {
            throw new Exception("No puede reclamar la misma persona que publica"); //FIXME CUSTOM EXCEPTION
        }

        this.reclamadaPor = reclamadaPor;
        cambiarEstado(EstadoPublicacion.EN_PROCESO_RECLAMO);
        super.getPublicadaPor().notificarTodos(generarNotificacionReclamo(this));
    }

    protected void doRechazarSolicitud() {
        this.reclamadaPor = null;
        cambiarEstado(EstadoPublicacion.PUBLICADA);
    }


    protected void doEntregar() throws Exception {
        cambiarEstado(EstadoPublicacion.ENTREGADA_AL_DUENIO);
    }


    private Notificacion generarNotificacionReclamo(PublicacionMascotaEncontrada pub) {
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo(pub.getReclamadaPor().getNombre() + " a reclamado una mascota que rescataste");
        notificacion.setDescripcion(pub.getReclamadaPor().getNombre() + " ha reclamada la mascota de la publicacion " + pub.getId());
        return notificacion;
    }

    public void recuperarComoNueva(Mascota mascota) {
        mascota.setId(this.getMascota().getId());
        super.setMascota(mascota);
        mascota.setDuenio(this.reclamadaPor);
        mascota.getDuenio().agregarMascota(mascota);
        mascota.pasarAEnCasa();
        this.finalizar();
    }

    public void recuperarComoExistente(Mascota mascota) {
        super.setMascota(mascota);
        mascota.pasarAEnCasa();
        this.finalizar();
    }

    @Override
    public List<Respuesta> getRespuestasContestadas() {
        return null;
    }

    @Override
    public Boolean getSePuedeFinalizar() {
        return this.getEstado().equals(EstadoPublicacion.ENTREGADA_AL_DUENIO);
    }

    @Override
    public Boolean getSePuedeEntregar() {
        return this.getEstado().equals(EstadoPublicacion.EN_PROCESO_RECLAMO);
    }

}
