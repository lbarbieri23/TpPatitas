package com.dds.mascotas.controllers;

import com.dds.mascotas.model.security.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class CommonController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession httpSession;


    public Usuario getCurrentLoggedUser() {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() )) {
            return null;
        }
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
