package com.ipartek.formacion.ejemplos.controladores.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.modelos.Asistente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/index")
public class IndexAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger la información recibida en la petición
		// Convertir las partes que sean necesarias
		// Crear objetos con todas las partes
		// Ejecutar lógica de negocio

		String url = "jdbc:mysql://localhost:3306/asistentes";
		String user = "root";
		String pass = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection con = DriverManager.getConnection(url, user, pass);
					PreparedStatement pst = con.prepareStatement("select * from asistentes");
					ResultSet rs = pst.executeQuery()) {
				ArrayList<Asistente> asistentes = new ArrayList<>();

				while (rs.next()) {
					long id = rs.getLong("id");
					String nombre = rs.getString("nombre");
					String apellidos = rs.getString("apellidos");

					Asistente asistente = new Asistente(id, nombre, apellidos);

					asistentes.add(asistente);
				}

				// Empaquetar modelo para la siguiente vista

				request.setAttribute("asistentes", asistentes);

				// Saltar a la siguiente vista

				request.getRequestDispatcher("/WEB-INF/vistas/admin/index.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
