import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Plato } from './plato';

@Injectable({
  providedIn: 'root',
})
export class PlatoService {
  private readonly url = 'http://localhost:8080/api/v1/platos';
  private readonly http = inject(HttpClient);

  obtenerPlatos(): Observable<Plato[]> {
    return this.http.get<any>(this.url).pipe(
      map((resultado: any) => resultado._embedded.platos)
    );
  }
}
