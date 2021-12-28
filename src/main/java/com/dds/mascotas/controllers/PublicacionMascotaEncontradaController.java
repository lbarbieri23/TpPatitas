package com.dds.mascotas.controllers;

import com.dds.helpers.UbicacionHelper;
import com.dds.mascotas.forms.PublicacionMascotaEncontradaForm;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.adapters.hogares.CriteriosBusqueda;
import com.dds.mascotas.model.adapters.hogares.DDSHogarAdapter;
import com.dds.mascotas.service.MascotaService;
import com.dds.mascotas.service.PersonaService;
import com.dds.mascotas.service.PublicacionService;
import com.dds.services.hogares.HogarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("auth/mypubs/mascota-encontrada")
public class PublicacionMascotaEncontradaController extends CommonController {

    private PublicacionService publicacionService;
    private PersonaService personaService;
    private MascotaService mascotaService;



    @Autowired
    public PublicacionMascotaEncontradaController(PublicacionService publicacionService, PersonaService personaService, MascotaService mascotaService) {
        this.publicacionService = publicacionService;
        this.personaService = personaService;
        this.mascotaService = mascotaService;
    }

    //MAIN VIEW
    @GetMapping(value = "/nueva-publicacion")
    public ModelAndView nueva(@ModelAttribute("command") PublicacionMascotaEncontradaForm form) throws Exception {
        Persona persona = personaService.findPersonaConOrganizacion(getCurrentLoggedUser());

        //FIXME if persona == null throws exception 404
        form.setPublicacion(new PublicacionMascotaEncontrada());
        form.setTipoMascotas(persona.getOrganizacion().getTiposDeMascota());

        CriteriosBusqueda criterios = new CriteriosBusqueda.CriterioBuilder().build();
        form.setHogares(new DDSHogarAdapter().buscarHogares(criterios));
        return new ModelAndView("publicacionMascotaEncontradaForm");
    }

    @PostMapping(value = "/")
    public ModelAndView guardar(@ModelAttribute("command") PublicacionMascotaEncontradaForm form) throws Exception {
        //Validar datos del form

        PublicacionMascotaEncontrada nueva = form.getPublicacion();
        Ubicacion ubicacion = UbicacionHelper.getInstance().get(form.getUbicacion());
        nueva.setUbicacion(ubicacion);
        publicacionService.nueva(nueva, getCurrentLoggedUser());
        String successMessage = URLEncoder.encode("Su publicacion se realizo con exito","UTF-8");
        return new ModelAndView("redirect:/auth/mypubs/?toastMessage=" + successMessage);
    }

    @GetMapping(value = "/findCaracteristicasBy", headers = "Accept=application/json")
    public @ResponseBody List<Caracteristica> findCaracteristicasBy(@RequestParam("tipoMascotaId") Long id) throws Exception {
        Persona persona = personaService.findPersonaConOrganizacion(getCurrentLoggedUser());
        return mascotaService.getCaracteristicas(persona.getOrganizacion(), id);
    }

    @GetMapping(value = "/{idPublicacion}")
    public ModelAndView editar(@ModelAttribute("command") PublicacionMascotaEncontradaForm form, @PathVariable("idPublicacion") Integer publicacionId) throws Exception {
        Persona persona = personaService.findPersonaConOrganizacion(getCurrentLoggedUser());
        PublicacionMascotaEncontrada publicacion = (PublicacionMascotaEncontrada) publicacionService.findPublicacionParaEditar(publicacionId, getCurrentLoggedUser());

        form.setPublicacion(publicacion);
        form.setMode(publicacionId.toString());
        form.setTipoMascotas(persona.getOrganizacion().getTiposDeMascota());
        return new ModelAndView("publicacionMascotaEncontradaForm");
    }

    @PostMapping(value = "/{idPublicacion}")
    public ModelAndView actualizar(@ModelAttribute("command") PublicacionMascotaEncontradaForm form, @PathVariable("idPublicacion") Integer publicacionId) throws Exception {
        //Validar datos del form

        PublicacionMascotaEncontrada toUpdate = form.getPublicacion();
        toUpdate.setId(publicacionId);
        Ubicacion ubicacion = UbicacionHelper.getInstance().get(form.getUbicacion());
        toUpdate.setUbicacion(ubicacion);
        publicacionService.actualizar(form.getPublicacion(), getCurrentLoggedUser());
        String successMessage = URLEncoder.encode("Su publicacion se actualizo con exito","UTF-8");
        return new ModelAndView("redirect:/auth/mypubs/?toastMessage=" + successMessage);
    }



}
