package com.ipartek.formacion.ejemplos.ipartexspring.config;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;

public class UsuarioLogin extends Usuario implements UserDetails {

	private static final long serialVersionUID = 8529729608587167893L;

	public UsuarioLogin() {
		super();
	}

	public UsuarioLogin(Long id, String nombre, String email, String password) {
		super(id, nombre, email, password);
	}

	public static UsuarioLogin of(Usuario usuario) {
		return new UsuarioLogin(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword());
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
		return getNombre();
	}

}
