package com.ipartek.formacion.ejemplos.ipartube.acciones;

import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Modelo;
import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoComentario;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoVideo;
import com.ipartek.formacion.ejemplos.ipartube.dtos.ComentarioDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDto;

public class PublicoAcciones {

	private static final DaoVideo DAO_VIDEO = (DaoVideo) Fabrica.getObjeto("dao.video");
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.getObjeto("dao.usuario");
	private static final DaoComentario DAO_COMENTARIO = (DaoComentario) Fabrica.getObjeto("dao.comentario");

	@Ruta("/index")
	public static String index(Modelo modelo) {
		modelo.salida().put("videos", DAO_VIDEO.obtenerTodos());
		modelo.salida().put("usuarios", DAO_USUARIO.obtenerTodos());

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

		VideoDetalleDto video = DAO_VIDEO.obtenerPorIdDto(idVideo).orElse(null);
		ComentarioDto comentario = idComentario != null ? DAO_COMENTARIO.obtenerPorIdDto(idComentario).orElse(null)
				: null;
		Iterable<ComentarioDto> comentarios = idComentario == null ? DAO_COMENTARIO.obtenerPorVideoDto(idVideo)
				: DAO_COMENTARIO.obtenerPorPadreDto(idComentario);

		VideoDto videoDto = new VideoDto(video, comentario, comentarios);

		// Empaquetar modelo para la siguiente vista

		modelo.salida().put("videodto", videoDto);

		// Saltar a la siguiente vista

		return "video";
	}

}
