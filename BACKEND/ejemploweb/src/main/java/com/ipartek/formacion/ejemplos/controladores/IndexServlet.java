package com.ipartek.formacion.ejemplos.controladores;

import java.io.IOException;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.accesodatos.AsistentesCrud;
import com.ipartek.formacion.ejemplos.modelos.Asistente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición
		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio

		ArrayList<Asistente> asistentes = AsistentesCrud.obtenerTodos();

		// Empaquetar modelo para la siguiente vista

		request.setAttribute("asistentes", asistentes);

		// Saltar a la siguiente vista

		request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);

	}

}
