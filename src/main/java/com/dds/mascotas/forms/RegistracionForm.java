package com.dds.mascotas.forms;

import com.dds.mascotas.model.Persona;
import com.dds.mascotas.model.PreguntaQuieroAdoptar;
import com.dds.mascotas.model.PublicacionQuieroAdoptar;
import com.dds.mascotas.model.TipoMascota;
import com.dds.mascotas.model.security.Rol;
import com.dds.mascotas.model.security.Usuario;

import java.io.Serializable;
import java.util.List;

public class RegistracionForm extends CommonForm implements Serializable {

    private Usuario usuario;
    private Rol rol;
    private Persona persona;
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}