package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public class VideoCrud {
	public static ArrayList<Video> obtenerTodos() {
		try (PreparedStatement pst = JdbcHelper.prepararSql("select * from videos");
				ResultSet rs = pst.executeQuery()) {
			ArrayList<Video> videos = new ArrayList<>();

			while (rs.next()) {
				long id = rs.getLong("id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String imagenUrl = rs.getString("imagen_url");
				Timestamp timestamp = rs.getTimestamp("fecha");
				LocalDateTime fecha = timestamp != null ? timestamp.toLocalDateTime() : null;
				String videoUrl = rs.getString("video_url");

				Video video = new Video(id, titulo, descripcion, imagenUrl, fecha, videoUrl);

				videos.add(video);
			}

			return videos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Video obtenerPorId(Long id) {
		try (PreparedStatement pst = JdbcHelper.prepararSql("select * from videos where id=?");) {
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				Video video = null;

				if (rs.next()) {
					String titulo = rs.getString("titulo");
					String descripcion = rs.getString("descripcion");
					String imagenUrl = rs.getString("imagen_url");
					Timestamp timestamp = rs.getTimestamp("fecha");
					LocalDateTime fecha = timestamp != null ? timestamp.toLocalDateTime() : null;
					String videoUrl = rs.getString("video_url");

					video = new Video(id, titulo, descripcion, imagenUrl, fecha, videoUrl);
				}

				return video;
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

	public static void insertar(Video video) {
		try (PreparedStatement pst = JdbcHelper.prepararSql(
				"insert into videos (titulo, descripcion, imagen_url, fecha, video_url) VALUES (?,?,?,?,?)");) {
			pst.setString(1, video.titulo());
			pst.setString(2, video.descripcion());
			pst.setString(3, video.imagenUrl());
			pst.setTimestamp(4, video.fecha() == null ? null : Timestamp.valueOf(video.fecha()));
			pst.setString(5, video.videoUrl());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void modificar(Video video) {
		try (PreparedStatement pst = JdbcHelper.prepararSql(
				"update videos set titulo=?, descripcion=?, imagen_url=?, fecha=?, video_url=? where id=?");) {
			pst.setString(1, video.titulo());
			pst.setString(2, video.descripcion());
			pst.setString(3, video.imagenUrl());
			pst.setTimestamp(4, Timestamp.valueOf(video.fecha()));
			pst.setString(5, video.videoUrl());
			pst.setLong(6, video.id());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
