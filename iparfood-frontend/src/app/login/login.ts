import { Component, inject } from '@angular/core';
import { Usuario } from '../usuario';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../usuario-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  errorLogin = false;
  cierreSesion = false;

  usuario: Usuario = {
    email: '',
    password: ''
  } as Usuario;

  private readonly usuarioService = inject(UsuarioService);
  private readonly router = inject(Router);

  envioFormulario() {
    console.log(this.usuario);

    this.usuarioService.autenticar(this.usuario).subscribe(
      usuario => { 
        this.errorLogin = !usuario;
        
        if(usuario) {
          this.router.navigate(['/']);
        }
      }
    );
  }
}
