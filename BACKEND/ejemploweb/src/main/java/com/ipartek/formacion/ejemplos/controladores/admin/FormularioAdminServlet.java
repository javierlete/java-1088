package com.ipartek.formacion.ejemplos.controladores.admin;

import java.io.IOException;

import com.ipartek.formacion.ejemplos.accesodatos.AsistentesCrud;
import com.ipartek.formacion.ejemplos.modelos.Asistente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/formulario")
public class FormularioAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición
		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio
		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista

		request.getRequestDispatcher("/WEB-INF/vistas/admin/formulario.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición

		String sId = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");

		// Convertir las partes que sean necesarias

		Long id = sId.isBlank() ? null : Long.parseLong(sId);

		// Crear objetos con todas las partes

		Asistente asistente = new Asistente(id, nombre, apellidos);

		// Ejecutar lógica de negocio

		AsistentesCrud.guardar(asistente);

		// Empaquetar modelo para la siguiente vista
		// Saltar a la siguiente vista

		response.sendRedirect("index");
	}

}
