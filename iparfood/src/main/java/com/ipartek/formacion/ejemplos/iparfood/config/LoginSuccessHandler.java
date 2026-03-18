package com.ipartek.formacion.ejemplos.iparfood.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.ejemplos.iparfood.dtos.PedidoDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final PedidoDto pedidoDto;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
                                        throws IOException, ServletException {

        var usuarioLogin = (UsuarioLogin) authentication.getPrincipal();

        pedidoDto.setUsuario(usuarioLogin.getUsuario());

        response.sendRedirect("/"); // o donde quieras
    }
}
