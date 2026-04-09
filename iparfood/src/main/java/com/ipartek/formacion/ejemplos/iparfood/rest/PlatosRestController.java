package com.ipartek.formacion.ejemplos.iparfood.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/platos")
public class PlatosRestController {
	private final UsuarioService usuarioService;
	
	@GetMapping
	public Iterable<Plato> get() {
		return usuarioService.listarPlatos(); 
	}
}
