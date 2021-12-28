package com.dds.mascotas.controllers;


import com.dds.mascotas.forms.PerfilForm;
import com.dds.mascotas.model.Organizacion;
import com.dds.mascotas.model.Persona;
import com.dds.mascotas.model.security.Usuario;
import com.dds.mascotas.service.PersonaService;
import org.opengis.filter.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;

//FIXME NO ME GUSTA EL SESSION ATTRIBUTES
@Controller
@RequestMapping("auth/profile")
public class PerfilController {

    private PersonaService personaService;

    @Autowired
    private PerfilController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping(value = "/")
    public ModelAndView perfil(@ModelAttribute("perfilForm") PerfilForm form, RedirectAttributes redirectAttributes) throws Exception {
        Usuario loggedUser = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Persona persona = personaService.findByLoggedUser(loggedUser);
        form.setPersona(persona);
        return new ModelAndView("perfil");
    }

    @PostMapping(value = "/update", params = { "updateProfile"})
    public ModelAndView updatePerfil(@ModelAttribute("perfilForm") PerfilForm form, RedirectAttributes redirectAttributes) throws Exception {
        personaService.actualizarPerfil(form.getPersona());
        String successMessage = URLEncoder.encode("Su publicacion se actualizo con exito","UTF-8");
        return new ModelAndView("redirect:/auth/profile/?toastMessage=" + successMessage);
    }

}
