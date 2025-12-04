'use strict';

const URL = 'http://localhost:3000/productos';

let formulario, tabla;

globalThis.addEventListener('DOMContentLoaded', async () => {
    formulario = document.forms[0];
    tabla = document.querySelector('table');

    mostrarTabla();
});

function anyadir() {
    console.log('Añadir');

    formulario.reset();

    mostrarFormulario();
}

async function editar(id) {
    console.log('Editar', id);

    try {
        const response = await fetch(`${URL}/${id}`);
        const producto = await response.json();

        console.log(producto);

        formulario.id.value = producto.id;
        formulario.nombre.value = producto.nombre;
        formulario.precio.value = producto.precio;

        mostrarFormulario();
    } catch (error) {
        alert('Ha habido un error al recibir los datos del producto');

        console.error(error);
    }
}

async function borrar(id) {
    console.log('Borrar', id);

    if (!confirm('¿Estás seguro de que quieres borrar el producto id ' + id + '?')) {
        return;
    }

    try {
        const response = await fetch(`${URL}/${id}`, { method: 'DELETE' });

        console.log(response.statusText);

        mostrarTabla();
    } catch (error) {
        console.error(error);
    }
}

async function guardar() {
    const producto = {
        nombre: formulario.nombre.value,
        precio: formulario.precio.value,
    };

    const id = formulario.id.value;

    let url, metodo;

    if (id) {
        producto.id = id;

        url = `${URL}/${id}`;
        metodo = 'PUT';
    } else {
        url = URL;
        metodo = 'POST';
    }

    try {
        const response = await fetch(url, {
            method: metodo,
            headers: { 'content-type': 'application/json' },
            body: JSON.stringify(producto),
        });

        const data = await response.json();

        console.log(data);
    } catch (error) {
        console.error(error);
    }

    mostrarTabla();
}



function mostrarFormulario() {
    formulario.style.display = null;
    tabla.style.display = 'none';
}

async function mostrarTabla() {
    await recargarTabla();

    tabla.style.display = null;
    formulario.style.display = 'none';
}

async function recargarTabla() {
    const respuesta = await fetch(URL);
    const productos = await respuesta.json();

    const tbody = document.querySelector('tbody');

    tbody.innerHTML = '';

    for (const producto of productos) {
        const tr = document.createElement('tr');

        tr.innerHTML = `
            <th>${producto.id}</th>
            <td>${producto.nombre}</td>
            <td>${producto.precio}</td>
            <td>
                <a href="javascript:editar(${producto.id})">Editar</a>
                <a href="javascript:borrar(${producto.id})">Borrar</a>
            </td>
        `;

        tbody.appendChild(tr);
    }
}
