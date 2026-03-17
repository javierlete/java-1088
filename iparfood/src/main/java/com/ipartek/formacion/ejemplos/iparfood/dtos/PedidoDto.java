package com.ipartek.formacion.ejemplos.iparfood.dtos;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.ipartek.formacion.ejemplos.iparfood.entidades.Pedido;

@Component
@SessionScope
public class PedidoDto extends Pedido {

}
