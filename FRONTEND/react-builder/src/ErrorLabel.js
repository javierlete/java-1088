import PropTypes from 'prop-types';

export default function ErrorLabel({ texto }) {
  return (
    <>
      {texto ? <span>[[[{texto}]]]</span> : null}
    </>
  );
}

ErrorLabel.propTypes = {
    texto: PropTypes.string,
};