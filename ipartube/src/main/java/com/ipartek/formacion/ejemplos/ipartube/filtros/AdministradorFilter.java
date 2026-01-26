package com.ipartek.formacion.ejemplos.ipartube.filtros;

import java.io.IOException;

import com.ipartek.formacion.ejemplos.ipartube.modelos.Usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/cf/admin/*")
public class AdministradorFilter extends HttpFilter {
	private static final long serialVersionUID = 2792678868543353992L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null || !"ADMINISTRADOR".equals(usuario.rol().nombre())) {
			response.sendRedirect(request.getContextPath() + "/cf/login");
			return;
		}

		super.doFilter(request, response, chain); // Damos permiso a la petición para continuar
	}

}
