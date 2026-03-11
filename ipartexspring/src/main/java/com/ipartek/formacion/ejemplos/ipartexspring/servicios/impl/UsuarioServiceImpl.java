package com.ipartek.formacion.ejemplos.ipartexspring.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexspring.repositorios.MensajeRepository;
import com.ipartek.formacion.ejemplos.ipartexspring.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.ipartexspring.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Mensaje enviarMensaje(Mensaje mensaje) {
		return mensajeRepository.save(mensaje);
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

}
