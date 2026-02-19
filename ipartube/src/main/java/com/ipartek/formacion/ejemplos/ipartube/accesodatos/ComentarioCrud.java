package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Comentario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public class ComentarioCrud {
	private static final String SQL_SELECT = "SELECT * FROM comentarios_completos";

	public static ArrayList<Comentario> obtenerPorVideo(Long idVideo) {
		ArrayList<Comentario> comentarios = new ArrayList<>();

		try (PreparedStatement pst = JdbcHelper.prepararSql(
				SQL_SELECT + " WHERE c_videos_id=? AND c_padre IS NULL ORDER BY c_fecha DESC")) {
			pst.setLong(1, idVideo);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					comentarios.add(filaAComentario(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return comentarios;
	}

	public static Comentario obtenerPorId(Long id) {
		Comentario comentario = null;

		try (PreparedStatement pst = JdbcHelper.prepararSql(SQL_SELECT + " WHERE c_id=?")) {
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					comentario = filaAComentario(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return comentario;
	}

	public static ArrayList<Comentario> obtenerPorPadre(Long idComentario) {
		ArrayList<Comentario> comentarios = new ArrayList<>();

		try (PreparedStatement pst = JdbcHelper
				.prepararSql(SQL_SELECT + " WHERE c_padre=? ORDER BY c_fecha DESC")) {
			pst.setLong(1, idComentario);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					comentarios.add(filaAComentario(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return comentarios;
	}

	public static void insertar(Comentario comentario) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql("CALL comentario_insertar(?,?,?,?,?)")) {
			comentarioAFila(comentario, pst);

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void comentarioAFila(Comentario comentario, PreparedStatement pst) throws SQLException {
		pst.setLong(1, comentario.usuario().id());
		pst.setLong(2, comentario.video().id());
		pst.setTimestamp(3, java.sql.Timestamp.valueOf(comentario.fecha()));
		pst.setString(4, comentario.texto());
		pst.setObject(5, comentario.comentarioPadre().id());
	}

	private static Comentario filaAComentario(ResultSet rs) {
		try {
			long id = rs.getLong("c_id");
			LocalDateTime fecha = rs.getTimestamp("c_fecha").toLocalDateTime();
			String texto = rs.getString("c_texto");
			Long idPadre = (Long) rs.getObject("c_padre");
			int respuestas = rs.getInt("respuestas");

			long usuarioId = rs.getLong("u_id");
			String usuarioNombre = rs.getString("u_nombre");
			String usuarioImagen = rs.getString("u_imagen_url");

			Usuario usuario = new Usuario(usuarioId, usuarioImagen, usuarioNombre, null, null, null);

			return new Comentario(id, usuario, null, fecha, texto, respuestas,
					new Comentario(idPadre, null, null, null, null, null, null));
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}
}
