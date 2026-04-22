import { CurrencyPipe } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { Plato } from '../plato';
import { PlatoService } from '../plato-service';

@Component({
  selector: 'app-admin',
  imports: [CurrencyPipe],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin {
  private readonly platoService = inject(PlatoService);
  platos = signal<Plato[]>([]);

  constructor() {
    this.platoService.obtenerPlatos().subscribe(platos => this.platos.set(platos));
  }

  borrar(id?: number): void {
    console.log('BORRAR', id);
  }
}
