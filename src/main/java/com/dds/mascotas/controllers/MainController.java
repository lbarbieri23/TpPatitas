package com.dds.mascotas.controllers;


import com.dds.mascotas.forms.MainForm;
import com.dds.mascotas.forms.PerfilForm;
import com.dds.mascotas.model.Publicacion;
import com.dds.mascotas.model.TipoPublicacion;
import com.dds.mascotas.model.security.Usuario;
import com.dds.mascotas.service.PersonaService;
import com.dds.mascotas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController extends CommonController {

    private final int MAX_ITEMS_PER_PAGE = 10;




    @GetMapping(value = "/isSpringUp", headers = "Accept=application/json", produces = "application/json;charset=UTF-8")
    public @ResponseBody Usuario findOrdenesByConceptoTodoPago() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("Diego");
        return usuario;
    }
    private PublicacionService publicacionService;
    private PersonaService personaService;

    @Autowired
    private MainController(PublicacionService publicacionService,  PersonaService personaService) {
        this.publicacionService = publicacionService;
        this.personaService = personaService;
    }



    @GetMapping(value = "/")
    public ModelAndView index() throws Exception {
        return new ModelAndView("redirect:/index.html");
    }

    @GetMapping(value = "/index.html")
    public ModelAndView home(@ModelAttribute("command") MainForm form, RedirectAttributes redirectAttributes) throws Exception {
        form.setPublicacionList(publicacionService.findBy(form.getTipoPublicacion(), form.getSearchCriteria(), 1, MAX_ITEMS_PER_PAGE, this.personaService.findByLoggedUser(getCurrentLoggedUser()) ));
        return new ModelAndView("main");
    }

    @PostMapping(value = "/search", params = "search")
    public ModelAndView search(@ModelAttribute("command") MainForm form) throws Exception {
        form.setPublicacionList(publicacionService.findBy(form.getTipoPublicacion(), form.getSearchCriteria(), 1, MAX_ITEMS_PER_PAGE, this.personaService.findByLoggedUser(getCurrentLoggedUser())));
        return new ModelAndView("main");
    }

    @PostMapping(value = "/search", params = "all")
    public ModelAndView searchAll(@ModelAttribute("command") MainForm form) throws Exception {
        form.setTipoPublicacion(null);
        form.setPublicacionList(publicacionService.findBy(form.getTipoPublicacion(), form.getSearchCriteria(), 1, MAX_ITEMS_PER_PAGE, this.personaService.findByLoggedUser(getCurrentLoggedUser())));
        return new ModelAndView("main");
    }

    @PostMapping(value = "/search", params = "perdidas")
    public ModelAndView searchPerdidas(@ModelAttribute("command") MainForm form) throws Exception {
        form.setTipoPublicacion(TipoPublicacion.MASCOTA_PERDIDA);
        form.setPublicacionList(publicacionService.findBy(form.getTipoPublicacion(), form.getSearchCriteria(), 1, MAX_ITEMS_PER_PAGE, this.personaService.findByLoggedUser(getCurrentLoggedUser())));
        return new ModelAndView("main");
    }

    @PostMapping(value = "/search", params = "encontradas")
    public ModelAndView searchEncontradas(@ModelAttribute("command") MainForm form) throws Exception {
        form.setTipoPublicacion(TipoPublicacion.MASCOTA_ENCONTRADA);
        form.setPublicacionList(publicacionService.findBy(form.getTipoPublicacion(), form.getSearchCriteria(), 1, MAX_ITEMS_PER_PAGE, this.personaService.findByLoggedUser(getCurrentLoggedUser())));
        return new ModelAndView("main");
    }

    @PostMapping(value = "/search", params = "enadopcion")
    public ModelAndView searchEnAdopcion(@ModelAttribute("command") MainForm form) throws Exception {
        form.setTipoPublicacion(TipoPublicacion.MASCOTA_EN_ADOPCION);
        form.setPublicacionList(publicacionService.findBy(form.getTipoPublicacion(), form.getSearchCriteria(), 1, MAX_ITEMS_PER_PAGE, this.personaService.findByLoggedUser(getCurrentLoggedUser())));
        return new ModelAndView("main");
    }

    @PostMapping(value = "/search", params = "quieroadoptar")
    public ModelAndView searchQuieroAdoptar(@ModelAttribute("command") MainForm form) throws Exception {
        form.setTipoPublicacion(TipoPublicacion.QUIERO_ADOPTAR);
        form.setPublicacionList(publicacionService.findBy(form.getTipoPublicacion(), form.getSearchCriteria(), 1, MAX_ITEMS_PER_PAGE, this.personaService.findByLoggedUser(getCurrentLoggedUser())));
        return new ModelAndView("main");
    }






    @GetMapping(value = "/{page}")
    public ModelAndView getListPage(@ModelAttribute("command") MainForm form, @PathVariable("page") Integer page) throws Exception {
        /*Long cantidadResultados = publicacionService.getCantidadAprobada(null, null);
        Long maxPages = (long) Math.ceil(cantidadResultados / MAX_ITEMS_PER_PAGE);
        if (page > maxPages) {
            throw new Exception("404");//FIXME
        }

        form.setPublicacionList(publicacionService.findBy(null, null, page, MAX_ITEMS_PER_PAGE));*/
        return new ModelAndView("index");
    }

}
