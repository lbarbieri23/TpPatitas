package com.dds.mascotas.model;

import com.dds.mascotas.model.rules.Respuesta;

import javax.persistence.*;
import javax.transaction.NotSupportedException;
import java.util.List;

@Entity
@Table(name = "publicaciones_mascota_perdida")
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public  class PublicacionMascotaPerdida extends Publicacion {

    @ManyToOne
    @JoinColumn(name = "encontrada_por")
    public Persona encontradaPor;


    public PublicacionMascotaPerdida() {
        this.setTipoPublicacion(TipoPublicacion.MASCOTA_PERDIDA);
    }
    public PublicacionMascotaPerdida(Persona publicadaPor, List<Foto> fotos, String descripcion, Ubicacion ubicacion, Mascota mascota) {
        super(publicadaPor, fotos, descripcion, ubicacion, mascota);
        this.setTipoPublicacion(TipoPublicacion.MASCOTA_PERDIDA);
        mascota.pasarAPerdida();
    }

    public Persona getEncontradaPor() {
        return encontradaPor;
    }

    public void setEncontradaPor(Persona reclamadaPor) {
        this.encontradaPor = encontradaPor;
    }

    @Override
    public void cancelar() throws Exception {
        super.cancelar();
        this.getMascota().pasarAEnCasa();
    }

    @Override
    public List<Respuesta> getRespuestasContestadas() {
        return null;
    }


    public void informarHallazgo(Persona encontradaPor) throws Exception {
        if (getPublicadaPor().equals(encontradaPor)) {
            throw new Exception("400"); //FIXME EXCEPTION BY SPRING
        }

        this.encontradaPor = encontradaPor;
        cambiarEstado(EstadoPublicacion.EN_PROCESO_RECLAMO);
        super.getPublicadaPor().notificarTodos(generarNotificacionEncuentro(this));
    }

    protected void doRechazarSolicitud() {
        this.encontradaPor = null;
        cambiarEstado(EstadoPublicacion.PUBLICADA);
    }

    @Override
    public void finalizar() {
        super.finalizar();
        this.getMascota().pasarAEnCasa();
    }

    private Notificacion generarNotificacionEncuentro(PublicacionMascotaPerdida pub) {
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo(pub.getEncontradaPor().getNombre() + " a encontrado a tu mascota " + pub.getMascota().getNombre());
        notificacion.setDescripcion(pub.getEncontradaPor().getNombre() + " a encontrado a tu mascota " + pub.getMascota().getNombre());
        return notificacion;
    }

    @Override
    public Boolean getSePuedeFinalizar() {
        return this.getEstado().equals(EstadoPublicacion.EN_PROCESO_RECLAMO);
    }

    @Override
    protected void doEntregar() throws Exception {
        throw new NotSupportedException();
    }

    @Override
    public Boolean getSePuedeEntregar() {
        return false;
    }


}
