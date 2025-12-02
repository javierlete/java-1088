'use strict';

const URL = 'http://localhost:3000/productos';

globalThis.addEventListener('DOMContentLoaded', async () => {
    const respuesta = await fetch(URL);
    const productos = await respuesta.json();
    
    const tbody = document.querySelector('tbody');

    for(const producto of productos) {
        const tr = document.createElement('tr');

        tr.innerHTML = `
            <th>${producto.id}</th>
            <td>${producto.nombre}</td>
            <td>${producto.precio}</td>
            <td>
                <a href="#">Editar</a>
                <a href="#">Borrar</a>
            </td>
        `;

        tbody.appendChild(tr);
    }
});