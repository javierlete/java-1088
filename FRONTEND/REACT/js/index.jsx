function App() {
    return (
        <main>
            <Titulo texto="Hola a todos" />
            <Titulo texto="Hola" />

            <LabelInput id="nombre" etiqueta="Nombre" tipo="text" />
            <LabelInput id="email" etiqueta="Email" tipo="email" />

            <Bolitas datos={['Uno', 'Dos', 'Tres', 'Cuatro']} />
            
            <ErrorLabel texto={'Texto del error'} />
            <ErrorLabel texto={''} />
            <ErrorLabel />
        </main>
    );
}

function Titulo({ texto }) {
    return <h1>{texto}</h1>;
}

function LabelInput({ id, etiqueta, tipo }) {
    return (
        <div>
            <label for={id}>{etiqueta}</label>
            <input id={id} type={tipo}></input>
        </div>
    );
}

function Bolitas({ datos }) {
    return (
        <ul>
            {datos.map(dato => <li>{dato}</li>)}
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