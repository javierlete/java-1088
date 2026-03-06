package com.ipartek.formacion.ejemplos.ipartex.presentacion.acciones;

import java.time.LocalDateTime;

import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Modelo;
import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.UsuarioNegocio;

public class UsuarioAcciones {
	private static final UsuarioNegocio NEGOCIO = (UsuarioNegocio) Fabrica.getObjeto("negocio.usuario");

	@Ruta("/usuario/nuevo-mensaje")
	public static String nuevoMensaje(Modelo modelo) {
		// Recoger la información recibida en la petición
		String texto = modelo.entrada().get("texto")[0];
		Usuario usuario = (Usuario) modelo.sesionEntrada().get("usuario");
		
		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		Mensaje mensaje = new Mensaje(null, texto, LocalDateTime.now(), usuario);
		
		// Ejecutar lógica de negocio
		NEGOCIO.enviarMensaje(mensaje);
		
		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista
		return "redirect:/index";
	}
}
