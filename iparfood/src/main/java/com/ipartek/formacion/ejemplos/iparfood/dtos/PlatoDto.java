package com.ipartek.formacion.ejemplos.iparfood.dtos;

import java.math.BigDecimal;

import com.ipartek.formacion.ejemplos.iparfood.entidades.TipoComida;

// TODO: Cambiar TipoComida por un TipoComidaDto
public record PlatoDto(Long id, String nombre, String descripcion, BigDecimal precio, TipoComida tipoComida) {

}
