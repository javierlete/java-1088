package com.ipartek.formacion.oop.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Empleado extends Persona {
	protected String dni;

	public Empleado(Long id, String nombre, LocalDate fechaNacimiento, String dni) {
		super(id, nombre, fechaNacimiento);
		this.dni = dni;
	}

	public Empleado(String nombre, LocalDate fechaNacimiento, String dni) {
		this(null, nombre, fechaNacimiento, dni);
	}

	public abstract BigDecimal getSueldoMensual();
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dni);
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
		Empleado other = (Empleado) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return String.format("Empleado [dni=%s, id=%s, nombre=%s, fechaNacimiento=%s]", dni, id, nombre,
				fechaNacimiento);
	}

}
