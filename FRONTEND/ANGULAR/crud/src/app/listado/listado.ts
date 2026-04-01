import { CurrencyPipe } from '@angular/common';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Boton } from "../boton/boton";

@Component({
  selector: 'app-listado',
  imports: [RouterLink, CurrencyPipe, Boton],
  templateUrl: './listado.html',
  styleUrl: './listado.css',
})
export class Listado {
  productos: Producto[] = [];
  
  private readonly productoService: ProductoService = inject(ProductoService);
  private readonly changeDetectorRef: ChangeDetectorRef = inject(ChangeDetectorRef);

  constructor() {
    this.refrescarListado();
  }

  private refrescarListado() {
    this.productoService.obtenerTodos().subscribe(productos => {
      console.log(productos);
      this.productos = productos;
      this.changeDetectorRef.markForCheck();
    });
  }

  borrar(id: number): void {
    console.log('borrar', id);

    this.productoService.borrar(id).subscribe(() => this.refrescarListado());
  }
}
