package com.spring5.security;

import java.util.ArrayList;
import java.util.List;

import com.dds.mascotas.model.security.Usuario;
import com.dds.mascotas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UsuarioService usuarioService;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Usuario usuario = null;

		if (authentication == null) {
			return null;
		}
		if (authentication.getCredentials() == null) {
			return null;
		}

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		try {
			usuario = usuarioService.login(username, password);
		} catch (Exception ex) {
			throw new BadCredentialsException(ex.getMessage());
		}

		if (usuario == null) {
			throw new BadCredentialsException("Usuario o password incorrectos");
		}


		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		return new UsernamePasswordAuthenticationToken(usuario, usuario.getPassword(), authorities);
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}