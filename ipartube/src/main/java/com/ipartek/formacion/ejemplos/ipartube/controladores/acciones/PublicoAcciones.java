package com.ipartek.formacion.ejemplos.ipartube.controladores.acciones;

import java.io.IOException;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.bibliotecas.criptografia.PwdMini;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.UsuarioCrud;
import com.ipartek.formacion.ejemplos.ipartube.accesodatos.VideoCrud;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PublicoAcciones {

	public static void index(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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

	public static void video(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	public static void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				session.setAttribute("usuario", usuario);
				// Saltar a la siguiente vista
				if ("ADMINISTRADOR".equals(usuario.rol().nombre())) {
					response.sendRedirect("admin/index");
				} else {
					response.sendRedirect("index");
				}
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

	public static void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Recoger la información recibida en la petición
		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio

		request.getSession().invalidate();

		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista

		response.sendRedirect("login");
	}

}
