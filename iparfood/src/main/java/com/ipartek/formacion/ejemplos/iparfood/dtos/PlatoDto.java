package com.ipartek.formacion.ejemplos.iparfood.dtos;

import java.math.BigDecimal;

public record PlatoDto(Long id, String nombre, String descripcion, BigDecimal precio, TipoComidaDto tipoComida) {

}
