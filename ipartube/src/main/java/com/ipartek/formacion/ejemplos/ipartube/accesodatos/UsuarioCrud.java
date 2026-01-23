package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public class UsuarioCrud {
	public static Usuario obtenerPorEmail(String email) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("select * from usuarios where email=?");) {
			pst.setString(1, email);

			try (ResultSet rs = pst.executeQuery()) {
				Usuario usuario = null;

				if (rs.next()) {
					String sId = rs.getString("id");
					String password = rs.getString("password");
					
					Long id = Long.parseLong(sId);

					usuario = new Usuario(id, email, password);
				}

				return usuario;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
