package com.ipartek.formacion.ejemplos.iparfood.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PlatoPostDto;
import com.ipartek.formacion.ejemplos.iparfood.dtos.PlatoPutDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.mappers.PlatoMapper;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AdministradorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Validated

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
	public Plato post(@RequestBody @Valid PlatoPostDto platoPostDto) {
		return administradorService.crearPlato(platoMapper.toNewEntity(platoPostDto));
	}

	@PutMapping("{id}")
	public Plato put(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid PlatoPutDto platoPutDto) {
		if (!id.equals(platoPutDto.id())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id debe coincidir con el id del plato");
		}

		return administradorService.modificarPlato(platoMapper.toEntity(platoPutDto));
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		administradorService.borrarPlato(id);
	}
}
