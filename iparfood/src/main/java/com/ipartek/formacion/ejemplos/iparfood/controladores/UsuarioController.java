package com.ipartek.formacion.ejemplos.iparfood.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.iparfood.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/")
public class UsuarioController {
	private final UsuarioService usuarioService;

	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("platos", usuarioService.listarPlatos());

		return "index";
	}

	@GetMapping("anadir")
	public String anadir(Long id) {
		usuarioService.anadirPlatoAPedido(id);

		return "redirect:/pedido";
	}
	
	@GetMapping("pedido")
	public String pedido() {
		return "pedido";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
}
