import { CurrencyPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Pedido } from '../pedido';
import { PedidoService } from '../pedido-service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-visualizacion-pedido',
  imports: [CurrencyPipe, RouterLink],
  templateUrl: './visualizacion-pedido.html',
  styleUrl: './visualizacion-pedido.css',
})
export class VisualizacionPedido {
  protected readonly pedidoService = inject(PedidoService);

  pedido: Pedido = this.pedidoService.pedido;

  confirmarPedido() {
    this.pedidoService.confirmarPedido().subscribe();
  }
}
