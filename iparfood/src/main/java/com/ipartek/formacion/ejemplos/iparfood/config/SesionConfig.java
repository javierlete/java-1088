package com.ipartek.formacion.ejemplos.iparfood.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@ControllerAdvice
public class SesionConfig {
	private final PedidoSesion pedidoSesion;
	
	@ModelAttribute
	private void pedido(Model modelo) {
		modelo.addAttribute("pedido", pedidoSesion);
	}
}
