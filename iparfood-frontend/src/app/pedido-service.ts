import { inject, Injectable } from '@angular/core';
import { Pedido } from './pedido';
import { Plato } from './plato';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PedidoService {
  private readonly url = 'http://localhost:8080/api/v2/pedidos';
  private readonly http = inject(HttpClient);

  public readonly pedidoInicial: Pedido = {
    platos: [] as Plato[]
  } as Pedido;

  get pedido(): Pedido {
    const pedidoStr = sessionStorage.getItem('pedido') ?? JSON.stringify(this.pedidoInicial);

    return JSON.parse(pedidoStr) as Pedido;
  }

  set pedido(pedido: Pedido) {
    sessionStorage.setItem('pedido', JSON.stringify(pedido));
  }

  get total(): number {
    return this.pedido.platos.map(p => p.precio).reduce((total, precio) => precio + total, 0);
  }

  anadirPlato(plato: Plato): Pedido {
    const pedidoModificado = this.pedido;

    pedidoModificado.platos.push(plato);

    this.pedido = pedidoModificado;

    return this.pedido;
  }

  confirmarPedido(): Observable<Pedido> {
    const pedido = this.pedido;

    pedido.fechaHora = new Date();

    this.pedido = pedido;

    return this.http.post<Pedido>(this.url, this.pedido, {
      headers: {
        'Content-type': 'application/json'
      }
    }).pipe(
      tap(resultado => console.log(resultado))
    );
  }
}
