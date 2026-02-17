package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import static com.ipartek.formacion.ejemplos.ipartube.accesodatos.Global.*;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class VideoCrud {
	public static List<Video> obtenerTodos() {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			List<Video> videos = em.createQuery("from Video", Video.class).getResultList();

			t.commit();

			return videos;
		}
	}

	public static List<VideoListadoDto> obtenerTodosDto() {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			List<VideoListadoDto> videos = em.createQuery("""
					select new com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto(
						v.id, v.titulo, v.imagenUrl, v.fecha, v.usuario.id, v.usuario.nombre
					)
					from Video v
					""", VideoListadoDto.class).getResultList();

			t.commit();

			return videos;
		}
	}

	public static Video obtenerPorId(Long id) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			Video video = em.find(Video.class, id);

			t.commit();

			return video;
		}
	}

	public static VideoDetalleDto obtenerPorIdDto(Long id) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			VideoDetalleDto video = em.createQuery("""
					select new com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto(
						v.id, v.titulo, v.descripcion, v.videoUrl, v.fecha, v.usuario.id, v.usuario.nombre
					)
					from Video v
					where v.id = :id
					""", VideoDetalleDto.class).setParameter("id", id).getSingleResultOrNull();

			t.commit();

			return video;
		}
	}

	public static Video insertar(Video video) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.persist(video);

			t.commit();

			return video;
		}
	}

	public static Video modificar(Video video) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.merge(video);

			t.commit();

			return video;
		}
	}

	public static void borrar(Long id) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.remove(em.find(Video.class, id));

			t.commit();
		}
	}
	
	public static void borrar(Long idUsuario, Long id) {
		try (EntityManager em = EMF.createEntityManager()) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			Video video = em.createQuery("from Video v where v.id = :id and v.usuario.id = :idUsuario", Video.class)
					.setParameter("id", id).setParameter("idUsuario", idUsuario).getSingleResultOrNull();

			if (video != null) {
				em.remove(video);
			}

			t.commit();
		}
	}
}
