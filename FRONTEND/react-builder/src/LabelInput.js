import './LabelInput.css';

import PropTypes from 'prop-types';
import { useState } from 'react';

export default function LabelInput({ id, etiqueta, tipo, valor, onValorChange }) {
    const [estado, setEstado] = useState(valor);

    function onChange(e) {
        setEstado(e.target.value);

        onValorChange(e.target.value);
    }

    return (
        <div className="label-input">
            <label htmlFor={id}>{etiqueta}</label>
            <input id={id} type={tipo} value={estado} onChange={onChange}></input>
        </div>
    );
}

LabelInput.propTypes = {
    id: PropTypes.string,
    etiqueta: PropTypes.string,
    tipo: PropTypes.string,
    valor: PropTypes.string,
    onValorChange: PropTypes.func,
};
