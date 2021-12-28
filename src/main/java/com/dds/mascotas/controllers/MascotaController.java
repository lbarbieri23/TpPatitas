package com.dds.mascotas.controllers;


import com.dds.mascotas.forms.MascotaForm;
import com.dds.mascotas.model.Mascota;
import com.dds.mascotas.model.TipoMascota;
import com.dds.mascotas.model.security.Rol;
import com.dds.mascotas.model.security.Usuario;
import com.dds.mascotas.service.MascotaService;
import com.dds.mascotas.service.PersonaService;
import com.dds.mascotas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("auth/pets")
public class MascotaController {

    private MascotaService mascotaService;

    @Autowired
    private MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }


    @GetMapping("/view/{id}")
    public ModelAndView viewDetails(@ModelAttribute("command") MascotaForm form, @PathVariable("id") Integer idMascota) throws Exception {
        Mascota mascota = mascotaService.findBy(idMascota);
        if (mascota == null) {
            throw  new Exception("404"); //FIXME
        }

        form.setMascota(mascota);
        return new ModelAndView("mascota");
    }



}
