package com.ipartek.formacion.oop.pojos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Colecciones {
	public static void main(String[] args) {
		Collection<String> coleccion = new ArrayList<>();
		
		coleccion.add("Uno");
		coleccion.add("Dos");
		coleccion.add("Tres");
		
		Iterable<String> iterable = coleccion;
		
		for(String dato: iterable) {
			System.out.println(dato);
		}
		
		Iterator<String> iterator = iterable.iterator();
		
		while(iterator.hasNext()) {
			String dato = iterator.next();
			
			System.out.println(dato);
		}
		
		Punto p = new Punto(6, 7);
		
		for(Integer coordenada: p) {
			System.out.println(coordenada);
		}
	}
}
