package com.dds.mascotas.controllers;

import com.dds.helpers.UbicacionHelper;
import com.dds.mascotas.forms.PublicacionMascotaPerdidaForm;
import com.dds.mascotas.model.*;
import com.dds.mascotas.service.MascotaService;
import com.dds.mascotas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;

@Controller
@RequestMapping("auth/mypubs/mascota-perdida")
public class PublicacionMascotaPerdidaController extends CommonController {

    private PublicacionService publicacionService;
    private MascotaService mascotaService;

    @Autowired
    public PublicacionMascotaPerdidaController(PublicacionService publicacionService, MascotaService mascotaService) {
        this.publicacionService = publicacionService;
        this.mascotaService = mascotaService;
    }

    //MAIN VIEW
    @GetMapping(value = "/nueva-publicacion")
    public ModelAndView nueva(@ModelAttribute("command") PublicacionMascotaPerdidaForm form, @RequestParam("mascota") Integer mascotaId) throws Exception {
        PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida();

        publicacion.setMascota(mascotaService.findBy(mascotaId));
        if (!publicacion.getMascota().getEstado().equals( EstadoMascota.EN_CASA)) {
            throw new Exception("Error"); //FIXME ERROR OR REDIRECT NO PUEDE PERDER UNA MASCOTA QUE NO TIENE.
        }

        form.setMode("");
        form.setPublicacion(publicacion);
        return new ModelAndView("publicacionMascotaPerdidaForm");
    }

    @PostMapping(value = "/")
    public ModelAndView guardar(@ModelAttribute("command") PublicacionMascotaPerdidaForm form) throws Exception {
        //Validar datos del form

        PublicacionMascotaPerdida nueva = form.getPublicacion();
        Ubicacion ubicacion = UbicacionHelper.getInstance().get(form.getUbicacion());
        nueva.setUbicacion(ubicacion);
        publicacionService.nueva(form.getPublicacion(), getCurrentLoggedUser());
        String successMessage = URLEncoder.encode("Su publicacion se realizo con exito","UTF-8");
        return new ModelAndView("redirect:/auth/mypubs/?toastMessage=" + successMessage);
    }


    @GetMapping(value = "/{idPublicacion}")
    public ModelAndView editar(@ModelAttribute("command") PublicacionMascotaPerdidaForm form, @PathVariable("idPublicacion") Integer publicacionId) throws Exception {
        PublicacionMascotaPerdida publicacion = (PublicacionMascotaPerdida) publicacionService.findPublicacionParaEditar(publicacionId, getCurrentLoggedUser());
        form.setPublicacion(publicacion);
        form.setMode(publicacionId.toString());
        return new ModelAndView("publicacionMascotaPerdidaForm");
    }

    @PostMapping(value = "/{idPublicacion}")
    public ModelAndView actualizar(@ModelAttribute("command") PublicacionMascotaPerdidaForm form, @PathVariable("idPublicacion") Integer publicacionId) throws Exception {
        //Validar datos del form

        PublicacionMascotaPerdida toUpdate = form.getPublicacion();
        toUpdate.setId(publicacionId);
        Ubicacion ubicacion = UbicacionHelper.getInstance().get(form.getUbicacion());
        toUpdate.setUbicacion(ubicacion);
        publicacionService.actualizar(form.getPublicacion(), getCurrentLoggedUser());
        String successMessage = URLEncoder.encode("Su publicacion se actualizo con exito","UTF-8");
        return new ModelAndView("redirect:/auth/mypubs/?toastMessage=" + successMessage);
    }
}