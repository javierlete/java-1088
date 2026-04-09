import { CurrencyPipe } from '@angular/common';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { Plato } from '../plato';
import { PlatoService } from '../plato-service';

@Component({
  selector: 'app-listado-platos',
  imports: [CurrencyPipe],
  templateUrl: './listado-platos.html',
  styleUrl: './listado-platos.css',
})
export class ListadoPlatos {
  protected platos!: Plato[];
  
  private readonly platoService = inject(PlatoService);
  private readonly changeDetectorRef = inject(ChangeDetectorRef);

  constructor() {
    this.platoService.obtenerPlatos().subscribe(platos => {
      console.log(platos);
      this.platos = platos;
      this.changeDetectorRef.markForCheck();
    });
  }
}
