import './App.css';

import { useState } from 'react';

import Titulo from './Titulo';
import LabelInput from './LabelInput';
import Bolitas from './Bolitas';
import ErrorLabel from './ErrorLabel';

export default function App() {
  const [nombre, setNombre] = useState('');

  function valorCambiado(valor) {
    setNombre(valor);
  }

  return (
    <main>
      <Titulo texto="Hola a todos" />
      <Titulo texto="Hola" />

      <div>
        Ejemplos de componentes
      </div>

      <LabelInput id="nombre" etiqueta="Nombre" tipo="text" valor="Javier" onValorChange={valorCambiado} />
      {nombre}
      <LabelInput id="email" etiqueta="Email" tipo="email" />

      <Bolitas datos={['Uno', 'Dos', 'Tres', 'Cuatro']} />

      <ErrorLabel texto={'Texto del error'} />
      <ErrorLabel texto={''} />
      <ErrorLabel />
    </main>
  );
}
