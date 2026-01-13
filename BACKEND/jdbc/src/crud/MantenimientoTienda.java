package crud;

import static bibliotecas.Consola.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private static final String SQL_SELECT_CLIENTES_FACTURAS_POR_CODIGO = SQL_SELECT_CLIENTES_FACTURAS
			+ " WHERE f.codigo=?";

	private static final String SQL_SELECT_CLIENTES = String.format("SELECT %s FROM clientes c", SQL_CLIENTES_CAMPOS);
	private static final String SQL_SELECT_FACTURAS_POR_CLIENTE_ID = String
			.format("SELECT %s FROM facturas f WHERE clientes_id=?", SQL_FACTURAS_CAMPOS);
	private static final String SQL_CLIENTES_INSERT = "INSERT INTO clientes (nombre, nif) VALUES (?,?)";
	private static final String SQL_FACTURAS_INSERT = "INSERT INTO facturas (clientes_id, codigo, fecha) VALUES (?,?,?)";

	public static void main(String[] args) {

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

				7. Crear factura con cliente

				8. Crear cliente

				9. Crear factura

				0. Salir
				""");
	}

	private static int pedirOpcion() {
		return leerInt("Selecciona una opción", REQUERIDO);
	}

	private static void procesarOpcion(int opcion) {
		switch (opcion) {
		case 1 -> listadoClientes();
		case 2 -> listadoFacturas();
		case 3 -> listadoFacturasAlternativo();
		case 5 -> buscarPorCodigo();
		case 7 -> crearFacturaConCliente();
		case 8 -> crearCliente();
		case 9 -> crearFactura();
		case 0 -> salir();
		default -> noExiste(opcion);
		}

		System.out.println();
	}

	private static void listadoClientes() {
		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement(SQL_SELECT_CLIENTES);
				var rs = pst.executeQuery()) {
			while (rs.next()) {
				mostrarLineaCliente(rs);
			}
		} catch (SQLException e) {
			System.out.println("Error en el listado de clientes");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	// Producto cartesiano
	private static void listadoFacturas() {
		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement(SQL_SELECT_CLIENTES_FACTURAS);
				var rs = pst.executeQuery()) {
			long id = -1L;

			while (rs.next()) {
				if (rs.getLong("c_id") != id) {
					id = rs.getLong("c_id");

					mostrarLineaCliente(rs);
				}

				p("\t");

				mostrarLineaFactura(rs);
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido mostrar el listado de facturas");
		}
	}

	// N+1
	private static void listadoFacturasAlternativo() {
		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pstClientes = con.prepareStatement(SQL_SELECT_CLIENTES);
				var pstFacturas = con.prepareStatement(SQL_SELECT_FACTURAS_POR_CLIENTE_ID);
				var rsClientes = pstClientes.executeQuery()) {
			while (rsClientes.next()) {
				mostrarLineaCliente(rsClientes);

				pstFacturas.setLong(1, rsClientes.getLong("c_id"));

				try (var rsFacturas = pstFacturas.executeQuery()) {
					while (rsFacturas.next()) {
						p("\t");
						mostrarLineaFactura(rsFacturas);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido mostrar el listado de facturas");
		}
	}

	private static void buscarPorCodigo() {
		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement(SQL_SELECT_CLIENTES_FACTURAS_POR_CODIGO)) {
			var codigo = leerString("Código de factura", REQUERIDO);

			pst.setString(1, codigo);

			var rs = pst.executeQuery();

			while (rs.next()) {
				mostrarLineaFactura(rs);
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido buscar por código");
		}
	}

	private static void crearFacturaConCliente() {
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASS);

			int id;

			con.setAutoCommit(false);

			try (var pstCliente = con.prepareStatement(SQL_CLIENTES_INSERT, Statement.RETURN_GENERATED_KEYS)) {
				pstCliente.setString(1, leerString("Nombre", REQUERIDO));
				pstCliente.setString(2, leerString("NIF", REQUERIDO));

				pstCliente.executeUpdate();

				try (var rsId = pstCliente.getGeneratedKeys()) {
					rsId.next();

					id = rsId.getInt(1);
				}
			}

			try (var pstFactura = con.prepareStatement(SQL_FACTURAS_INSERT)) {
				pstFactura.setInt(1, id);
				pstFactura.setString(2, leerString("Código", REQUERIDO));
				pstFactura.setDate(3, java.sql.Date.valueOf(leerLocalDate("Fecha", REQUERIDO)));

				pstFactura.executeUpdate();
			}

			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					System.out.println("No se ha podido deshacer la transacción");
					e1.printStackTrace();
				}
			}
			
			System.out.println("No se ha podido crear la factura con cliente");
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// No hacemos nada si hay un error en el cierre
				}
			}

		}
	}

	private static void crearCliente() {
		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement(SQL_CLIENTES_INSERT)) {
			pst.setString(1, leerString("Nombre", REQUERIDO));
			pst.setString(2, leerString("NIF", REQUERIDO));

			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("No se ha podido crear el cliente");
		}
	}

	private static void crearFactura() {
		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement(SQL_FACTURAS_INSERT)) {
			pst.setInt(1, leerInt("Código de cliente", REQUERIDO));
			pst.setString(2, leerString("Código", REQUERIDO));
			pst.setDate(3, java.sql.Date.valueOf(leerLocalDate("Fecha", REQUERIDO)));

			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("No se ha podido crear la factura");
		}

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
