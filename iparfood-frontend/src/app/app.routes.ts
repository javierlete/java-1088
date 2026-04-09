import { Routes } from '@angular/router';
import { ListadoPlatos } from './listado-platos/listado-platos';
import { VisualizacionPedido } from './visualizacion-pedido/visualizacion-pedido';

export const routes: Routes = [
    { path: '', component: ListadoPlatos },
    { path: 'pedido', component: VisualizacionPedido },
];
