package com.ipartek.formacion.ejemplos.ipartube.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 255)
	@Column(name = "imagen_url")
	private String imagenUrl;
	
	@NotBlank
	@Size(max = 20)
	private String nombre;
	
	@NotBlank
	@Email
	@Size(max = 255)
	@Column(unique = true)
	private String email;
	
	@NotBlank
	@Size(max = 255)
	private String password;
	
	@NotNull
	@ManyToOne // (cascade = CascadeType.PERSIST)
	private Rol rol;

	public Usuario(Long id, String imagenUrl, String nombre, String email, String password, Rol rol) {
		super();
		this.id = id;
		this.imagenUrl = imagenUrl;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.rol = rol;
	}

	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", imagenUrl=" + imagenUrl + ", nombre=" + nombre + ", email=" + email
				+ ", password=" + password + ", rol=" + rol + "]";
	}

}
