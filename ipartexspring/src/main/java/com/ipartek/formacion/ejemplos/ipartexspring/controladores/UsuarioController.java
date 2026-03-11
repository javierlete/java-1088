package com.ipartek.formacion.ejemplos.ipartexspring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.ipartexspring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexspring.servicios.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("nuevo-mensaje")
	public String nuevoMensaje(Mensaje mensaje) {
		usuarioService.enviarMensaje(mensaje);
		
		return "redirect:/index";
	}
}
