package com.ipartek.formacion.ejemplos.ipartube.controladores;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final ArrayList<Video> VIDEOS = new ArrayList<Video>();

	static {
		for (long i = 1; i <= 9; i++) {
			Video video = new Video(i, "Video " + i, "Descripción " + i, "https://picsum.photos/1600/900?" + i,
					LocalDateTime.now(), "https://www.youtube.com/embed/ChrLRauOR28?si=bOcjf4mXy4_6HQmk");

			VIDEOS.add(video);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = request.getPathInfo();

		switch (ruta) {
		case "/index" -> index(request, response);
		case "/video" -> video(request, response);
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

		ArrayList<Video> videos = VIDEOS;

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

		Video video = VIDEOS.stream().filter(v -> v.id() == id).findFirst().get();

		// Empaquetar modelo para la siguiente vista

		request.setAttribute("video", video);

		// Saltar a la siguiente vista

		request.getRequestDispatcher("/WEB-INF/vistas/video.jsp").forward(request, response);
	}
}
