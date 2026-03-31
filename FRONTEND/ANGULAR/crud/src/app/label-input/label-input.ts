import { Component, input, model } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'i-label-input',
  imports: [FormsModule],
  templateUrl: './label-input.html',
  styleUrl: './label-input.css',
})
export class LabelInput {
  etiqueta = input.required<string>();
  nombre = input.required<string>();
  valor = model.required<any>();
  tipo = input<'text' | 'number'>('text');
  paso = input<number | undefined>();
}
