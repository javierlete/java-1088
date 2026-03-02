package com.ipartek.formacion.ejemplos.ipartube.rest;

import com.ipartek.formacion.ejemplos.bibliotecas.JwtUtils;
import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartube.dtos.LoginDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/login")
public class LoginRest {
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.getObjeto("dao.usuario");
	
	@POST
	public TokenResponse autenticar(LoginDto usuarioLogin) {
		Usuario usuarioLogueado = DAO_USUARIO.obtenerPorEmail(usuarioLogin.email());

		if (usuarioLogueado != null && usuarioLogueado.getPassword().equals(usuarioLogin.password())) {
			// Generamos el token con las JwtUtils que hemos creado
			String token = JwtUtils.generate(usuarioLogueado.getId(), usuarioLogueado.getNombre(), usuarioLogueado.getRol().getNombre());
			
			// Devolvemos un json: { "token": "laksjdhl kajsdghlkahsfjlkahsdf " }
			return new TokenResponse(token);
		}

		throw new jakarta.ws.rs.NotAuthorizedException("Usuario o contraseña incorrectos");
	}

	public static class TokenResponse {
		public String token;

		public TokenResponse(String token) {
			this.token = token;
		}
	}
}
