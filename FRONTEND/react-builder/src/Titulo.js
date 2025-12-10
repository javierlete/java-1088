import PropTypes from 'prop-types';

export default function Titulo({ texto }) {
  return <h1>{texto}</h1>;
}

Titulo.propTypes = {
    texto: PropTypes.string,
}