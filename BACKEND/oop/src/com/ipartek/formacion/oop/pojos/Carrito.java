package com.ipartek.formacion.oop.pojos;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Carrito {
	private ArrayList<Linea> lineas = new ArrayList<>();

	public void meter(Producto producto) {
		meter(producto, 1);
	}

	public void meter(Producto producto, Integer cantidad) {
		lineas.add(new Linea(producto, cantidad));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for(Linea linea: lineas) {
			total = total.add(linea.total());
		}
		
		return total;
	}

	public ArrayList<Linea> getLineas() {
		return lineas;
	}

	@Override
	public String toString() {
		return String.format("Carrito [lineas=%s]", lineas);
	}

	public static record Linea(Producto producto, Integer cantidad) {
		public BigDecimal total() {
			return producto.getPrecio().multiply(new BigDecimal(cantidad));
		}
	}

}
