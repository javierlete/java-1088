package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcHelper {
	public static PreparedStatement prepararSql(String sql) throws Exception {
		String url = "jdbc:mysql://localhost:3306/ipartube";
		String user = "root";
		String pass = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);

			return con.prepareStatement(sql);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
