import { Routes } from '@angular/router';
import { ListadoPlatos } from './listado-platos/listado-platos';
import { VisualizacionPedido } from './visualizacion-pedido/visualizacion-pedido';
import { Login } from './login/login';
import { Admin } from './admin/admin';
import { PlatoFormulario } from './plato-formulario/plato-formulario';

export const routes: Routes = [
    { path: '', component: ListadoPlatos },
    { path: 'admin', component: Admin },
    { path: 'admin/plato', component: PlatoFormulario },
    { path: 'admin/plato/:id', component: PlatoFormulario },
    { path: 'login', component: Login },
    { path: 'pedido', component: VisualizacionPedido },
];
