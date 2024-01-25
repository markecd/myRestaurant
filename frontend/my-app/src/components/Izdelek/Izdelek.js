import './Izdelek.css';

function Izdelek({ naziv, kolicina, odstrani }) {
    return (
      <div className='izdelek col-lg-12'>
        <p style={{ fontWeight: 'bold' }}>{naziv}</p>
        <p style={{ fontWeight: 'bold' }}>Količina: {kolicina}</p>
        <button onClick={odstrani} className="nazaj-button">Odstrani</button>
      </div>
    );
  }

export default Izdelek;