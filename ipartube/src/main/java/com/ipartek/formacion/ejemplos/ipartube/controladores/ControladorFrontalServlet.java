package com.ipartek.formacion.ejemplos.ipartube.controladores;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.bibliotecas.criptografia.PwdMini;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.UsuarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.VideoCrud;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = request.getPathInfo();

		switch (ruta) {
		case "/index" -> index(request, response);
		case "/video" -> video(request, response);
		case "/login" -> login(request, response);
		case "/logout" -> logout(request, response);
		case "/admin/index" -> adminIndex(request, response);
		case "/admin/borrar" -> adminBorrar(request, response);
		case "/admin/formulario" -> adminFormulario(request, response);
		case "/admin/guardar" -> adminGuardar(request, response);
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

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("GET".equals(request.getMethod())) {
			// Recoger la información recibida en la petición
			// Convertir las partes que sean necesarias
			// Crear objetos con todas las partes
			// Ejecutar lógica de negocio
			// Empaquetar modelo para la siguiente vista
			// Saltar a la siguiente vista
			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
			return;
		}

		// Recoger la información recibida en la petición

		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			System.out.println(PwdMini.hash(password));		
			
			// Convertir las partes que sean necesarias
			// Crear objetos con todas las partes
			// Ejecutar lógica de negocio

			Usuario usuario = UsuarioCrud.obtenerPorEmail(email);
			
			if (usuario != null && PwdMini.verify(password, usuario.password())) {
				// Empaquetar modelo para la siguiente vista
				session.setAttribute("email", email);
				// Saltar a la siguiente vista
				response.sendRedirect("admin/index");
			} else {
				// Empaquetar modelo para la siguiente vista
				request.setAttribute("email", email);
				// Saltar a la siguiente vista
				request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Recoger la información recibida en la petición
		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio
		
		request.getSession().invalidate();
		
		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista
		
		response.sendRedirect("login");
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

	private void adminGuardar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición

		String sId = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String sFecha = request.getParameter("fecha");
		String imagenUrl = request.getParameter("imagen");
		String videoUrl = request.getParameter("video");
		String descripcion = request.getParameter("descripcion");

		// Convertir las partes que sean necesarias

		Long id = sId.isBlank() ? null : Long.parseLong(sId);
		LocalDateTime fecha = sFecha.isBlank() ? null : LocalDateTime.parse(sFecha);

		// Crear objetos con todas las partes

		Video video = new Video(id, titulo, descripcion, imagenUrl, fecha, videoUrl);

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
