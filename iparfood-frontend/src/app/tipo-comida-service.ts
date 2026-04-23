import { inject, Injectable } from '@angular/core';
import { TipoComida } from './tipo-comida';
import { Observable, of, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class TipoComidaService {
  private readonly url = 'http://localhost:8080/api/v2/tipos-comida';

  private readonly http = inject(HttpClient);

  obtenerTodos(): Observable<TipoComida[]> {
    return this.http.get<TipoComida[]>(this.url).pipe(
      tap(tipos => console.log(tipos))
    );
  }
}
