package com.ipartek.formacion.ejemplos.iparfood.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PlatoDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.mappers.PlatoMapper;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AdministradorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/platos")
public class PlatosRestController {
	private final AdministradorService administradorService;
	
	private final PlatoMapper platoMapper;
	
	@GetMapping
	public Iterable<Plato> get() {
		return administradorService.listarPlatos(); 
	}
	
	@GetMapping("{id}")
	public Plato getId(@PathVariable Long id) {
		return administradorService.obtenerPlatoPorId(id);
	}
	
	@PostMapping
	public Plato post(@RequestBody PlatoDto platoDto) {
		return administradorService.crearPlato(platoMapper.toEntity(platoDto));
	}
	
	@PutMapping("{id}")
	public Plato put(@PathVariable Long id, @RequestBody PlatoDto platoDto) {
		return administradorService.modificarPlato(platoMapper.toEntity(platoDto));
	}
	
	
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		administradorService.borrarPlato(id);
	}
}
