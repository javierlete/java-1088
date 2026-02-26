package com.ipartek.formacion.oop.pruebas;

import java.util.function.BiFunction;

public class LambdasPruebas {
	public static void main(String[] args) {
		Operacion operacion = new Operacion() {
			@Override
			public int calcular(int a, int b) {
				return a + b;
			}
		};
		
		System.out.println(operacion.calcular(5, 6));
		
		operacion = new Operacion() {
			@Override
			public int calcular(int a, int b) {
				return a - b;
			}
		};

		System.out.println(operacion.calcular(5, 6));
		
		operacion = (a, b) -> a * b;
		
		System.out.println(operacion.calcular(5, 6));
		
		BiFunction<Integer, Integer, Integer> funcion = (a, b) -> a / b;
		
		System.out.println(funcion.apply(10, 2));
	}

	interface Operacion {
		int calcular(int a, int b);
	}
}
