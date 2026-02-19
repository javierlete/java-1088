package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public class UsuarioCrud {
	private static final String SQL_SELECT = "SELECT * FROM usuarios_completos";

	public static ArrayList<Usuario> obtenerTodos() {
		try (PreparedStatement pst = JdbcHelper.prepararSql(SQL_SELECT); ResultSet rs = pst.executeQuery()) {

			ArrayList<Usuario> usuarios = new ArrayList<>();

			Usuario usuario = null;

			while (rs.next()) {
				usuario = filaAUsuario(rs);

				usuarios.add(usuario);
			}

			return usuarios;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Usuario obtenerPorEmail(String email) {
		try (PreparedStatement pst = JdbcHelper.prepararSql(SQL_SELECT + " where u_email=?");) {
			pst.setString(1, email);

			try (ResultSet rs = pst.executeQuery()) {
				Usuario usuario = null;

				if (rs.next()) {
					usuario = filaAUsuario(rs);
				}

				return usuario;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Usuario filaAUsuario(ResultSet rs) throws SQLException {
		String sId = rs.getString("u_id");
		String nombre = rs.getString("u_nombre");
		String imagenUrl = rs.getString("u_imagen_url");
		String email = rs.getString("u_email");
		String password = rs.getString("u_password");

		Long id = Long.parseLong(sId);

		String sRolId = rs.getString("r_id");
		String rolNombre = rs.getString("r_nombre");
		String rolDescripcion = rs.getString("r_descripcion");

		Long rolId = Long.parseLong(sRolId);

		Rol rol = new Rol(rolId, rolNombre, rolDescripcion);

		return new Usuario(id, imagenUrl, nombre, email, password, rol);
	}

}
