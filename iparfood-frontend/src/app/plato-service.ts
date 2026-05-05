import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Plato } from './plato';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PlatoService {
  private readonly apiUrl = environment.apiUrl;
  private readonly url = `${this.apiUrl}/api/v2/platos`;
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
