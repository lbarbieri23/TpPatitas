package com.dds.mascotas.controllers;


import com.dds.mascotas.forms.PublicacionForm;
import com.dds.mascotas.service.MascotaService;
import com.dds.mascotas.service.PersonaService;
import com.dds.mascotas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pubs")
public class PublicacionController extends CommonController {

    private PublicacionService publicacionService;
    private PersonaService personaService;
    private MascotaService mascotaService;

    @Autowired
    private PublicacionController(PublicacionService publicacionService, PersonaService personaService, MascotaService mascotaService) {
        this.publicacionService = publicacionService;
        this.personaService = personaService;
        this.mascotaService = mascotaService;
    }


    @GetMapping(value = "/view/{id}")
    public ModelAndView view(@ModelAttribute("command") PublicacionForm form, @PathVariable("id") Integer id) throws Exception {
        form.setPublicacion( publicacionService.findPublicacionPublicada(id));
        if (getCurrentLoggedUser() != null) {
            form.setMisMascotas(mascotaService.findMascotasEnCasaBy(getCurrentLoggedUser()));
        }
        return new ModelAndView("publicacionDetalle");
    }


    @GetMapping(value = "/actions/reclamar/{id}")
    public ModelAndView reclamar(@ModelAttribute("command") PublicacionForm form, @PathVariable("id") Integer id) throws Exception {
        publicacionService.reclamarMascota(getCurrentLoggedUser(), id);
        return new ModelAndView("redirect:/pubs/actions/reclamo/success/" + id);
    }

    @GetMapping(value = "/actions/contactar/{id}")
    public ModelAndView contactar(@ModelAttribute("command") PublicacionForm form, @PathVariable("id") Integer id) throws Exception {
        publicacionService.informarHallazgoMascota(getCurrentLoggedUser(), id);
        return new ModelAndView("redirect:/pubs/actions/contactar/success/" + id);
    }

    @GetMapping(value = "/actions/adoptar/{id}")
    public ModelAndView adoptar(@ModelAttribute("command") PublicacionForm form, @PathVariable("id") Integer id) throws Exception {
        publicacionService.adoptar(getCurrentLoggedUser(), id);
        return new ModelAndView("redirect:/pubs/actions/adopcion/success/" + id);
    }

    @PostMapping(value = "/actions/ofrecer/{id}")
    public ModelAndView ofrecer(@ModelAttribute("command") PublicacionForm form, @PathVariable("id") Integer id) throws Exception {
        publicacionService.ofrecer(getCurrentLoggedUser(), id, form.getMascota());
        return new ModelAndView("redirect:/pubs/actions/ofrecer/success/" + id);
    }


    //FIXME VER COMO VALIDAR PUBLICACIONES SIN REPETIR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @GetMapping(value = "/actions/reclamo/success/{id}")
    public ModelAndView successReclamo(@ModelAttribute("command") PublicacionForm form, @PathVariable("id") Integer id) throws Exception {
        /*//FIXME COMMON CONTROLLER
        Usuario loggedUser = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Persona persona = personaService.findBy(loggedUser);
        Publicacion publicacion = publicacionService.findBy(id);

        if (publicacion.getTipoPublicacion().getId().intValue() != TipoPublicacion.MASCOTA_ENCONTRADA.getId().intValue()) {
            throw new Exception("400"); //FIXME EXCEPTION BY SPRING
        }

        PublicacionMascotaEncontrada p = (PublicacionMascotaEncontrada)publicacion;

        //FIXME OVERRIDE EQUALS DE PERSONA
        if (p.getReclamadaPor().getId().intValue() != persona.getId().intValue()) {
            throw new Exception("403"); //FIXME EXCEPTION BY SPRING
        }

        if (!p.getEstado().equals(EstadoPublicacion.EN_PROCESO_RECLAMO)) {
            throw new Exception("400"); //FIXME EXCEPTION BY SPRING
        }*/

        form.setSuccessActionMessage("Se ha notificado al dueño de la publicación para que se ponga en contacto con usted.");
        return new ModelAndView("publicationActionSuccess");
    }

    @GetMapping(value = "/actions/contactar/success/{id}")
    public ModelAndView successContacto(@ModelAttribute("command") PublicacionForm form, @PathVariable("id") Integer id) throws Exception {
        form.setSuccessActionMessage("Se ha notificado al dueño de la publicación para que se ponga en contacto con usted.");
        return new ModelAndView("publicationActionSuccess");
    }

    @GetMapping(value = "/actions/adopcion/success/{id}")
    public ModelAndView successAdopcion(@ModelAttribute("command") PublicacionForm form, @PathVariable("id") Integer id) throws Exception {
        form.setSuccessActionMessage("Se ha notificado al dueño de la publicación para que se ponga en contacto con usted.");
        return new ModelAndView("publicationActionSuccess");
    }

    @GetMapping(value = "/actions/ofrecer/success/{id}")
    public ModelAndView successOfrecer(@ModelAttribute("command") PublicacionForm form, @PathVariable("id") Integer id) throws Exception {
        form.setSuccessActionMessage("Se ha notificado al dueño de la publicación para que se ponga en contacto con usted.");
        return new ModelAndView("publicationActionSuccess");
    }



}