package com.ipartek.formacion.ejemplos.iparfood.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/login")
public class LoginRestController {
	private final AnonimoService anonimoService;
	
	@PostMapping
	public Usuario login(@RequestBody Usuario usuario) {
		return anonimoService.login(usuario);
	}
}
