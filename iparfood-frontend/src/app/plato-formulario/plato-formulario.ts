import { JsonPipe } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Plato } from '../plato';
import { PlatoService } from '../plato-service';
import { TipoComida } from '../tipo-comida';
import { TipoComidaService } from '../tipo-comida-service';

@Component({
  selector: 'app-plato-formulario',
  imports: [JsonPipe, FormsModule],
  templateUrl: './plato-formulario.html',
  styleUrl: './plato-formulario.css',
})
export class PlatoFormulario {
  private readonly platoService = inject(PlatoService);
  private readonly tipoComidaService = inject(TipoComidaService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  plato: Plato = {
    tipoComida: {
      id: 0
    }
  } as Plato;
  tiposComida = signal<TipoComida[]>([]);

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (id) {
      this.platoService.obtenerPlatoPorId(id).subscribe(plato => this.plato = plato);
    }

    this.tipoComidaService.obtenerTodos().subscribe(tipos => this.tiposComida.set(tipos));
  }

  guardar() {
    console.log('guardar', this.plato);

    const suscripcion = this.plato.id ? this.platoService.modificar(this.plato) : this.platoService.insertar(this.plato);

    suscripcion.subscribe(_ => this.router.navigate(['admin']));
  }
}

