import { ChangeDetectorRef, Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Plato } from './plato';
import { PlatoService } from './plato-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('iparfood-frontend');
  protected platos!: Plato[];
  
  private readonly platoService = inject(PlatoService);
  private readonly changeDetectorRef = inject(ChangeDetectorRef);

  constructor() {
    this.platoService.obtenerPlatos().subscribe(platos => {
      console.log(platos);
      this.platos = platos;
      this.changeDetectorRef.markForCheck();
    });
  }
}
