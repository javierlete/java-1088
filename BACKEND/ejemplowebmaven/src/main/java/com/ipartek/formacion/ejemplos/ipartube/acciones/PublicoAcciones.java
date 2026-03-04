package com.ipartek.formacion.ejemplos.ipartube.acciones;

import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Modelo;
import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDto;
import com.ipartek.formacion.ejemplos.ipartube.logicanegocio.PublicoNegocio;

public class PublicoAcciones {

	private static final PublicoNegocio NEGOCIO = (PublicoNegocio) Fabrica.getObjeto("negocio.publico");

	@Ruta("/index")
	public static String index(Modelo modelo) {
		// Recoger la información recibida en la petición
		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes

		// Ejecutar lógica de negocio
		// Empaquetar modelo para la siguiente vista
		modelo.salida().put("videos", NEGOCIO.listadoVideos());
		modelo.salida().put("usuarios", NEGOCIO.listadoUsuarios());

		// Saltar a la siguiente vista
		return "index";
	}

	@Ruta("/video")
	public static String video(Modelo modelo) {
		// Recoger la información recibida en la petición
		String sIdVideo = modelo.entrada().getOrDefault("id", new String[] { null })[0];
		String sIdComentario = modelo.entrada().getOrDefault("comentario", new String[] { null })[0];

		// Convertir las partes que sean necesarias
		Long idVideo = Long.parseLong(sIdVideo);
		Long idComentario = sIdComentario == null ? null : Long.parseLong(sIdComentario);

		// Crear objetos con todas las partes

		// Ejecutar lógica de negocio
		VideoDto videoDto = NEGOCIO.detalleVideo(idVideo, idComentario).orElse(null);
		
		// Empaquetar modelo para la siguiente vista
		modelo.salida().put("videodto", videoDto);

		// Saltar a la siguiente vista
		return "video";
	}

}
