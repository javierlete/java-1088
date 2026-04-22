import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Plato } from '../plato';
import { JsonPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-plato-formulario',
  imports: [RouterLink, JsonPipe, FormsModule],
  templateUrl: './plato-formulario.html',
  styleUrl: './plato-formulario.css',
})
export class PlatoFormulario {
  private readonly route = inject(ActivatedRoute);
  plato: Plato = {} as Plato;

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (id) {
      this.plato = { id, nombre: 'Nombre ' + id, precio: id, descripcion: 'Descripción ' + id, tipoComida: { id, nombre: 'TipoComida ' + id } };
    }
  }
}
