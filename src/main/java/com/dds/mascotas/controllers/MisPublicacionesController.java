package com.dds.mascotas.controllers;


import com.dds.mascotas.forms.MisPublicacionesForm;
import com.dds.mascotas.forms.PublicacionForm;
import com.dds.mascotas.model.Publicacion;
import com.dds.mascotas.model.TipoPublicacion;
import com.dds.mascotas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("auth/mypubs")
public class MisPublicacionesController extends CommonController {

    private PublicacionService publicacionService;


    @Autowired
    public MisPublicacionesController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }


    //MAIN VIEW
    @GetMapping(value = "/")
    public ModelAndView misPublicaciones(@ModelAttribute("command") MisPublicacionesForm form) throws Exception {
        form.setPublicaciones(publicacionService.findMisPublicaciones(getCurrentLoggedUser()));
        form.setPublicacionesPendienteAccion(publicacionService.findPendientes(getCurrentLoggedUser()));
        return new ModelAndView("mispublicaciones");
    }



    //ACTIONS
    @GetMapping(value = "/view/{id}")
    public ModelAndView view(@ModelAttribute("command") MisPublicacionesForm form, @PathVariable("id") Integer id) throws Exception {
        form.setPublicacion( publicacionService.findPublicacion(id, getCurrentLoggedUser()));
        form.setPermiteAcciones(false);
        return new ModelAndView("publicacionDetalle");
    }

    //FIXME PATCH O POST?
    @PatchMapping(value = "/rechazar/{id}")
    public ModelAndView rechazarSolicitud(@ModelAttribute("command") MisPublicacionesForm form, @PathVariable("id") Integer id) throws Exception {
        publicacionService.rechazarSolicitud(id, getCurrentLoggedUser());
        return new ModelAndView("redirect:/auth/mypubs/");
    }

    @PatchMapping(value = "/entregar/{id}")
    public ModelAndView entregar(@ModelAttribute("command") MisPublicacionesForm form, @PathVariable("id") Integer id) throws Exception {
        publicacionService.entregar(id, getCurrentLoggedUser());
        return new ModelAndView("redirect:/auth/mypubs/");
    }


    @PatchMapping(value = "/cancelar/{id}")
    public ModelAndView cancelar(@ModelAttribute("command") MisPublicacionesForm form, @PathVariable("id") Integer id) throws Exception {
        publicacionService.cancelar(id, getCurrentLoggedUser());
        return new ModelAndView("redirect:/auth/mypubs/");
    }

    @PatchMapping(value = "/finalizar/{id}")
    public ModelAndView finalizar(@ModelAttribute("command") MisPublicacionesForm form, @PathVariable("id") Integer id) throws Exception {
        publicacionService.finalizar(id, getCurrentLoggedUser());
        return new ModelAndView("redirect:/auth/mypubs/");
    }

    @GetMapping(value = "/editar/{id}")
    public ModelAndView editar(@ModelAttribute("command") MisPublicacionesForm form, @PathVariable("id") Integer id) throws Exception {
        Publicacion publicacion = publicacionService.findPublicacion(id, getCurrentLoggedUser());

        //FIXME ESTOS REDIRECT SON FEOS, PODRIAMOS USAR EL ID DE TIPO DE MASCOTA
        if (publicacion != null) {
            switch (publicacion.getTipoPublicacion().getId()) {
                case 1: //MASCOTA PERDIDA
                    return new ModelAndView("redirect:/auth/mypubs/mascota-perdida/" + publicacion.getId());
                case 2: //MASCOTA ENCONTRADA
                    return new ModelAndView("redirect:/auth/mypubs/mascota-encontrada/" + publicacion.getId());
                case 3: //MASCOTA EN ADOPCION
                    return new ModelAndView("redirect:/auth/mypubs/mascota-en-adopcion/" + publicacion.getId());
                case 4: // QUIERO ADOPTAR
                    return new ModelAndView("redirect:/auth/mypubs/quiero-adoptar/" + publicacion.getId());
            }
        }
        return new ModelAndView("redirect:/auth/mypubs/");
    }

}