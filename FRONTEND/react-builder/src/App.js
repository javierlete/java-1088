import './App.css';

import Titulo from './Titulo';
import LabelInput from './LabelInput';
import Bolitas from './Bolitas';
import ErrorLabel from './ErrorLabel';

function valorCambiado(valor) {
  console.log(valor);
}

export default function App() {
  return (
    <main>
      <Titulo texto="Hola a todos" />
      <Titulo texto="Hola" />

      <div>
        Ejemplos de componentes
      </div>

      <LabelInput id="nombre" etiqueta="Nombre" tipo="text" valor="Javier" onValorChange={valorCambiado} />
      <LabelInput id="email" etiqueta="Email" tipo="email" />

      <Bolitas datos={['Uno', 'Dos', 'Tres', 'Cuatro']} />

      <ErrorLabel texto={'Texto del error'} />
      <ErrorLabel texto={''} />
      <ErrorLabel />
    </main>
  );
}
