package com.ipartek.formacion.ejemplos.controladores.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

		String url = "jdbc:mysql://localhost:3306/asistentes";
		String user = "root";
		String pass = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection con = DriverManager.getConnection(url, user, pass);
					PreparedStatement pst = con.prepareStatement("delete from asistentes where id=?");
					) {
				pst.setLong(1, id);
				
				pst.executeUpdate();
				
				// Empaquetar modelo para la siguiente vista
				// Saltar a la siguiente vista

				response.sendRedirect("index");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
