package com.ipartek.formacion.ejemplos.iparfood.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.iparfood.entidades.TipoComida;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AdministradorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/tipos-comida")
public class TiposComidaRestController {
	private final AdministradorService administradorService;
	
	@GetMapping
	public Iterable<TipoComida> get() {
		return administradorService.listarTiposComida();
	}
}
