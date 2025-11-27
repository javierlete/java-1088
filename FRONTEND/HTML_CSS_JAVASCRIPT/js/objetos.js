const objeto = {
    inicial: 1
};

objeto.dato1 = 5;
objeto.dato2 = 6;

console.log(typeof objeto, objeto);
console.log(objeto.dato1);
console.log(objeto['dato2']);

const p1 = {
    nombre: 'Javier',
    apellidos: 'Lete',
};

const p2 = {
    nombre: 'Pepe',
    apellidos: 'Pérez',
};

console.log(p1, p2);

p1.getNombreCompleto = function () { return `${this.nombre} ${this.apellidos}` };
p2.getNombreCompleto = p1.getNombreCompleto;

console.log(p1.getNombreCompleto());

p1.apellidos = 'Lete García';

console.log(p1.getNombreCompleto());

console.log(p2.getNombreCompleto());
