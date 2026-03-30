import { Injectable } from '@angular/core';
import { Producto } from './producto';
import { PRODUCTOS } from './mock-productos';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  obtenerTodos(): Producto[] {
    return PRODUCTOS;
  }

  obtenerPorId(id: number): Producto | undefined {
    return PRODUCTOS.find(p => p.id === id);
  }

  insertar(producto: Producto): Producto {
    const id = Math.max(...PRODUCTOS.map(p => p.id)) + 1;
    producto.id = id;

    PRODUCTOS.push(producto);

    return producto;
  }

  modificar(producto: Producto): Producto {
    PRODUCTOS[PRODUCTOS.findIndex(p => producto.id === p.id)] = producto;

    return producto;
  }
  
  borrar(id: number): void {
    PRODUCTOS.splice(PRODUCTOS.findIndex(p => id === p.id), 1);
  }
}
