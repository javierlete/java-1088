import { inject, Injectable, signal } from '@angular/core';
import { Usuario } from './usuario';
import { catchError, Observable, of, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { PedidoService } from './pedido-service';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  usuario = signal<Usuario | undefined>(undefined);

  private readonly http = inject(HttpClient);
  private readonly apiUrl = environment.apiUrl;
  private readonly url = `${this.apiUrl}/api/v2/login`;
  private readonly pedidoService = inject(PedidoService);

  constructor() {
    // Al iniciar la app, restauramos el usuario desde sessionStorage
    const raw = sessionStorage.getItem('usuario');

    if (raw) {
      this.usuario.set(JSON.parse(raw));
    }
  }

  autenticar(usuario: Usuario): Observable<Usuario | undefined> {
    return this.http.post<Usuario>(this.url, usuario).pipe(
      tap(usuarioLogin => {
        console.log(usuarioLogin);
        this.usuario.set(usuarioLogin);
        sessionStorage.setItem('usuario', JSON.stringify(usuarioLogin));
        this.pedidoService.pedido = { ...this.pedidoService.pedido, usuario: usuarioLogin };
        console.log(this.pedidoService.pedido);
      }),
      catchError(err => {
        console.error(err);
        return of(undefined)
      })
    );
  }

  logout(): Observable<any> {
    this.usuario.set(undefined);
    sessionStorage.removeItem('usuario');
    this.pedidoService.pedido = this.pedidoService.pedidoInicial;

    return of(null);
  }
}
