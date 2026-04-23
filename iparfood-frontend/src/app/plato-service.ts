import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Plato } from './plato';

@Injectable({
  providedIn: 'root',
})
export class PlatoService {
  private readonly url = 'http://localhost:8080/api/v2/platos';
  private readonly http = inject(HttpClient);

  obtenerPlatos(): Observable<Plato[]> {
    return this.http.get<any>(this.url);
  }

  obtenerPlatoPorId(id: number): Observable<Plato> {
    return this.http.get<Plato>(`${this.url}/${id}`);
  }

  insertar(plato: Plato): Observable<Plato> {
    console.log('insertar', plato);
    return this.http.post<Plato>(this.url, plato);
  }

  modificar(plato: Plato): Observable<Plato> {
    console.log('modificar', plato);
    return this.http.put<Plato>(`${this.url}/${plato.id}`, plato);
  }

  borrar(id?: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`);
  }
}
