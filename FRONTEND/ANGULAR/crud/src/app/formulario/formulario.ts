import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from "@angular/router";
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';

@Component({
  selector: 'app-formulario',
  imports: [FormsModule],
  templateUrl: './formulario.html',
  styleUrl: './formulario.css',
})
export class Formulario {
  producto: Producto = {} as Producto;

  private readonly route: ActivatedRoute = inject(ActivatedRoute);
  private readonly router: Router = inject(Router);
  private readonly productoService = inject(ProductoService);

  constructor() {
    const id: number = Number(this.route.snapshot.params['id']);

    console.log('formulario', id);

    if (id) {
      this.producto = this.productoService.obtenerPorId(id) ?? {} as Producto;
    }
  }

  guardar(): void {
    console.log('guardar', this.producto);

    if (this.producto.id) {
      this.productoService.modificar(this.producto);
    } else {
      this.productoService.insertar(this.producto);
    }

    this.router.navigate(['']);
  }
}
