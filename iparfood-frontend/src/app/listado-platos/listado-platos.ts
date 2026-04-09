import { CurrencyPipe } from '@angular/common';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { Plato } from '../plato';
import { PlatoService } from '../plato-service';
import { PedidoService } from '../pedido-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listado-platos',
  imports: [CurrencyPipe],
  templateUrl: './listado-platos.html',
  styleUrl: './listado-platos.css',
})
export class ListadoPlatos {
  protected platos!: Plato[];

  private readonly platoService = inject(PlatoService);
  private readonly pedidoService = inject(PedidoService);
  private readonly changeDetectorRef = inject(ChangeDetectorRef);
  private readonly router = inject(Router);

  constructor() {
    this.platoService.obtenerPlatos().subscribe(platos => {
      console.log(platos);
      this.platos = platos;
      this.changeDetectorRef.markForCheck();
    });
  }

  anadirPedido(plato: Plato) {
    console.log(plato);
    
    console.log(this.pedidoService.anadirPlato(plato));

    this.router.navigate(['pedido']);
  }
}
