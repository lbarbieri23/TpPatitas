package com.dds.mascotas.controllers;

import com.dds.mascotas.model.security.Usuario;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession httpSession;


    @GetMapping(value = "/login")
    public ModelAndView loginHome(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) throws Exception {
        httpSession.setAttribute("referer-url", request.getHeader("Referer"));
        usuario.setUsername("test");
        return new ModelAndView("login");
    }

    @PostMapping(value = "/login")
    public ModelAndView doLogin(@ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
        String originalUrl = (String) httpSession.getAttribute("referer-url");
        try {
            SecurityContext sc = SecurityContextHolder.getContext();

            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
            sc.setAuthentication(authentication);

            if (sc.getAuthentication().isAuthenticated()) {
                HttpSession session = request.getSession(true);
                session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result.reject("error.code", new Object[] { ex.getMessage() }, "");
            return new ModelAndView("login");
        } finally {
            usuario.setUsername("");
            usuario.setPassword("");
        }

        httpSession.removeAttribute("referer-url");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();

        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
