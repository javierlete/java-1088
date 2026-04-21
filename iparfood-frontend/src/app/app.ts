import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { UsuarioService } from './usuario-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly usuarioService = inject(UsuarioService);
  protected readonly router = inject(Router);

  logout() {
    this.usuarioService.logout().subscribe(
      _ => this.router.navigate(['login'], { queryParams: { logout: true }})
    );
  }
}
