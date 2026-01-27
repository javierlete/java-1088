package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public class VideoCrud {
	public static ArrayList<Video> obtenerTodos() {
		try (PreparedStatement pst = JdbcHelper.prepararSql("""
				SELECT 
				    v.id AS v_id,
				    v.titulo AS v_titulo,
				    v.descripcion AS v_descripcion,
				    v.fecha AS v_fecha,
				    v.imagen_url AS v_imagen_url,
				    v.video_url AS v_video_url,
				    u.id AS u_id,
				    u.email AS u_email
				FROM
				    videos AS v
				        JOIN
				    usuarios AS u ON v.usuarios_id = u.id
				""");
				ResultSet rs = pst.executeQuery()) {
			ArrayList<Video> videos = new ArrayList<>();

			while (rs.next()) {
				long id = rs.getLong("v_id");
				String titulo = rs.getString("v_titulo");
				String descripcion = rs.getString("v_descripcion");
				String imagenUrl = rs.getString("v_imagen_url");
				Timestamp timestamp = rs.getTimestamp("v_fecha");
				LocalDateTime fecha = timestamp != null ? timestamp.toLocalDateTime() : null;
				String videoUrl = rs.getString("v_video_url");

				long usuarioId = rs.getLong("u_id");
				String usuarioEmail = rs.getString("u_email");
				
				Usuario usuario = new Usuario(usuarioId, usuarioEmail, null, null);
				
				Video video = new Video(id, titulo, descripcion, imagenUrl, fecha, videoUrl, usuario);

				videos.add(video);
			}

			return videos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Video obtenerPorId(Long id) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("""
				SELECT 
				    v.titulo AS v_titulo,
				    v.descripcion AS v_descripcion,
				    v.fecha AS v_fecha,
				    v.imagen_url AS v_imagen_url,
				    v.video_url AS v_video_url,
				    u.id AS u_id,
				    u.email AS u_email
				FROM
				    videos AS v
				        JOIN
				    usuarios AS u ON v.usuarios_id = u.id
				WHERE v.id=?
				""");) {
			
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				Video video = null;

				if (rs.next()) {
					String titulo = rs.getString("v_titulo");
					String descripcion = rs.getString("v_descripcion");
					String imagenUrl = rs.getString("v_imagen_url");
					Timestamp timestamp = rs.getTimestamp("v_fecha");
					LocalDateTime fecha = timestamp != null ? timestamp.toLocalDateTime() : null;
					String videoUrl = rs.getString("v_video_url");

					long usuarioId = rs.getLong("u_id");
					String usuarioEmail = rs.getString("u_email");
					
					Usuario usuario = new Usuario(usuarioId, usuarioEmail, null, null);
					
					video = new Video(id, titulo, descripcion, imagenUrl, fecha, videoUrl, usuario);
				}

				return video;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Video> obtenerPorIdUsuario(Long idUsuario) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("""
				SELECT 
					v.id AS v_id,
				    v.titulo AS v_titulo,
				    v.descripcion AS v_descripcion,
				    v.fecha AS v_fecha,
				    v.imagen_url AS v_imagen_url,
				    v.video_url AS v_video_url,
				    u.id AS u_id,
				    u.email AS u_email
				FROM
				    videos AS v
				        JOIN
				    usuarios AS u ON v.usuarios_id = u.id
				WHERE u.id=?
				""");) {
			
			pst.setLong(1, idUsuario);
			
			try (ResultSet rs = pst.executeQuery()) {
				ArrayList<Video> videos = new ArrayList<>();
				
				Video video = null;
				
				while (rs.next()) {
					long id = rs.getLong("v_id");
					String titulo = rs.getString("v_titulo");
					String descripcion = rs.getString("v_descripcion");
					String imagenUrl = rs.getString("v_imagen_url");
					Timestamp timestamp = rs.getTimestamp("v_fecha");
					LocalDateTime fecha = timestamp != null ? timestamp.toLocalDateTime() : null;
					String videoUrl = rs.getString("v_video_url");
					
					String usuarioEmail = rs.getString("u_email");
					
					Usuario usuario = new Usuario(idUsuario, usuarioEmail, null, null);
					
					video = new Video(id, titulo, descripcion, imagenUrl, fecha, videoUrl, usuario);
					
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
		try (PreparedStatement pst = JdbcHelper.prepararSql("delete from videos where id=?");) {
			pst.setLong(1, id);
			
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean borrar(Long id, Long idUsuario) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("delete from videos where id=? AND usuarios_id=?");) {
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
		try (PreparedStatement pst = JdbcHelper.prepararSql(
				"insert into videos (titulo, descripcion, imagen_url, fecha, video_url, usuarios_id) VALUES (?,?,?,?,?,?)");) {
			pst.setString(1, video.titulo());
			pst.setString(2, video.descripcion());
			pst.setString(3, video.imagenUrl());
			pst.setTimestamp(4, video.fecha() == null ? null : Timestamp.valueOf(video.fecha()));
			pst.setString(5, video.videoUrl());
			pst.setLong(6, video.usuario().id());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void modificar(Video video) {
		try (PreparedStatement pst = JdbcHelper.prepararSql(
				"update videos set titulo=?, descripcion=?, imagen_url=?, fecha=?, video_url=?, usuarios_id=? where id=?");) {
			pst.setString(1, video.titulo());
			pst.setString(2, video.descripcion());
			pst.setString(3, video.imagenUrl());
			pst.setTimestamp(4, video.fecha() == null ? null : Timestamp.valueOf(video.fecha()));
			pst.setString(5, video.videoUrl());
			pst.setLong(6, video.usuario().id());
			pst.setLong(7, video.id());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean modificar(Video video, Long idUsuario) {
		try (PreparedStatement pst = JdbcHelper.prepararSql(
				"update videos set titulo=?, descripcion=?, imagen_url=?, fecha=?, video_url=?, usuarios_id=? where id=? AND usuarios_id=?");) {
			pst.setString(1, video.titulo());
			pst.setString(2, video.descripcion());
			pst.setString(3, video.imagenUrl());
			pst.setTimestamp(4, video.fecha() == null ? null : Timestamp.valueOf(video.fecha()));
			pst.setString(5, video.videoUrl());
			pst.setLong(6, video.usuario().id());
			pst.setLong(7, video.id());
			pst.setLong(8, idUsuario);
			
			int numeroRegistrosModificados = pst.executeUpdate();
			
			return numeroRegistrosModificados == 1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
