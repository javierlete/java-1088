package com.ipartek.formacion.ejemplos.iparfood.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.ejemplos.iparfood.dtos.UsuarioDto;
import com.ipartek.formacion.ejemplos.iparfood.dtos.UsuarioLoginDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;
import com.ipartek.formacion.ejemplos.iparfood.mappers.UsuarioMapper;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v2/login")
public class LoginRestController {
	private final AnonimoService anonimoService;
	private final UsuarioMapper usuarioMapper;
	
	@PostMapping
	public UsuarioDto login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
		Usuario usuarioLogin = anonimoService.login(usuarioMapper.toEntity(usuarioLoginDto));

		if(usuarioLogin == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrectos");
		}
		
		return usuarioMapper.toDto(usuarioLogin);
	}
}
