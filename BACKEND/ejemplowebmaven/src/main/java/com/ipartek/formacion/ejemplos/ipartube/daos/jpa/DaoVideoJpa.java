package com.ipartek.formacion.ejemplos.ipartube.daos.jpa;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.dao.DaoJpa;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoVideo;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;

public class DaoVideoJpa extends DaoJpa<Video> implements DaoVideo {

	public DaoVideoJpa() {
		super(Video.class, "com.ipartek.formacion.ejemplos.ipartube.modelos");
	}

	@Override
	public Iterable<VideoListadoDto> obtenerTodosDto() {
		return ejecutarJpa(em -> em.createQuery("""
				select new com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto(
					v.id, v.titulo, v.imagenUrl, v.fecha, v.usuario.id, v.usuario.nombre
				)
				from Video v
				""", VideoListadoDto.class).getResultList());
	}

	@Override
	public Optional<VideoDetalleDto> obtenerPorIdDto(Long id) {
		return ejecutarJpa(em -> Optional.ofNullable(em.createQuery("""
					select new com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDetalleDto(
					v.id, v.titulo, v.descripcion, v.videoUrl, v.fecha, v.usuario.id, v.usuario.nombre
				)
				from Video v
				where v.id = :id
				""", VideoDetalleDto.class).setParameter("id", id).getSingleResultOrNull()));
	}

	@Override
	public void borrar(Long idUsuario, Long id) {
		ejecutarJpa(em -> {
			Video video = em.createQuery("from Video v where v.id = :id and v.usuario.id = :idUsuario", Video.class)
					.setParameter("id", id).setParameter("idUsuario", idUsuario).getSingleResultOrNull();
			if (video != null) {
				em.remove(video);
			}

			return null;
		});
	}
}
