import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
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
    console.log('obtenerPlatoPorId', id);
    return of({ id, nombre: 'Nombre ' + id, precio: id, descripcion: 'Descripción ' + id, tipoComida: { id, nombre: 'TipoComida ' + id } });
  }

  borrar(id?: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`);
  }
}
