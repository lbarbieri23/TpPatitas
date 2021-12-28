package com.dds.mascotas.model;

import com.dds.mascotas.model.rules.Respuesta;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "publicaciones_quiero_adoptar")
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public  class PublicacionQuieroAdoptar extends Publicacion {

    @ManyToOne
    @JoinColumn(name = "id_tipo_mascota")
    private TipoMascota tipoMascota;

    @ManyToOne
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "id_adoptada_a")
    private Persona adoptadaA;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion")
    private List<CaracteristicaQuieroAdoptar> caracteristicasDeMascotaBuscada;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion")
    private List<RespuestaQuieroAdoptar> respuestas;

    public PublicacionQuieroAdoptar(){
        this.setTipoPublicacion(TipoPublicacion.QUIERO_ADOPTAR);
    }

    public PublicacionQuieroAdoptar(Persona publicadaPor, List<Foto> fotos, String descripcion, Ubicacion ubicacion, TipoMascota tipoMascota, Sexo sexo) {
        super(publicadaPor, fotos, descripcion, ubicacion, null);
        this.tipoMascota = tipoMascota;
        this.sexo = sexo;
        this.setTipoPublicacion(TipoPublicacion.QUIERO_ADOPTAR);
    }

    public List<RespuestaQuieroAdoptar> getRespuestas() {
        return respuestas;
    }

    public void agregarRespuesta(RespuestaQuieroAdoptar respuesta) {
        if (respuestas == null) {
            respuestas = new ArrayList<>();
        }
        respuestas.add(respuesta);
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Transactional
    public List<CaracteristicaQuieroAdoptar> getCaracteristicasDeMascotaBuscada() {
        return caracteristicasDeMascotaBuscada;
    }
    public void setCaracteristicasDeMascotaBuscada(List<CaracteristicaQuieroAdoptar> caracteristicasDeMascotaBuscada) {
        this.caracteristicasDeMascotaBuscada = caracteristicasDeMascotaBuscada;
    }

    public void setRespuestas(List<RespuestaQuieroAdoptar> respuestas) {
        this.respuestas = respuestas;
    }

    public void agregarCaracteristica(CaracteristicaQuieroAdoptar caracteristica) {
        if (caracteristicasDeMascotaBuscada == null) {
            caracteristicasDeMascotaBuscada = new ArrayList<>();
        }
        caracteristicasDeMascotaBuscada.add(caracteristica);
    }


    public Persona getAdoptadaA() {
        return adoptadaA;
    }

    public void setAdoptadaA(Persona adoptadaA) {
        this.adoptadaA = adoptadaA;
    }


    public void adoptar(Persona adoptadaA, Mascota mascota) throws Exception {
        if (getPublicadaPor().equals(adoptadaA)) {
            throw new Exception("No puede adoptar una mascota a si mismo"); //FIXME CUSTOM EXCEPTION
        }

        if (!adoptadaA.tieneMascota(mascota)) {
            throw new Exception("La mascota no pertence a la persona " + adoptadaA.getNombre()); //FIXME CUSTOM EXCEPTION
        }

        if (!mascota.estaEnCasa()) {
            throw new Exception("No puede ofrecer en adopcion una mascota que no se encuentre en casa "); //FIXME CUSTOM EXCEPTION
        }

        this.adoptadaA = adoptadaA;
        super.setMascota(mascota);
        cambiarEstado(EstadoPublicacion.EN_PROCESO_RECLAMO);
        super.getPublicadaPor().notificarTodos(generarNotificacionAdopcion(this));
    }

    protected void doRechazarSolicitud() {
        this.adoptadaA = null;
        super.setMascota(null);
        cambiarEstado(EstadoPublicacion.PUBLICADA);
    }

    protected void doEntregar() throws Exception {
        cambiarEstado(EstadoPublicacion.ENTREGADA_AL_DUENIO);
    }

    @Override
    public void finalizar() {
        super.finalizar();
        this.getMascota().cambiarDuenio(super.getPublicadaPor());
    }

    //TODO VIEW THIS
    private Notificacion generarNotificacionAdopcion(PublicacionQuieroAdoptar pub) {
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo(pub.getAdoptadaA().getNombre() + " quiere adoptar a tu mascota " + pub.getMascota().getNombre());
        notificacion.setDescripcion(pub.getAdoptadaA().getNombre() + " quiere adoptar a tu mascota " + pub.getMascota().getNombre());
        return notificacion;
    }


    public List<Respuesta> getRespuestasContestadas() {
        List<Respuesta> results = new ArrayList<>();
        if (this.getSexo() != null)
            results.add(this.getSexo());
        if (this.getCaracteristicasDeMascotaBuscada() != null) {
            results.addAll(this.getCaracteristicasDeMascotaBuscada().stream().map(x -> (Respuesta) x).collect(Collectors.toList()));
        }
        if (this.getRespuestas() != null) {
            results.addAll(this.getRespuestas().stream().map(x -> (Respuesta) x).collect(Collectors.toList()));
        }
        return results;
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
