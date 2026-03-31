import { Injectable } from '@angular/core';
import { Producto } from './producto';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  private readonly URL = 'http://localhost:3000/productos/';

  async obtenerTodos(): Promise<Producto[]> {
    const respuesta = await fetch(this.URL);

    console.log(respuesta);

    const productos = await respuesta.json();

    console.log(productos);

    return productos;
  }

  async obtenerPorId(id: number): Promise<Producto | undefined> {
    const respuesta = await fetch(this.URL + id);
    return await respuesta.json();
  }

  async insertar(producto: Producto): Promise<Producto> {
    const respuesta = await fetch(this.URL, {
      body: JSON.stringify(producto),
      method: 'POST',
      headers: {
        'Content-type': 'application/json'
      }
    });

    return await respuesta.json();
  }

  async modificar(producto: Producto): Promise<Producto> {
    const respuesta = await fetch(this.URL + producto.id, {
      body: JSON.stringify(producto),
      method: 'PUT',
      headers: {
        'Content-type': 'application/json'
      }
    });

    return await respuesta.json();
  }
  
  async borrar(id: number): Promise<void> {
    await fetch(this.URL + id, { method: 'DELETE' });
  }
}
