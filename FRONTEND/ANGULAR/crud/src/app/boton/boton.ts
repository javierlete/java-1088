import { CommonModule } from '@angular/common';
import { Component, input, output } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'i-boton',
  imports: [CommonModule, RouterLink],
  templateUrl: './boton.html',
  styleUrl: './boton.css',
})
export class Boton {
  etiqueta = input.required<string>();
  tipo = input<'primary' | 'danger'>('primary');
  tamanyo = input<'sm' | 'lg' | undefined>();
  icono = input<boolean>(false);
  vinculo = input<string | undefined>();
  pulsado = output<void>();
}
