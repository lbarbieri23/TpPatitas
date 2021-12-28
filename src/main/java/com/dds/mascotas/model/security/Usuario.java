package com.dds.mascotas.model.security;

import com.dds.mascotas.model.security.Rol;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_seq")
    @SequenceGenerator(name = "usuarios_id_seq", sequenceName = "usuarios_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne()
    @JoinColumn(name = "id_rol")
    private Rol rol;

    public Usuario() {

    }

    public Usuario(String username, Rol rol) {
        this.username = username;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) throws Exception {
        if (!isValidPassword(password)) {
            throw new Exception("La password no cumple con las politicas de seguridad");
        }
        this.password = hash(password);
    }

    private String hash(String source) {
        return source; //TODO toMD5
    }

    public String getPassword() {
        return password;
    }

    private Boolean isValidPassword(String password) {
        //TODO IMPLEMENTAR PARA ENTREGA 1
        return true;
    }
    public Boolean esAdministrador() {
        return this.rol.getId() == 1;
    }

    public Boolean esUsuario() {
        return this.rol.getId() == 2;
    }

    public Boolean esVoluntario() {
        return this.rol.getId() == 3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
