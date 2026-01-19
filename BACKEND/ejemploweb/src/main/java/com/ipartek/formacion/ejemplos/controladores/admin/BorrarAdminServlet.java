package com.ipartek.formacion.ejemplos.controladores.admin;

import java.io.IOException;
import java.sql.PreparedStatement;

import com.ipartek.formacion.ejemplos.bibliotecas.JdbcHelper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/borrar")
public class BorrarAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición

		String sId = request.getParameter("id");

		// Convertir las partes que sean necesarias

		Long id = Long.parseLong(sId);

		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio

		try (PreparedStatement pst = JdbcHelper.prepararSql("delete from asistentes where id=?");) {
			pst.setLong(1, id);

			pst.executeUpdate();

			// Empaquetar modelo para la siguiente vista
			// Saltar a la siguiente vista

			response.sendRedirect("index");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
