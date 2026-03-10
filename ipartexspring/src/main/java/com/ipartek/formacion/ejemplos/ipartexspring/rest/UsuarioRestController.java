package com.ipartek.formacion.ejemplos.ipartexspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.servicios.UsuarioService;

@RestController
@RequestMapping("/api/v2/usuario")
public class UsuarioRestController {
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("mensajes")
	public Mensaje enviarMensaje(@RequestBody Mensaje mensaje) {
		return usuarioService.enviarMensaje(mensaje);
	}
}
