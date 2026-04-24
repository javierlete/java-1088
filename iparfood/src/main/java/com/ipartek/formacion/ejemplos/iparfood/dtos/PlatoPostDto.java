package com.ipartek.formacion.ejemplos.iparfood.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PlatoPostDto(
        @NotBlank
        @Size(max = 20)
        String nombre,

        @Size(max = 2000)
        String descripcion,

        @NotNull
        @Positive
        BigDecimal precio,

        @NotNull
        @Positive
        Long tipoComidaId
) {}
