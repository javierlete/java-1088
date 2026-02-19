package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.CallableStatement;
import java.sql.SQLIntegrityConstraintViolationException;

public class MeGustaCrud {

	private static final String SQL_INSERTAR = "CALL megusta(?,?)";
	private static final String SQL_BORRAR = "CALL nomegusta(?,?)";

	public static void insertar(Long idUsuario, Long idVideo) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_INSERTAR)) {
			pst.setLong(1, idUsuario);
			pst.setLong(2, idVideo);
			
			pst.executeUpdate();
		} catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("Al usuario ya le gustaba el video");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void borrar(Long idUsuario, Long idVideo) {
		try (CallableStatement pst = JdbcHelper.procedimientoSql(SQL_BORRAR)) {
			pst.setLong(1, idUsuario);
			pst.setLong(2, idVideo);
			
			int numeroRegistrosBorrados = pst.executeUpdate();
			
			if(numeroRegistrosBorrados == 0) {
				System.out.println("Al usuario no le gustaba el video en un principio");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
