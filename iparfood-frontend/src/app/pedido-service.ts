import { Injectable } from '@angular/core';
import { Pedido } from './pedido';
import { Plato } from './plato';

@Injectable({
  providedIn: 'root',
})
export class PedidoService {
  private readonly pedidoInicial: Pedido = {
    usuario: {
      nombre: 'Javier'
    },
    platos: [] as Plato[]
  } as Pedido;

  get pedido(): Pedido {
    const pedidoStr = sessionStorage.getItem('pedido') ?? JSON.stringify(this.pedidoInicial);

    return JSON.parse(pedidoStr) as Pedido;
  }

  set pedido(pedido: Pedido) {
    sessionStorage.setItem('pedido', JSON.stringify(pedido));
  }

  anadirPlato(plato: Plato): Pedido {
    const pedidoModificado = this.pedido;

    pedidoModificado.platos.push(plato);

    this.pedido = pedidoModificado;

    return this.pedido;
  }
}
