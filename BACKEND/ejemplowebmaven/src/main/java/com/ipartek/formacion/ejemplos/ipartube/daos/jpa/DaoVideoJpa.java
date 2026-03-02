package com.ipartek.formacion.ejemplos.ipartube.daos.jpa;

import static com.ipartek.formacion.ejemplos.ipartube.daos.jpa.Configuracion.*;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartube.daos.DaoVideo;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public class DaoVideoJpa implements DaoVideo {

	@Override
	public Iterable<VideoListadoDto> obtenerTodosDto() {
		return DAO.ejecutarJpa(em -> em.createQuery("""
				select new com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto(
					v.id, v.titulo, v.imagenUrl, v.fecha, v.usuario.id, v.usuario.nombre
				)
				from Video v
				""", VideoListadoDto.class).getResultList());
	}

	@Override
	public Optional<VideoDetalleDto> obtenerPorIdDto(Long id) {
		return DAO.ejecutarJpa(em -> Optional.ofNullable(em.createQuery("""
					select new com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto(
					v.id, v.titulo, v.descripcion, v.videoUrl, v.fecha, v.usuario.id, v.usuario.nombre
				)
				from Video v
				where v.id = :id
				""", VideoDetalleDto.class).setParameter("id", id).getSingleResultOrNull()));
	}

	@Override
	public void borrar(Long idUsuario, Long id) {
		DAO.ejecutarJpa(em -> {
			Video video = em.createQuery("from Video v where v.id = :id and v.usuario.id = :idUsuario", Video.class)
					.setParameter("id", id).setParameter("idUsuario", idUsuario).getSingleResultOrNull();
			if (video != null) {
				em.remove(video);
			}

			return null;
		});
	}

	@Override
	public Iterable<Video> obtenerTodos() {
		return DAO.ejecutarJpa(em -> em.createQuery("from Video", Video.class).getResultList());
	}

	@Override
	public Optional<Video> obtenerPorId(Long id) {
		return DAO.ejecutarJpa(em -> Optional.ofNullable(em.find(Video.class, id)));
	}

	@Override
	public Video insertar(Video video) {
		return DAO.ejecutarJpa(em -> {
			em.persist(video);
			return video;
		});
	}

	@Override
	public Video modificar(Video video) {
		return DAO.ejecutarJpa(em -> {
			em.merge(video);
			return video;
		});
	}

	@Override
	public void borrar(Long id) {
		DAO.ejecutarJpa(em -> {
			em.remove(em.find(Video.class, id));
			return null;
		});
	}

}
