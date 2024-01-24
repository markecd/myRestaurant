// Miza.js
import React from 'react';
import './Miza.css';
import { Button } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';

function Miza() {
  const history = useHistory();

  const handleNarociloClick = () => {
    history.push('/narocilo');
  };

  return (
    <div className="miza-container">
      <div className='row'>
        <h3>Miza 13</h3>
        <h4>Stanje</h4>
        <div className='col-lg-6'>
          <Button
            variant="primary"
            type="button"
            className="miza-button"
            onClick={handleNarociloClick}
          >
            Naročilo
          </Button>
        </div>
        <div className='col-lg-6'>
          <Button variant="primary" type="submit" className="miza-button">
            Račun
          </Button>
        </div>
      </div>
    </div>
  );
}

export default Miza;
