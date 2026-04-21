import { Component, inject, signal } from '@angular/core';
import { Usuario } from '../usuario';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../usuario-service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  errorLogin = signal(false);
  cierreSesion = false;

  usuario: Usuario = {
    email: '',
    password: ''
  } as Usuario;

  private readonly usuarioService = inject(UsuarioService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  constructor() {
    this.cierreSesion = Boolean(this.route.snapshot.queryParamMap.get('logout'));
  }

  envioFormulario() {
    console.log('Credenciales enviadas', this.usuario);

    this.usuarioService.autenticar(this.usuario).subscribe(
      usuario => {
        console.log('Usuario recibido', usuario);

        this.errorLogin.set(!usuario);

        if (usuario) {
          this.router.navigate(['/']);
        }
      }
    );
  }
}
