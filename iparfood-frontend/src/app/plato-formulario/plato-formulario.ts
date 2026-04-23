import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Plato } from '../plato';
import { JsonPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PlatoService } from '../plato-service';
import { TipoComida } from '../tipo-comida';
import { TipoComidaService } from '../tipo-comida-service';

@Component({
  selector: 'app-plato-formulario',
  imports: [RouterLink, JsonPipe, FormsModule],
  templateUrl: './plato-formulario.html',
  styleUrl: './plato-formulario.css',
})
export class PlatoFormulario {
  private readonly platoService = inject(PlatoService);
  private readonly tipoComidaService = inject(TipoComidaService);
  private readonly route = inject(ActivatedRoute);
  
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
}
