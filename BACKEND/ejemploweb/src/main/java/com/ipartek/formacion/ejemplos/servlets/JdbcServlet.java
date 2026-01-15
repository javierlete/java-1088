package com.ipartek.formacion.ejemplos.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/jdbc")
public class JdbcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();

		String url = "jdbc:sqlite:jdbc.db";
		String user = "";
		String pass = "";

		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection con = DriverManager.getConnection(url, user, pass); Statement st = con.createStatement()) {
				st.executeUpdate("drop table if exists personas");
				st.executeUpdate("create table personas (id integer primary key, nombre varchar(30))"); 

				st.executeUpdate("insert into personas (nombre) values ('leo')");
				st.executeUpdate("insert into personas (nombre) values ('yui')");

				ResultSet rs = st.executeQuery("select * from personas");

				out.println("<ul>");
				
				while (rs.next()) {
					out.printf("<li>%s %s</li>\n", rs.getString("id"), rs.getString("nombre"));
				}
				
				out.println("</ul>");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
