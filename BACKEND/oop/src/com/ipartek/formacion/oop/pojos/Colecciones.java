package com.ipartek.formacion.oop.pojos;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Colecciones {
	public static void main(String[] args) {
		Collection<String> coleccion = Set.of("Uno", "Dos", "Tres");
		
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
