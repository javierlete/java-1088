package com.ipartek.formacion.ejemplos.iparfood.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.iparfood.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/")
public class UsuarioController {
	private final PedidoDto pedido;
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
	public String pedido(Model modelo) {
		System.out.println("USUARIO CONTROLLER " + pedido);
		
		modelo.addAttribute("pedido", pedido);
		return "pedido";
	}
}
