package crud;

import static bibliotecas.Consola.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MantenimientoTienda {
	private static final String URL = "jdbc:mysql://localhost:3306/tienda";
	private static final String USER = "tienda_user";
	private static final String PASS = "tienda_user_pass";

	private static final String SQL_CLIENTES_CAMPOS = "c.id AS c_id, c.nombre AS c_nombre, c.nif AS c_nif";
	private static final String SQL_FACTURAS_CAMPOS = "f.id AS f_id, f.codigo AS f_codigo, f.fecha AS f_fecha";
	
	private static final String SQL_SELECT_CLIENTES_FACTURAS = String.format("""
			select %s, %s
			from clientes c
			join facturas f on c.id = f.clientes_id
			""", SQL_CLIENTES_CAMPOS, SQL_FACTURAS_CAMPOS);
	private static final String SQL_SELECT_CLIENTES_FACTURAS_POR_CODIGO = SQL_SELECT_CLIENTES_FACTURAS + " WHERE f.codigo=?";
	
	private static final String SQL_SELECT_CLIENTES = String.format("SELECT %s FROM clientes c", SQL_CLIENTES_CAMPOS);
	private static final String SQL_SELECT_FACTURAS_POR_CLIENTE_ID = String.format("SELECT %s FROM facturas f WHERE clientes_id=?", SQL_FACTURAS_CAMPOS);
	private static final String SQL_CLIENTES_INSERT = "INSERT INTO clientes (nombre, nif) VALUES (?,?)";
	private static final String SQL_FACTURAS_INSERT = "INSERT INTO facturas (clientes_id, codigo, fecha) VALUES (?,?,?)";

	public static void main(String[] args) throws SQLException {

		int opcion;

		do {
			mostrarMenu();
			opcion = pedirOpcion();
			procesarOpcion(opcion);
		} while (opcion != 0);
	}

	private static void mostrarMenu() {
		pl("""
				FACTURAS
				========
				1. Listado clientes
				2. Listado facturas
				3. Listado facturas alternativo

				5. Buscar por código

				8. Crear cliente

				9. Crear factura

				0. Salir
				""");
	}

	private static int pedirOpcion() {
		return leerInt("Selecciona una opción", REQUERIDO);
	}

	private static void procesarOpcion(int opcion) throws SQLException {
		switch (opcion) {
		case 1 -> listadoClientes();
		case 2 -> listadoFacturas();
		case 3 -> listadoFacturasAlternativo();
		case 5 -> buscarPorCodigo();
		case 8 -> crearCliente();
		case 9 -> crearFactura();
		case 0 -> salir();
		default -> noExiste(opcion);
		}
		
		System.out.println();
	}

	private static void listadoClientes() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASS);

		// Producto cartesiano

		var pst = con.prepareStatement(SQL_SELECT_CLIENTES);
		var rs = pst.executeQuery();

		while (rs.next()) {
			mostrarLineaCliente(rs);
		}
	}

	private static void listadoFacturas() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASS);

		// Producto cartesiano

		var pst = con.prepareStatement(SQL_SELECT_CLIENTES_FACTURAS);
		var rs = pst.executeQuery();

		long id = -1L;

		while (rs.next()) {
			if (rs.getLong("c_id") != id) {
				id = rs.getLong("c_id");

				mostrarLineaCliente(rs);
			}

			p("\t");
			
			mostrarLineaFactura(rs);
		}
	}

	private static void listadoFacturasAlternativo() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASS);

		// N+1

		var pstClientes = con.prepareStatement(SQL_SELECT_CLIENTES);
		var pstFacturas = con.prepareStatement(SQL_SELECT_FACTURAS_POR_CLIENTE_ID);

		var rsClientes = pstClientes.executeQuery();

		while (rsClientes.next()) {
			mostrarLineaCliente(rsClientes);

			pstFacturas.setLong(1, rsClientes.getLong("c_id"));

			var rsFacturas = pstFacturas.executeQuery();

			while (rsFacturas.next()) {
				p("\t");
				mostrarLineaFactura(rsFacturas);
			}
		}
	}

	private static void buscarPorCodigo() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASS);
		var pst = con.prepareStatement(SQL_SELECT_CLIENTES_FACTURAS_POR_CODIGO);
		
		var codigo = leerString("Código de factura", REQUERIDO);
		
		pst.setString(1, codigo);
		
		var rs = pst.executeQuery();
		
		while(rs.next()) {
			mostrarLineaFactura(rs);
		}
	}

	private static void crearCliente() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASS);
		var pst = con.prepareStatement(SQL_CLIENTES_INSERT);
		
		pst.setString(1, leerString("Nombre", REQUERIDO));
		pst.setString(2, leerString("NIF", REQUERIDO));
		
		pst.executeUpdate();
	}

	private static void crearFactura() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASS);
		var pst = con.prepareStatement(SQL_FACTURAS_INSERT);

		pst.setInt(1, leerInt("Código de cliente", REQUERIDO));
		pst.setString(2, leerString("Código", REQUERIDO));
		pst.setDate(3, java.sql.Date.valueOf(leerLocalDate("Fecha", REQUERIDO)));
		
		pst.executeUpdate();
	}

	private static void salir() {
		pl("Gracias por usar esta aplicación");
	}

	private static void noExiste(int opcion) {
		pl("ERROR: No existe la opción " + opcion);
	}

	private static void mostrarLineaCliente(ResultSet rsClientes) throws SQLException {
		System.out.printf("%-2s %-20s %-20s\n", rsClientes.getString("c_id"), rsClientes.getString("c_nombre"),
				rsClientes.getString("c_nif"));
	}

	private static void mostrarLineaFactura(ResultSet rsFacturas) throws SQLException {
		System.out.printf("%-2s %8s %10s\n", rsFacturas.getString("f_id"), rsFacturas.getString("f_codigo"),
				rsFacturas.getString("f_fecha"));
	}
}
