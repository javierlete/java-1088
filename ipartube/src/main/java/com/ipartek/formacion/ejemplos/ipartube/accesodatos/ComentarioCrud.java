package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Comentario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

public class ComentarioCrud {
	private static final String SQL_SELECT = """
			SELECT
			    c.id AS c_id,
			    c.fecha AS c_fecha,
			    c.texto AS c_texto,
			    c.comentario_padre_id AS c_padre,
			    u.id AS u_id,
			    u.nombre AS u_nombre,
			    u.imagen_url AS u_imagen_url,
			    (SELECT 
			            COUNT(*)
			        FROM
		            	ipartube.comentarios
			        WHERE
			            comentarios.comentario_padre_id = c_id) AS respuestas
			FROM
			    comentarios c
			        JOIN
			    usuarios u ON c.usuarios_id = u.id
			""";

	public static ArrayList<Comentario> obtenerPorVideo(Long idVideo) {
		ArrayList<Comentario> comentarios = new ArrayList<>();

		try (PreparedStatement pst = JdbcHelper.prepararSql(SQL_SELECT + "WHERE c.videos_id=? AND c.comentario_padre_id IS NULL ORDER BY c.fecha DESC")) {
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
	
		try (PreparedStatement pst = JdbcHelper.prepararSql(SQL_SELECT + "WHERE c.id=?")) {
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

		try (PreparedStatement pst = JdbcHelper.prepararSql(SQL_SELECT + "WHERE c.comentario_padre_id=? ORDER BY c.fecha DESC")) {
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
		try (PreparedStatement pst = JdbcHelper
				.prepararSql("INSERT INTO comentarios (usuarios_id, videos_id, fecha, texto, comentario_padre_id) VALUES (?,?,?,?,?)")) {
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

			return new Comentario(id, usuario, null, fecha, texto, respuestas, new Comentario(idPadre, null, null, null, null, null, null));
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}
}
