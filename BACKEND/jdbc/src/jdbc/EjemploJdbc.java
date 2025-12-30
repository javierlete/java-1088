package jdbc;

import java.sql.*;

/**
 * Clase de ejemplo de <code>JDBC</code>
 * 
 * @author Javier Lete
 */
public class EjemploJdbc {
	/**
	 * Método de entrada para cualquier aplicación de Java
	 * @param args Los argumentos de la consola
	 * @throws SQLException En este caso lanzamos esta excepción porque no gestionamos a nivel interno la misma
	 */
	public static void main(String[] args) throws SQLException {
		/*
		 * En este ejemplo vamos a mostrar
		 * cómo conectar con una base de datos con JDBC
		 */
		
		String url = "jdbc:sqlite:jdbc.db"; // Definimos la URL
		String user = "";
		String pass = "";
		
		Connection con = DriverManager.getConnection(url, user, pass); // Conectamos con la base de datos
		Statement st = con.createStatement(); // Creamos una sentencia

		st.executeUpdate("drop table if exists personas"); // Borramos la tabla si existiera
		st.executeUpdate("create table personas (id integer primary key, nombre varchar(30))"); // Creamos una nueva tabla
		
		// Insertamos registros
		st.executeUpdate("insert into personas (nombre) values ('leo')");
		st.executeUpdate("insert into personas (nombre) values ('yui')");

		ResultSet rs = st.executeQuery("select * from personas"); // Pedimos todos los registros

		while (rs.next()) { // Mientras haya registros pasamos al siguiente...
			System.out.printf("%5s %-20s\n", rs.getString("id"), rs.getString("nombre")); // ...y mostramos su información
		}
	}
}
