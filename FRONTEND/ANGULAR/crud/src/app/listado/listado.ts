import { Component } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Producto } from '../producto';
import { PRODUCTOS } from '../mock-productos';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-listado',
  imports: [RouterLink, CurrencyPipe],
  templateUrl: './listado.html',
  styleUrl: './listado.css',
})
export class Listado {
  productos: Producto[] = PRODUCTOS;

  borrar(id: number): void {
    console.log('borrar', id);
  }
}
