package com.ipartek.formacion.ejemplos.ipartexspring.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexspring.repositorios.MensajeRepository;
import com.ipartek.formacion.ejemplos.ipartexspring.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.ipartexspring.servicios.AnonimoService;

import jakarta.validation.Valid;

@Validated
@Service
public class AnonimoServiceImpl implements AnonimoService {

	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Iterable<Mensaje> listarMensajes() {
		return mensajeRepository.findAll();
	}

	@Override
	public Usuario registrar(@Valid Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
			return usuario;
		} else {
			return Optional.empty();
		}
	}

}
