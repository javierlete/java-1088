import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from "@angular/router";
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { LabelInput } from "../label-input/label-input";
import { Boton } from "../boton/boton";

@Component({
  selector: 'app-formulario',
  imports: [FormsModule, LabelInput, Boton],
  templateUrl: './formulario.html',
  styleUrl: './formulario.css',
})
export class Formulario {
  producto: Producto = {} as Producto;

  private readonly route: ActivatedRoute = inject(ActivatedRoute);
  private readonly router: Router = inject(Router);
  private readonly productoService: ProductoService = inject(ProductoService);
  private readonly changeDetectorRef: ChangeDetectorRef = inject(ChangeDetectorRef);

  constructor() {
    const id: number = Number(this.route.snapshot.params['id']);

    console.log('formulario', id);

    if (id) {
      this.productoService.obtenerPorId(id).then(producto => { // NOSONAR
          this.producto = producto ?? {} as Producto;
          this.changeDetectorRef.markForCheck();
        }
      );
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
