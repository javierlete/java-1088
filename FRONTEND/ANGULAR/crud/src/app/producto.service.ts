import { inject, Injectable } from '@angular/core';
import { Producto } from './producto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  private readonly URL = 'http://localhost:3000/productos/';
  private readonly http: HttpClient = inject(HttpClient);

  obtenerTodos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.URL);
  }

  obtenerPorId(id: number): Observable<Producto | undefined> {
    return this.http.get<Producto | undefined>(this.URL + id);
  }

  insertar(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(this.URL, producto);
  }

  modificar(producto: Producto): Observable<Producto> {
    return this.http.put<Producto>(this.URL + producto.id, producto);
  }
  
  borrar(id: number): Observable<void> {
    return this.http.delete<void>(this.URL + id);
  }
}
