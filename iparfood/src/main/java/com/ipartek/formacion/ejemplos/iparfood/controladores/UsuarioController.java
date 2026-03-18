package com.ipartek.formacion.ejemplos.iparfood.controladores;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;
import com.ipartek.formacion.ejemplos.iparfood.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor

@Slf4j

@Controller
@RequestMapping("/")
public class UsuarioController {
	private final UsuarioService usuarioService;
	private final PedidoDto pedidoSesion;

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
	
	@GetMapping("confirmar-pedido")
	public String confirmarPedido() {
		log.debug(pedidoSesion.toString());
	    
	    usuarioService.confirmarPedido(pedidoSesion.clone());
	    
	    pedidoSesion.getPlatos().clear();
	    
		return "redirect:/listado-pedidos";
	}
	
	@GetMapping("listado-pedidos")
	public String listadoPedidos(Model modelo, @AuthenticationPrincipal Usuario usuario) {
		modelo.addAttribute("pedidos", usuarioService.listarPedidos(usuario.getId()));
		
		return "listado-pedidos"; 
	}
}
