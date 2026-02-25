package com.ipartek.formacion.oop.pojos;

import static java.lang.Math.*;

public class PuntoCartesiano {
	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getDistancia() {
		return sqrt(pow(x, 2) + pow(y, 2));
	}
	
	public double getAngulo() {
		return toDegrees(atan2(y, x));
	}

	@Override
	public String toString() {
		return "Punto [x=" + x + ", y=" + y + "]";
	}

}
