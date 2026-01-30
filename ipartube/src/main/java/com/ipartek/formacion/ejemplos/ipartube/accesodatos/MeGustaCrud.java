package com.ipartek.formacion.ejemplos.ipartube.accesodatos;

import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

public class MeGustaCrud {

	private static final String SQL_INSERTAR = "INSERT INTO usuarios_le_gusta_videos (usuarios_id, videos_id) VALUES (?,?)";

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

}
