import { Routes } from '@angular/router';
import { Listado } from './listado/listado';
import { Formulario } from './formulario/formulario';

export const routes: Routes = [
    { path: '', component: Listado },
    { path: 'formulario', component: Formulario },
    { path: 'formulario/:id', component: Formulario },
];
