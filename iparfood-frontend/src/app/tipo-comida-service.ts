import { Injectable } from '@angular/core';
import { TipoComida } from './tipo-comida';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TipoComidaService {
  obtenerTodos(): Observable<TipoComida[]> {
    return of([
      { id: 1, nombre: 'UNO' },
      { id: 2, nombre: 'DOS' },
      { id: 3, nombre: 'TRES' },
    ]);
  }
}
