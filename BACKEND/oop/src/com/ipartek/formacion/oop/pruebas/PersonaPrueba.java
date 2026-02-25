package com.ipartek.formacion.oop.pruebas;

import com.ipartek.formacion.oop.pojos.Local;
import com.ipartek.formacion.oop.pojos.Persona;

public class PersonaPrueba {
	public static void main(String[] args) {
		Local local = new Local();
		
		Persona pepe = new Persona("Pepe");
		
		local.entrar(pepe);
		local.entrar(new Persona("Juan"));
		
		for(Persona persona: local.getPersonas()) {
			System.out.println(persona);
		}
		
		local.salir(pepe);
		
		System.out.println(local);
	}
}
