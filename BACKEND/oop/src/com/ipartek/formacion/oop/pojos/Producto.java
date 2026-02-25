package com.ipartek.formacion.oop.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Producto {
	// VARIABLES DE INSTANCIA
	private Long id;
	private String nombre;
	private BigDecimal precio;
	private LocalDate fabricacion;

	// CONSTRUCTORES
	public Producto(Long id, String nombre, BigDecimal precio, LocalDate fabricacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fabricacion = fabricacion;
	}

	// Constructor de copia
	public Producto(Producto producto) {
		this(producto.id, producto.nombre, producto.precio, producto.fabricacion);
	}

	public Producto(String nombre, BigDecimal precio, LocalDate fabricacion) {
		this(null, nombre, precio, fabricacion);
	}

	public Producto(String nombre, BigDecimal precio) {
		this(null, nombre, precio, null);
	}

	public Producto() {
		this(null, null, null, null);
	}
	
	// GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public LocalDate getFabricacion() {
		return fabricacion;
	}

	public void setFabricacion(LocalDate fabricacion) {
		this.fabricacion = fabricacion;
	}

	// HASHCODE
	@Override
	public int hashCode() {
		return Objects.hash(fabricacion, id, nombre, precio);
	}

	// EQUALS
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(fabricacion, other.fabricacion) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio);
	}

	// TOSTRING
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", fabricacion=" + fabricacion
				+ "]";
	}

}
