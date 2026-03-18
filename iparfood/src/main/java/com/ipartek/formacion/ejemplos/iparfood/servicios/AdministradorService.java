package com.ipartek.formacion.ejemplos.iparfood.servicios;

import java.util.Collection;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.entidades.TipoComida;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface AdministradorService {
	Collection<Pedido> listarPedidos();

	Collection<Plato> listarPlatos();
	
	Plato crearPlato(@Valid Plato plato);
	Plato modificarPlato(@Valid Plato plato);
	void borrarPlato(@NotNull Long id);

	Collection<TipoComida> listarTiposComida();

	Plato obtenerPlatoPorId(Long id);
}
