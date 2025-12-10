function valorCambiado(valor) {
    console.log(valor);
}

function App() {

    return (
        <React.StrictMode>
            <main>
                <Titulo texto="Hola a todos" />
                <Titulo texto="Hola" />

                <LabelInput id="nombre" etiqueta="Nombre" tipo="text" valor="Javier" onValorChange={valorCambiado} />
                <LabelInput id="email" etiqueta="Email" tipo="email" />

                <Bolitas datos={['Uno', 'Dos', 'Tres', 'Cuatro']} />

                <ErrorLabel texto={'Texto del error'} />
                <ErrorLabel texto={''} />
                <ErrorLabel />
            </main>
        </React.StrictMode>
    );
}

function Titulo({ texto }) {
    return <h1>{texto}</h1>;
}

function LabelInput({ id, etiqueta, tipo, valor, onValorChange }) {
    const [estado, setEstado] = React.useState(valor);

    function onChange(e) {
        setEstado(e.target.value);

        onValorChange(e.target.value);
    }

    return (
        <div>
            <label htmlFor={id}>{etiqueta}</label>
            <input id={id} type={tipo} value={estado} onChange={onChange}></input>
        </div>
    );
}

function Bolitas({ datos }) {
    return (
        <ul>
            {datos.map(dato => <li key={dato}>{dato}</li>)}
        </ul>
    );
}

function ErrorLabel({ texto }) {
    return (
        <>
            {texto ? <span>[[[{texto}]]]</span> : null}
        </>
    );
}

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<App />);