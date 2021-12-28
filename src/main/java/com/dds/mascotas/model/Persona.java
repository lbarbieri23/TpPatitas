package com.dds.mascotas.model;

import com.dds.mascotas.forms.PerfilForm;
import com.dds.mascotas.model.security.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personas_id_seq")
    @SequenceGenerator(name = "personas_id_seq", sequenceName = "personas_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Transient
    private Foto foto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "documento")
    private String documento;

    @Transient
    private List<Contacto> contactos = new ArrayList<Contacto>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_duenio")
    private List<Mascota> misMascotas= new ArrayList<Mascota>();

    @ManyToOne()
    @JoinColumn(name = "id_organizacion")
    private Organizacion organizacion;

    public Persona() {

    }

    public Persona(Usuario usuario, Contacto dato, Organizacion organizacion) {
        this.usuario = usuario;
        this.organizacion = organizacion;
        this.agregarDatosDeContacto(dato);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public List<Mascota> getMisMascotas() {
        return misMascotas;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public void agregarDatosDeContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public void agregarMascota(Mascota mascota) {
        mascota.setDuenio(this);
        misMascotas.add(mascota);
    }

    public void notificarTodos(Notificacion n) {
        contactos.stream().forEach(x -> x.notificar(n));
    }


    public void quitarMascota(Mascota mascota) {
        misMascotas.remove(mascota);
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public void setMisMascotas(List<Mascota> misMascotas) {
        this.misMascotas = misMascotas;
    }

    public boolean tieneMascota(Mascota mascota) {
        if (misMascotas == null || misMascotas.size() ==0)
            return false;

        return misMascotas.contains(mascota);
    }
}
