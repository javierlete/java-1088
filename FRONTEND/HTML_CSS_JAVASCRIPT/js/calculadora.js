'use strict';

const etiquetas = [
    '%', '^', 'C', '/',
    '7', '8', '9', 'X',
    '4', '5', '6', '-',
    '1', '2', '3', '+',
    '+/-', '0', ',', '=',
];

const operaciones = {
    '+': (a, b) => a + b, 
    '-': (a, b) => a - b, 
    'X': (a, b) => a * b, 
    '/': (a, b) => a / b,
    '^': (a, b) => a ** b,
}

let display, op1, op, op2;

globalThis.addEventListener('DOMContentLoaded', () => {
    display = document.querySelector('#display');

    cargarBotonera();
});

function cargarBotonera() {
    const botonera = document.querySelector('#botonera');

    for (const etiqueta of etiquetas) {
        const boton = document.createElement('button');

        boton.innerText = etiqueta;

        if (etiqueta === '=') {
            boton.className = 'igual';

            boton.addEventListener('click', ejecutarOperacion);
        } else if (Number.isNaN(+etiqueta)) {
            boton.className = 'operacion';

            boton.addEventListener('click', operacionPulsada);
        } else {
            boton.addEventListener('click', nuevoNumero);
        }

        botonera.appendChild(boton);
    }
}

function nuevoNumero(e) {
    const numeroDisplay = display.innerText;
    const numeroPulsado = e.target.innerText;

    if (numeroDisplay === '0') {
        mostrar('');
    }

    mostrar(display.innerText + numeroPulsado);
}

function operacionPulsada(e) {
    const operacion = e.target.innerText;
    const numero = display.innerText;

    switch (operacion) {
        case 'CE':
        case 'C':
            mostrar('0');
            break;
        case '%':
            mostrar(normalizar(numero) / 100);
            break;
        case ',':
            if (!numero.includes(',')) {
                mostrar(numero + ',');
            }
            break;
        case '+/-':
            if (numero.includes('-')) {
                mostrar(numero.replace('-', ''));
            } else {
                mostrar('-' + numero);
            }

            break;
        case '=':
            ejecutarOperacion();

            break;
        default:
            operacionBinaria(numero, operacion);
    }
}

function operacionBinaria(numero, operacion) {
    op1 = normalizar(numero);
    display.innerText = '0';

    op = operaciones[operacion];
}

function ejecutarOperacion() {
    op2 = normalizar(display.innerText);

    mostrar(redondear(op(normalizar(op1), normalizar(op2))));
}

function normalizar(numero) {
    return +(String(numero).replace(',', '.'));
}

function formatear(numero) {
    return String(numero).replace('.', ',');
}

function redondear(numero) {
    return Math.round(numero * 1000000000000000) / 1000000000000000;
}

function mostrar(numero) {
    display.innerText = formatear(numero);
}
