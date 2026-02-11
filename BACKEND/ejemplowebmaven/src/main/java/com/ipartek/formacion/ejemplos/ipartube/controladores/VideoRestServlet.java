package com.ipartek.formacion.ejemplos.ipartube.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.accesodatos.VideoCrud;
import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoListadoDto;
import com.ipartek.formacion.ejemplos.ipartube.modelos.Video;
import com.ipartek.formacion.ejemplos.ipartube.pruebas.VideoCrudPruebas;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/videos/*")
public class VideoRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Jsonb JSON = JsonbBuilder.create();

	static {
		VideoCrudPruebas.main(null);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		Long id = obtenerId(request);

		if (id != null) {
			Video video = VideoCrud.obtenerPorId(id);
			
			if(video ==null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			out.append(JSON.toJson(video));
			return;
		}

		List<VideoListadoDto> videos = VideoCrud.obtenerTodosDto();

		String json = JSON.toJson(videos);

		out.append(json);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		Video video = JSON.fromJson(request.getReader(), Video.class);

		VideoCrud.insertar(video);

		response.setStatus(HttpServletResponse.SC_CREATED);
		
		out.append(JSON.toJson(video));
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		Long id = obtenerId(request);

		Video video = JSON.fromJson(request.getReader(), Video.class);

		if (id == null || id != video.getId()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		VideoCrud.modificar(video);

		out.append(JSON.toJson(video));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = obtenerId(request);

		VideoCrud.borrar(id);
		
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

	private static Long obtenerId(HttpServletRequest request) {
		String path = request.getPathInfo();

		if (path == null) {
			return null;
		}

		return Long.parseLong(path.substring(1));
	}
}
