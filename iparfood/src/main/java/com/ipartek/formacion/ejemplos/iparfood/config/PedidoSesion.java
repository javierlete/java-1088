package com.ipartek.formacion.ejemplos.iparfood.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;

@Component
@SessionScope
public class PedidoSesion extends Pedido {

}
