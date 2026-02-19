package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcHelper {
	private static final String URL = "jdbc:mysql://localhost:3306/ipartube";
	private static final String USER = "root";
	private static final String PASS = "1234";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Connection obtenerConexion() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}

	public static PreparedStatement prepararSql(String sql) throws Exception {
		try {
			Connection con = obtenerConexion();
			return con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

	public static CallableStatement procedimientoSql(String sql) throws Exception {
		try {
			Connection con = obtenerConexion();
			return con.prepareCall(sql);
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}
}
