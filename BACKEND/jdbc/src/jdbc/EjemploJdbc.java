package jdbc;

import java.sql.*;

public class EjemploJdbc {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:sqlite:jdbc.db";
		String user = "";
		String pass = "";

		Connection con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();

		st.executeUpdate("drop table if exists personas");
		st.executeUpdate("create table personas (id integer primary key, nombre varchar(30))");
		st.executeUpdate("insert into personas (nombre) values ('leo')");
		st.executeUpdate("insert into personas (nombre) values ('yui')");

		ResultSet rs = st.executeQuery("select * from personas");

		while (rs.next()) {
			System.out.printf("%5s %-20s\n", rs.getString("id"), rs.getString("nombre"));
		}
	}
}
