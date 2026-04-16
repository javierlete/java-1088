package com.ipartek.formacion.ejemplos.iparfood.dtos;

import java.util.Collection;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;

public record PedidoDto(Collection<Plato> platos) {

}
