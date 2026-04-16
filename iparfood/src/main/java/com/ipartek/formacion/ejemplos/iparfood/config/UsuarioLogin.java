package com.ipartek.formacion.ejemplos.iparfood.config;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class UsuarioLogin extends Usuario implements UserDetails {

	private static final long serialVersionUID = 8529729608587167893L;

	@SuppressWarnings("java:S3252") // Lombok builder heredado; uso intencional en subclase
	public static UsuarioLogin of(Usuario usuario) {
		return UsuarioLogin.builder().id(usuario.getId()).nombre(usuario.getNombre())
				.email(usuario.getEmail()).password(usuario.getPassword()).rol(usuario.getRol()).build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new GrantedAuthority() {
			@Override
			public @Nullable String getAuthority() {
				return "ROLE_" + getRol();
			}

			@Override
			public String toString() {
				return getAuthority();
			}
		});
	}

	@Override
	public String getUsername() {
		return getNombre();
	}

	public Usuario getUsuario() {
		return Usuario.builder().id(getId()).nombre(getUsername()).direccion(getDireccion()).email(getEmail())
				.password(getPassword()).rol(getRol()).build();
	}

}
