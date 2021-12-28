package com.dds.mascotas.controllers;

import com.dds.helpers.UbicacionHelper;
import com.dds.mascotas.forms.PublicacionMascotaPerdidaForm;
import com.dds.mascotas.forms.PublicacionQuieroAdoptarForm;
import com.dds.mascotas.model.*;
import com.dds.mascotas.service.MascotaService;
import com.dds.mascotas.service.PersonaService;
import com.dds.mascotas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("auth/mypubs/quiero-adoptar")
public class PublicacionMascotaQuieroAdoptarController extends CommonController {

    private PublicacionService publicacionService;
    private MascotaService mascotaService;
    private PersonaService personaService;

    @Autowired
    public PublicacionMascotaQuieroAdoptarController(PublicacionService publicacionService, MascotaService mascotaService, PersonaService personaService) {
        this.publicacionService = publicacionService;
        this.mascotaService = mascotaService;
        this.personaService = personaService;
    }

    //MAIN VIEW

    @GetMapping(value = "/nueva-publicacion")
    public ModelAndView nueva(@ModelAttribute("command") PublicacionQuieroAdoptarForm form) throws Exception {
        Persona persona = personaService.findPersonaConOrganizacion(getCurrentLoggedUser());
        PublicacionQuieroAdoptar publicacion = new PublicacionQuieroAdoptar();

        form.setTipoMascotas(persona.getOrganizacion().getTiposDeMascota());
        form.setPreguntas(persona.getOrganizacion().getPreguntasQuieroAdoptar());
        form.setPublicacion(publicacion);
        return new ModelAndView("publicacionQuieroAdoptarForm");
    }

    @PostMapping(value = "/")
    public ModelAndView guardar(@ModelAttribute("command") PublicacionQuieroAdoptarForm form) throws Exception {
        //Validar datos del form
        PublicacionQuieroAdoptarForm formVERGA = form;
        PublicacionQuieroAdoptar nueva = form.getPublicacion();

        nueva.setCaracteristicasDeMascotaBuscada(form.getPublicacion().getCaracteristicasDeMascotaBuscada());

        Ubicacion ubicacion = UbicacionHelper.getInstance().get(form.getUbicacion());
        nueva.setUbicacion(ubicacion);
        publicacionService.nueva(nueva, getCurrentLoggedUser());
        String successMessage = URLEncoder.encode("Su publicacion se realizo con exito","UTF-8");
        return new ModelAndView("redirect:/auth/mypubs/?toastMessage=" + successMessage);
    }

    @GetMapping(value = "/{idPublicacion}")
    public ModelAndView editar(@ModelAttribute("command") PublicacionQuieroAdoptarForm form, @PathVariable("idPublicacion") Integer publicacionId) throws Exception {
        PublicacionQuieroAdoptar publicacion = (PublicacionQuieroAdoptar) publicacionService.findPublicacionParaEditar(publicacionId, getCurrentLoggedUser());
        form.setPublicacion(publicacion);
        form.setTipoMascotas(this.mascotaService.findTiposDeMascotas());

        form.setEstaEditando(true);
        Persona persona = personaService.findPersonaConOrganizacion(getCurrentLoggedUser());
        form.setPreguntas(persona.getOrganizacion().getPreguntasQuieroAdoptar());
        return new ModelAndView("publicacionQuieroAdoptarForm");
    }

    @GetMapping(value = "/findCaracteristicasBy", headers = "Accept=application/json")
    public @ResponseBody List<Caracteristica> findCaracteristicasBy(@RequestParam("tipoMascotaId") Long id) throws Exception {
        Persona persona = personaService.findPersonaConOrganizacion(getCurrentLoggedUser());
        return mascotaService.getCaracteristicas(persona.getOrganizacion(), id);

    }
}
