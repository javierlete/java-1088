package com.ipartek.formacion.oop.pruebas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalPruebas {
	public static void main(String[] args) {
		System.out.println(0.1 + 0.2);
		
		System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
		
		BigDecimal bd1 = new BigDecimal("10");
		BigDecimal bd2 = new BigDecimal("3");
		
		System.out.println(bd1.divide(bd2, 2, RoundingMode.HALF_UP));
	}
}
