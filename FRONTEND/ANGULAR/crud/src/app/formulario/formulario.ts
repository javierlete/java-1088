import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from "@angular/router";
import { PRODUCTOS } from '../mock-productos';
import { Producto } from '../producto';

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

  constructor() {
    const id: number = +this.route.snapshot.params['id'];

    console.log('formulario', id);

    this.producto = PRODUCTOS.find(p => p.id === id)!;
  }

  guardar(): void {
    console.log('guardar', this.producto);

    this.router.navigate(['']);
  }
}
