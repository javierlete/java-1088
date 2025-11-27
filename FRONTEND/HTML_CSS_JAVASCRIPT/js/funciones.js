console.log(typeof sumar, sumar);

console.log(sumar(5, 6));

const restar = function (a, b) {
    return a - b;
};

console.log(restar(6, 4));

let operacion = restar;

console.log(operacion(4, 3));

const multiplicar = (a, b) => a * b;

operacion = multiplicar;

console.log(operacion(4, 3));

console.log(calculadora(3, sumar, 2));

function sumar(a, b) {
    return a + b;
}

function calculadora(a, op, b) {
    return op(a, b);
}