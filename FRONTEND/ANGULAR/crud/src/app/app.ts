import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Listado } from "./listado/listado";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Listado],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('crud');
}
