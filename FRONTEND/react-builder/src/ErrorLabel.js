import './ErrorLabel.css';
import PropTypes from 'prop-types';

export default function ErrorLabel({ texto }) {
  return (
    <>
      {texto ? <span className="error-label">{texto}</span> : null}
    </>
  );
}

ErrorLabel.propTypes = {
    texto: PropTypes.string,
};