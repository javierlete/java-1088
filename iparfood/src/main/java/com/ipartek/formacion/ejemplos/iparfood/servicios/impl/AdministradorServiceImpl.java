package com.ipartek.formacion.ejemplos.iparfood.servicios.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.iparfood.repositorios.PlatoRepository;
import com.ipartek.formacion.ejemplos.iparfood.servicios.AdministradorService;
import com.ipartek.formacion.ejemplos.iparfood.servicios.ServicioException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Validated
@Slf4j
// @Log

@Service
public class AdministradorServiceImpl implements AdministradorService {
//	private static final Logger log = Logger.getLogger(AdministradorServiceImpl.class.getName());
	
	private final PedidoRepository pedidoRepository;
	private final PlatoRepository platoRepository;
	
	@Override
	public Collection<Pedido> listarPedidos() {
		return pedidoRepository.findAll();
	}

	@Override
	public Collection<Plato> listarPlatos() {
		return platoRepository.findAll();
	}

	@Override
	public Plato crearPlato(@Valid Plato plato) {
		if(plato.getId() != null) {
			log.error("El plato a crear no debería tener id");
			
			throw new ServicioException("El plato a crear no debería tener id");
		}
		
		return platoRepository.save(plato);
	}

	@Override
	public Plato modificarPlato(@Valid Plato plato) {
		if(plato.getId() == null) {
			log.error("El plato a modificar debe tener id");

			throw new ServicioException("El plato a modificar debe tener id");
		}
		
		return platoRepository.save(plato);
	}

	@Override
	public void borrarPlato(@NotNull Long id) {
		platoRepository.deleteById(id);
	}
}
