package com.ipartek.formacion.ejemplos.iparfood.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "pedidos")
public class Pedido implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime fechaHora;
	
	@NotNull
	@ManyToOne
	private Usuario usuario;

	@ManyToMany
	@Builder.Default
	private Collection<Plato> platos = new ArrayList<>();

	@Transient // Indica a JPA que este campo no existe en la DB
	@JsonProperty(access = JsonProperty.Access.READ_ONLY) // Solo para salida JSON
	public BigDecimal getTotal() {
		return platos.stream().map(Plato::getPrecio).filter(java.util.Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public Pedido clone() {
		return Pedido.builder().usuario(usuario).platos(new ArrayList<>(platos)).build();
	}
}
