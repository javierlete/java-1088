package com.ipartek.formacion.ejemplos.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hola")
public class HolaMundoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("""
				<!DOCTYPE html>
				<html>
				<head>
					<title>Hola a todos</title>
					<meta charset="UTF-8">
				</head>
				<body>
					<h1>Hola a todos desde una página escrita en una servlet</h1>
				</body>
				</html>
				""");
	}

}
