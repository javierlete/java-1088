import { inject, Injectable, signal } from '@angular/core';
import { Usuario } from './usuario';
import { catchError, Observable, of, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  usuario = signal<Usuario | undefined>(undefined);

  private readonly http = inject(HttpClient);
  private readonly url = 'http://localhost:8080/api/v2/login';

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

    return of(null);
  }
}
