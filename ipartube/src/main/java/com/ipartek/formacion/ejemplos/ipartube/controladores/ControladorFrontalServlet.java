package com.ipartek.formacion.ejemplos.ipartube.controladores;

import java.io.IOException;
import java.time.LocalDateTime;

import com.ipartek.formacion.ejemplos.ipartube.controladores.acciones.AdministradorAcciones;
import com.ipartek.formacion.ejemplos.ipartube.controladores.acciones.PublicoAcciones;
import com.ipartek.formacion.ejemplos.ipartube.controladores.acciones.UsuarioAcciones;

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

		request.setAttribute("ahora", LocalDateTime.now());
		
		switch (ruta) {
		case "/index" -> PublicoAcciones.index(request, response);
		case "/video" -> PublicoAcciones.video(request, response);
		case "/login" -> PublicoAcciones.login(request, response);
		case "/logout" -> PublicoAcciones.logout(request, response);
		case "/usuario" -> PublicoAcciones.usuario(request, response);
		case "/usuario/video/borrar" -> UsuarioAcciones.borrar(request, response);
		case "/usuario/video/guardar" -> UsuarioAcciones.guardar(request, response);
		case "/usuario/video/comentar" -> UsuarioAcciones.comentar(request, response);
		case "/usuario/video/megusta" -> UsuarioAcciones.meGusta(request, response);
		case "/admin/index" -> AdministradorAcciones.adminIndex(request, response);
		case "/admin/borrar" -> AdministradorAcciones.adminBorrar(request, response);
		case "/admin/formulario" -> AdministradorAcciones.adminFormulario(request, response);
		case "/admin/guardar" -> AdministradorAcciones.adminGuardar(request, response);
		default -> request.getRequestDispatcher("/WEB-INF/vistas" + ruta + ".jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
