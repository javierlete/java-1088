package com.ipartek.formacion.ejemplos.iparfood.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.iparfood.entidades.TipoComida;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/tipos-comida")
public class TiposComidaRestController {
	@GetMapping
	public Iterable<TipoComida> get() {
		return List.of(TipoComida.builder().id(1L).nombre("Uno").build(),
				TipoComida.builder().id(2L).nombre("Dos").build());
	}
}
