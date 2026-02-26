package com.ipartek.formacion.oop.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class EmpleadoPorHoras extends Empleado {
	private BigDecimal sueldoHora;
	private Integer numeroHoras;

	public EmpleadoPorHoras(Long id, String nombre, LocalDate fechaNacimiento, String dni, BigDecimal sueldoHora,
			Integer numeroHoras) {
		super(id, nombre, fechaNacimiento, dni);
		this.sueldoHora = sueldoHora;
		this.numeroHoras = numeroHoras;
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return sueldoHora.multiply(new BigDecimal(numeroHoras));
	}

	public BigDecimal getSueldoHora() {
		return sueldoHora;
	}

	public void setSueldoHora(BigDecimal sueldoHora) {
		this.sueldoHora = sueldoHora;
	}

	public Integer getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(Integer numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numeroHoras, sueldoHora);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoPorHoras other = (EmpleadoPorHoras) obj;
		return Objects.equals(numeroHoras, other.numeroHoras) && Objects.equals(sueldoHora, other.sueldoHora);
	}

	@Override
	public String toString() {
		return String.format(
				"EmpleadoPorHoras [sueldoHora=%s, numeroHoras=%s, dni=%s, id=%s, nombre=%s, fechaNacimiento=%s]",
				sueldoHora, numeroHoras, dni, id, nombre, fechaNacimiento);
	}

}
