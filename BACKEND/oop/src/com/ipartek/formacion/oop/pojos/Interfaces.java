package com.ipartek.formacion.oop.pojos;

import java.util.ArrayList;

public class Interfaces {
	public static void main(String[] args) {
		ArrayList<Rodable> saco = new ArrayList<>();
		
		saco.add(new Balon());
		saco.add(new Naranja());
		
		for(Rodable r: saco) {
			if(r instanceof Comestible c) {
				c.comer();
			}
			
			r.rodar();
			
			if(r instanceof Comestible c) {
				c.comer();
			}
		}
	}
}

interface Rodable {
	void rodar();
}

interface Comestible {
	void comer();
}

class Balon extends ObjetoDeporte implements Rodable {
	@Override
	public void rodar() {
		System.out.println("Balón rodando");
	}
}

class Naranja extends Fruto implements Comestible, Rodable {
	private boolean porElSuelo = false;
	private boolean mordida = false;

	@Override
	public void rodar() {
		if (mordida) {
			System.out.println("Ya no es redonda");
		} else {
			porElSuelo = true;
			System.out.println("Naranja rodando");
		}
	}

	@Override
	public void comer() {
		mordida = true;
		
		if (porElSuelo) {
			System.out.println("PUAG, QUE ASCO");
		} else {
			System.out.println("Ñam que rica");
		}
	}

}

class Fruto {
}

class ObjetoDeporte {
}