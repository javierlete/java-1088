// PROTOTIPO PERSONA
function Persona(nombre, apellidos) {
    this.nombre = nombre;
    this.apellidos = apellidos;
}

Persona.prototype.getNombreCompleto = function () { return `${this.nombre} ${this.apellidos}` };

Object.defineProperty(Persona.prototype, "nombreCompleto", {
  get() {
    return `${this.nombre} ${this.apellidos}`;
  },
  set(nombreCompleto) {
    const partes = nombreCompleto.split(' ');
    this.nombre = partes[0];
    this.apellidos = partes[1];
  }
});
// FIN PROTOTIPO PERSONA

const javier = new Persona('Javier', 'Lete');

console.log(typeof javier, javier);

const pepe = new Persona('Pepe');

console.log(pepe);

pepe.apellidos = 'Pérez'

console.log(pepe);

javier.profesiones = ['Formador', 'Programador', 'Músico'];

console.log(javier);

console.log(javier.getNombreCompleto());
console.log(pepe.getNombreCompleto());

console.log(typeof javier, Object.getPrototypeOf(javier).constructor.name);
console.log(Object.getPrototypeOf(javier) === Persona.prototype);
console.log(Persona.prototype.isPrototypeOf(javier));
console.log(javier instanceof Persona);

console.log('Javier'.toUpperCase());

String.prototype.toUpperCase = () => 'Never gonna give you up...'; // NOSONAR

console.log('Javier'.toUpperCase());

const nuevo = new Persona();

console.log(nuevo);

nuevo.nombreCompleto = 'Nuevo Nuevez';

console.log(nuevo.nombre);

console.log(nuevo.nombreCompleto);