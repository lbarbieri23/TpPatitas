package com.dds.mascotas.controllers;

import com.dds.mascotas.forms.RegistracionForm;
import com.dds.mascotas.model.security.Rol;
import com.dds.mascotas.model.security.Usuario;
import com.dds.mascotas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    private UsuarioService usuarioService;

    @Autowired
    private RegisterController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/register")
    public ModelAndView loadRegister(@ModelAttribute("registracionForm") RegistracionForm regForm ) throws Exception {
        return new ModelAndView("register");
    }

    @PostMapping(value = "/register")
    public ModelAndView doRegister(@ModelAttribute("registracionForm") RegistracionForm regForm, BindingResult result) throws Exception {
        if( regForm.getUsuario().getPassword().equals(regForm.getRepeatPassword())) {
           // regForm.getUsuario().setRol(regForm.getRol());
            this.usuarioService.registrarUsuarioNuevo(regForm.getUsuario());
        } else {
            // tirar error
            result.reject("error.code", new Object[] { "Las contraseñas no coinciden" }, "");
        }
    // cartelito de exito 
        return new ModelAndView("redirect:/");
    }

}