package com.ipartek.formacion.ejemplos.ipartexspring.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.repositorios.MensajeRepository;
import com.ipartek.formacion.ejemplos.ipartexspring.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Override
	public Mensaje enviarMensaje(Mensaje mensaje) {
		return mensajeRepository.save(mensaje);
	}

}
