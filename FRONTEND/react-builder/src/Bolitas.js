import PropTypes from 'prop-types';

export default function Bolitas({ datos }) {
  return (
    <ul>
      {datos.map(dato => <li key={dato}>{dato}</li>)}
    </ul>
  );
}

Bolitas.propTypes = {
    datos: PropTypes.arrayOf(PropTypes.string).isRequired,
};
