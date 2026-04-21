import { Injectable, signal } from '@angular/core';
import { Usuario } from './usuario';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  usuario = signal<Usuario | undefined>(undefined);

  constructor() {
    // Al iniciar la app, restauramos el usuario desde sessionStorage
    const raw = sessionStorage.getItem('usuario');
    
    if (raw) {
      this.usuario.set(JSON.parse(raw));
    }
  }

  autenticar(usuario: Usuario): Observable<Usuario | undefined> {
    if (usuario.email === 'javier@email.net' && usuario.password === 'javier') {
      usuario.nombre = 'Javier';
      usuario.password = '';

      this.usuario.set(usuario);
      sessionStorage.setItem('usuario', JSON.stringify(usuario));

      return of(usuario);
    } else {
      return of(undefined);
    }
  }

  logout(): Observable<any> {
    this.usuario.set(undefined);
    sessionStorage.removeItem('usuario');
    
    return of(null);
  }
}
