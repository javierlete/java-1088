package com.ipartek.formacion.ejemplos.ipartexspring.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexspring.servicios.AnonimoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2/anonimo")
public class AnonimoRestController {
	@Autowired
	private AnonimoService anonimoService;

	@GetMapping("mensajes")
	public Iterable<Mensaje> getMensajes() {
		return anonimoService.listarMensajes();
	}

	@PostMapping("usuarios")
	public Usuario postUsuario(@Valid @RequestBody Usuario usuario) {
		return anonimoService.registrar(usuario);
	}

	@PostMapping("autenticar")
	public Usuario autenticar(@RequestBody Usuario usuario) {
		Optional<Usuario> usuarioAutenticado = anonimoService.autenticar(usuario.getEmail(), usuario.getPassword());
		
		if(usuarioAutenticado.isPresent()) {
			return usuarioAutenticado.get();
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}
}
