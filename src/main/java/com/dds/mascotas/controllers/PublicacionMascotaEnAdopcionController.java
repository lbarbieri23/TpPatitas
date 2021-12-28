package com.dds.mascotas.controllers;

import com.dds.helpers.UbicacionHelper;
import com.dds.mascotas.forms.MisPublicacionesForm;
import com.dds.mascotas.forms.PublicacionMascotaEnAdopcionForm;
import com.dds.mascotas.forms.PublicacionMascotaPerdidaForm;
import com.dds.mascotas.model.*;
import com.dds.mascotas.service.MascotaService;
import com.dds.mascotas.service.PersonaService;
import com.dds.mascotas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
@Controller
@RequestMapping("auth/mypubs/mascota-en-adopcion")
public class PublicacionMascotaEnAdopcionController extends CommonController {

    private PublicacionService publicacionService;
    private MascotaService mascotaService;
    private PersonaService personaService;

    @Autowired
    public PublicacionMascotaEnAdopcionController(PublicacionService publicacionService, MascotaService mascotaService, PersonaService personaService) {
        this.publicacionService = publicacionService;
        this.mascotaService = mascotaService;
        this.personaService = personaService;
    }

    //MAIN VIEW
    @GetMapping(value = "/nueva-publicacion")
    public ModelAndView nueva(@ModelAttribute("command") PublicacionMascotaEnAdopcionForm form, @RequestParam("mascota") Integer mascotaId) throws Exception {
        Persona persona = personaService.findPersonaConOrganizacion(getCurrentLoggedUser());
        PublicacionMascotaEnAdopcion publicacion = new PublicacionMascotaEnAdopcion();
        publicacion.setMascota(mascotaService.findBy(mascotaId));
        if (!publicacion.getMascota().getEstado().equals( EstadoMascota.EN_CASA)) {
            throw new Exception("Error"); //FIXME ERROR OR REDIRECT NO PUEDE PERDER UNA MASCOTA QUE NO TIENE.
        }

        form.setPreguntasAdopcion(persona.getOrganizacion().getPreguntasAdopcion());
        form.setPublicacion(publicacion);
        return new ModelAndView("publicacionMascotaEnAdopcionForm");
    }

    @PostMapping(value = "/")
    public ModelAndView guardar(@ModelAttribute("command") PublicacionMascotaEnAdopcionForm form) throws Exception {
        //Validar datos del form

        PublicacionMascotaEnAdopcion nueva = form.getPublicacion();
        Ubicacion ubicacion = UbicacionHelper.getInstance().get(form.getUbicacion());
        nueva.setUbicacion(ubicacion);
        publicacionService.nueva(nueva, getCurrentLoggedUser());
        String successMessage = URLEncoder.encode("Su publicacion se realizo con exito","UTF-8");
        return new ModelAndView("redirect:/auth/mypubs/?toastMessage=" + successMessage);
    }
    @GetMapping(value = "/{idPublicacion}")
    public ModelAndView editar(@ModelAttribute("command") PublicacionMascotaEnAdopcionForm form, @PathVariable("idPublicacion") Integer publicacionId) throws Exception {
        PublicacionMascotaEnAdopcion publicacion = (PublicacionMascotaEnAdopcion) publicacionService.findPublicacionParaEditar(publicacionId, getCurrentLoggedUser());
        form.setPublicacion(publicacion);
        form.setMode(publicacionId.toString());
        Persona persona = personaService.findPersonaConOrganizacion(getCurrentLoggedUser());
        form.setPreguntasAdopcion(persona.getOrganizacion().getPreguntasAdopcion());
        return new ModelAndView("publicacionMascotaEnAdopcionForm");
    }

}


