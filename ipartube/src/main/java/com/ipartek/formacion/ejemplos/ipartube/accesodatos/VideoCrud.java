package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public class VideoCrud {
	private static final String SQL_SELECT = "CALL videos_completos()";
	private static final String SQL_SELECT_USUARIO = "CALL videos_completos_usuario(?)";
	private static final String SQL_SELECT_ID = "CALL videos_completos_por_id(?,?)";
	private static final String SQL_SELECT_ID_USUARIO = "CALL videos_completos_por_usuario(?)";

	private static final String SQL_INSERT = "CALL videos_completos_insert(?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE = "CALL videos_completos_update(?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE_USUARIO = "CALL videos_completos_update_usuario(?,?,?,?,?,?,?,?)";
	
	private static final String SQL_DELETE = "CALL videos_completos_borrar(?)";
	private static final String SQL_DELETE_ID_USUARIO = "CALL videos_completos_borrar_usuario(?,?)";

	public static ArrayList<Video> obtenerTodos() {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_SELECT)) {
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Video> videos = new ArrayList<>();
			
			while (rs.next()) {
				Video video = filaAVideo(rs);
				
				videos.add(video);
			}
			
			return videos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList<Video> obtenerTodos(Long idUsuario) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_SELECT_USUARIO)) {
			pst.setObject(1, idUsuario);
			ResultSet rs = pst.executeQuery();

			ArrayList<Video> videos = new ArrayList<>();

			while (rs.next()) {
				Video video = filaAVideo(rs);

				videos.add(video);
			}

			return videos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Video obtenerPorId(Long id) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_SELECT_ID);) {
			pst.setObject(1, null);
			pst.setLong(2, id);

			try (ResultSet rs = pst.executeQuery()) {
				Video video = null;

				if (rs.next()) {
					video = filaAVideo(rs);
				}

				return video;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Video> obtenerPorIdUsuario(Long idUsuario) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_SELECT_ID_USUARIO);) {
			pst.setLong(1, idUsuario);

			try (ResultSet rs = pst.executeQuery()) {
				ArrayList<Video> videos = new ArrayList<>();

				Video video = null;

				while (rs.next()) {
					video = filaAVideo(rs);

					videos.add(video);
				}

				return videos;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void borrar(Long id) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_DELETE);) {
			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean borrar(Long id, Long idUsuario) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_DELETE_ID_USUARIO);) {
			pst.setLong(1, id);
			pst.setLong(2, idUsuario);

			int numeroRegistrosBorrados = pst.executeUpdate();

			return numeroRegistrosBorrados == 1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void insertar(Video video) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_INSERT);) {
			videoAFila(video, pst);

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void modificar(Video video) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_UPDATE);) {
			videoAFila(video, pst);

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean modificar(Video video, Long idUsuario) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_UPDATE_USUARIO);) {
			videoAFila(video, idUsuario, pst);

			int numeroRegistrosModificados = pst.executeUpdate();

			return numeroRegistrosModificados == 1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void videoAFila(Video video, PreparedStatement pst) throws SQLException {
		videoAFila(video, null, pst);
	}

	private static void videoAFila(Video video, Long idUsuario, PreparedStatement pst) throws SQLException {
		pst.setString(1, video.titulo());
		pst.setString(2, video.descripcion());
		pst.setString(3, video.imagenUrl());
		pst.setTimestamp(4, video.fecha() == null ? null : Timestamp.valueOf(video.fecha()));
		pst.setString(5, video.videoUrl());
		pst.setLong(6, video.usuario().id());

		if (video.id() == null) {
			return;
		}

		pst.setLong(7, video.id());

		if (idUsuario == null) {
			return;
		}

		pst.setLong(8, idUsuario);
	}

	private static Video filaAVideo(ResultSet rs) throws SQLException {
		long id = rs.getLong("v_id");
		String titulo = rs.getString("v_titulo");
		String descripcion = rs.getString("v_descripcion");
		String imagenUrl = rs.getString("v_imagen_url");
		Timestamp timestamp = rs.getTimestamp("v_fecha");
		LocalDateTime fecha = timestamp != null ? timestamp.toLocalDateTime() : null;
		String videoUrl = rs.getString("v_video_url");

		long usuarioId = rs.getLong("u_id");
		String usuarioNombre = rs.getString("u_nombre");
		String usuarioImagen = rs.getString("u_imagen_url");

		long numeroMeGusta = rs.getLong("numero_me_gusta");
		boolean meGusta = rs.getBoolean("me_gusta");

		Usuario usuario = new Usuario(usuarioId, usuarioImagen, usuarioNombre, null, null, null);

		return new Video(id, titulo, descripcion, imagenUrl, fecha, videoUrl, usuario, numeroMeGusta, meGusta);
	}
}
