package com.ipartek.formacion.ejemplos.ipartube.controladores;

import java.io.IOException;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.VideoCrud;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = request.getPathInfo();

		switch (ruta) {
		case "/index" -> index(request, response);
		case "/video" -> video(request, response);
		case "/admin/index" -> adminIndex(request, response);
		case "/admin/borrar" -> adminBorrar(request, response);
		case "/admin/formulario" -> adminFormulario(request, response);
		default -> request.getRequestDispatcher("/WEB-INF/vistas" + ruta + ".jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Recoger la información recibida en la petición
		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio

		ArrayList<Video> videos = VideoCrud.obtenerTodos();

		// Empaquetar modelo para la siguiente vista

		request.setAttribute("videos", videos);

		// Saltar a la siguiente vista
		request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
	}

	private void video(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recoger la información recibida en la petición

		String sId = request.getParameter("id");

		// Convertir las partes que sean necesarias

		Long id = Long.parseLong(sId);

		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio

		Video video = VideoCrud.obtenerPorId(id);

		// Empaquetar modelo para la siguiente vista

		request.setAttribute("video", video);

		// Saltar a la siguiente vista

		request.getRequestDispatcher("/WEB-INF/vistas/video.jsp").forward(request, response);
	}

	private void adminIndex(HttpServletRequest request, HttpServletResponse response)
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

	private void adminBorrar(HttpServletRequest request, HttpServletResponse response)
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

	private void adminFormulario(HttpServletRequest request, HttpServletResponse response)
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
		// Saltar a la siguiente vista

		request.getRequestDispatcher("/WEB-INF/vistas/admin/formulario.jsp").forward(request, response);
	}

}
