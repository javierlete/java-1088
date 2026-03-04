package com.ipartek.formacion.ejemplos.ipartex.entidades;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "mensajes")
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 512)
	private String texto;

	@NotNull
	@PastOrPresent
	@Column(name = "fecha_hora")
	private LocalDateTime fechaHora;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	public Mensaje(Long id, String texto, LocalDateTime fechaHora, Usuario usuario) {
		super();
		this.id = id;
		this.texto = texto;
		this.fechaHora = fechaHora;
		this.usuario = usuario;
	}

	public Mensaje() {
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

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, id, texto, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(fechaHora, other.fechaHora) && Objects.equals(id, other.id)
				&& Objects.equals(texto, other.texto) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return String.format("Mensaje [id=%s, texto=%s, fechaHora=%s, usuario=%s]", id, texto, fechaHora, usuario);
	}

}
