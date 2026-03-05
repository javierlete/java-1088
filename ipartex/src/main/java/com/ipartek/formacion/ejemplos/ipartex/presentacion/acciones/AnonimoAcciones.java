package com.ipartek.formacion.ejemplos.ipartex.presentacion.acciones;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Modelo;
import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.bibliotecas.logicanegocio.LogicaNegocioException;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;

public class AnonimoAcciones {
	private static final AnonimoNegocio NEGOCIO = (AnonimoNegocio) Fabrica.getObjeto("negocio.anonimo");

	@Ruta("/index")
	public static String index(Modelo modelo) {
		modelo.salida().put("mensajes", NEGOCIO.listarMensajes());

		return "index";
	}

	@Ruta("/registrar")
	public static String registrar(Modelo modelo) {
		return "registrar";
	}

	@Ruta("/registrar-post")
	public static String registrarPost(Modelo modelo) {
		// Recoger la información recibida en la petición
		String nombre = modelo.entrada().get("nombre")[0];
		String email = modelo.entrada().get("email")[0];
		String password = modelo.entrada().get("password")[0];

		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		Usuario usuario = new Usuario(null, nombre, email, password);

		// Ejecutar lógica de negocio
		try {
			NEGOCIO.registrar(usuario);
		} catch (LogicaNegocioException lne) {
			// Empaquetar modelo para la siguiente vista
			modelo.salida().put("usuario", usuario);
			modelo.salida().put("errores", lne.getErrores());

			// Saltar a la siguiente vista
			return "registrar";
		}

		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista
		return "redirect:/index";
	}

	@Ruta("/login")
	public static String login(Modelo modelo) {
		return "login";
	}

	@Ruta("/login-post")
	public static String loginPost(Modelo modelo) {
		// Recoger la información recibida en la petición
		String email = modelo.entrada().get("email")[0];
		String password = modelo.entrada().get("password")[0];

		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes

		// Ejecutar lógica de negocio
		Optional<Usuario> usuario = NEGOCIO.autenticar(email, password);

		if (usuario.isEmpty()) {
			// Empaquetar modelo para la siguiente vista
			modelo.salida().put("email", email);
			modelo.salida().put("errores", "El usuario o la contraseña son incorrectos");

			// Saltar a la siguiente vista
			return "login";
		}

		// Empaquetar modelo para la siguiente vista
		modelo.sesionSalida().put("usuario", usuario.get());
		
		// Saltar a la siguiente vista
		return "redirect:/index";
	}
}
