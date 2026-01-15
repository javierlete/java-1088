package com.ipartek.formacion.ejemplos.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/saludar")
public class SaludaMundoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		
		response.getWriter().printf("""
				<!DOCTYPE html>
				<html>
				<head>
					<title>Saludo</title>
					<meta charset="UTF-8">
				</head>
				<body>
					<h1>Hola %s</h1>
				</body>
				</html>
				""", nombre);
	}

}
