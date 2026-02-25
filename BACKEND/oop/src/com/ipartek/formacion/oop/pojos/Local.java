package com.ipartek.formacion.oop.pojos;

import java.util.ArrayList;

public class Local {
	private ArrayList<Persona> personas = new ArrayList<>();

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void entrar(Persona persona) {
		personas.add(persona);
	}

	public void salir(Persona persona) {
		personas.remove(persona);
	}

	@Override
	public String toString() {
		return String.format("Local [personas=%s]", personas);
	}

}
