import { Component } from '@angular/core';
import { Titulo } from './titulo/titulo';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [Titulo]
})
export class App {
}
