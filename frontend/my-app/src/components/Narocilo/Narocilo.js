// Narocilo.js
import React from 'react';
import './Narocilo.css';
import { Button } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';

function Narocilo() {
    const history = useHistory();

  const handleNazajClick = () => {
    history.push('/home');
  };

  return (
    <div className="narocilo-container">
      <Button onClick={handleNazajClick} className="nazaj-button">
        Nazaj
      </Button>
      <div className="narocilo-content">
        <h2 className="narocilo-title">Naročilo</h2>
        <p>Ime izdelka: XYZ</p>
        <p>Količina: 3</p>
      </div>
    </div>
  );
}

export default Narocilo;
