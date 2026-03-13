package com.ipartek.formacion.ejemplos.ipartexspring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexspring.servicios.AnonimoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class AnonimoController {
	@Autowired
	private AnonimoService anonimoService;

	@GetMapping({"", "index"})
	public String index(Model modelo) {
		modelo.addAttribute("mensajes", anonimoService.listarMensajes());

		return "index";
	}

	@GetMapping("registrar")
	public String registrar(Usuario usuario) {
		return "registrar";
	}

	@PostMapping("registrar-post")
	public String registrarPost(@Valid Usuario usuario, BindingResult bindingResult, Model modelo) {
		if (bindingResult.hasErrors()) {
			return "registrar";
		}
		
		anonimoService.registrar(usuario);

		return "redirect:/index";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
}
