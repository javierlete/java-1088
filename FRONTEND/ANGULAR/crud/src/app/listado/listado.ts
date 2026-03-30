import { CurrencyPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';

@Component({
  selector: 'app-listado',
  imports: [RouterLink, CurrencyPipe],
  templateUrl: './listado.html',
  styleUrl: './listado.css',
})
export class Listado {
  productos: Producto[] = [];
  
  private readonly productoService: ProductoService = inject(ProductoService);

  constructor() {
    this.productos = this.productoService.obtenerTodos();
  }

  borrar(id: number): void {
    console.log('borrar', id);

    this.productoService.borrar(id);
  }
}
