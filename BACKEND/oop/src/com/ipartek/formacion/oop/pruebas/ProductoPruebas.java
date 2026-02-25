package com.ipartek.formacion.oop.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ipartek.formacion.oop.pojos.Producto;

public class ProductoPruebas {
	public static void main(String[] args) {
		Producto producto;

		producto = new Producto(5L, "Portátil", new BigDecimal("1234.12"), LocalDate.of(2025, 1, 2));

		producto.setId(5L);

		System.out.println(producto.getId());

		System.out.println(producto);

		Producto p = new Producto();

		System.out.println(p);

		Producto p2 = new Producto("Ratón", new BigDecimal("12.34"), LocalDate.now());

		System.out.println(p2);

		Producto prod1 = new Producto("Prueba", new BigDecimal("1234"), LocalDate.now());
		Producto prod2 = new Producto(prod1);

		System.out.println(prod1);
		System.out.println(prod2);

		System.out.println("¿Son el mismo producto? " + (prod1 == prod2 ? "Sí" : "No"));
		System.out.println("¿Son idénticos? " + (prod1.equals(prod2) ? "Sí" : "No"));

		prod2.setPrecio(new BigDecimal("1111"));

		System.out.println(prod1);
		System.out.println(prod2);

	}
}
