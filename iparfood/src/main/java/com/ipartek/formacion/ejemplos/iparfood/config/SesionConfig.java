package com.ipartek.formacion.ejemplos.iparfood.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PedidoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@ControllerAdvice
public class SesionConfig {
	private final PedidoDto pedido;
	
	@ModelAttribute
	private void pedido(Model modelo) {
		modelo.addAttribute("pedido", pedido);
	}
}
