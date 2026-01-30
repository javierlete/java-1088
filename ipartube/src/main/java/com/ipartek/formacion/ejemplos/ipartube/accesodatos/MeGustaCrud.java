package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

public class MeGustaCrud {

	private static final String SQL_INSERTAR = "INSERT INTO usuarios_le_gusta_videos (usuarios_id, videos_id) VALUES (?,?)";
	private static final String SQL_BORRAR = "DELETE FROM usuarios_le_gusta_videos WHERE usuarios_id=? AND videos_id=?";

	public static void insertar(Long idUsuario, Long idVideo) {
		try (PreparedStatement pst = JdbcHelper.prepararSql(SQL_INSERTAR)) {
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
		try (PreparedStatement pst = JdbcHelper.prepararSql(SQL_BORRAR)) {
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
