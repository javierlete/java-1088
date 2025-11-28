'use strict';

let h1;

globalThis.addEventListener('DOMContentLoaded', () => {
    h1 = document.querySelector('h1');

    h1.addEventListener('click', () => {
        h1.innerText = 'Ya no mires la consola, que esto es DOM';
    });

    console.log(h1.innerText);

    rellenarSelect();

    gestionarRange();

    gestionarOpacidad();
});

function gestionarOpacidad() {
    const range = document.querySelector('input[type=range]');

    range.addEventListener('input', e => h1.style.opacity = e.target.value/100);
}

function gestionarRange() {
    const ranges = document.querySelectorAll('input[type=range]');

    for (const range of ranges) {

        const input = document.createElement('input');

        input.style.width = '2rem';

        range.after(input);

        range.addEventListener('input', e => input.value = e.target.value);
    }
}

function rellenarSelect() {
    const opciones = [{ id: 1, texto: 'UNO' }, { id: 2, texto: 'DOS' }, { id: 3, texto: 'TRES' }];

    const select = document.querySelector('select');

    select.addEventListener('change', evento => console.log(evento.target.value));

    for (const opcion of opciones) {
        const option = crearOption(opcion);

        select.appendChild(option);
    }
}

function crearOption(opcion) {
    console.log(opcion);

    const option = document.createElement('option');

    console.log(option.outerHTML);

    option.value = opcion.id;

    console.log(option.outerHTML);

    option.innerText = opcion.texto;

    console.log(option.outerHTML);

    return option;
}

