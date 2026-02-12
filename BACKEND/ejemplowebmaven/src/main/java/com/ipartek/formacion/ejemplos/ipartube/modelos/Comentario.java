package com.ipartek.formacion.ejemplos.ipartube.modelos;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "comentarios")
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 1000)
	private String texto;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@ManyToOne
	private Video video;

	@NotNull
	@PastOrPresent
	private LocalDateTime fecha = LocalDateTime.now();

	@ManyToOne
	private Comentario comentarioPadre;
	
	@OneToMany(mappedBy = "comentarioPadre")
	private List<Comentario> respuestas;

	public Comentario(Long id, @NotBlank @Size(max = 1000) String texto, @NotNull Usuario usuario, @NotNull Video video,
			@NotNull @PastOrPresent LocalDateTime fecha, Comentario comentarioPadre) {
		super();
		this.id = id;
		this.texto = texto;
		this.usuario = usuario;
		this.video = video;
		this.fecha = fecha;
		this.comentarioPadre = comentarioPadre;
	}

	public Comentario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Comentario getComentarioPadre() {
		return comentarioPadre;
	}

	public void setComentarioPadre(Comentario comentarioPadre) {
		this.comentarioPadre = comentarioPadre;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto + ", usuario=" + usuario + ", video=" + video + ", fecha="
				+ fecha + ", comentarioPadre=" + comentarioPadre + "]";
	}

}
