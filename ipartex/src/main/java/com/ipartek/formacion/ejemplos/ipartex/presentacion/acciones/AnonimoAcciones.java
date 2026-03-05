package com.ipartek.formacion.ejemplos.ipartex.presentacion.acciones;

import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Modelo;
import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.LogicaNegocioException;

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
			modelo.salida().put("usuario", usuario);
			modelo.salida().put("errores", lne.getErrores());

			return "registrar";
		}
		
		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista
		return "redirect:/index";
	}
}
