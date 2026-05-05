import { inject, Injectable } from '@angular/core';
import { TipoComida } from './tipo-comida';
import { Observable, of, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TipoComidaService {
  private readonly apiUrl = environment.apiUrl;
  private readonly url = `${this.apiUrl}/api/v2/tipos-comida`;

  private readonly http = inject(HttpClient);

  obtenerTodos(): Observable<TipoComida[]> {
    return this.http.get<TipoComida[]>(this.url).pipe(
      tap(tipos => console.log(tipos))
    );
  }
}
