package tiempo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EjemploTiempo {
	public static void main(String[] args) {
		Date d = new Date();
		
		Calendar c = new GregorianCalendar();
		
		java.sql.Date jsd = new java.sql.Date(d.getTime());
		
		LocalDate ld = LocalDate.now();
		
		System.out.println(d);
		System.out.println(c);
		System.out.println(jsd);
		System.out.println(ld);
		
		LocalDateTime inicio = LocalDateTime.of(2026, 1, 2, 8, 15);
		
		System.out.println(inicio);
		
		java.sql.Timestamp inicioBd = java.sql.Timestamp.valueOf(inicio);
		
		System.out.println(inicioBd);
		
		String inicioTexto = "2026-01-02 08:15:00";
		
		inicioBd = java.sql.Timestamp.valueOf(inicioTexto);
		
		System.out.println(inicioBd);
		
		DateTimeFormatter formateadorFechas = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		inicio = LocalDateTime.parse(inicioTexto, formateadorFechas);
		
		System.out.println(formateadorFechas.format(inicio));
	}
}
