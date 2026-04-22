package com.ipartek.formacion.ejemplos.iparfood.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AdministradorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/platos")
public class PlatosRestController {
	private final AdministradorService administradorService;
	
	@GetMapping
	public Iterable<Plato> get() {
		return administradorService.listarPlatos(); 
	}
	
	@GetMapping("{id}")
	public Plato getId(@PathVariable Long id) {
		return administradorService.obtenerPlatoPorId(id);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		administradorService.borrarPlato(id);
	}
}
