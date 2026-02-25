package com.ipartek.formacion.oop.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.oop.pojos.Carrito;
import com.ipartek.formacion.oop.pojos.Carrito.Linea;
import com.ipartek.formacion.oop.pojos.Producto;

public class CarritoPruebas {
	public static void main(String[] args) {
		Carrito carrito = new Carrito();

		carrito.meter(new Producto("Portátil", new BigDecimal("1234")));
		carrito.meter(new Producto("Monitor", new BigDecimal("123")), 2);

		System.out.println(carrito);

		for (Linea linea : carrito.getLineas()) {
			System.out.printf("%-10s (%5s €) %2s unidades: %5s €\n", linea.producto().getNombre(),
					linea.producto().getPrecio(), linea.cantidad(), linea.total());
		}

		System.out.println(carrito.getTotal() + " €");
	}
}
