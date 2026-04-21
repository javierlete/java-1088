package com.ipartek.formacion.ejemplos.iparfood.servicios.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class AnonimoServiceImpl implements AnonimoService {
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Usuario login(Usuario usuario) {
		System.out.println(usuario);
		
		var usuarioAutenticadoOptional = usuarioRepository.findByEmail(usuario.getEmail());

		System.out.println(usuarioAutenticadoOptional);
		
		if (usuarioAutenticadoOptional.isEmpty()) {
			return null;
		}

		var usuarioAutenticado = usuarioAutenticadoOptional.get();

		if (passwordEncoder.matches(usuario.getPassword(), usuarioAutenticado.getPassword())) {
			return usuarioAutenticado;
		} else {
			return null;
		}
	}
}
