package com.ipartek.formacion.oop.pruebas;

import com.ipartek.formacion.oop.pojos.Poligono;
import com.ipartek.formacion.oop.pojos.Punto;

public class PoligonoPruebas {
	public static void main(String[] args) {
		Poligono poligono = new Poligono("Prueba");
		
		poligono.puntoNuevo(new Punto(3, 4));
		poligono.puntoNuevo(new Punto(6, 4));
		poligono.puntoNuevo(null);
		poligono.puntoNuevo(new Punto(1, 2));
		
		System.out.println(poligono);
	}
}
