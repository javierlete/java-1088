package com.ipartek.formacion.ejemplos.ipartube.rest;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.UsuarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.dtos.LoginDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/login")
public class LoginRest {
	@POST
	public Usuario autenticar(LoginDto usuarioLogin) {
		Usuario usuarioLogueado = UsuarioCrud.obtenerPorEmail(usuarioLogin.email());
		
		if(usuarioLogueado != null && usuarioLogueado.getPassword().equals(usuarioLogin.password())) {
			return usuarioLogueado;
		}
		
		throw new jakarta.ws.rs.NotAuthorizedException("Usuario o contraseña incorrectos");
	}
}
