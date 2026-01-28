package com.ipartek.formacion.ejemplos.ipartube.controladores.acciones;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.UsuarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.VideoCrud;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdministradorAcciones {

	public static void adminIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición
		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio
	
		ArrayList<Video> videos = VideoCrud.obtenerTodos();
	
		// Empaquetar modelo para la siguiente vista
	
		request.setAttribute("videos", videos);
	
		// Saltar a la siguiente vista
		request.getRequestDispatcher("/WEB-INF/vistas/admin/index.jsp").forward(request, response);
	}

	public static void adminBorrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición
	
		String sId = request.getParameter("id");
	
		// Convertir las partes que sean necesarias
	
		Long id = Long.parseLong(sId);
	
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio
	
		// VideoCrud.borrar(id);
	
		VideoCrud.borrar(id);
	
		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista
	
		response.sendRedirect("index");
	}

	public static void adminFormulario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición
	
		String sId = request.getParameter("id");
	
		if (sId != null) {
			// Convertir las partes que sean necesarias
	
			Long id = Long.parseLong(sId);
	
			// Crear objetos con todas las partes
			// Ejecutar lógica de negocio
	
			Video video = VideoCrud.obtenerPorId(id);
	
			// Empaquetar modelo para la siguiente vista
	
			request.setAttribute("video", video);
		}

		// Ejecutar lógica de negocio
		ArrayList<Usuario> usuarios = UsuarioCrud.obtenerTodos();
		
		// Empaquetar modelo para la siguiente vista
		request.setAttribute("usuarios", usuarios);
		
		// Saltar a la siguiente vista
	
		request.getRequestDispatcher("/WEB-INF/vistas/admin/formulario.jsp").forward(request, response);
	}

	public static void adminGuardar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición
	
		String sId = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String sFecha = request.getParameter("fecha");
		String imagenUrl = request.getParameter("imagen");
		String videoUrl = request.getParameter("video");
		String descripcion = request.getParameter("descripcion");
		
		String sIdUsuario = request.getParameter("usuario");
	
		// Convertir las partes que sean necesarias
	
		Long id = sId.isBlank() ? null : Long.parseLong(sId);
		LocalDateTime fecha = sFecha.isBlank() ? null : LocalDateTime.parse(sFecha);
	
		Long idUsuario = sId.isBlank() ? null : Long.parseLong(sIdUsuario);
		
		// Crear objetos con todas las partes
	
		Usuario usuario = new Usuario(idUsuario, null, null, null, null, null);
		
		Video video = new Video(id, titulo, descripcion, imagenUrl, fecha, videoUrl, usuario);
	
		// Ejecutar lógica de negocio
	
		if (id == null) {
			VideoCrud.insertar(video);
		} else {
			VideoCrud.modificar(video);
		}
	
		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista
	
		response.sendRedirect("index");
	}

}
