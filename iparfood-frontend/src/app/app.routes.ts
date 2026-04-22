import { Routes } from '@angular/router';
import { ListadoPlatos } from './listado-platos/listado-platos';
import { VisualizacionPedido } from './visualizacion-pedido/visualizacion-pedido';
import { Login } from './login/login';
import { Admin } from './admin/admin';

export const routes: Routes = [
    { path: '', component: ListadoPlatos },
    { path: 'admin', component: Admin },
    { path: 'login', component: Login },
    { path: 'pedido', component: VisualizacionPedido },
];
