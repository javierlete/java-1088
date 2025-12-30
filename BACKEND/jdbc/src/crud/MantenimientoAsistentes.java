package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MantenimientoAsistentes {
	private static final Scanner SC = new Scanner(System.in);
	
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
		System.out.println("""
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
		System.out.println("Dime la opción que quieres");
		
		return SC.nextLine();
	}

	private static void procesarOpcion(String opcion) throws SQLException {
		switch(opcion) {
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

		System.out.printf("%5s %-15s %-30s %-40s\n", "ID", "Nombre", "Apellidos", "Notas");
		System.out.printf("%5s %-15s %-30s %-40s\n", "==", "======", "=========", "=====");

		while (rs.next()) { // Mientras haya registros pasamos al siguiente...
			System.out.printf("%5s %-15s %-30s %-40s\n", rs.getString("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("notas") != null ? rs.getString("notas") : "SIN RELLENAR"); // ...y mostramos su información
		}
	}

	private static void buscarPorId() throws SQLException {
		System.out.println("Dime el id");
		
		int id = Integer.parseInt(SC.nextLine());
		
		ResultSet rs = st.executeQuery("SELECT * FROM asistentes WHERE id=" + id);

		System.out.printf("%5s %-15s %-30s %-40s\n", "ID", "Nombre", "Apellidos", "Notas");
		System.out.printf("%5s %-15s %-30s %-40s\n", "==", "======", "=========", "=====");

		while (rs.next()) { // Mientras haya registros pasamos al siguiente...
			System.out.printf("%5s %-15s %-30s %-40s\n", rs.getString("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("notas")); // ...y mostramos su información
		}
	}

	private static void insertar() throws SQLException {
		System.out.print("Nombre: ");
		String nombre = SC.nextLine();

		System.out.print("Apellidos: ");
		String apellidos = SC.nextLine();
		
		st.executeUpdate("INSERT INTO asistentes (nombre, apellidos) VALUES ('" + nombre + "', '"+ apellidos +"')");
		
		System.out.println("Insertado");
	}

	private static void modificar() {
		System.out.println("MODIFICAR");
	}

	private static void borrar() {
		System.out.println("BORRAR");
	}

	private static void salir() {
		System.out.println("Gracias por usar este programa. Cerrando");
	}

	private static void error() {
		System.out.println("OPCIÓN NO RECONOCIDA");
	}
}
