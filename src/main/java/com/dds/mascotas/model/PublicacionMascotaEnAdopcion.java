package com.dds.mascotas.model;

import com.dds.mascotas.model.rules.Puntuable;
import com.dds.mascotas.model.rules.Respuesta;

import javax.persistence.*;
import javax.transaction.NotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "publicaciones_mascota_en_adopcion")
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public  class PublicacionMascotaEnAdopcion extends Publicacion implements Puntuable {

    @ManyToOne
    @JoinColumn(name = "adoptada_por")
    private Persona adoptadaPor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion")
    private List<RespuestaAdopcion> respuestas;

    public PublicacionMascotaEnAdopcion(){
        this.setTipoPublicacion(TipoPublicacion.MASCOTA_EN_ADOPCION);
    }

    public PublicacionMascotaEnAdopcion(Persona publicadaPor, List<Foto> fotos, String descripcion, Ubicacion ubicacion, Mascota mascota) {
        super(publicadaPor, fotos, descripcion, ubicacion, mascota);
        this.setTipoPublicacion(TipoPublicacion.MASCOTA_EN_ADOPCION);
    }

    public List<RespuestaAdopcion> getRespuestas() {
        return respuestas;
    }

    public void agregarRespuesta(RespuestaAdopcion respuesta) {
        if (respuestas == null) {
            respuestas = new ArrayList<>();
        }
        respuestas.add(respuesta);
    }

    public Persona getAdoptadaPor() {
        return adoptadaPor;
    }

    public void setAdoptadaPor(Persona adoptadaPor) {
        this.adoptadaPor = adoptadaPor;
    }

    @Override
    public void cancelar() throws Exception {
        super.cancelar();
        this.getMascota().pasarAEnCasa();
    }


    public void adoptar(Persona adoptadaPor) throws Exception{
        if (getPublicadaPor().equals(adoptadaPor)) {
            throw new Exception("No puede reclamar la misma persona que publica"); //FIXME CUSTOM EXCEPTION
        }

        this.adoptadaPor = adoptadaPor;
        cambiarEstado(EstadoPublicacion.EN_PROCESO_RECLAMO);
        super.getPublicadaPor().notificarTodos(generarNotificacionAdopcion(this));
    }

    protected void doRechazarSolicitud() {
        this.adoptadaPor = null;
        cambiarEstado(EstadoPublicacion.PUBLICADA);
    }

    @Override
    public void finalizar() {
        super.finalizar();
        this.getMascota().cambiarDuenio(adoptadaPor);
    }

    private Notificacion generarNotificacionAdopcion(PublicacionMascotaEnAdopcion pub) {
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo(pub.getAdoptadaPor().getNombre() + " quiere adoptar a tu mascota " + pub.getMascota().getNombre());
        notificacion.setDescripcion(pub.getAdoptadaPor().getNombre() + " quiere adoptar a tu mascota " + pub.getMascota().getNombre());
        return notificacion;
    }


    @Override
    public List<Respuesta> getRespuestasContestadas() {
        List<Respuesta> results = new ArrayList<>();
        results.add((Respuesta) this.getMascota().getSexo());
        results.addAll(this.getMascota().getCaracteristicas().stream().map(x -> (Respuesta)x).collect(Collectors.toList()));
        results.addAll(this.getRespuestas().stream().map(x -> (Respuesta)x).collect(Collectors.toList()));
        return results;
    }


    @Override
    public Boolean getSePuedeFinalizar() {
        return this.getEstado().equals(EstadoPublicacion.EN_PROCESO_RECLAMO);
    }

    @Override
    protected void doEntregar() throws Exception{
        throw new NotSupportedException();
    }

    @Override
    public Boolean getSePuedeEntregar() {
        return false;
    }

    public void setRespuestas(List<RespuestaAdopcion> respuestas) {
        this.respuestas = respuestas;
    }

}
