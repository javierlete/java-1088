package com.ipartek.formacion.ejemplos.ipartube.modelos;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "videos")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 255)
	private String titulo;

	@Lob
	@Size(max = 5000)
	private String descripcion;

	@Size(max = 255)
	@Column(name = "imagen_url")
	private String imagenUrl;

	@NotNull
	@PastOrPresent
	private LocalDateTime fecha = LocalDateTime.now();

	@NotNull
	@Size(max = 255)
	@Column(name = "video_url")
	private String videoUrl;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@Transient
	private Long numeroMeGusta;

	@Transient
	private Boolean meGusta;

	public Video(Long id, @NotBlank @Size(max = 255) String titulo, @Size(max = 5000) String descripcion,
			@Size(max = 255) String imagenUrl, @NotNull @PastOrPresent LocalDateTime fecha,
			@NotNull @Size(max = 255) String videoUrl, @NotNull Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagenUrl = imagenUrl;
		this.fecha = fecha;
		this.videoUrl = videoUrl;
		this.usuario = usuario;
	}

	public Video() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getNumeroMeGusta() {
		return numeroMeGusta;
	}

	public void setNumeroMeGusta(Long numeroMeGusta) {
		this.numeroMeGusta = numeroMeGusta;
	}

	public Boolean getMeGusta() {
		return meGusta;
	}

	public void setMeGusta(Boolean meGusta) {
		this.meGusta = meGusta;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", imagenUrl=" + imagenUrl
				+ ", fecha=" + fecha + ", videoUrl=" + videoUrl + ", usuario=" + usuario + ", numeroMeGusta="
				+ numeroMeGusta + ", meGusta=" + meGusta + "]";
	}

}
