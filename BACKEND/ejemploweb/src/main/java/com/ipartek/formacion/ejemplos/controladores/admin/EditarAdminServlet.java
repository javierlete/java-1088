package com.ipartek.formacion.ejemplos.controladores.admin;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ipartek.formacion.ejemplos.bibliotecas.JdbcHelper;
import com.ipartek.formacion.ejemplos.modelos.Asistente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/editar")
public class EditarAdminServlet extends HttpServlet {
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

		try (PreparedStatement pst = JdbcHelper.prepararSql("select * from asistentes where id=?");) {
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			Asistente asistente = null;

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");

				asistente = new Asistente(id, nombre, apellidos);
			}

			// Empaquetar modelo para la siguiente vista

			request.setAttribute("asistente", asistente);

			// Saltar a la siguiente vista

			request.getRequestDispatcher("/WEB-INF/vistas/admin/formulario.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
