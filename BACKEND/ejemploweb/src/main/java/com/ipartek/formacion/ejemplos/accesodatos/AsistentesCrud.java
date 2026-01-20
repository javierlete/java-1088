package com.ipartek.formacion.ejemplos.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.modelos.Asistente;

public class AsistentesCrud {
	public static ArrayList<Asistente> obtenerTodos() {
		try (PreparedStatement pst = JdbcHelper.prepararSql("select * from asistentes");
				ResultSet rs = pst.executeQuery()) {
			ArrayList<Asistente> asistentes = new ArrayList<>();

			while (rs.next()) {
				long id = rs.getLong("id");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");

				Asistente asistente = new Asistente(id, nombre, apellidos);

				asistentes.add(asistente);
			}

			return asistentes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Asistente obtenerPorId(Long id) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("select * from asistentes where id=?");) {
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			Asistente asistente = null;

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");

				asistente = new Asistente(id, nombre, apellidos);
			}
			
			return asistente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void guardar(Asistente asistente) {
		if(asistente.id() == null) {
			insertar(asistente);
		} else {
			modificar(asistente);
		}
	}
	
	public static void insertar(Asistente asistente) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("insert into asistentes (nombre, apellidos) values (?,?)")) {
			pst.setString(1, asistente.nombre());
			pst.setString(2, asistente.apellidos());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void modificar(Asistente asistente) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("update asistentes set nombre=?, apellidos=? where id=?")) {
			pst.setString(1, asistente.nombre());
			pst.setString(2, asistente.apellidos());
			pst.setLong(3, asistente.id());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void borrar(Long id) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("delete from asistentes where id=?");) {
			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
