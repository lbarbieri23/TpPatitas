package com.dds.mascotas.model;

import com.dds.helpers.IUbicable;
import com.dds.helpers.UbicacionHelper;
import com.dds.mascotas.model.rules.Respuesta;
import com.dds.mascotas.model.security.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "publicaciones")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publicacion implements IUbicable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publicaciones_id_seq")
    @SequenceGenerator(name = "publicaciones_id_seq", sequenceName = "publicaciones_id_seq", allocationSize = 1)
    private Integer id;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_tipo_publicacion")
    private TipoPublicacion tipoPublicacion;


    @ManyToOne
    @JoinColumn(name = "publicada_por")
    private Persona publicadaPor;

    @ManyToOne
    @JoinColumn(name = "id_mascota")
    private Mascota mascota;

    private String detalle;
    private String descripcion;

    @Embedded
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoPublicacion estado;

    @ManyToOne
    @JoinColumn(name = "id_organizacion")
    private Organizacion organizacion;

    @ManyToOne
    @JoinColumn(name = "aprobada_por")
    private Usuario aprobadaPor;

    @ManyToOne
    @JoinColumn(name = "rechazada_por")
    private Usuario rechazadaPor;

    @Transient
    private boolean recomendada;


    public Publicacion() {
        this.setEstado(EstadoPublicacion.PENDIENTE);
        this.setFecha(LocalDate.now());
    }

    public Publicacion(Persona publicadaPor, List<Foto> fotos, String descripcion, Ubicacion ubicacion, Mascota mascota) {
        this.publicadaPor = publicadaPor;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.organizacion = publicadaPor.getOrganizacion();
        this.fecha = LocalDate.now();
        this.estado = EstadoPublicacion.PENDIENTE;
        this.mascota = mascota;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Persona getPublicadaPor() {
        return publicadaPor;
    }

    public void setPublicadaPor(Persona publicadaPor) {
        this.publicadaPor = publicadaPor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPublicacion estado) {
        this.estado = estado;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Usuario getAprobadaPor() {
        return aprobadaPor;
    }

    public void setAprobadaPor(Usuario aprobadaPor) {
        this.aprobadaPor = aprobadaPor;
    }

    public Usuario getRechazadaPor() {
        return rechazadaPor;
    }

    public void setRechazadaPor(Usuario rechazadaPor) {
        this.rechazadaPor = rechazadaPor;
    }


    public boolean isRecomendada() {
        return recomendada;
    }

    public void setRecomendada(boolean recomendada) {
        this.recomendada = recomendada;
    }

    public void aprobar(Usuario aprobadaPor) {
        this.aprobadaPor = aprobadaPor;
        cambiarEstado(EstadoPublicacion.PUBLICADA);
    }

    public void rechazar(Usuario rechazadaPor) {
        this.rechazadaPor = rechazadaPor;
        cambiarEstado(EstadoPublicacion.RECHAZADA);
    }


    public void finalizar() {
        cambiarEstado(EstadoPublicacion.FINALIZADA);
    }

    public void cancelar() throws Exception {
        if (!getSePuedeCancelar()) {
            throw new Exception("La publicacion no se puede cancelar"); //FIXME
        }
        cambiarEstado(EstadoPublicacion.CANCELADA);
    }

    protected void cambiarEstado(EstadoPublicacion nuevo) {
        this.estado = nuevo;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    @Override
    public Double getLatitud() {
        return this.ubicacion.getLatitud();
    }

    @Override
    public Double getLongitud() {
        return this.ubicacion.getLongitud();
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public abstract List<Respuesta> getRespuestasContestadas();


    public  Boolean getSePuedeEditar() {
        return this.getEstado().equals(EstadoPublicacion.PUBLICADA) || this.getEstado().equals(EstadoPublicacion.PENDIENTE);
    }

    public Boolean getSePuedeRechazar() {
        return this.getEstado().equals(EstadoPublicacion.PENDIENTE);
    }

    public Boolean getSePuedeAprobar() {
        return this.getEstado().equals(EstadoPublicacion.PENDIENTE);
    }

    public  Boolean getSePuedeCancelar() {
        return this.getEstado().equals(EstadoPublicacion.PUBLICADA);
    }


    public void rechazarSolicitud() throws Exception {
        if (!getSePuedeRechazarSolicitud()) {
            throw new Exception("No se puede rechazar la solicitud");
        }

        this.doRechazarSolicitud();
    }

    protected abstract void doRechazarSolicitud();

    public Boolean getSePuedeRechazarSolicitud() {
        return this.getEstado().equals(EstadoPublicacion.EN_PROCESO_RECLAMO);
    }

    public abstract Boolean getSePuedeFinalizar();


    public void entregar() throws Exception {
        if (!getSePuedeEntregar()) {
            throw new Exception("No se puede entregar este tipo de publicación");
        }

        this.doEntregar();
    }

    protected abstract void doEntregar() throws Exception;

    public abstract Boolean getSePuedeEntregar();






    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Publicacion)) {
            return false;
        }
        return ((Publicacion)obj).getId().equals(this.getId());
    }
}