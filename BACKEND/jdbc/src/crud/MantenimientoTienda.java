package crud;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MantenimientoTienda {
	private static final String URL = "jdbc:mysql://localhost:3306/tienda";
	private static final String USER = "tienda_user";
	private static final String PASS = "tienda_user_pass";

	private static final String SQL_SELECT = """
			select *
			from clientes c
			join facturas f on c.id = f.clientes_id;
			""";

	public static void main(String[] args) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASS);

		// Producto cartesiano

		var pst = con.prepareStatement(SQL_SELECT);
		var rs = pst.executeQuery();

		long id = -1L;

		while (rs.next()) {
			if (rs.getLong("id") != id) {
				id = rs.getLong("c.id");

				System.out.printf("\n%-2s %-20s %-20s\n\n", rs.getString("c.id"), rs.getString("c.nombre"),
						rs.getString("c.nif"));
			}

			System.out.printf("%-2s %8s %10s\n", rs.getString("f.id"), rs.getString("f.codigo"),
					rs.getString("f.fecha"));
		}

		// N+1

		var pstClientes = con.prepareStatement("SELECT * FROM clientes c");
		var pstFacturas = con.prepareStatement("SELECT * FROM facturas f WHERE clientes_id=?");

		var rsClientes = pstClientes.executeQuery();

		while (rsClientes.next()) {
			System.out.printf("\n%-2s %-20s %-20s\n\n", rsClientes.getString("c.id"), rsClientes.getString("c.nombre"),
					rsClientes.getString("c.nif"));

			pstFacturas.setLong(1, rsClientes.getLong("id"));

			var rsFacturas = pstFacturas.executeQuery();

			while (rsFacturas.next()) {
				System.out.printf("%-2s %8s %10s\n", rsFacturas.getString("f.id"), rsFacturas.getString("f.codigo"),
						rsFacturas.getString("f.fecha"));
			}
		}

	}
}
