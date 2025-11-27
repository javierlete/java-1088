'use strict';

console.log('Ejemplo de JavaScript');

console.log(typeof undefined);
console.log(typeof true);
console.log(typeof 123);
console.log(typeof 'texto');
console.log(typeof console.log);
console.log(typeof console);

console.log(!!0);
console.log(!!'');
console.log(!!undefined);
console.log(!!null);

console.debug('VERBOSE');
console.log('INFO');
console.warn('WARNING');
console.error('ERROR');

console.log('5' == 5);
console.log('5' === 5); // NOSONAR typeof '5' == typeof 5 && '5' == 5

console.log('11' + 1);
console.log(11 + '1');
console.log('11' - 1);

console.log(variable);

var variable = 'Variable var'; // NOSONAR

console.log(variable);

let x;

x = 1;

console.log(x);

x = 'pepe';

console.log(x);

const y = 0;

console.log(y);

// NOSONAR y = 2; // NO LO PERMITE

const numeroRecibido = 'asdfds';

const opcion = +numeroRecibido;

console.log(opcion);

console.log(opcion == NaN); // NOSONAR

console.log(NaN == NaN); // NOSONAR

if (!Number.isNaN(opcion)) {
    switch (opcion) {
        case 1: console.log('El número uno'); break;
        case 5: console.log('El cinco mola'); break;
        default: console.log('Otro número');
    }
}

const mes = 6;

let dias;

switch (mes) {
    case 2: dias = 28; break;
    case 4:
    case 6:
    case 9:
    case 11: dias = 30; break;
    default: dias = 31;
}

console.log(`El mes ${mes} tiene ${dias} días`);

const arr = Array(3); // NOSONAR

arr[0] = 5;
arr[1] = 6;
arr[2] = 7;
arr[3] = 8;
arr[4] = 'Texto'; // NOSONAR
arr[10] = 'Por que me da la gana';

arr['hola'] = 'Hello'; // NOSONAR
arr.pepe = 'Colega';

console.log(arr['pepe']);

arr.push('Yepa');

console.log(arr);

const diasMeses = [31, 28, 31, 30, 31];

console.log(diasMeses.length);

const nombres = [];

nombres.push('Pepe', 'Juan', 'Pedro');

console.log(nombres);

const diccionario = [];

diccionario['perro'] = 'dog'; // NOSONAR
diccionario.casa = 'home';

console.log(diccionario);

console.log(diccionario.perro);
console.log(diccionario['casa']);
