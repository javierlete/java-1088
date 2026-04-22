import { Component } from '@angular/core';
import { Plato } from '../plato';
import { PLATOS } from '../mock-platos';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-admin',
  imports: [CurrencyPipe],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin {
  platos: Plato[] = PLATOS;
}
