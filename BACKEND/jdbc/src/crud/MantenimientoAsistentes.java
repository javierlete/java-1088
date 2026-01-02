package crud;

import static bibliotecas.Consola.leerInt;
import static bibliotecas.Consola.leerString;
import static bibliotecas.Consola.pf;
import static bibliotecas.Consola.pl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MantenimientoAsistentes {
	private static final String URL = "jdbc:sqlite:asistentes.db"; // Definimos la URL
	private static final String USER = "";
	private static final String PASS = "";

	private static Connection con;
	private static Statement st;

	public static void main(String[] args) throws SQLException {
		con = DriverManager.getConnection(URL, USER, PASS); // Conectamos con la base de datos
		st = con.createStatement(); // Creamos una sentencia

		String opcion;

		do {
			mostrarMenu();
			opcion = pedirOpcion();
			procesarOpcion(opcion);
		} while (!opcion.equals("0"));
	}

	private static void mostrarMenu() {
		pl("""
				MENU
				====

				1. LISTADO
				2. BUSCAR POR ID

				3. INSERTAR
				4. MODIFICAR
				5. BORRAR

				0. SALIR
				""");
	}

	private static String pedirOpcion() {
		return leerString("Selecciona la opción");
	}

	private static void procesarOpcion(String opcion) throws SQLException {
		switch (opcion) {
		case "1" -> listado();
		case "2" -> buscarPorId();

		case "3" -> insertar();
		case "4" -> modificar();
		case "5" -> borrar();

		case "0" -> salir();

		default -> error();
		}
	}

	private static void listado() throws SQLException {
		ResultSet rs = st.executeQuery("SELECT * FROM asistentes"); // Pedimos todos los registros

		pf("%5s %-15s %-30s %-40s\n", "ID", "Nombre", "Apellidos", "Notas");
		pf("%5s %-15s %-30s %-40s\n", "==", "======", "=========", "=====");

		while (rs.next()) { // Mientras haya registros pasamos al siguiente...
			pf("%5s %-15s %-30s %-40s\n", rs.getString("id"), rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("notas") != null ? rs.getString("notas") : "SIN RELLENAR"); // ...y
																								// mostramos
																								// su
																								// información
		}
	}

	private static void buscarPorId() throws SQLException {
		int id = leerInt("Dime el id");

		ResultSet rs = st.executeQuery("SELECT * FROM asistentes WHERE id=" + id);

		pf("%5s %-15s %-30s %-40s\n", "ID", "Nombre", "Apellidos", "Notas");
		pf("%5s %-15s %-30s %-40s\n", "==", "======", "=========", "=====");

		while (rs.next()) { // Mientras haya registros pasamos al siguiente...
			pf("%5s %-15s %-30s %-40s\n", rs.getString("id"), rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("notas")); // ...y mostramos su información
		}
	}

	private static void insertar() throws SQLException {
		String nombre = leerString("Nombre");
		String apellidos = leerString("Apellidos");

		st.executeUpdate("INSERT INTO asistentes (nombre, apellidos) VALUES ('" + nombre + "', '" + apellidos + "')");

		pl("Insertado");
	}

	private static void modificar() throws SQLException {
		int id = leerInt("Dime el id");
		String nombre = leerString("Nombre");
		String apellidos = leerString("Apellidos");

		st.executeUpdate("UPDATE asistentes SET nombre='" + nombre + "', apellidos='" + apellidos + "' WHERE id=" + id);

		pl("Modificado");
	}

	private static void borrar() throws SQLException {
		int id = leerInt("Dime el id");

		st.executeUpdate("DELETE FROM asistentes WHERE id=" + id);

		pl("Borrado");
	}

	private static void salir() {
		pl("Gracias por usar este programa. Cerrando");
	}

	private static void error() {
		pl("OPCIÓN NO RECONOCIDA");
	}
}
