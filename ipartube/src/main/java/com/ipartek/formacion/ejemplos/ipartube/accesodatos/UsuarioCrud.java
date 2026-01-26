package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Rol;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public class UsuarioCrud {
	public static ArrayList<Usuario> obtenerTodos() {
		try (PreparedStatement pst = JdbcHelper.prepararSql("""
				select *
				from usuarios
				join roles on usuarios.roles_id = roles.id
				"""); ResultSet rs = pst.executeQuery()) {

			ArrayList<Usuario> usuarios = new ArrayList<>();

			Usuario usuario = null;

			while (rs.next()) {
				String sId = rs.getString("usuarios.id");
				String email = rs.getString("usuarios.email");
				String password = rs.getString("usuarios.password");

				Long id = Long.parseLong(sId);

				String sRolId = rs.getString("roles.id");
				String rolNombre = rs.getString("roles.nombre");
				String rolDescripcion = rs.getString("roles.descripcion");

				Long rolId = Long.parseLong(sRolId);

				Rol rol = new Rol(rolId, rolNombre, rolDescripcion);

				usuario = new Usuario(id, email, password, rol);
				
				usuarios.add(usuario);
			}

			return usuarios;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Usuario obtenerPorEmail(String email) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("""
				select *
				from usuarios
				join roles on usuarios.roles_id = roles.id
				where email=?
				""");) {
			pst.setString(1, email);

			try (ResultSet rs = pst.executeQuery()) {
				Usuario usuario = null;

				if (rs.next()) {
					String sId = rs.getString("usuarios.id");
					String password = rs.getString("usuarios.password");

					Long id = Long.parseLong(sId);

					String sRolId = rs.getString("roles.id");
					String rolNombre = rs.getString("roles.nombre");
					String rolDescripcion = rs.getString("roles.descripcion");

					Long rolId = Long.parseLong(sRolId);

					Rol rol = new Rol(rolId, rolNombre, rolDescripcion);

					usuario = new Usuario(id, email, password, rol);
				}

				return usuario;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
