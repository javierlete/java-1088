package com.ipartek.formacion.ejemplos.ipartexspring.entidades;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = -7375734781548673829L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String nombre;

	@NotBlank
	@Email
	@Size(max = 255)
	@Column(unique = true)
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;

	public Usuario(Long id, String nombre, String email, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nombre, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return String.format("Usuario [id=%s, nombre=%s, email=%s, password=%s]", id, nombre, email, password);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new GrantedAuthority() {
			@Override
			public @Nullable String getAuthority() {
				return "ROLE_USUARIO";
			}
			
			@Override
			public String toString() {
				return getAuthority();
			}
		});
	}

	@Override
	public String getUsername() {
		return nombre;
	}

}
