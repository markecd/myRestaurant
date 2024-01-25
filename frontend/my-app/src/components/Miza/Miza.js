// Miza.js
import React, { useState } from 'react';
import './Miza.css';
import { Button } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';

function Miza({ data }) {
  const [stanjeMize, setStanjeMize] = useState(data[1]);
  const history = useHistory();

  const handleNarociloClick = () => {
    history.push(`/narocilo/${data[0]}`);
  };

  const handlePostregelClick = async (id) => {
    const novoStanje = stanjeMize === 'ZASEDENO_NEPOSTREZENO' ? 'ZASEDENO_POSTREZENO' : 'ZASEDENO_NEPOSTREZENO';
    setStanjeMize(novoStanje);

    const response1 = await fetch(`http://localhost:8080/api/v1/mize/posodobiStanje/${id}/${novoStanje}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    });
    const data1 = await response1.json();
    console.log(data1);

    const response2 = await fetch(`http://localhost:8080/api/v1/narocila/dobiZadnjeNarociloByMiza/${id}`);
    const idNarocilo = await response2.json();
    console.log(idNarocilo);

    const response3 = await fetch(`http://localhost:8080/api/v1/narocila/spremeniStanje/POSTREZENO/${idNarocilo}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    });
    const data3 = await response3.json();
    console.log(data3);
    
  }

  let mizaBorder = data[4] === 'PRIPRAVLJENO' ? {
    border: '2px solid green',
    boxShadow: '0px 0px 10px green'
  } : data[4] === 'V_PRIPRAVI' ? {
    border: '2px solid yellow',
    boxShadow: '0px 0px 10px yellow'
  } : {};


  let stanjeMizeClass;
  let stanjeMizeNapis;

  switch (stanjeMize) {
    case ('NEZASEDENO'):
      stanjeMizeNapis = 'NEZASEDENO';
      mizaBorder = {
        border: '1px solid #ddd'
      }
      break;
    case ('ZASEDENO_NEPOSTREZENO'):
      stanjeMizeNapis = 'NEPOSTREŽENO';
      stanjeMizeClass = 'stanje-mize-rdeca'
      break;
    case ('ZASEDENO_POSTREZENO'):
      stanjeMizeNapis = 'POSTREŽENO';
      stanjeMizeClass = 'stanje-mize-zelena';
      mizaBorder = {
        border: '1px solid #ddd'
      }
      break;
  }

  return (
    <div className="miza-container" style={mizaBorder}>
      <div className='row'>
        <h3>Miza {data[2]}</h3>
        <h5 className={stanjeMizeClass}>{stanjeMizeNapis}</h5>
        <div className='col-lg-12'>
          {stanjeMize === 'ZASEDENO_NEPOSTREZENO' ? (<div>
            <Button
              variant="primary"
              type="button"
              className="miza-button"
              onClick={handleNarociloClick}
            >
              Naročilo
            </Button>
            <Button
              variant="primary"
              type="button"
              className="miza-button"
              onClick={() => handlePostregelClick(data[0])}
            >
              Postregel
            </Button>
          </div>
          ) : (
            <Button variant="primary" type="submit" className="miza-button">
              Račun
            </Button>
          )}
        </div>
      </div>
    </div>
  );
}

export default Miza;
