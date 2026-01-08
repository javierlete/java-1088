package crud;

import static bibliotecas.Consola.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MantenimientoAsistentes {
	private static final String FORMATO_LISTADO = "%5s %-10s %-15s %-20s %-20s %-40s\n";
	
//	private static final String URL = "jdbc:sqlite:asistentes.db"; // Definimos la URL
//	private static final String USER = "";
//	private static final String PASS = "";

	private static final String URL = "jdbc:mysql://localhost:3306/java1088"; // Definimos la URL
	private static final String USER = "root";
	private static final String PASS = "1234";

	private static Connection con;
	private static PreparedStatement pst;

	public static void main(String[] args) throws SQLException {
		con = DriverManager.getConnection(URL, USER, PASS); // Conectamos con la base de datos

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
		return leerString("Selecciona la opción", REQUERIDO);
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
		pst = con.prepareStatement("SELECT * FROM asistentes");

		ResultSet rs = pst.executeQuery(); // Pedimos todos los registros

		cabeceraListado();

		while (rs.next()) { // Mientras haya registros pasamos al siguiente...
			lineaListado(rs);
		}
	}

	private static void buscarPorId() throws SQLException {
		int id = leerInt("Dime el id", REQUERIDO);

		pst = con.prepareStatement("SELECT * FROM asistentes WHERE id=?");

		pst.setInt(1, id);

		ResultSet rs = pst.executeQuery();

		cabeceraListado();
		
		if (rs.next()) {
			lineaListado(rs);
		}
	}

	private static void insertar() throws SQLException {
		String nombre = leerString("Nombre", REQUERIDO);
		String apellidos = leerString("Apellidos", REQUERIDO);

		String notas = leerString("Notas", OPCIONAL);
		LocalDateTime entrada = leerLocalDateTime("Entrada", OPCIONAL);
		LocalDateTime salida = leerLocalDateTime("Salida", OPCIONAL);

		pst = con.prepareStatement(
				"INSERT INTO asistentes (nombre, apellidos, notas, entrada, salida) VALUES (?,?,?,?,?)");

		pst.setString(1, nombre);
		pst.setString(2, apellidos);
		pst.setString(3, notas);
		pst.setTimestamp(4, entrada == null ? null : java.sql.Timestamp.valueOf(entrada));
		pst.setTimestamp(5, salida == null ? null : java.sql.Timestamp.valueOf(salida));

		pst.executeUpdate();

		pl("Insertado");
	}

	private static void modificar() throws SQLException {
		int id = leerInt("Dime el id", REQUERIDO);
		String nombre = leerString("Nombre", REQUERIDO);
		String apellidos = leerString("Apellidos", REQUERIDO);

		String notas = leerString("Notas", OPCIONAL);
		LocalDateTime entrada = leerLocalDateTime("Entrada", OPCIONAL);
		LocalDateTime salida = leerLocalDateTime("Salida", OPCIONAL);

		pst = con.prepareStatement(
				"UPDATE asistentes SET nombre=?, apellidos=?, notas=?, entrada=?, salida=? WHERE id=?");

		pst.setString(1, nombre);
		pst.setString(2, apellidos);
		pst.setString(3, notas);
		pst.setTimestamp(4, java.sql.Timestamp.valueOf(entrada));
		pst.setTimestamp(5, java.sql.Timestamp.valueOf(salida));
		pst.setInt(6, id);

		pst.executeUpdate();

		pl("Modificado");
	}

	private static void borrar() throws SQLException {
		int id = leerInt("Dime el id", REQUERIDO);

		pst = con.prepareStatement("DELETE FROM asistentes WHERE id=?");

		pst.setInt(1, id);

		pst.executeUpdate();

		pl("Borrado");
	}

	private static void salir() {
		pl("Gracias por usar este programa. Cerrando");
	}

	private static void cabeceraListado() {
		pf(FORMATO_LISTADO, "ID", "Nombre", "Apellidos", "Entrada", "Salida", "Notas");
		pf(FORMATO_LISTADO, "==", "======", "=========", "=======", "======", "=====");
	}

	private static void lineaListado(ResultSet rs) throws SQLException {
		pf(FORMATO_LISTADO, rs.getString("id"), rs.getString("nombre"), rs.getString("apellidos"),
				formatearFecha(rs.getTimestamp("entrada")),
				formatearFecha(rs.getTimestamp("salida")),
				rs.getString("notas") != null ? rs.getString("notas") : "SIN RELLENAR");
	}

	private static String formatearFecha(java.sql.Timestamp fecha) throws SQLException {
		if(fecha == null) {
			return "X";
		}
	
		return FORMATO_FECHA_HORA.format(fecha.toLocalDateTime());
	}

	private static void error() {
		pl("OPCIÓN NO RECONOCIDA");
	}
}
