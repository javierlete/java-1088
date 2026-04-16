package com.ipartek.formacion.ejemplos.iparfood.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PlatoDto;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AdministradorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/admin")
public class AdministradorController {
	private final AdministradorService administradorService;

	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("platos", administradorService.listarPlatos());

		return "admin/index";
	}

	@GetMapping("platos/formulario")
	public String platosFormulario(Model modelo, Long id) {
		var plato = new Plato();

		modelo.addAttribute("tiposComida", administradorService.listarTiposComida());

		if (id != null) {
			plato = administradorService.obtenerPlatoPorId(id);
		}

		modelo.addAttribute("plato", plato);

		return "admin/plato";
	}

	@PostMapping("platos/formulario")
	public String platosFormularioPost(@Valid PlatoDto platoDto, BindingResult bindingResult, Model modelo) {
		if (bindingResult.hasErrors()) {
			modelo.addAttribute("tiposComida", administradorService.listarTiposComida());
			return "admin/plato";
		}

		var plato = Plato.builder().id(platoDto.id()).nombre(platoDto.nombre()).descripcion(platoDto.descripcion())
				.precio(platoDto.precio()).tipoComida(platoDto.tipoComida()).build();

		if (plato.getId() != null) {
			administradorService.modificarPlato(plato);
		} else {
			administradorService.crearPlato(plato);
		}

		return "redirect:/admin";
	}

	@GetMapping("platos/borrar")
	public String platosBorrar(Long id) {
		administradorService.borrarPlato(id);

		return "redirect:/admin";
	}
}
