package com.ipartek.formacion.ejemplos.iparfood.dtos;

import java.util.Collection;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Plato;
import com.ipartek.formacion.ejemplos.iparfood.entidades.Usuario;

public record PedidoDto(Usuario usuario, Collection<Plato> platos) {

}
