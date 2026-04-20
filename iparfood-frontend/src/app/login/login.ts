import { Component } from '@angular/core';
import { Usuario } from '../usuario';
import { FormsModule } from '@angular/forms';

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

  envioFormulario() {
    console.log(this.usuario);
  }
}
