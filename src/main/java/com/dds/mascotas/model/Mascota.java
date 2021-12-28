package com.dds.mascotas.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mascotas_id_seq")
    @SequenceGenerator(name = "mascotas_id_seq", sequenceName = "mascotas_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_mascota")
    private TipoMascota tipoMascota;

    @ManyToOne
    @JoinColumn(name = "id_duenio")
    private Persona duenio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apodo")
    private String apodo;

    @Column(name = "edad")
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;

    @Column(name = "descripcion_fisica")
    private String descripcionFisica;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mascota")
    private List<Foto> fotos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mascota")
    private List<CaracteristicaMascota> caracteristicas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoMascota estado;

    @ManyToOne
    @JoinColumn(name = "id_hogar")
    private Hogar hogar;

    public Mascota() {

    }

    public Mascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
        this.estado = EstadoMascota.EN_CASA;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void agregarCaracteristica(CaracteristicaMascota caracteristicaMascota) {
        caracteristicas.add(caracteristicaMascota);
    }

    public List<CaracteristicaMascota> getCaracteristicas() {
        return caracteristicas;
    }

    public void agregarFoto(Foto foto) {
        this.fotos.add(foto);
    }

    public Persona getDuenio() {
        return duenio;
    }

    public void setDuenio(Persona duenio) {
        this.duenio = duenio;
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getDescripcionFisica() {
        return descripcionFisica;
    }

    public void setDescripcionFisica(String descripcionFisica) {
        this.descripcionFisica = descripcionFisica;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public void setCaracteristicas(List<CaracteristicaMascota> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public void setEstado(EstadoMascota estado) {
        this.estado = estado;
    }

    public Hogar getHogar() {
        return hogar;
    }

    public void setHogar(Hogar hogar) {
        this.hogar = hogar;
    }

    public Boolean tieneDuenio() {
        return this.duenio != null;
    }

    public EstadoMascota getEstado() {
        return estado;
    }

    public void normalizarFotos() throws Exception {
        List<Foto> fotosNormalizadas = new ArrayList<>();
        if (fotos != null && fotos.size() > 0) {
            for (Foto f: fotos) {
                fotosNormalizadas.add(f.normalizar(duenio.getOrganizacion().getConfiguracionFotos()));
            }
        }
        this.fotos = fotosNormalizadas;
    }

    public void pasarAEnCasa() {
        cambiarEstado(EstadoMascota.EN_CASA);
        if (estaEnUnHogar()) {
            this.hogar = null;
        }
    }

    public void pasarAPerdida() {
        cambiarEstado(EstadoMascota.PERDIDA);
    }

    public void pasarARescatada() {
        cambiarEstado(EstadoMascota.RESCATADA);
    }

    private void cambiarEstado(EstadoMascota nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public Boolean estaEnUnHogar() {
        return this.hogar != null;
    }

    public void cambiarDuenio(Persona adoptadaPor) {
        this.getDuenio().quitarMascota(this);
        this.setDuenio(adoptadaPor);
        this.getDuenio().agregarMascota(this);
        this.pasarAEnCasa();
    }

    public boolean tieneCaracterisitca(CaracteristicaMascota c) {
        if (caracteristicas == null) {
            return false;
        }

        Optional<CaracteristicaMascota> valorMascota = caracteristicas.stream().filter(x -> x.getCaracteristica().equals(c.getCaracteristica())).findAny();
        if (!valorMascota.isPresent()) {
            return false;
        }

        return valorMascota.get().getValor().equals(c.getValor());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Mascota)) {
            return false;
        }
        return ((Mascota)obj).getId().intValue() == this.getId().intValue();
    }

    public boolean estaEnCasa() {
        return this.getEstado().equals(EstadoMascota.EN_CASA);
    }
}
