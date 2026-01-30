package com.ipartek.formacion.ejemplos.ipartube.controladores.acciones;

import java.io.IOException;
import java.time.LocalDateTime;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.ComentarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.MeGustaCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.VideoCrud;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Comentario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UsuarioAcciones {

	public static void borrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición

		HttpSession session = request.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String sId = request.getParameter("id");

		// Convertir las partes que sean necesarias

		Long id = Long.parseLong(sId);

		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio

		// VideoCrud.borrar(id);

		VideoCrud.borrar(id, usuario.id());

		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista

		response.sendRedirect(request.getContextPath() + "/cf/usuario?id=" + usuario.id());
	}

	public static void guardar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		String sId = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String imagenUrl = request.getParameter("imagen");
		String videoUrl = request.getParameter("video");
		String descripcion = request.getParameter("descripcion");

		// Convertir las partes que sean necesarias

		Long id = sId == null ? null : Long.parseLong(sId);
		LocalDateTime fecha = LocalDateTime.now();

		// Crear objetos con todas las partes

		Video video = new Video(id, titulo, descripcion, imagenUrl, fecha, videoUrl, usuario, null);

		// Ejecutar lógica de negocio

		if (id == null) {
			VideoCrud.insertar(video);
		} else {
			VideoCrud.modificar(video);
		}

		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista

		response.sendRedirect(request.getContextPath() + "/cf/usuario?id=" + usuario.id());
	}

	public static void comentar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Recoger la información recibida en la petición

		HttpSession session = request.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		String sIdVideo = request.getParameter("id-video");
		String sIdComentarioPadre = request.getParameter("id-comentario-padre");
		LocalDateTime fecha = LocalDateTime.now();
		String texto = request.getParameter("texto");

		// Convertir las partes que sean necesarias

		Long idVideo = Long.parseLong(sIdVideo);
		Long idComentarioPadre = sIdComentarioPadre.isBlank() ? null : Long.parseLong(sIdComentarioPadre);

		// Crear objetos con todas las partes

		Video video = new Video(idVideo, null, null, null, null, null, null, null);
		Comentario comentarioPadre = new Comentario(idComentarioPadre, null, null, null, null, null, null);
		Comentario comentario = new Comentario(null, usuario, video, fecha, texto, null, comentarioPadre);

		// Ejecutar lógica de negocio

		ComentarioCrud.insertar(comentario);

		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista

		String url = request.getContextPath() + "/cf/video?id=" + idVideo;
		
		if(idComentarioPadre != null) {
			url += "&comentario=" + idComentarioPadre;
		}
		
		url += "#comentarios";
		
		response.sendRedirect(url);
	}

	public static void meGusta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Recoger la información recibida en la petición
		
		String sId = request.getParameter("id");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		// Convertir las partes que sean necesarias
		
		Long id = Long.parseLong(sId);
		
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio

		MeGustaCrud.insertar(usuario.id(), id);
		
		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista
		
		response.sendRedirect(request.getHeader("Referer"));
	}

}
