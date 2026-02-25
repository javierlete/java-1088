package com.ipartek.formacion.oop.pruebas;

import com.ipartek.formacion.oop.pojos.Punto;
import com.ipartek.formacion.oop.pojos.PuntoCartesiano;

public class PuntoPrueba {
	public static void main(String[] args) {
		Punto punto = new Punto();
		
		punto.setX(3);
		punto.setY(4);
		
		System.out.println(punto);

		System.out.println(punto.getDistancia());
		
		System.out.println(punto.getAngulo());
		
		System.out.println(punto.getX());
		
		System.out.println(punto.getY());
		
		
		PuntoCartesiano puntoCartesiano = new PuntoCartesiano();
		
		puntoCartesiano.setX(3);
		puntoCartesiano.setY(4);
		
		System.out.println(puntoCartesiano);

		System.out.println(puntoCartesiano.getDistancia());
		
		System.out.println(puntoCartesiano.getAngulo());
		
		System.out.println(puntoCartesiano.getX());
		
		System.out.println(puntoCartesiano.getY());
		
	}
}
