package com.dds.mascotas.model;

import com.dds.helpers.IUbicable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hogares")
public class Hogar implements IUbicable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hogares_id_seq")
    @SequenceGenerator(name = "hogares_id_seq", sequenceName = "hogares_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "descripcion")
    private String nombre;

    @Transient
    private Ubicacion ubicacion;

    @Transient
    private String direccion;

    @Transient
    private String telefono;

    @Transient
    private String apiId;

    @Transient
    private Boolean aceptaGatos;

    @Transient
    private Boolean aceptaPerros;

    @Transient
    private Boolean poseePatio;

    @Transient
    private List<String> caracteristicas;

    @Transient
    private Boolean completo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public Boolean getPoseePatio() {
        return poseePatio;
    }

    public void setPoseePatio(Boolean poseePatio) {
        this.poseePatio = poseePatio;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public Boolean getCompleto() {
        return completo;
    }

    public void setCompleto(Boolean completo) {
        this.completo = completo;
    }

    public Boolean getAceptaGatos() {
        return aceptaGatos;
    }

    public void setAceptaGatos(Boolean aceptaGatos) {
        this.aceptaGatos = aceptaGatos;
    }

    public Boolean getAceptaPerros() {
        return aceptaPerros;
    }

    public void setAceptaPerros(Boolean aceptaPerros) {
        this.aceptaPerros = aceptaPerros;
    }

    public void agregarCaracteristicas(String caracteristica) {
        if (this.caracteristicas == null) {
            this.caracteristicas = new ArrayList<>();
        }

        this.caracteristicas.add(caracteristica);
    }

    public void agregarCaracteristicas(List<String> caracteristicas) {
        if (this.caracteristicas == null) {
            this.caracteristicas = new ArrayList<>();
        }

        this.caracteristicas.addAll(caracteristicas);
    }

    @Override
    public Double getLatitud() {
        return this.ubicacion.getLatitud();
    }

    @Override
    public Double getLongitud() {
        return this.ubicacion.getLongitud();
    }




}
